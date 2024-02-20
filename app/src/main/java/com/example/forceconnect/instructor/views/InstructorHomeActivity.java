package com.example.forceconnect.instructor.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.forceconnect.R;
import com.example.forceconnect.databinding.ActivityInstructorHomeBinding;
import com.example.forceconnect.instructor.models.InstructorUser;
import com.example.forceconnect.instructor.viewmodel.MyViewModel;
import com.example.forceconnect.instructor.views.fragments.BlogFragment;
import com.example.forceconnect.instructor.views.fragments.HomeFragment;
import com.example.forceconnect.instructor.views.fragments.NewsFragment;
import com.example.forceconnect.instructor.views.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InstructorHomeActivity extends AppCompatActivity {

    //Widgets
    private Button btnLogout,btnUser;


    private MyViewModel myViewModel;
    private ActivityInstructorHomeBinding binding;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_home);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_instructor_home);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.idBottomNavigationHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.idHomeBNM){
                    replaceFragments(new HomeFragment());
                } else if(itemId == R.id.idNewsBNM){
                    replaceFragments(new NewsFragment());
                } else if(itemId == R.id.idBlogBNM){
                    replaceFragments(new BlogFragment());
                } else if (itemId == R.id.idProfileBNM) {
                    replaceFragments(new ProfileFragment());
                }
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        super.onStart();
    }
    private void replaceFragments(Fragment fragment){
        FragmentManager frm = getSupportFragmentManager();
        FragmentTransaction frt = frm.beginTransaction();
        frt.replace(R.id.idFrameLayout,fragment);
        frt.commit();
    }
}