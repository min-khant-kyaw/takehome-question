package com.example.trialqn;

import android.content.Context;
import android.graphics.Bitmap;
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

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView title;
        TextView url;
        ImageView image;
        WebView webView;
    }

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


        //Create new View for animation purposes
        final View result;

        //ViewHolder
        final ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.listImage);
            holder.title = (TextView) convertView.findViewById(R.id.listTitle);
            holder.url = (TextView) convertView.findViewById(R.id.listUrl);
            holder.webView = (WebView) convertView.findViewById(R.id.webView);

            result = convertView;
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

//        holder.image.setImageResource(image);
        holder.url.setText(url);

        //WebView Object
        holder.webView.loadUrl(url);
        holder.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                String t = view.getTitle();
                holder.title.setText(t);
            }
        });

        holder.webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                holder.image.setImageBitmap(icon);
            }
        });

        return convertView;
    }
}
