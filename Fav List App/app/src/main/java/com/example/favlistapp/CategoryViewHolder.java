package com.example.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtCategoryNumber;
    private TextView txtCategoryName;

    public CategoryViewHolder(View view) {
        super(view);
        txtCategoryNumber = view.findViewById(R.id.categoryNumberTextView);
        txtCategoryName = view.findViewById(R.id.categoryNameTextView);
    }

    public TextView getTxtCategoryNumber() {
        return txtCategoryNumber;
    }

    public TextView getTxtCategoryName() {
        return txtCategoryName;
    }

}
