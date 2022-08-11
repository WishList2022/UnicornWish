package org.techtown.Activity;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.Activity.databinding.ActivityItemEditBinding;

public class EditActivity extends AppCompatActivity {

    private ActivityItemEditBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        //binding
=======
        binding = ActivityItemEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


>>>>>>> main
    }
}
