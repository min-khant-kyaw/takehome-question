package com.example.trialqn;

import android.net.Uri;

public class Website {
    private Uri image;
    private String title;
    private String url;

    //Constructor
    public Website(Uri image, String title, String url) {
        this.image = image;
        this.title = title;
        this.url = url;
    }

    //Setter & Getter
    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
