package com.example.forceconnect.instructor.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.forceconnect.R;
import com.example.forceconnect.instructor.adapters.RowAdapterInstructor;
import com.example.forceconnect.instructor.models.InstructorUser;
import com.example.forceconnect.instructor.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewInstructor,recyclerViewLearners;
    private View view;
    private MyViewModel myViewModel;
    private ArrayList<InstructorUser> list;
    private RowAdapterInstructor adapterInstructor;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        list = new ArrayList<>();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewInstructor = view.findViewById(R.id.idRecyclerViewInstructorHF);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        recyclerViewInstructor.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        myViewModel.getUserData().observe(getViewLifecycleOwner(), new Observer<List<InstructorUser>>() {

            @Override
            public void onChanged(List<InstructorUser> instructorUsers) {
                list.clear();
                list.addAll(instructorUsers);
                System.out.println(list);
                adapterInstructor = new RowAdapterInstructor(getContext(),list);
                recyclerViewInstructor.setAdapter(adapterInstructor);
                adapterInstructor.notifyDataSetChanged();
            }
        });

    }
}