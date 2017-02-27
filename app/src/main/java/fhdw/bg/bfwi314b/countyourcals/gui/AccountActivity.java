package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Sina Sander
 */

public class AccountActivity extends Activity {

    public User user;

    private TextView name;
    private TextView gender;
    private EditText maxCals;
    private DataStorageController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);  //Assign layout to Activity

        user = (User) this.getIntent().getSerializableExtra("user");    //get user from the call of MainActivity
        controller = new DataStorageController(AccountActivity.this);   //DataStorageController is initialized to handle possible changes of the saved data.

        //Bind Layout-Elements to variables
        name = (TextView) findViewById(R.id.AccountNameValue);
        gender = (TextView) findViewById(R.id.AccountGenderValue);
        maxCals = (EditText) findViewById(R.id.AccountMaxCalsValue);
        Button buttonLogout = (Button) findViewById(R.id.AccountButtonLogout);

        fillUserData(); //personalize the necessary fields with the specific user data

        //Specify what happens if Button is clicked
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //hence the activity is expected to provide a result to its parent, the result is specified here and the activity will be destroyed after that.
                Intent intent = new Intent();
                setResult(1, intent);//Result Code "1" informes the parent activity that the user was logged out.
                finish();//Activity gets destroyed
            }
        });
    }

    //personalize the necessary fields with the specific user data
    public void fillUserData() {
        //user-object has to be not null
        if (user != null) {
            name.setText(user.getName());
            gender.setText(user.getGender().toString());
            maxCals.setText(user.getMaxKCal().toString());
        }
    }

    @Override
    public void onBackPressed() {   //If the Back-button is pressed, this method is called to handle the event
        //if changes were made, they will be saved
        user.setMaxKCal(Integer.parseInt(maxCals.getText().toString()));
        controller.editUser(user);
        Intent intent = new Intent();
        intent.putExtra("user", user);//the latest change to the user will be set as result with the result-code "2" to be handled in the parent activity.
        setResult(2, intent);
        finish(); //Activity gets destroyed

        super.onBackPressed();//standard back-button actions will be performed here
    }
}
