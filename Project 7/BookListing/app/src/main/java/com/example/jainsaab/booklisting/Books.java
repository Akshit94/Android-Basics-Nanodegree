package com.example.jainsaab.booklisting;

public class Books {

    private String mAuthor;
    private String mTitle;

    Books(String author, String title){
        mAuthor = author;
        mTitle = title;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getTitle(){
        return mTitle;
    }
}
