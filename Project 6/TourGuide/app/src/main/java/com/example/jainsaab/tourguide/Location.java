package com.example.jainsaab.tourguide;


class Location {

    private String mName;
    private String mLocation;
    private int mImageId;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    int getImageId() {
        return mImageId;
    }

    String getLocation() {
        return mLocation;
    }

    Location(String name, String location, int imageId) {
        mName = name;
        mLocation = location;
        mImageId = imageId;
    }
}
