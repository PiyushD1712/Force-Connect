package com.example.forceconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.forceconnect.instructor.views.InstructorHomeActivity;
import com.example.forceconnect.instructor.views.InstructorLoginActivity;
import com.example.forceconnect.instructor.views.InstructorRegisterActivity;
import com.example.forceconnect.learner.views.LearnerRegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btnInstructor,btnLearner;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInstructor = findViewById(R.id.idInstructorBtn);
        btnLearner = findViewById(R.id.idLearnerBtn);
        firebaseAuth = FirebaseAuth.getInstance();

        btnInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InstructorLoginActivity.class));
            }
        });
        btnLearner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LearnerRegistrationActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this, InstructorHomeActivity.class));
        }
        super.onStart();
    }
}