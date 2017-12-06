package com.mobotechnology.bipinpandey.retrofit_handdirty.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobotechnology.bipinpandey.retrofit_handdirty.MainActivityPresenter;
import com.mobotechnology.bipinpandey.retrofit_handdirty.R;
import com.mobotechnology.bipinpandey.retrofit_handdirty.adapter.NoticeAdapter;
import com.mobotechnology.bipinpandey.retrofit_handdirty.model.Notice;
import com.mobotechnology.bipinpandey.retrofit_handdirty.model.NoticeList;
import com.mobotechnology.bipinpandey.retrofit_handdirty.my_interface.GetNoticeDataService;
import com.mobotechnology.bipinpandey.retrofit_handdirty.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.view {

    private NoticeAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initProgressBar();


        /** Create handle for the RetrofitInstance interface*/
        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<NoticeList> call = service.getNoticeData();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");
        showProgressbar();

        call.enqueue(new Callback<NoticeList>() {
            @Override
            public void onResponse(Call<NoticeList> call, Response<NoticeList> response) {
                generateNoticeList(response.body().getNoticeArrayList());
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<NoticeList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressbar();
            }
        });
    }

    /**
     * Method to generate List of notice using RecyclerView with custom adapter
     */
    private void generateNoticeList(ArrayList<Notice> empDataList) {
        recyclerView = findViewById(R.id.recycler_view_employee_list);

        adapter = new NoticeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        this.addContentView(relativeLayout, params);
    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
