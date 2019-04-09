package com.example.alumno_fp.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveActivity extends AppCompatActivity {

    EditText etId,etPlace,etCountry;
    Button buttonSave;
    private final int CODE_SAVE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        initUI();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                String place = etPlace.getText().toString().trim();
                String country = etCountry.getText().toString().trim();
                if (validate(id) && validate(place) && validate(country)){
                    Intent intent = new Intent();
                    intent.putExtra("Id",id);
                    intent.putExtra("Place",place);
                    intent.putExtra("Country",country);
                    setResult(CODE_SAVE,intent);
                    finish();
                }else{
                   Toast.makeText(getApplicationContext(),"¡Campos obligatorios!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUI(){
        etId = findViewById(R.id.et_id);
        etPlace = findViewById(R.id.et_place);
        etCountry = findViewById(R.id.et_country);
        buttonSave = findViewById(R.id.button_save);
    }

    private boolean validate(String string){
        boolean isValid = true;
        if (string.isEmpty()){
            isValid = false;
        }

        return isValid;
    }
}
