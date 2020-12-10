package com.sun.navdemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> liveData;

    public MutableLiveData<String> getLiveData(){
        if (liveData == null) {
            liveData = new MutableLiveData<String>();
            liveData.setValue("");
        }
        return liveData;
    }


}
