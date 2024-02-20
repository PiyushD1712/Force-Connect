package com.example.forceconnect.instructor.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.forceconnect.R;
import com.example.forceconnect.databinding.ActivityInstructorRegisterBinding;
import com.example.forceconnect.instructor.viewmodel.MyViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InstructorRegisterActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private ActivityInstructorRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_register);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_instructor_register);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.idInsRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= binding.idInsRegMail.getText().toString().trim();
                String pass= binding.idInsRegPass.getText().toString().trim();
                myViewModel.registerInstructor(mail,pass);
            }
        });

    }
}