package com.example.favlistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryItemsActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private FloatingActionButton addItemFab;
    private Category category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_ITEMS_KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        itemsRecyclerView = findViewById(R.id.ItemsInListRecyclerView);
        addItemFab = findViewById(R.id.addItemsToListFab);

        addItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        setTitle("Fav List >> " + category.getName());

    }

    private void displayItemCreationDialog() {

        final EditText itemsEditText = new EditText(this);
        itemsEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Enter name of the item here")
                .setView(itemsEditText)
                .setPositiveButton("Create Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String itemName = itemsEditText.getText().toString();
                        category.getItems().add()

                    }
                }).show();

    }
}