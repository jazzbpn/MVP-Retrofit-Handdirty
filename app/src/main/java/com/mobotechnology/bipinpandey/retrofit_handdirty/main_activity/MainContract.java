package com.mobotechnology.bipinpandey.retrofit_handdirty.main_activity;

import com.mobotechnology.bipinpandey.retrofit_handdirty.model.Notice;

import java.util.ArrayList;

/**
 * Created by bpn on 12/6/17.
 */

public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the next random quote is fetched from the GetQuoteInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataList(ArrayList<Notice> noticeArrayList);

    }

    /**
     * Interactors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeInteractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Notice> noticeArrayList);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
}
