package com.mobotechnology.bipinpandey.retrofit_handdirty.my_interface;

import com.mobotechnology.bipinpandey.retrofit_handdirty.model.NoticeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetNoticeDataService {

    @GET("bins/1bsqcn/")
    Call<NoticeList> getNoticeData();

    /**
     * @since Not used, Just to know how to use @query to get JSONObject
     * Get data using query
     * */
    @GET("bins/path/")
    Call<NoticeList> getNoticeDataData(@Query("company_no") int companyNo);

}