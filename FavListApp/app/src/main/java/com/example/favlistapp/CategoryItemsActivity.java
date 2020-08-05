package com.example.favlistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private FloatingActionButton addItemFab;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_ITEMS_KEY);
        setTitle(category.getName());

        itemsRecyclerView = findViewById(R.id.ItemsInListRecyclerView);
        itemsRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addItemFab = findViewById(R.id.addItemsToListFab);

        addItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            displayItemCreationDialog();

            }
        });

        setTitle("Fav List >> " + category.getName());

    }

    private void displayItemCreationDialog() {

        final EditText itemsEditText = new EditText(this);
        itemsEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle(R.string.items_dialog_title)
                .setView(itemsEditText)
                .setPositiveButton(R.string.positive_button_item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String itemName = itemsEditText.getText().toString();
                        category.getItems().add(itemName);
                        ItemsRecyclerAdapter itemsRecyclerAdapter = (ItemsRecyclerAdapter) itemsRecyclerView.getAdapter();
                        itemsRecyclerAdapter.notifyItemChanged(category.getItems().size() - 1);
                        dialogInterface.dismiss();

                    }
                })
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.CATEGORY_ITEMS_KEY, category);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();

    }
}



