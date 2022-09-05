package org.techtown.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.techtown.Activity.databinding.ActivityLoginBinding;

import Api.ApiProvider;
import Api.ServerApi;
import Login.LoginRequest;
import Login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "ContentValues";
    private ActivityLoginBinding binding;
    public static String accessToken;

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        preferences = getSharedPreferences("User",MODE_PRIVATE);
        editor = preferences.edit();



        // 자동 로그인
        if (preferences.getBoolean("Check", false) == true){
            AutoLogin();
            binding.Autologgin.setChecked(true);
            binding.etLoginTypeID.setText(preferences.getString("id", ""));
            binding.etLoginTypePW.setText(preferences.getString("pw", ""));

        }


        // 회원가입 Activity로 이동
        binding.tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Login 클릭 리스너
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singincheck();
            }
        });
    }


    private void Singincheck() {
        String account_id = binding.etLoginTypeID.getText().toString();
        String password = binding.etLoginTypePW.getText().toString();

        if (account_id.length() == 0) {
            Toast.makeText(LoginActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else if (password.length() == 0) {
            Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
        } else {
            Login(account_id, password);
            Log.d(TAG, "account_id: " + account_id);
            Log.d(TAG, "password: " + password);
        }
    }


    public void Login(String account_id, String password) {

        Log.d(TAG, "LoginResponse: 실행됨");
        LoginRequest loginRequest = new LoginRequest(account_id, password);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
             if (response.isSuccessful()){

                 accessToken = response.body().getAccessToken();
                 Toast.makeText(LoginActivity.this, "🎉로그인에 성공했습니다!🎉", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(intent);
                 Log.d(TAG,"onResponse: "+ response.code());

                 if(binding.Autologgin.isChecked()){
                     editor.putBoolean("Check", true).apply();
                     editor.putString("id", account_id).apply();
                     editor.putString("pw", password).apply();
                 }
             }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "통신 실패..", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t);

            }
        });
    }

    private void AutoLogin(){
        String account_id = preferences.getString("id","");
        String password = preferences.getString("pw", "");
        LoginRequest loginRequest = new LoginRequest(account_id, password);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                accessToken = response.body().getAccessToken();

                if (binding.Autologgin.isChecked()){
                    editor.putBoolean("check",true).commit();
                    editor.putString("id", account_id).commit();
                    editor.putString("pw", password).commit();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "🎉로그인에 성공했습니다!🎉", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }
}


