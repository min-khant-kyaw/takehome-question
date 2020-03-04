package com.example.trialqn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupDialog.PopupDialogListener {
    ListView listView;
    ImageView listImage;
    TextView listTitle, listDesc;
    FloatingActionButton addBtn;
    WebView webView;
    Toolbar toolbar;

    //Add Websites to ArrayList
    ArrayList<Website> websiteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        webView = findViewById(R.id.webView);
        listImage = findViewById(R.id.listImage);
        listTitle = findViewById(R.id.listTitle);
        listDesc = findViewById(R.id.listUrl);
        addBtn = findViewById(R.id.addBtn);

        //Setting App Toolbar
        setSupportActionBar(toolbar);

        //Create the Website Object
        Website google = new Website(R.drawable.ic_search_black_24dp, "Google", "https://www.google.com");
        Website yahoo = new Website(R.drawable.ic_adb_black_24dp, "Yahoo", "https://www.yahoo.com");
        Website cna = new Website(R.drawable.ic_announcement, "CNA", "https://www.channelnewsasia.com");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void showPopUp() {
        PopupDialog popupDialog = new PopupDialog();
        popupDialog.show(getSupportFragmentManager(), "popup Dialog");
    }

    public void addModel(Integer image, String url, String title) {
        Website newWebsite = new Website(image, title, url);
        websiteList.add(newWebsite);
        WebsiteListAdapter adapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);
        listView.setAdapter(adapter);
    }
}
