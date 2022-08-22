package org.techtown.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.Activity.databinding.ActivityItemEditBinding;

import Request.EditRequest;

public class EditActivity extends AppCompatActivity {

    private ActivityItemEditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.editBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editcheck();
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


    public void EditPost(String title, String contnet){



    }


}
