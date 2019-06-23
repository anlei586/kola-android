package com.imkola.client.mvp;


public interface BasePresenter<V extends BaseView>{
    void attachView(V view);

    void detachView();
}
