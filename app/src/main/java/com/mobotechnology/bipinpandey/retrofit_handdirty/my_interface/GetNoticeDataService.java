package com.mobotechnology.bipinpandey.retrofit_handdirty.my_interface;

import com.mobotechnology.bipinpandey.retrofit_handdirty.model.NoticeList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetNoticeDataService {

    @GET("bins/1bsqcn/")
    Call<NoticeList> getNoticeData();

}