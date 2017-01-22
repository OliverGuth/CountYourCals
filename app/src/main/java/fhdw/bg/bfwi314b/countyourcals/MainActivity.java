package fhdw.bg.bfwi314b.countyourcals;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonFoodDetail = (Button) findViewById(R.id.MainButtonFoodDetail);
        final Button buttonDiary = (Button) findViewById(R.id.MainButtonDiary);
        final Button buttonAccount = (Button) findViewById(R.id.MainButtonAccount);
        final Button buttonSettings = (Button) findViewById(R.id.MainButtonSettings);
        final Button buttonNewEntry = (Button) findViewById(R.id.MainButtonNewEntry);

        buttonFoodDetail.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));
        buttonDiary.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerBlue));
        buttonAccount.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerBlue));
        buttonSettings.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));
        buttonNewEntry.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));

        buttonFoodDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                startActivity(intent);
            }
        });

        buttonDiary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonNewEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
