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
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList, this);
        recyclerView.setAdapter(mainAdapter);


        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.wishInquiry("Bearer " + LoginActivity.accessToken).enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                arrayList.add(response.body());
                mainAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
    }

    void moveSee() {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }


}
