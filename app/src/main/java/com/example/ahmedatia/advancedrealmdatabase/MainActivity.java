package com.example.ahmedatia.advancedrealmdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.MemoryHandler;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    EditText text;
    Button add, get, update, delete;
    Realm realm;
    DataBase realmDataBAse;
    String myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.textView);
        add = (Button) findViewById(R.id.add);
        get = (Button) findViewById(R.id.get);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);

        realm = Realm.getDefaultInstance();
        realmDataBAse = new DataBase(realm, this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myText = getTextFromTheEditText();
                realmDataBAse.saveData(myText);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myText=getTextFromTheEditText();
                realmDataBAse.updateCurrentUser(myText);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myText = getTextFromTheEditText();
                realmDataBAse.deleteUsingNAme(myText);

            }
        });


        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realmDataBAse.getAllUser();

            }
        });


    }

    private String getTextFromTheEditText() {
        return text.getText().toString();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}
