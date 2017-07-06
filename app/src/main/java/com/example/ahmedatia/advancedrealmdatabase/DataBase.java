package com.example.ahmedatia.advancedrealmdatabase;

import android.content.Context;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by ahmedatia on 06/07/2017.
 */

public class DataBase {
    Realm realm;
    Context context;

    public DataBase(Realm realm, Context context) {
        this.realm = realm;
        this.context = context;
    }

    public void saveData(final String myText) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Model user = bgRealm.createObject(Model.class);
                user.setText(myText);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, myText + "  Added to Data Base ", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getAllUser() {
        RealmResults<Model> result = realm.where(Model.class)
                .findAll();

        if (result.isLoaded()) {
            // Results are now available
            Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteUsingNAme(String myText) {
        final RealmResults<Model> results = realm.where(Model.class)
                .beginsWith("text", myText)
                .findAll();

        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                // remove single match

                //  results.deleteFirstFromRealm();
                //  results.deleteLastFromRealm();

                // remove a single object

                // Model dog = results.get(5);
                //dog.deleteFromRealm();

                // Delete all matches

                // a7sen 7all Ta3mel Bloc Try Catch Exception
                if (results.size() == 0) {
                    Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    results.deleteAllFromRealm();
                    Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    public void updateCurrentUser(String myText) {

        final RealmResults<Model> results = realm.where(Model.class)
                .beginsWith("text", myText)
                .findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //  Model dog = realm.createObject(Model.class);
                // dog.setText("Fido");
                if (results.size() == 0) {
                    Toast.makeText(context, "User Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    results.get(0).setText("AhmedAtia");
                    Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }
}
