package com.example.forceconnect.instructor.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.forceconnect.R;
import com.example.forceconnect.databinding.ActivityInstructorLoginBinding;
import com.example.forceconnect.databinding.ActivityInstructorRegisterBinding;
import com.example.forceconnect.instructor.viewmodel.MyViewModel;
import com.google.firebase.auth.FirebaseUser;

public class InstructorLoginActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private ActivityInstructorLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_login);

        binding  = DataBindingUtil.setContentView(this,R.layout.activity_instructor_login);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.idInsLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.signInInstructor(binding.idInsLoginMail.getText().toString().trim(),binding.idInsLoginPass.getText().toString().trim());
            }
        });
        binding.idNewUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InstructorLoginActivity.this,InstructorRegisterActivity.class));
            }
        });
    }

}