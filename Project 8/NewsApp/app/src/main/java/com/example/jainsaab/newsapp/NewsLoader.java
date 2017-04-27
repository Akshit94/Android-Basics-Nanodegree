package com.example.jainsaab.newsapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class NewsLoader extends AsyncTaskLoader<List<News>> {
    private Context mContext;

    NewsLoader(Context context) {
        super(context);
        mContext = context;
    }

    private List<News> getNewsDataFromJson(String newsJson) {
        if (newsJson != null) {
            ArrayList<News> newsList = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(newsJson);
                JSONObject responseData = jsonObject.getJSONObject("response");
                JSONArray arr = responseData.getJSONArray("results");

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String newsTitle = jsonPart.getString("webTitle");
                    String newsSection = jsonPart.getString("sectionName");
                    String newsLink = jsonPart.getString("webUrl");

                    News newsObject = new News(newsSection, newsTitle, newsLink);
                    newsList.add(newsObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return newsList;
        } else {
            Toast.makeText(mContext, "No results found.", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    @Override
    public List<News> loadInBackground() {

        HttpURLConnection urlConnection;
        String newsJsonStr = "";

        try {
            String baseUrl = "https://content.guardianapis.com/search?q=android&api-key=24135679-dce8-45a6-ab43-fd39d5812a17";
            URL url = new URL(baseUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;
                newsJsonStr += current;
                data = reader.read();

            }

            return getNewsDataFromJson(newsJsonStr);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }
}
