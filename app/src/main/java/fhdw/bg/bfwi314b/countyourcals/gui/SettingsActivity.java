package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.R;


public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button buttonFactoryReset = (Button) findViewById(R.id.SettingsButtonFactoryReset);
        final Spinner spinnerLanguage = (Spinner) findViewById(R.id.SettingsLanguageSpinner);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Deutsch");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);


        buttonFactoryReset.setBackgroundDrawable(getResources().getDrawable(R.color.BayerGreen));

        buttonFactoryReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                //startActivity(intent);
            }
        });
    }
}
