package com.example.recify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditRecipe extends AppCompatActivity {

    EditText nameinpt, typeinpt, ingredientinpt, methodinpt;
    Button button_update, button_delete;
    String id, name, type, ingredients, method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recipe);

        nameinpt = findViewById(R.id.nameinpt2);
        typeinpt = findViewById(R.id.typeinpt2);
        ingredientinpt = findViewById(R.id.ingredientinpt2);
        methodinpt = findViewById(R.id.methodinpt2);
        button_update = findViewById(R.id.button_update);
        button_delete = findViewById(R.id.button_delete);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameinpt.getText().toString().trim();
                type = typeinpt.getText().toString().trim();
                ingredients = ingredientinpt.getText().toString().trim();
                method = methodinpt.getText().toString().trim();

                MyDatabaseHelper myDB = new MyDatabaseHelper(EditRecipe.this);
                myDB.updateData(id, name, type, ingredients, method);
            }
        });
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("type")
                && getIntent().hasExtra("ingredients") && getIntent().hasExtra("method")) {
            //getting intent data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            ingredients = getIntent().getStringExtra("ingredients");
            method = getIntent().getStringExtra("method");

            //setting intent data
            nameinpt.setText(name);
            typeinpt.setText(type);
            ingredientinpt.setText(ingredients);
            methodinpt.setText(method);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?");
        builder.setMessage("Are you sure you want to delete " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(EditRecipe.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}