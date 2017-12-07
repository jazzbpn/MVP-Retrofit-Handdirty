package com.mobotechnology.bipinpandey.retrofit_handdirty.main_activity;

import android.os.Handler;

import com.mobotechnology.bipinpandey.retrofit_handdirty.model.Notice;

import java.util.ArrayList;

/**
 * Created by bpn on 12/7/17.
 */

public class GetNoticeIntractorImpl implements MainContract.GetNoticeInteractor{

    private ArrayList<Notice> noticeArrayList = new ArrayList<>();


    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onFinishedListener.onFinished(setNoticeArrayList());
            }
        }, 1200);

    }

    private ArrayList<Notice> setNoticeArrayList(){

        noticeArrayList.clear();
        noticeArrayList.add(new Notice("1" , "Some title 1" , "Try to learn MVP-HandDirty 1" , "http://google.com"));
        noticeArrayList.add(new Notice("2" , "Some title 2" , "Try to learn MVP-HandDirty 1" , "http://google.com"));
        noticeArrayList.add(new Notice("3" , "Some title 3" , "Try to learn MVP-HandDirty 1" , "http://google.com"));
        noticeArrayList.add(new Notice("4" , "Some title 4" , "Try to learn MVP-HandDirty 1" , "http://google.com"));
        noticeArrayList.add(new Notice("5" , "Some title 5" , "Try to learn MVP-HandDirty 1" , "http://google.com"));

        return noticeArrayList;
    }
}
