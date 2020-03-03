package com.example.trialqn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Dialog newDialog;
    ListView itemsList;
    ImageView image;
    TextView listTitle, listDesc;
    FloatingActionButton addBtn;

    private ArrayList<String> items;
//    private ArrayList<Website> mWebsiteList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newDialog = new Dialog(this);

        itemsList = findViewById(R.id.itemsList);
        image = findViewById(R.id.image);
        listTitle = findViewById(R.id.listTitle);
        listDesc = findViewById(R.id.listDesc);
        addBtn = findViewById(R.id.addBtn);

//        items = FileHelper.readData(this);
////        mWebsiteList = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
//        itemsList.setAdapter(adapter);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
                Toast.makeText(MainActivity.this, "POP UP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showPopUp() {
        PopupDialog popupDialog = new PopupDialog();
        popupDialog.show(getSupportFragmentManager(), "popup Dialog");
    }
}
