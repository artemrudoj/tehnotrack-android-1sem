package com.artem.hometask.model;

import android.os.Bundle;

/**
 * Created by artem on 19.04.16.
 */
public class Technology {
    public static String TECHNOLOGY = "technology";
    public static String PICTURE = "picture";
    public static String TITLE = "title";
    public static String INFO = "info";

    String picture;
    String title;
    String info;

    public Technology(String picture, String title, String info) {
        this.picture = picture;
        this.title = title;
        this.info = info;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    Bundle populateBundle(Bundle bundle) {
        if(picture != null)
            bundle.putString(PICTURE, picture);
        if(title != null)
            bundle.putString(TITLE, title);
        if(info != null)
            bundle.putString(INFO, info);
        return bundle;
    }
}
