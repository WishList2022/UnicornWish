package org.techtown.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.techtown.Activity.databinding.ActivityItemInfoBinding;
import Api.ApiProvider;
import Api.ServerApi;
import Request.WishPostRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoActivity extends AppCompatActivity {

    private static final String TAG = "ContentValues";
    private ActivityItemInfoBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.infoBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                POSTcheck();
            }
        });

    }

    private void POSTcheck(){
        String title = binding.infoEtvTitle.getText().toString();
        String content = binding.infoEtvContent.getText().toString();

        if (title.length() == 0){
            Toast.makeText(InfoActivity.this, "Wish 제목을 입력해주세요", Toast.LENGTH_SHORT).show();
        }
        else if (content.length() == 0){
            Toast.makeText(InfoActivity.this, "Wish 내용을 입력주세요", Toast.LENGTH_SHORT).show();
        }
        else{
            Post();
        }

    }


    private  void Post(){
        String title =  binding.infoEtvTitle.getText().toString();
        String content = binding.infoEtvContent.getText().toString();
        WishPostRequest postRequest = new WishPostRequest(title,content);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.WishPost("Bearer" + LoginActivity.accessToken, postRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){

                    Toast.makeText(InfoActivity.this, "Wish가 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    binding.infoEtvTitle.setText("");
                    binding.infoEtvContent.setText("");

                    binding.infoEtvTitle.requestFocus();
                }
                Toast.makeText(getApplicationContext(),"" + response.code(),Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "Wish 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
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
