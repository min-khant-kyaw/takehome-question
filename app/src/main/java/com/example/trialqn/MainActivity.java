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
import android.widget.AbsListView.MultiChoiceModeListener;
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
    ListView list;
    ImageView listImage;
    TextView listTitle, listDesc;
    FloatingActionButton addBtn;
    WebView webView;
    Toolbar toolbar;
    WebsiteListAdapter listviewadapter;
    private static ArrayList<Website> websiteList = new ArrayList<Website>();
    private boolean ascending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.listView);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
//        webView = findViewById(R.id.webView);
        listImage = findViewById(R.id.listImage);
        listTitle = findViewById(R.id.listTitle);
        listDesc = findViewById(R.id.listUrl);
        addBtn = findViewById(R.id.addBtn);

        //Setting App Toolbar
        setSupportActionBar(toolbar);

        //Create the Website Object
        Website google = new Website(R.drawable.image_failed, "Test title", "https://google.com");
        Website yahoo = new Website(R.drawable.image_failed, "Test title", "https://yahoo.com");
        Website cna = new Website(R.drawable.image_failed, "Test title", "https://channelnewsasia.com");

        //add them to ArrayList
        websiteList.add(google);
        websiteList.add(yahoo);
        websiteList.add(cna);

        listviewadapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);

        //show adapter in view and sort out the data
        list.setAdapter(listviewadapter);
        this.sortData(true);

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
        list.setMultiChoiceModeListener(new MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listviewadapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Website selecteditem = listviewadapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                listviewadapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        getSupportActionBar().show();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getSupportActionBar().hide();
                mode.getMenuInflater().inflate(R.menu.main, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listviewadapter.removeSelection();
                getSupportActionBar().show();
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
        }
        else
        {
            Collections.reverse(websiteList);
        }

        WebsiteListAdapter adapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);
        list.setAdapter(adapter);
    }


    public void showPopUp() {
        PopupDialog popupDialog = new PopupDialog();
        popupDialog.show(getSupportFragmentManager(), "popup Dialog");
    }


    public void addModel(Integer image, String url, String title) {
        Website newWebsite = new Website(image, title, url);
        websiteList.add(newWebsite);
        WebsiteListAdapter adapter = new WebsiteListAdapter(this, R.layout.listitem, websiteList);
        list.setAdapter(adapter);
        this.sortData(true);
    }
}
