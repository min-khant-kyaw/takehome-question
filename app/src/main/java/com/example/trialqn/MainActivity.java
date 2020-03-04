package com.example.trialqn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements PopupDialog.PopupDialogListener {

    //Declare Variables
    ListView listView;
    ImageView listImage;
    TextView listTitle, listDesc;
    FloatingActionButton addBtn;
    WebView webView;
    Toolbar toolbar;
    WebsiteListAdapter listviewadapter;
    private static ArrayList<Website> websiteList = new ArrayList<>();
    private boolean ascending = true;

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

        this.fillData();

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        //Capture ListView Item Click
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = listView.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listviewadapter.getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Website selectedItem = listviewadapter.getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                listviewadapter.remove(selectedItem);
                            }
                        }
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listviewadapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });

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
        switch (item.getItemId()) {
            case R.id.action_sort:
                this.sortData(ascending);
                ascending = !ascending;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortData(boolean asc) {
        //SORT ARRAY ASCENDING AND DESCENDING
        //https:// & https://www. could mess up with the sorting function
        if (asc)
        {
            listviewadapter.sort(new Comparator<Website>() {
                @Override
                public int compare(Website o1, Website o2) {
                    return o1.getUrl().compareTo(o2.getUrl());
                }
            });
            Toast.makeText(this, "TRUE", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Collections.reverse(websiteList);
            Toast.makeText(this, "FALSE", Toast.LENGTH_SHORT).show();
        }

        WebsiteListAdapter adapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);
        listView.setAdapter(adapter);
    }


    public void fillData() {
        //Create the Website Object
        Website google = new Website(R.drawable.ic_search_black_24dp, "Google", "https://google.com");
        Website yahoo = new Website(R.drawable.ic_adb_black_24dp, "Yahoo", "https://yahoo.com");
        Website cna = new Website(R.drawable.ic_announcement, "CNA", "https://channelnewsasia.com");

        websiteList.add(google);
        websiteList.add(yahoo);
        websiteList.add(cna);

        listviewadapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);
        listView.setAdapter(listviewadapter);
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
        this.sortData(true);
    }
}
