package com.example.jainsaab.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Books> {

    public BooksAdapter(Context context, ArrayList<Books> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Books books = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.books_item, parent, false);
        }

        TextView authorName = (TextView) convertView.findViewById(R.id.author);
        TextView titleName = (TextView) convertView.findViewById(R.id.title);

        if (books != null) {
            authorName.setText(books.getAuthor());
            titleName.setText(books.getTitle());
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
