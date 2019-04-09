package com.example.alumno_fp.sqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {

    public static class PlaceViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView textId,textPlace,textCountry;

        PlaceViewHolder(View itemView){
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            textId = itemView.findViewById(R.id.text_id);
            textPlace = itemView.findViewById(R.id.text_place);
            textCountry = itemView.findViewById(R.id.text_country);
        }
    }

    List<Place> places;
    Context context;

    PlaceAdapter(List<Place> places,Context context){
        this.places = places;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        final PlaceViewHolder pvh = new PlaceViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.textId.setText(places.get(i).getId());
        placeViewHolder.textPlace.setText(places.get(i).getPlace());
        placeViewHolder.textCountry.setText(places.get(i).getCountry());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

}
