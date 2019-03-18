package com.bawei.dzc.mvp.persenter;

import com.bawei.dzc.mvp.model.MainModel;
import com.bawei.dzc.mvp.view.MainView;

public class MainPersenterIml implements MainPersenter,MainModel.CallBackListener{

    private MainModel mainModel;
    private MainView mainView;
    public MainPersenterIml(MainModel mainModel, MainView mainView){
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void success(String data) {

    }

    @Override
    public void fail() {

    }

    @Override
    public void doData() {

    }
}
