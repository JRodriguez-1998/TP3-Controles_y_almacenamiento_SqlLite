package com.example.tp3_controlesyalmacenamientosqllite.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("MI CUENTA");
    }

    public LiveData<String> getText() {
        return mText;
    }
}