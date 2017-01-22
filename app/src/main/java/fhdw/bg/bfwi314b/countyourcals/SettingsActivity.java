package fhdw.bg.bfwi314b.countyourcals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button buttonFactoryReset = (Button) findViewById(R.id.SettingsButtonFactoryReset);
        final Spinner spinnerLanguage = (Spinner) findViewById(R.id.SettingsLanguageSpinner);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Deutsch");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        buttonFactoryReset.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));

        buttonFactoryReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                //startActivity(intent);
            }
        });
    }
}
