package com.example.alumno_fp.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    private EditText etId,etPlace,etCountry;
    private Button buttonUpdate;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initUI();

        initData();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.valueOf(etId.getText().toString().trim());
                String place = etPlace.getText().toString().trim();
                String country = etCountry.getText().toString().trim();
                if (validate(place) && validate(country)){
                    ContentValues values = new ContentValues();
                    values.put("name",place);
                    values.put("country",country);

                    db.update("Places",values,"id = " + id,null);

                    Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initUI(){
        etId = findViewById(R.id.et_id);
        etPlace = findViewById(R.id.et_place);
        etCountry = findViewById(R.id.et_country);
        buttonUpdate = findViewById(R.id.button_update);

        PlacesSQLiteHelper psdbh = new PlacesSQLiteHelper(this,"DBPlaces",null,1);
        db = psdbh.getWritableDatabase();
    }

    private void initData(){
        Intent intent = getIntent();
        etId.setText(intent.getStringExtra("Id"));
        etPlace.setText(intent.getStringExtra("Place"));
        etCountry.setText(intent.getStringExtra("Country"));
    }

    private boolean validate(String string){
        boolean isValid = true;
        if (string.isEmpty()){
            isValid = false;
        }

        return isValid;
    }
}
