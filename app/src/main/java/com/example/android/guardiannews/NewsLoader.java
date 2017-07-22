package com.example.android.guardiannews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by nohava on 22. 7. 2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>>{

    // logtag for logging
    private static final String LOG_TAG = NewsLoader.class.getName();

    // query url
    private String mUrl;

    // constructotr
    public NewsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        List<News> news = QueryUtils.getData(mUrl);
        return news;
    }
}
