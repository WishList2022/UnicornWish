package org.techtown.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.Activity.databinding.ActivityItemInfoBinding;

import java.nio.charset.StandardCharsets;

import Api.ApiProvider;
import Api.ServerApi;
import Login.LoginRequest;
import WishPost.PostRequest;
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
        public void onClick(View view) {
            Btn();
            }
        });

    binding.infoBtnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    });

    binding.btnNor.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            color = "WHITE";

        }
    });

    binding.btnBlu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            color = "BLUE";
        }
    });

    binding.btnGre.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            color = "GREEN";
        }
    });


    binding.btnRed.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            color = "RED";
        }
    });

    binding.btnYel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            color = "YELLOW";
        }
    });

    }


    private void Btn(){
        String tilte = binding.infoEtvTitle.getText().toString();
        String content = binding.infoEtvContent.getText().toString();

        if (tilte.length() == 0 || content.length() == 0){
            Toast.makeText(InfoActivity.this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }else
        {
            BtnPost(tilte, content, color);
        }
    }

    public void BtnPost(String tilte, String content, String color){
    }

}
