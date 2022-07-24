package org.techtown.Activity;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import org.techtown.Activity.databinding.ActivityRegisterBinding;

import Api.ApiProvider;
import Api.ServerApi;
import Register.RegisterRequest;
import Register.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singup();
            }
        });
    }
    
    
    private void Singup(){
        String account_id = binding.etRegisterTypeID.getText().toString();
        String password = binding.etRegisterTypePW.getText().toString();
        String username = binding.etRegisterTypeName.getText().toString();
        
        if (username.length() == 0 || account_id.length() == 0 || password.length() == 0){
            Toast.makeText(RegisterActivity.this, "모든 항목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }else{
            RegisterResponse();
        }
    }


    public void RegisterResponse(){
        String account_id = binding.etRegisterTypeID.getText().toString();
        String password = binding.etRegisterTypePW.getText().toString();
        String username = binding.etRegisterTypeName.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest(account_id, password, username);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);

        serverApi.Singnup(registerRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG,"onResponse: "+response.code());
                if (response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "🎉회원가입에 성공했습니다!🎉", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, "오류가 났습니다...", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "통신 실패 하...", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"onFailure: "+t);
            }
        });
    }
}

