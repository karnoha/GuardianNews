package com.example.android.guardiannews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nohava on 22. 7. 2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    private static final String DATE_SEPARATOR = "T";

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // get current position
        News currentNews = getItem(position);

        // create text views and assign values
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.list_view_title);
        titleTextView.setText(currentNews.getTitle());

        TextView sectionTextView = (TextView) listItemView.findViewById(R.id.list_view_section);
        sectionTextView.setText(currentNews.getSection());

        // date has to be separated from time - they're in the same object in json
        String dateToFormat = currentNews.getDate();
        String dateSeparatedFromTime = "";
        String[] parts = dateToFormat.split(DATE_SEPARATOR);
        dateSeparatedFromTime = parts[0];
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.list_view_date);
        dateTextView.setText(dateSeparatedFromTime);
        return listItemView;
    }
}
