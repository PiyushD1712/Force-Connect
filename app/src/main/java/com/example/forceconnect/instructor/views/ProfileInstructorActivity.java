package com.example.forceconnect.instructor.views;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.forceconnect.R;
import com.example.forceconnect.databinding.ActivityProfileInstructorBinding;
import com.example.forceconnect.instructor.models.InstructorUser;
import com.example.forceconnect.instructor.viewmodel.MyViewModel;

public class ProfileInstructorActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private ActivityProfileInstructorBinding binding;
    private EditText edName,edLocation,edYOE,edPosition;
    private ImageView imageView,addPhotoBtn;

    private ActivityResultLauncher<String> mTakePhoto;
    private Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_instructor);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile_instructor);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        edName = binding.idInsNameProfile;
        edLocation = binding.idInsLocProfile;
        edPosition = binding.idInsPosProfile;
        edYOE = binding.idInsYOEProfile;
        imageView = binding.idImagePreview;
        addPhotoBtn = binding.idAddPhoto;

        binding.idButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTakePhoto.launch("image/*");
            }
        });
        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri o) {
                imageView.setImageURI(o);
                uri = o;
            }
        });
    }

    private void saveData() {
        String name = edName.getText().toString().trim();
        String location = edLocation.getText().toString().trim();
        String yoe = edYOE.getText().toString().trim();
        String position = edPosition.getText().toString().trim();


        InstructorUser user = new InstructorUser(name,position,yoe,location,uri.toString());
        myViewModel.writeUser(user);
        finish();
    }
}