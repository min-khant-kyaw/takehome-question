package com.example.trialqn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WebsiteListAdapter extends ArrayAdapter<Website> {

    private static final String TAG = "WebsiteListAdapter";

    private Context mContext;
    int mResource;

    public WebsiteListAdapter( Context context, int resource,  ArrayList<Website> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get Website Information
        Integer image = getItem(position).getImage();
        String title = getItem(position).getTitle();
        String url = getItem(position).getUrl();

        //Create new Website object with the information
        Website website = new Website(image, title, url);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView listImage = convertView.findViewById(R.id.listImage);
        TextView listTitle = convertView.findViewById(R.id.listTitle);
        TextView listUrl = convertView.findViewById(R.id.listUrl);

        listImage.setImageResource(image);
        listTitle.setText(title);
        listUrl.setText(url);

        return convertView;
    }
}
