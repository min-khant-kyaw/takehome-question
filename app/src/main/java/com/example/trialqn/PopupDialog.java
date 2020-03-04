package com.example.trialqn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class PopupDialog extends AppCompatDialogFragment {

    EditText linkInput;
    private PopupDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup, null);

        builder.setView(view)
                .setTitle("Add Website Link")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Integer defaultImage = R.drawable.image_failed;
                        String url = linkInput.getText().toString();
                        String defaultTitle = "Default";
//                        Website newLink = new Website(R.drawable.ic_search_black_24dp, "Default", url);
                        listener.addModel(defaultImage, url, defaultTitle);
                    }
                });
        linkInput = view.findViewById(R.id.linkInput);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (PopupDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement PopupDialogListener");
        }
    }

    public interface PopupDialogListener {
        void addModel(Integer image, String url, String title);
    }
}
