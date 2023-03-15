package com.example.recify;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RecipeSpeech extends AppCompatActivity {

    ImageView speechButton;
    EditText speechText;
    Button button_copy;

    private static final int RECOGNIZER_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_speechtt);

        button_copy = findViewById(R.id.button_copy);

        speechButton = findViewById(R.id.image_speech);
        speechText = findViewById(R.id.editText_speech);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                    Intent data = result.getData();
                    speechText.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
                }
            }
        });

        speechButton.setOnClickListener(view -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start speaking");

            launcher.launch(intent);
        });
        //This is referenced from https://www.youtube.com/watch?v=nlYT3rfsXN4&list=PLSrm9z4zp4mG9o873gDqCuGZkgOb_TR4N&index=16&ab_channel=Stevdza-San
        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EditText", speechText.getText().toString());
                clipboard.setPrimaryClip(clip);
                
                Toast.makeText(RecipeSpeech.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
