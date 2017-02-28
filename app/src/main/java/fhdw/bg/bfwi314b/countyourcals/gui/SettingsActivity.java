package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Sina Sander
 */

public class SettingsActivity extends Activity {

    //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button buttonFactoryReset = (Button) findViewById(R.id.SettingsButtonFactoryReset);
        final Button buttonCredits = (Button) findViewById(R.id.SettingsButtonCredits);
        final Spinner spinnerLanguage = (Spinner) findViewById(R.id.SettingsLanguageSpinner);

        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Deutsch");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerArray); //set language list to language dropdown

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        //handle click event for factory reset button
        buttonFactoryReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DialogFactory dialogFactory = new DialogFactory(SettingsActivity.this);
                dialogFactory.BeSureDialog();//Create warning
            }
        });

        //on click on credits button credits dialog will be shown
        buttonCredits.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DialogFactory(SettingsActivity.this).CreateCreditsDialog();
            }
        });
    }

    //perform factory reset and finish activity
    public void factoryReset() {
        new DataStorageController(SettingsActivity.this).factoryReset(); //perform factory reset
        Intent intent = new Intent();
        setResult(1, intent);   //send resultcode 1 to signalize that the user is not logged in anymore to parent activity (MainActivity)
        finish();   //destroy this activity
    }

    //method is called when back button is pressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(2, intent);//send resultcode 2 to signalize that the user is not changed to parent activity (MainActivity)
        finish();//destroy this activity
        super.onBackPressed();
    }
}
