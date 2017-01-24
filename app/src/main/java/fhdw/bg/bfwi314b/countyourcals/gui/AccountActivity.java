package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fhdw.bg.bfwi314b.countyourcals.R;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        final Button buttonLogout = (Button) findViewById(R.id.AccountButtonLogout);
        buttonLogout.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                //startActivity(intent);
            }
        });
    }
}
