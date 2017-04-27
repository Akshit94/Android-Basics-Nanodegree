package com.example.jainsaab.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class NewsAdapter extends ArrayAdapter<News> {

    private List<News> newsList = new ArrayList<>();

    NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
        newsList = news;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        News news = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_view_item, parent, false);
        }

        TextView sectionName = (TextView) convertView.findViewById(R.id.section);
        TextView titleName = (TextView) convertView.findViewById(R.id.title);

        if (news != null) {
            sectionName.setText(news.getSection());
            titleName.setText(news.getTitle());
        }
        // Return the completed view to render on screen
        return convertView;
    }

    void setNewsList(List<News> data) {
        newsList.addAll(data);
        notifyDataSetChanged();
    }
}
