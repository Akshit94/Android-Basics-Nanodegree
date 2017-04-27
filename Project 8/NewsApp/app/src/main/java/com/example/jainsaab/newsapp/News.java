package com.example.jainsaab.newsapp;

class News {

    private String mSection;
    private String mTitle;
    private String mUrl;

    News(String section, String title, String url) {
        mSection = section;
        mTitle = title;
        mUrl = url;
    }

    String getSection() {
        return mSection;
    }

    String getTitle() {
        return mTitle;
    }

    String getUrl() {
        return mUrl;
    }
}
