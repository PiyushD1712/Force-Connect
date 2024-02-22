package com.example.forceconnect.instructor.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.forceconnect.instructor.models.InstructorUser;
import com.example.forceconnect.instructor.repository.InstructorRepo;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private InstructorRepo repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new InstructorRepo(application);
    }

    public void signOUT(){
        repository.signOUT();
    }

    public void registerInstructor(String email,String pass){
        repository.createUserInstructor(email, pass);
    }

    public void signInInstructor(String email,String pass){
        repository.signInInstructor(email, pass);
    }

    public void writeUser(InstructorUser instructorUser){
        repository.writeUserData(instructorUser);
    }
    public MutableLiveData<List<InstructorUser>> getUserData(){
        return repository.getMutableLiveData();
    }
}
