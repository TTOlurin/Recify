package com.example.recify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRecipe extends AppCompatActivity {

    EditText nameinpt, typeinpt, ingredientinpt, methodinpt;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

        nameinpt = findViewById(R.id.nameinpt);
        typeinpt = findViewById(R.id.typeinpt);
        ingredientinpt = findViewById(R.id.ingredientinpt);
        methodinpt = findViewById(R.id.methodinpt);
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddRecipe.this);
                myDB.addRecipe(nameinpt.getText().toString().trim(),
                        typeinpt.getText().toString().trim(),
                        ingredientinpt.getText().toString().trim(),
                        methodinpt.getText().toString().trim());
            }
        });
    }
}