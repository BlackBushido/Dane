package com.example.dane;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdatePhoneActivity extends AppCompatActivity {

    public static final String PHONE_OUT_KEY = "com.example.database.PRODUCER_KEY";
    private Button cancelButton, webSiteButton, updateButton;
    private EditText producerField, modelField, androidVersionField, webSiteField;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_phone_view);

        cancelButton = findViewById(R.id.cancelButton);
        webSiteButton = findViewById(R.id.webSiteButton);
        updateButton = findViewById(R.id.updateButton);

        producerField = findViewById(R.id.producerField);
        modelField = findViewById(R.id.modelField);
        androidVersionField = findViewById(R.id.androidVersionField);
        webSiteField = findViewById(R.id.webSiteField);

        Intent intent = getIntent();
        Phone phone = intent.getParcelableExtra(MainActivity.PHONE_KEY);

        producerField.setText(phone.getMProducer());
        modelField.setText(phone.getMModel());
        androidVersionField.setText(phone.getMVersion());
        webSiteField.setText(phone.getMWeb());

        cancelButton.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        updateButton.setOnClickListener(view -> {
            if(!producerField.getText().toString().isEmpty() && !modelField.getText().toString().isEmpty() && !androidVersionField.getText().toString().isEmpty() && !webSiteField.getText().toString().isEmpty()){
                if(!URLUtil.isValidUrl(webSiteField.getText().toString())){
                    Toast.makeText(getApplicationContext(), getString(R.string.invalidUrl), Toast.LENGTH_SHORT).show();
                }else{
                    Bundle bundle = new Bundle();
                    Phone phoneOut = new Phone(
                            phone.getMIndex(),
                            producerField.getText().toString(),
                            modelField.getText().toString(),
                            androidVersionField.getText().toString(),
                            webSiteField.getText().toString()
                    );
                    bundle.putParcelable(PHONE_OUT_KEY, phoneOut);

                    Intent intentOut = new Intent();
                    intentOut.putExtras(bundle);
                    setResult(RESULT_FIRST_USER+1, intentOut);
                    finish();
                }
            }else {
                Toast.makeText(getApplicationContext(), getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
