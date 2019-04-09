package com.example.alumno_fp.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

public class Swipe extends ItemTouchHelper.SimpleCallback {

    private PlaceAdapter mAdapter;
    private SQLiteDatabase db;

    public Swipe(PlaceAdapter adapter,SQLiteDatabase database){
        super(0,ItemTouchHelper.LEFT);
        mAdapter = adapter;
        db = database;
    }
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int position = viewHolder.getAdapterPosition();
        Place place = mAdapter.getPlace(position);

        mAdapter.removePlace(place);
        db.delete("Places","id = '" + place.getId() + "'",null);

        mAdapter.notifyDataSetChanged();
    }
}
