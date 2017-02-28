package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;

/**
 * Created by Sina Sander
 */

public class MainActivity extends Activity {

    private DialogFactory dialogFactory;

    public User user;

    //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //onResume() get's called on start of the activity after the onCreate Method. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onResume() {
        super.onResume();
        initializeActivity(); //initialize all elements of gui
    }

    //initialize all elements of gui
    private void initializeActivity() {
        dialogFactory = new DialogFactory(this);

        //set Buttons with background, text and image
        ButtonTextImage buttonFoodDetail = new ButtonTextImage(this, R.id.MainButtonFoodDetail, R.color.BayerGreen);
        buttonFoodDetail.setText(R.id.button_text, "Verwalten");
        buttonFoodDetail.setImageResource(R.id.button_image, R.drawable.manager);

        ButtonTextImage buttonDiary = new ButtonTextImage(this, R.id.MainButtonDiaryDetail, R.color.BayerBlue);
        buttonDiary.setText(R.id.button_text, "Tagebuch");
        buttonDiary.setImageResource(R.id.button_image, R.drawable.diary);

        ButtonTextImage buttonAccount = new ButtonTextImage(this, R.id.MainButtonAccountDetail, R.color.BayerBlue);
        buttonAccount.setText(R.id.button_text, "Profil");
        buttonAccount.setImageResource(R.id.button_image, R.drawable.account);

        ButtonTextImage buttonSettings = new ButtonTextImage(this, R.id.MainButtonSettingsDetail, R.color.BayerGreen);
        buttonSettings.setText(R.id.button_text, "Einstellungen");
        buttonSettings.setImageResource(R.id.button_image, R.drawable.settings);

        final Button buttonNewEntry = (Button) findViewById(R.id.MainButtonNewEntry);

        //set click event handler for all buttons in view
        buttonFoodDetail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (user != null) {
                    //if user is logged in start the manager activity and provide the logged in user via the intent
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
                    //if user is logged in start the diary activity and provide the logged in user via the intent
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
                    //if user is logged in start the account activity and provide the logged in user via the intent. This call should result return value after child activity is destroyed completely
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
                    //if user is logged in start the settings activity via the intent. This call should result return value after child activity is destroyed completely
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivityForResult(intent, 2);
                } else
                    dialogFactory.CreateLoginDialog();
            }
        });

        //create new diary entry dialog if button is pressed
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

    //this method will be called if the activity instance will be destroyed and recreated in landscape mode. Some values need to be saved and reloaded in the new activity instance. Here they will be saved.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (user != null) {
            savedInstanceState.putSerializable("user", user);
            savedInstanceState.putBoolean("getUser", true);
        } else {
            savedInstanceState.putBoolean("getUser", false);
        }
    }

    //this method will be called if the activity instance will be recreated. Some values need to be reloaded in the new activity instance. Here they will be loaded.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getBoolean("getUser")) {
            user = (User) savedInstanceState.getSerializable("user");
        }
    }

    //if the activities which were called with return value return a value it will be processed here
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //requestcode 1 = Account activity finished
        if (requestCode == 1) {
            if (resultCode == 1) {
                user = null;//user is logged out
            } else if (resultCode == 2) {
                user = (User) data.getSerializableExtra("user"); //user was changed but stays logged in
            }
            //requestcode 2 = Settings activity finished
        } else if (requestCode == 2) {
            if (resultCode == 1) {
                user = null; //user is logged out
            } else if (resultCode == 2) {
                //do nothing
            }
        }
    }
}
