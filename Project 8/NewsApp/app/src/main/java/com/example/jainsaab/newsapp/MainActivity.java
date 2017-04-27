package com.example.jainsaab.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    ListView mNewsListView;
    TextView mEmptyView;
    ArrayList<String> newsWebUrlList;
    ArrayList<News> newsList;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsWebUrlList = new ArrayList<>();
        newsList = new ArrayList<>();

        mNewsListView = (ListView) findViewById(R.id.news_list_view);

        mEmptyView = (TextView) findViewById(R.id.empty_view);
        if (!isNetworkAvailable()) {
            mEmptyView.setText(getResources().getString(R.string.no_network));
        }

        mNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = newsWebUrlList.get(i);
                Intent newsIntent = new Intent(Intent.ACTION_VIEW);
                newsIntent.setData(Uri.parse(url));
                startActivity(newsIntent);
            }
        });

        mNewsListView.setEmptyView(mEmptyView);
        newsAdapter = new NewsAdapter(getApplicationContext(), newsList);
        mNewsListView.setAdapter(newsAdapter);
        getSupportLoaderManager().initLoader(0, null, this).forceLoad();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        newsAdapter.setNewsList(data);
        for (int i = 0; i < data.size(); ++i) {
            News news = data.get(i);
            newsWebUrlList.add(news.getUrl());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.setNewsList(new ArrayList<News>());
    }
}
