package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.R;

public class MainActivity extends Activity {

    private DialogFactory dialogFactory;
    public User user;
    private List<Food> foods;
    private List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogFactory = new DialogFactory();
        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();
        user = new User("Oliver", 'M', 2500, "Deutsch");

        //User holen

        ButtonTextImage buttonFoodDetail = new ButtonTextImage(this,R.id.MainButtonFoodDetail, R.color.BayerGreen);
        buttonFoodDetail.setText(R.id.button_text, "Verwalten");
        buttonFoodDetail.setImageResource(R.id.button_image, R.drawable.food);

        ButtonTextImage buttonDiary = new ButtonTextImage(this,R.id.MainButtonDiaryDetail, R.color.BayerBlue);
        buttonDiary.setText(R.id.button_text, "Tagebuch");
        buttonDiary.setImageResource(R.id.button_image, R.drawable.diary);
        //buttonDiary.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);

        ButtonTextImage buttonAccount = new ButtonTextImage(this,R.id.MainButtonAccountDetail, R.color.BayerBlue);
        buttonAccount.setText(R.id.button_text, "Profil");
        buttonAccount.setImageResource(R.id.button_image, R.drawable.account);
        //buttonAccount.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);

        ButtonTextImage buttonSettings = new ButtonTextImage(this,R.id.MainButtonSettingsDetail, R.color.BayerGreen);
        buttonSettings.setText(R.id.button_text, "Einstellungen");
        buttonSettings.setImageResource(R.id.button_image, R.drawable.settings);
        //buttonSettings.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);



        final Button buttonNewEntry = (Button) findViewById(R.id.MainButtonNewEntry);

        buttonFoodDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                startActivity(intent);
            }
        });

        buttonDiary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                //intent.putExtra("user", (Parcelable)user);
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
                buttonNewEntry.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialogFactory.CreateNewDiaryEntryDialog(MainActivity.this, foods, meals);
                    }});
            }
        });
    }
}
