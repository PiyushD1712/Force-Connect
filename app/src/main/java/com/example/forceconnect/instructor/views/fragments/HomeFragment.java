package com.example.forceconnect.instructor.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forceconnect.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewInstructor,recyclerViewLearners;
    private View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewInstructor = view.findViewById(R.id.idRecyclerViewInstructorHF);
        recyclerViewInstructor.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));

    }
}