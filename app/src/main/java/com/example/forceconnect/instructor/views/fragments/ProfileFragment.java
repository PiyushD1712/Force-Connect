package com.example.forceconnect.instructor.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.forceconnect.R;
import com.example.forceconnect.databinding.FragmentProfileBinding;
import com.example.forceconnect.instructor.models.InstructorUser;
import com.example.forceconnect.instructor.viewmodel.MyViewModel;
import com.example.forceconnect.instructor.views.InstructorLoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private MyViewModel myViewModel;
    private ImageView imageView;
    private Button btnSave,btnLogOut,btnDelete;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        binding = DataBindingUtil.bind(view);

        btnDelete = binding.idDeleteProfile;
        btnLogOut = binding.idLogoutProfile;

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.signOUT();
                startActivity(new Intent(getActivity(), InstructorLoginActivity.class));
                getActivity().finishAffinity();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.deleteUser(FirebaseAuth.getInstance().getCurrentUser());
            }
        });
        
    }
}