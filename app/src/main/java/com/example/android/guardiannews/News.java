package com.example.android.guardiannews;

/**
 * Created by nohava on 20. 7. 2017.
 */

public class News {


    // variables declaration
    private String mTitle;
    private String mSection;
    private String mDate;
    private String mUrl;


    // constructor
    public News(String title, String section, String date, String url){
        mTitle = title;
        mSection = section;
        mDate = date;
        mUrl = url;
    }


    // getter methods
    public String getTitle(){
        return mTitle;
    }

    public String getSection(){
        return mSection;
    }

    public String getDate(){
        return mDate;
    }

    public String getUrl(){
        return mUrl;
    }
}
