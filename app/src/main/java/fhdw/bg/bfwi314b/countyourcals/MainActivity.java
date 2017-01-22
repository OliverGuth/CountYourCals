package fhdw.bg.bfwi314b.countyourcals;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.Button1);
        final Button button2 = (Button) findViewById(R.id.Button2);
        final Button button3 = (Button) findViewById(R.id.Button3);
        final Button button4 = (Button) findViewById(R.id.Button4);

        button1.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));
        button2.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerBlue));
        button3.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerBlue));
        button4.setBackgroundDrawable(getResources().getDrawable(R.color.colorBayerGreen));

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
