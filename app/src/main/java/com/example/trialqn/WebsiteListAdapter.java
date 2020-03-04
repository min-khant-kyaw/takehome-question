package com.example.trialqn;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WebsiteListAdapter extends ArrayAdapter<Website> {

    private static final String TAG = "WebsiteListAdapter";

    private ArrayList<Website> websiteList;
    private Context context;
    //    private int mResource;
    private int lastPosition = -1;
    private SparseBooleanArray mSelectedItemsIds;
    LayoutInflater inflater;

    public WebsiteListAdapter(Context context, int resourceId,
                              ArrayList<Website> websiteList) {
        super(context, resourceId, websiteList);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.websiteList = websiteList;
        inflater = LayoutInflater.from(context);
//        mResource = resource;
    }

    private class ViewHolder {
        TextView title;
        TextView url;
        ImageView image;
        WebView webView;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Get Website Information
        Integer image = getItem(position).getImage();
        String title = getItem(position).getTitle();
        String url = getItem(position).getUrl();


        //Create new View for animation purposes
        final View result;

        //ViewHolder
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem, null);
            holder.image = (ImageView) convertView.findViewById(R.id.listImage);
            holder.title = (TextView) convertView.findViewById(R.id.listTitle);
            holder.url = (TextView) convertView.findViewById(R.id.listUrl);
            holder.webView = (WebView) convertView.findViewById(R.id.webView);

            result = convertView;

            convertView.setTag(holder);
        } else {
            result = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        //Capture position and set to the TextViews
        holder.url.setText(url);
        //WebView Object
        holder.webView.loadUrl(url);
        holder.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                String t = view.getTitle();
                holder.title.setText(t);
            }
        });
        //Capture position and set to the ImageView
        holder.webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                holder.image.setImageBitmap(icon);
            }
        });

        Animation animFadeIn = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.fade_in_anim);
        convertView.startAnimation(animFadeIn);
        return convertView;
    }

    @Override
    public void remove(Website object) {
        websiteList.remove(object);
        notifyDataSetChanged();
    }

    public ArrayList<Website> getWebsiteList() {
        return websiteList;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
