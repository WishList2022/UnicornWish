package org.techtown.Activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.Activity.databinding.ActivityItemEditBinding;

import Api.ApiProvider;
import Api.ServerApi;
import Request.EditRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    private ActivityItemEditBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Intent intent  = getIntent();
        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        String content = extras.getString("content");


        binding.editEtvTitle.setText(title);
        binding.editEtvContent.setText(content);



        binding.editBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editcheck();
            }
        });

        binding.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        binding.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("삭제");
                builder.setMessage("정말 삭제하시겠습니까?");

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        binding.editEtvTitle.setText("");
                        binding.editEtvContent.setText("");

                        binding.editEtvTitle.requestFocus();

                        Toast.makeText(EditActivity.this, "삭제가 완료 되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }







    public void Editcheck(){
        String title = binding.editEtvTitle.getText().toString();
        String contnet = binding.editEtvTitle.getText().toString();

        if (title.length() == 0 || contnet.length() == 0){
            Toast.makeText(EditActivity.this, "내용을 수정해주세요", Toast.LENGTH_SHORT).show();
        }
        else{
            EditPost(title, contnet);
        }
    }


    public void EditPost(String title, String content){

        EditRequest editRequest = new EditRequest(title, content);
        ServerApi serverApi = ApiProvider.getRetrofit().create(ServerApi.class);
        serverApi.WishEdit("Bearer "+ LoginActivity.accessToken, editRequest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(EditActivity.this, "Wish가 수정되었습니다", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(EditActivity.this, "저도 모르겠네요..", Toast.LENGTH_SHORT).show();
            }
        });



    }



}
