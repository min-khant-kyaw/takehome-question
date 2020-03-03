package com.example.trialqn;

import android.net.Uri;

public class Website {
    private Integer image;
    private String title;
    private String url;

    //Constructor
    public Website(Integer image, String title, String url) {
        this.image = image;
        this.title = title;
        this.url = url;
    }

    //Setter & Getter
    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
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
