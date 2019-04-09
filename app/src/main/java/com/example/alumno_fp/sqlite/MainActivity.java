package com.example.alumno_fp.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonPlaces;

    private List<Place> places;
    private RecyclerView listPlaces;
    private PlaceAdapter mAdapter;
    private final int CODE_SAVE = 1;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initAdapter();

        buttonPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SaveActivity.class);
                startActivityForResult(intent,CODE_SAVE);
            }
        });
    }

    private void initUI(){
        buttonPlaces = findViewById(R.id.button_places);
        places = new ArrayList<>();

        listPlaces = findViewById(R.id.list_places);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listPlaces.setLayoutManager(llm);
        listPlaces.setHasFixedSize(true);
        PlacesSQLiteHelper psdbh = new PlacesSQLiteHelper(this,"DBPlaces",null,1);
        db = psdbh.getWritableDatabase();
        readDB();
    }

    private void initAdapter(){
        mAdapter = new PlaceAdapter(places,MainActivity.this);
        listPlaces.setAdapter(mAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new Swipe(mAdapter,db));
        itemTouchHelper.attachToRecyclerView(listPlaces);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == CODE_SAVE){
            String place = data.getStringExtra("Place");
            String country = data.getStringExtra("Country");

            try {
                ContentValues newRegister = new ContentValues();
                newRegister.put("name",place);
                newRegister.put("country",country);
                db.insert("Places",null,newRegister);

                if (getLastID() != 0){
                    places.add(new Place(String.valueOf(getLastID()),place,country));
                    mAdapter.notifyDataSetChanged();
                }
            }catch (Exception ex){
                Log.i("ErrorDB",ex.getMessage());
            }
        }
    }

    private void readDB(){
        Cursor cursor = db.rawQuery("SELECT id,name,country FROM Places",null);
        if (cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String country = cursor.getString(2);

                places.add(new Place(id,name,country));
            }while(cursor.moveToNext());
        }
    }

    private int getLastID(){
        Cursor cursor = db.rawQuery("SELECT id FROM Places",null);
        int id = 0;
        if (cursor.moveToLast()){
            id = cursor.getInt(0);
        }

        return id;
    }
}
