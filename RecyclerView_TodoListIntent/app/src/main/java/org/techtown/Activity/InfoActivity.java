package org.techtown.Activity;

import static android.content.ContentValues.TAG;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.session.MediaSession;
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
import WishPost.PostRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.nio.charset.StandardCharsets;

import Api.ApiProvider;
import Api.ServerApi;
import Login.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

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
                color = "하얀색";
                BtnPost();
            }
        });

        binding.btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "빨간색";
            }
        });

        binding.btnBlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "파란색";
            }
        });

        binding.btnGre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "초록색";
            }
        });

        binding.btnYel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = "노란색";
            }
        });


    }

    private  void BtnPost(){
        String title = binding.infoEtvTitle.getText().toString();
        String content = binding.infoEtvContent.getText().toString();

        PostRequest postRequest = new PostRequest(title, content, color);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        Class<LoginActivity> accessToken = LoginActivity.class;
        serverApi.WishPost(accessToken, postRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    binding.infoEtvTitle.setText("");
                    binding.infoEtvContent.setText("");
                    binding.btnNor.setText("");
                    Toast.makeText(InfoActivity.this, "Wish가 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "관리자에게 문의 해주세요..", Toast.LENGTH_SHORT).show();
            }
        });

    binding.infoBtnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    });
    }
}
