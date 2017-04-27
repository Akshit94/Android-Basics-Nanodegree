package com.example.jainsaab.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    ListView booksListView;
    EditText editText;
    TextView emptyView;
    BooksAdapter booksAdapter;
    LinearLayout mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksListView = (ListView) findViewById(R.id.books_list_view);
        editText = (EditText) findViewById(R.id.search_edit_text);
        mProgressBar = (LinearLayout) findViewById(R.id.progress_bar);
        emptyView = (TextView) findViewById(R.id.empty_view);

        booksListView.setEmptyView(emptyView);

        Button searchButton = (Button) findViewById(R.id.search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), R.string.enter_book_name, Toast.LENGTH_SHORT).show();

                } else {
                    if (!isNetworkAvailable()) {
                        emptyView.setText(R.string.no_internet);
                        editText.setText("");
                    } else {
                        booksListView.setEmptyView(mProgressBar);
                        mProgressBar.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);

                        String query = editText.getText().toString();
                        editText.setText("");
                        new BooksFetchTask().execute(query);
                    }
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class BooksFetchTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection;
            String booksJsonStr = "";
            try {
                String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=" + strings[0] + "&maxResults=10";

                URL url = new URL(baseUrl);

                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    booksJsonStr += current;
                    data = reader.read();

                }

                return booksJsonStr;
            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                ArrayList<Books> bookList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray arr = jsonObject.getJSONArray("items");

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jsonPart = arr.getJSONObject(i);

                        JSONObject volumeInfo = jsonPart.getJSONObject("volumeInfo");

                        String title = volumeInfo.getString("title");
                        String authors = "";
                        try {
                            JSONArray authorArr = volumeInfo.getJSONArray("authors");
                            for (int x = 0; x < authorArr.length(); x++) {
                                authors += authorArr.get(x) + "    ";
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Books booksObject = new Books(authors, title);
                        bookList.add(booksObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                booksAdapter = new BooksAdapter(getApplicationContext(), bookList);
                booksListView.setAdapter(booksAdapter);
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_results_found), Toast.LENGTH_LONG).show();
            }
            mProgressBar.setVisibility(View.GONE);
            booksListView.setEmptyView(emptyView);
        }
    }
}
