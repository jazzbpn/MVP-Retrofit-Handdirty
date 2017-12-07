package com.mobotechnology.bipinpandey.retrofit_handdirty.main_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.mobotechnology.bipinpandey.retrofit_handdirty.R;
import com.mobotechnology.bipinpandey.retrofit_handdirty.adapter.NoticeAdapter;
import com.mobotechnology.bipinpandey.retrofit_handdirty.model.Notice;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    private NoticeAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initProgressBar();


        presenter = new MainPresenterImpl(this , new GetNoticeIntractorImpl());
        presenter.requestDataFromServer();


//        /** Create handle for the RetrofitInstance interface*/
//        GetNoticeDataService service = RetrofitInstance.getRetrofitInstance().create(GetNoticeDataService.class);
//
//        /** Call the method with parameter in the interface to get the notice data*/
//        Call<NoticeList> call = service.getNoticeData();
//
//        /**Log the URL called*/
//        Log.wtf("URL Called", call.request().url() + "");
//
//        call.enqueue(new Callback<NoticeList>() {
//            @Override
//            public void onResponse(Call<NoticeList> call, Response<NoticeList> response) {
//                generateNoticeList(response.body().getNoticeArrayList());
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(Call<NoticeList> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//        });
    }

    /**
     * Method to generate List of notice using RecyclerView with custom adapter
     */
    private void generateNoticeList(ArrayList<Notice> noticeDataList) {
        recyclerView = findViewById(R.id.recycler_view_employee_list);

        adapter = new NoticeAdapter(noticeDataList);

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
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataList(ArrayList<Notice> noticeArrayList) {
        generateNoticeList(noticeArrayList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            presenter.onRefreshButtonClick();
        }

        return super.onOptionsItemSelected(item);
    }
}
