package org.techtown.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Api.ApiProvider;
import Api.ServerApi;
import Response.GetResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private ArrayList<GetResponse> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(MainActivity.this, arrayList);

        recyclerView.setAdapter(mainAdapter);

        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.wishInquiry("Bearer" + LoginActivity.accessToken).enqueue(new Callback<ArrayList<GetResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GetResponse>> call, Response<ArrayList<GetResponse>> response) {
                if(response.isSuccessful()){
                    arrayList.addAll(response.body());
                    mainAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetResponse>> call, Throwable t) {

            }
        });

        Button btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveSee();
            }
        });
    }

    void Edit(){

    }

    void moveSee() {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }


}
