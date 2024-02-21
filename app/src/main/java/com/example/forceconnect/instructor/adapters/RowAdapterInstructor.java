package com.example.forceconnect.instructor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.forceconnect.R;
import com.example.forceconnect.databinding.CardUserLayoutBinding;
import com.example.forceconnect.instructor.models.InstructorUser;

import java.util.ArrayList;

public class RowAdapterInstructor extends RecyclerView.Adapter<RowAdapterInstructor.InstructorViewHolder> {
    private Context context;
    private ArrayList<InstructorUser> userList;

    public RowAdapterInstructor(Context context, ArrayList<InstructorUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardUserLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.card_user_layout,
                parent,
                false
        );
        return new InstructorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        InstructorUser user = userList.get(position);
        holder.binding.setInstr(user);
        Glide.with(context).load(user.getImgUrl()).into(holder.binding.idImageICUL);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class InstructorViewHolder extends RecyclerView.ViewHolder{
        CardUserLayoutBinding binding;
        public InstructorViewHolder(CardUserLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
