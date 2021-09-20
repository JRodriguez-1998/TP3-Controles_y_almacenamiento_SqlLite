package com.example.tp3_controlesyalmacenamientosqllite.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp3_controlesyalmacenamientosqllite.AdminSQLiteOpenHelper;
import com.example.tp3_controlesyalmacenamientosqllite.Parqueo;
import com.example.tp3_controlesyalmacenamientosqllite.R;
import com.example.tp3_controlesyalmacenamientosqllite.databinding.FragmentHomeBinding;
import com.example.tp3_controlesyalmacenamientosqllite.dialogo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private GridView grid;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AdminSQLiteOpenHelper admSql = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(), "ParkingControl",null, 1);
        SQLiteDatabase bd = admSql.getReadableDatabase();

        Integer idUsuario = getActivity().getIntent().getIntExtra("idUsuario", 0);
        ArrayList<Parqueo> listaparq = new ArrayList<>();
        Cursor cursor = bd.rawQuery("select matricula,minutos from parqueos where idUsuario =" + idUsuario,null);
        if(cursor.moveToFirst()){
            do {
                Parqueo p = new Parqueo();
                p.setMatricula(cursor.getString(0));
                p.setMinutos(cursor.getInt(1));
                listaparq.add(p);

            }
            while (cursor.moveToNext());
        }

        GridView grid = (GridView) root.findViewById(R.id.grid_view);
        grid.setAdapter(new ArrayAdapter<Parqueo>(getActivity(), android.R.layout.simple_list_item_1,listaparq));



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton btnAgregar = view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo d = new dialogo();
                d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo");
                Navigation.findNavController(view).navigate(R.id.dialogo);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}