package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Bundle;

/**
 * Created by Sina Sander
 */

public class SplashActivity extends Activity {

    //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(1500); //wait for 1,5 seconds before calling the MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent); //Call the MainActivity
        finish();//destroy this activity
    }
}
