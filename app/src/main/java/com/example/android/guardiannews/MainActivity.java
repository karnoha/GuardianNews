package com.example.android.guardiannews;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.app.LoaderManager;
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

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String API_URL = "http://content.guardianapis.com/search?api-key=test";

    private static final int NEWS_LOADER = 1;
    private NewsAdapter mAdapter;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER, null, this);
        } else {
            View progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No internet");
        }
    }

    @Override
    public android.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, API_URL);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<News>> loader, List<News> newsList) {
        View progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        mEmptyStateTextView.setText("No news found");

        mAdapter.clear();
        if (newsList != null && !newsList.isEmpty()) {
            mAdapter.addAll(newsList);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
