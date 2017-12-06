package com.mobotechnology.bipinpandey.retrofit_handdirty;

import android.view.View;

/**
 * Created by bpn on 12/6/17.
 */

public class MainActivityPresenter {

    private View view;

    public MainActivityPresenter(View view) {
        this.view = view;
    }

    public interface view{

        void showProgressbar();
        void hideProgressbar();

    }

}
