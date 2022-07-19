package org.techtown.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.Activity.databinding.ActivityLoginBinding;
import org.techtown.Activity.databinding.ActivityMainBinding;

import Api.ApiProvider;
import Api.ServerApi;
import Login.LoginRequest;
import Login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singin();
            }
        });
    }

    private void Singin(){
        String account_id = binding.etLoginTypeID.getText().toString();
        String password = binding.etLoginTypePW.getText().toString();

        if (account_id.length() == 0){
            Toast.makeText(LoginActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
        }else if (password.length() == 0){
            Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        }else{
            LoginResponse();
        }
    }

    public void LoginResponse(){
        String account_id = binding.etLoginTypeID.getText().toString();
        String password = binding.etLoginTypePW.getText().toString();

        LoginRequest loginRequest = new LoginRequest(account_id, password);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "🎉로그인에 성공했습니다!🎉", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인에 실패했습니다..", Toast.LENGTH_SHORT).show();
            }
        });




    }





}
