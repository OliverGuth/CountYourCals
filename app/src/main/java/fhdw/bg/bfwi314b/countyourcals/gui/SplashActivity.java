package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(1500);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
