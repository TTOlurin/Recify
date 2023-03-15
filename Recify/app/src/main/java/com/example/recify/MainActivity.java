package com.example.recify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddRecipe.class);
                startActivity(intent);
            }
        });

        //Reference: The following code is from Android example https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity?answertab=modifieddesc#tab-top
        /*Button abtn = (Button)findViewById(R.id.addBtn);

        abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddRecipe.class));

            }
        });*/

        Button lbtn = (Button)findViewById(R.id.listBtn);

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecipeSpeech.class));

            }
        });

        Button brbtn = (Button)findViewById(R.id.browseBtn);

        brbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrowseRecipes.class));

            }
        });
        //Reference complete

    }


}

