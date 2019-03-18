package com.bawei.dzc.mvp.model;

public interface MainModel {

    interface CallBackListener{
        void success(String data);
        void fail();
    }

    void getDAta(CallBackListener listener);
}
