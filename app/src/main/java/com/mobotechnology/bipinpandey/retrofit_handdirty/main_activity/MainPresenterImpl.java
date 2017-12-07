package com.mobotechnology.bipinpandey.retrofit_handdirty.main_activity;

import com.mobotechnology.bipinpandey.retrofit_handdirty.model.Notice;

import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNoticeInteractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeInteractor getNoticeInteractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeInteractor getNoticeInteractor) {
        this.mainView = mainView;
        this.getNoticeInteractor = getNoticeInteractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        if(mainView != null){
            mainView.showProgress();
        }
        getNoticeInteractor.getNoticeArrayList(this);

    }

    @Override
    public void requestDataFromServer() {
        getNoticeInteractor.getNoticeArrayList(this);
    }

    @Override
    public void onFinished(ArrayList<Notice> noticeArrayList) {
        if(mainView != null){
            mainView.setDataList(noticeArrayList);
            mainView.hideProgress();
        }
    }
}
