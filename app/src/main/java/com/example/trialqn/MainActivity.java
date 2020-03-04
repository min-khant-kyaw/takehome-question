package com.example.trialqn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageView listImage;
    TextView listTitle, listDesc;
    FloatingActionButton addBtn;
    WebView webView;

    private ArrayList<String> items;
//    private ArrayList<Website> mWebsiteList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        webView = findViewById(R.id.webView);
        listImage = findViewById(R.id.listImage);
        listTitle = findViewById(R.id.listTitle);
        listDesc = findViewById(R.id.listUrl);
        addBtn = findViewById(R.id.addBtn);

        //Create the Website Object
        Website google = new Website(R.drawable.ic_search_black_24dp, "Google", "https://www.google.com");
        Website yahoo = new Website(R.drawable.ic_adb_black_24dp, "Yahoo", "https://www.yahoo.com");
        Website cna = new Website(R.drawable.ic_announcement, "CNA", "https://www.channelnewsasia.com");

        //Add Websites to ArrayList
        ArrayList<Website> websiteList = new ArrayList<>();
        websiteList.add(google);
        websiteList.add(yahoo);
        websiteList.add(cna);

        WebsiteListAdapter adapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);
        listView.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });
    }

    public void showPopUp() {
        PopupDialog popupDialog = new PopupDialog();
        popupDialog.show(getSupportFragmentManager(), "popup Dialog");
    }
}
