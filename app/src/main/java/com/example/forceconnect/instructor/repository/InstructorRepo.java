package com.example.forceconnect.instructor.repository;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.forceconnect.MainActivity;
import com.example.forceconnect.R;
import com.example.forceconnect.instructor.models.InstructorUser;
import com.example.forceconnect.instructor.views.InstructorHomeActivity;
import com.example.forceconnect.instructor.views.ProfileInstructorActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InstructorRepo {
    private FirebaseFirestore database;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private Context context;
    private StorageReference storage;
    private List<InstructorUser> list;
    private MutableLiveData<List<InstructorUser>> mutableLiveData;

    public InstructorRepo(Context context){
        this.context = context;
        database = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance().getReference();
        list = new ArrayList<>();
        mutableLiveData = new MutableLiveData<>();
    }
    public InstructorRepo(){}

    //  Authentication of the Instructor is done here.
    public void createUserInstructor(String email, String pass){
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            firebaseAuth.createUserWithEmailAndPassword(email,pass)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            user = firebaseAuth.getCurrentUser();
                            Toast.makeText(context, "User Created...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, ProfileInstructorActivity.class);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent,options.toBundle());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            Toast.makeText(context, "Fields should not be empty!", Toast.LENGTH_SHORT).show();
        }
    }
    public void signOUT(){
        firebaseAuth.signOut();
        Toast.makeText(context, "Logged Out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainActivity.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent,options.toBundle());
    }
    public void signInInstructor(String email,String pass){
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show();
                    user = firebaseAuth.getCurrentUser();
                    Intent intent = new Intent(context, InstructorHomeActivity.class);
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent,options.toBundle());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(context,"Fields Empty",Toast.LENGTH_SHORT).show();
        }
    }

    // Database entry of the user:
    public void writeUserData(InstructorUser instructorUser){
        if(instructorUser!=null){
            Uri myUri = Uri.parse(instructorUser.getImgUrl());
            StorageReference filePath =storage.child("user_images")
                    .child("Instructors")
                    .child("image_"+ Timestamp.now().getSeconds());

            filePath.putFile(myUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imgUrl = uri.toString();
                            database.collection("Instructor")
                                    .document(firebaseAuth.getCurrentUser().getUid())
                                    .set(instructorUser)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(context, InstructorHomeActivity.class);
                                            ActivityOptions options = ActivityOptions.makeCustomAnimation(context, android.R.anim.fade_in, android.R.anim.fade_out);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            context.startActivity(intent,options.toBundle());
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    public MutableLiveData<List<InstructorUser>> getMutableLiveData() {
        CollectionReference reference = database.collection("Instructor");
        reference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<InstructorUser> user1 = queryDocumentSnapshots.toObjects(InstructorUser.class);

                mutableLiveData.postValue(user1);
            }
        });
        return mutableLiveData;
    }
}
