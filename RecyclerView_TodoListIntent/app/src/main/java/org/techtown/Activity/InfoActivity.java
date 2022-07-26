package org.techtown.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.Activity.databinding.ActivityItemInfoBinding;

import Api.ApiProvider;
import Api.ServerApi;
import Register.RegisterRequest;
import WishPostRequest.PostRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {

    private ActivityItemInfoBinding binding;
    private String color = "WHITE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.infoBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnPost();
            }
        });

        binding.btnNor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "WHITE";
            }
        });

        binding.btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "RED";
            }
        });

        binding.btnBlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "BLUE";
            }
        });
        binding.btnGre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "GREEN";
            }
        });
        binding.btnYel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "YELLOW";
            }
        });


    }

    private  void BtnPost(){
        String title = binding.infoEtvTitle.getText().toString();
        String content = binding.infoEtvContent.getText().toString();

        PostRequest postRequest = new PostRequest(title, content, color);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);

        serverApi.WishPost(postRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG,"onResponse: "+response.code());
                if (response.isSuccessful()){
                    Toast.makeText(InfoActivity.this, "🎉Wish가 등록되었습니다!🎉", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                }else{
                    Toast.makeText(InfoActivity.this, "오류가 났습니다...", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "통신 실패 하...", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onFailure: "+t);
            }
        });
    }
}
