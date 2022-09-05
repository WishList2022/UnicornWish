package org.techtown.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import Api.ApiProvider;
import Api.ServerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arrayList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mainAdapter = new MainAdapter(arrayList);

        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(arrayList);

        recyclerView.setAdapter(mainAdapter);

        fetchFeed();


        // 전체체크 삭제
//        Button All_Check = (Button) findViewById(R.id.All_Check);
//        All_Check.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int cnt = mainAdapter.getItemCount();
//
//                for(int i = 0; i<cnt; i++){
//
//                }
//            }
//        });

        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveSee();
            }
        });
    }

    void moveSee() {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }

    protected void fetchFeed() {
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);

        serverApi.wishInquiry("Bearer " + LoginActivity.accessToken).enqueue(new Callback<FetchFeedResponse>() {
            @Override
            public void onResponse(Call<FetchFeedResponse> call, Response<FetchFeedResponse> response) {


                FetchFeedResponse resp = response.body();
                arrayList.clear();


                for (int i = 0; i < resp.getFeed_list().size(); i++) {
                    arrayList.add((resp.getFeed_list().get(i)));
                }
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FetchFeedResponse> call, Throwable t) {

            }
        });
    }

        @Override
        protected void onResume () {
            super.onResume();

            fetchFeed();
        }
}


