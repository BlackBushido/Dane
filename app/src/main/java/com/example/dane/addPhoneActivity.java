package com.example.dane;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addPhoneActivity extends AppCompatActivity {

    private EditText producerField, modelField, androidVersionField, webSiteField;
    public static final String PRODUCER_KEY = "com.example.database.PRODUCER_KEY";
    public static final String MODEL_KEY = "com.example.database.MODEL_KEY";
    public static final String ANDROID_VERSION_KEY = "com.example.database.ANDROID_VERSION_KEY";
    public static final String WEB_SITE_KEY = "com.example.database.WEB_SITE_KEY";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phone_view);
        setUpField();
    }

    private void setUpField() {
        Button cancel_Button = findViewById(R.id.cancelButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button webSiteButton = findViewById(R.id.webSiteButton);

        producerField = findViewById(R.id.producerField);
        modelField = findViewById(R.id.modelField);
        androidVersionField = findViewById(R.id.androidVersionField);
        webSiteField = findViewById(R.id.webSiteField);

        cancel_Button.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        saveButton.setOnClickListener(view -> {
            if(!producerField.getText().toString().isEmpty() && !modelField.getText().toString().isEmpty() && !androidVersionField.getText().toString().isEmpty() && !webSiteField.getText().toString().isEmpty()){
                if(!URLUtil.isValidUrl(webSiteField.getText().toString())){
                    Toast.makeText(getApplicationContext(), getString(R.string.invalidUrl), Toast.LENGTH_SHORT).show();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString(PRODUCER_KEY, producerField.getText().toString());
                    bundle.putString(MODEL_KEY, modelField.getText().toString());
                    bundle.putString(ANDROID_VERSION_KEY, androidVersionField.getText().toString());
                    bundle.putString(WEB_SITE_KEY, webSiteField.getText().toString());

                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }else {
                Toast.makeText(getApplicationContext(), getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();
            }
        });

        webSiteButton.setOnClickListener(view -> {
            if(!URLUtil.isValidUrl(webSiteField.getText().toString())) {
                Toast.makeText(getApplicationContext(), getString(R.string.invalidUrl), Toast.LENGTH_SHORT).show();
            }else{
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(webSiteField.getText().toString())));
            }
        });
    }
}
