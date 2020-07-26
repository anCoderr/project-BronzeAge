package com.example.favlistapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    String[] categories = {"Hobbies", "Sports", "Games", "Gadgets", "Food", "Countries"};

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_viewholder, parent, false);
        return new CategoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.getTxtCategoryNumber().setText(Integer.toString(position + 1));
        holder.getTxtCategoryName().setText(categories[position]);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }
}
