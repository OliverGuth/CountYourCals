package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

import static android.R.attr.data;

public class AccountActivity extends Activity {

    public User user;
    private DialogFactory dialogFactory;

    private TextView name;
    private TextView gender;
    private EditText maxCals;
    private DataStorageController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        user = (User) this.getIntent().getSerializableExtra("user");
        dialogFactory = new DialogFactory(this);
        controller = new DataStorageController(AccountActivity.this);

        name = (TextView) findViewById(R.id.AccountNameValue);
        gender =  (TextView) findViewById(R.id.AccountGenderValue);
        maxCals = (EditText) findViewById(R.id.AccountMaxCalsValue);

        fillUserData();

        final Button buttonLogout = (Button) findViewById(R.id.AccountButtonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
        }});
    }

    public void fillUserData()
    {
        if(user != null)
        {
            name.setTag("workInProgress" );
            name.setText(user.getName());
            name.setTag(null);
            gender.setText(user.getGender().toString());
            maxCals.setText(user.getMaxKCal().toString());
        }
    }

    @Override
    public void onBackPressed() {
        user.setMaxKCal(Integer.parseInt(maxCals.getText().toString()));
        controller.editUser(user);
        Intent intent = new Intent();
        intent.putExtra("user", user);
        setResult(2, intent);
        finish();
        super.onBackPressed();
    }
}
