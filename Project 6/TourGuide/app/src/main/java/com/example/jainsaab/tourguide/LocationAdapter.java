package com.example.jainsaab.tourguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<Location>{

    public LocationAdapter(Context context, ArrayList<Location> locations) {
        super(context, 0, locations);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Location location = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text_view_name);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.text_view_location);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_item_image_view);
        // Populate the data into the template view using the data object
        if (location != null) {
            tvName.setText(location.getName());
            tvLocation.setText(location.getLocation());
            if(location.getImageId() != 0){
                imageView.setImageDrawable(convertView.getResources().getDrawable(location.getImageId()));
            }
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
