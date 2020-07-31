package com.example.favlistapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTextView ;

    public ItemsViewHolder(@NonNull View itemView) {

        super(itemView);

        itemTextView = itemView.findViewById(R.id.itemNameTextView);

    }


}
