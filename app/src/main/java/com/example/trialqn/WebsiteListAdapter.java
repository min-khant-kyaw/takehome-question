package com.example.trialqn;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trialqn.R;
import java.util.List;

public class WebsiteListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Website> mWebsiteList;

    //Constructor
    public WebsiteListAdapter(Context mContext, List<Website> mWebsiteList) {
        this.mContext = mContext;
        this.mWebsiteList = mWebsiteList;
    }

    @Override
    public int getCount() {
        return mWebsiteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWebsiteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.listitem, null);
        ImageView image = v.findViewById(R.id.image);
        TextView listTitle = v.findViewById(R.id.listTitle);
        TextView listDesc = v.findViewById(R.id.listDesc);
        //SetText
        image.setImageURI(mWebsiteList.get(position).getImage());
        listTitle.setText(mWebsiteList.get(position).getTitle());
        listDesc.setText(mWebsiteList.get(position).getUrl());

        return v;
    }
}
