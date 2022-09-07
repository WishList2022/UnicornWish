package org.techtown.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

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

        Button removeSelected = (Button) findViewById(R.id.btn_remove_selected);
        removeSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                mainAdapter.startRemoveList();
            }
        });


        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: add ");
                moveSee();
            }
        });
    }


    private void Allcheck() {
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.WishAll("Bearer " + LoginActivity.accessToken).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                arrayList.clear();
                mainAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Wish가 모두 삭제되었습니다!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "이정호 이Zi발~~", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        fetchFeed();
    }
}


