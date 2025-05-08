package com.example.sit708_71p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editName, editDescription, editContact;
    Button btnAdd;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    ItemAdapter adapter;
    ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editDescription = findViewById(R.id.editDescription);
        editContact = findViewById(R.id.editContact);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        dbHelper = new DBHelper(this);
        items = dbHelper.getAllItems();
        adapter = new ItemAdapter(this, items, dbHelper);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String desc = editDescription.getText().toString();
            String contact = editContact.getText().toString();

            dbHelper.addItem(name, desc, contact);
            items.clear();
            items.addAll(dbHelper.getAllItems());
            adapter.notifyDataSetChanged();

            editName.setText("");
            editDescription.setText("");
            editContact.setText("");
        });
    }
}