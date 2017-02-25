package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableFuture;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

public class MainActivity extends Activity {

    private DialogFactory dialogFactory;
    private DataStorageController controller;

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initializeActivity();
    }

    private void initializeActivity() {
        dialogFactory = new DialogFactory(this);
        controller = new DataStorageController(this);

        ButtonTextImage buttonFoodDetail = new ButtonTextImage(this, R.id.MainButtonFoodDetail, R.color.BayerGreen);
        buttonFoodDetail.setText(R.id.button_text, "Verwalten");
        buttonFoodDetail.setImageResource(R.id.button_image, R.drawable.manager);

        ButtonTextImage buttonDiary = new ButtonTextImage(this, R.id.MainButtonDiaryDetail, R.color.BayerBlue);
        buttonDiary.setText(R.id.button_text, "Tagebuch");
        buttonDiary.setImageResource(R.id.button_image, R.drawable.diary);
        //buttonDiary.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);

        ButtonTextImage buttonAccount = new ButtonTextImage(this, R.id.MainButtonAccountDetail, R.color.BayerBlue);
        buttonAccount.setText(R.id.button_text, "Profil");
        buttonAccount.setImageResource(R.id.button_image, R.drawable.account);
        //buttonAccount.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);

        ButtonTextImage buttonSettings = new ButtonTextImage(this, R.id.MainButtonSettingsDetail, R.color.BayerGreen);
        buttonSettings.setText(R.id.button_text, "Einstellungen");
        buttonSettings.setImageResource(R.id.button_image, R.drawable.settings);
        //buttonSettings.setBackgroundColor(R.id.Button_Layout ,R.color.colorBayerGreen);


        final Button buttonNewEntry = (Button) findViewById(R.id.MainButtonNewEntry);

        buttonFoodDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else
                    dialogFactory.CreateLoginDialog();
            }
        });

        buttonDiary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else
                    dialogFactory.CreateLoginDialog();
            }
        });

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                    intent.putExtra("user", user);
                    startActivityForResult(intent, 1);
                } else
                    dialogFactory.CreateLoginDialog();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivityForResult(intent, 2);
                } else
                    dialogFactory.CreateLoginDialog();
            }
        });

        buttonNewEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (user != null) {
                    dialogFactory.CreateNewDiaryEntryDialog(user);
                } else
                    dialogFactory.CreateLoginDialog();
            }
        });
        if (user == null) dialogFactory.CreateLoginDialog();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if(user != null)
        {
            savedInstanceState.putSerializable("user", user);
            savedInstanceState.putBoolean("getUser", true);
        }
        else
        {
            savedInstanceState.putBoolean("getUser", false);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState.getBoolean("getUser")) {
            user = (User) savedInstanceState.getSerializable("user");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1) {
            if (resultCode == 1) {
                user = null;
            } else if (resultCode == 2) {
                user = (User) data.getSerializableExtra("user");
            }
        }
        else if(requestCode == 2){
            if (resultCode == 1) {
                user = null;
            } else if (resultCode == 2) {
            }
        }
    }
}
