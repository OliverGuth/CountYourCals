package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.User;

public class AccountActivity extends Activity {

    private User user;
    private DialogFactory dialogFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user = new User("Oliver", 'M', 2500, "Deutsch");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        dialogFactory = new DialogFactory(this);
        final Button buttonLogout = (Button) findViewById(R.id.AccountButtonLogout);
        buttonLogout.setBackgroundDrawable(getResources().getDrawable(R.color.BayerGreen));

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialogFactory.CreateLoginDialog(AccountActivity.this);

        }});
    }
}
