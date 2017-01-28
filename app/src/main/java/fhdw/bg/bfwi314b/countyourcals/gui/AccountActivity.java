package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.datastorage.User;

public class AccountActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user = new User("Oliver", 'M', 2500);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        final Button buttonLogout = (Button) findViewById(R.id.AccountButtonLogout);
        buttonLogout.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(AccountActivity.this);
                View view = getLayoutInflater().inflate(R.layout.login_dialog, null);
                final EditText loginUserName = (EditText) view.findViewById(R.id.LoginNameValue);
                Button login = (Button) view.findViewById(R.id.LoginButton);

                login.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if(loginUserName.getText().equals("Oliver"))
                            Toast.makeText(AccountActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(AccountActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogBuilder.setView(view);
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
        }});
    }
}
