package com.example.recify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BrowseRecipes extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_ic_button;

    MyDatabaseHelper myDB;
    ArrayList<String> r_id, r_name, r_type, r_ingredients, r_method;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_recipe);

        recyclerView = findViewById(R.id.recyclerView);
        add_ic_button = findViewById(R.id.add_ic_button);
        add_ic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BrowseRecipes.this, AddRecipe.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(BrowseRecipes.this);
        r_id = new ArrayList<>();
        r_name = new ArrayList<>();
        r_type = new ArrayList<>();
        r_ingredients = new ArrayList<>();
        r_method = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(BrowseRecipes.this, this, r_id, r_name, r_type, r_ingredients, r_method);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BrowseRecipes.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Recipes Yet...", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                r_id.add(cursor.getString(0));
                r_name.add(cursor.getString(1));
                r_type.add(cursor.getString(2));
                r_ingredients.add(cursor.getString(3));
                r_method.add(cursor.getString(4));
            }
        }
    }

}