package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;

import fhdw.bg.bfwi314b.countyourcals.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonTextImage buttonFoodDetail = new ButtonTextImage(this,R.id.MainButtonFoodDetail, R.color.colorBayerGreen);
        buttonFoodDetail.setText(R.id.button_text, "Verwalten");
        buttonFoodDetail.setImageResource(R.id.button_image, R.drawable.food);
        buttonFoodDetail.setBackgroundColor(R.id.MainButtonFoodDetail ,R.color.colorBayerGreen);

        ButtonTextImage buttonDiary = new ButtonTextImage(this,R.id.MainButtonDiaryDetail, R.color.colorBayerBlue);
        buttonDiary.setText(R.id.button_text, "Tagebuch");
        buttonDiary.setImageResource(R.id.button_image, R.drawable.diary);
        //buttonDiary.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);

        ButtonTextImage buttonAccount = new ButtonTextImage(this,R.id.MainButtonAccountDetail, R.color.colorBayerBlue);
        buttonAccount.setText(R.id.button_text, "Profil");
        buttonAccount.setImageResource(R.id.button_image, R.drawable.account);
        //buttonAccount.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);

        ButtonTextImage buttonSettings = new ButtonTextImage(this,R.id.MainButtonSettingsDetail, R.color.colorBayerGreen);
        buttonSettings.setText(R.id.button_text, "Einstellungen");
        buttonSettings.setImageResource(R.id.button_image, R.drawable.settings);
        //buttonSettings.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);










        final Button buttonNewEntry = (Button) findViewById(R.id.MainButtonNewEntry);

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
