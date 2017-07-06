package com.example.ahmedatia.advancedrealmdatabase;

import io.realm.RealmObject;

/**
 * Created by ahmedatia on 06/07/2017.
 */

public class Model extends RealmObject {
    private String text;

    public void setText(String text) {
        this.text = text;
    }
}
