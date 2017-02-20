package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;

import static fhdw.bg.bfwi314b.countyourcals.gui.StatisticsState.*;

public class StatisticsActivity extends Activity {

    private StatisticsState state;
    private Button OneWeek;
    private Button TwoWeeks;
    private Button FourWeeks;
    private TextView averageCalories;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    @Override
    protected void onResume() {
        super.onResume();

        initializeActivity();
    }

    private void initializeActivity()
    {
        OneWeek = (Button) findViewById(R.id.StatisticsOneWeekButton);
        TwoWeeks = (Button) findViewById(R.id.StatisticsTwoWeeksButton);
        FourWeeks = (Button) findViewById(R.id.StatisticsFourWeeksButton);
        averageCalories = (TextView) findViewById(R.id.StatisticsAverageCaloriesValue);
        date = (TextView) findViewById(R.id.StatisticsDate);

        if (state == null)state = OneWeekState;

        OneWeek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = OneWeekState;
                updateView();
            }});
        TwoWeeks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = TwoWeeksState;
                updateView();
            }});
        FourWeeks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = FourWeeksState;
                updateView();
            }});

        updateView();
    }

    public void updateView()
    {
        int calories = 0;
        int entryCount = 0;
        Date timeFrameStart;
        Date timeFrameEnd = Calendar.getInstance().getTime();
        Date entryDate;

        /*
        switch (state)
        {
            case OneWeekState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                timeFrameStart = calendar.getTime();

                for (DiaryEntry entry: entries)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendar.setTime(entry.getTimeStamp());
                    entryDate = calendar.getTime();

                    if((entryDate.getTime() > timeFrameStart.getTime()) && (entryDate.getTime() < timeFrameEnd.getTime()))
                    {
                        calories = calories + entry.getConsumedKCal();
                        entryCount++;
                    }
                }
                averageCalories.setText((calories/entryCount) + " kcal");
                date.setText(new SimpleDateFormat("dd.MM.yyyy").format(timeFrameStart) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(timeFrameEnd));
                break;
            case TwoWeeksState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);

                calendar = Calendar.getInstance();
                calendar.add(Calendar.WEEK_OF_YEAR, -2);
                timeFrameStart = calendar.getTime();

                for (DiaryEntry entry: entries)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendar.setTime(entry.getTimeStamp());
                    entryDate = calendar.getTime();

                    if((entryDate.getTime() > timeFrameStart.getTime()) && (entryDate.getTime() < timeFrameEnd.getTime()))
                    {
                        calories = calories + entry.getConsumedKCal();
                        entryCount++;
                    }
                }
                averageCalories.setText((calories/entryCount) + " kcal");
                date.setText(new SimpleDateFormat("dd.MM.yyyy").format(timeFrameStart) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(timeFrameEnd));
                break;
            case FourWeeksState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);

                calendar = Calendar.getInstance();
                calendar.add(Calendar.WEEK_OF_YEAR, -4);
                timeFrameStart = calendar.getTime();

                for (DiaryEntry entry: entries)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendar.setTime(entry.getTimeStamp());
                    entryDate = calendar.getTime();

                    if((entryDate.getTime() > timeFrameStart.getTime()) && (entryDate.getTime() < timeFrameEnd.getTime()))
                    {
                        calories = calories + entry.getConsumedKCal();
                        entryCount++;
                    }
                }

                averageCalories.setText((calories/entryCount) + " kcal");
                date.setText(new SimpleDateFormat("dd.MM.yyyy").format(timeFrameStart) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(timeFrameEnd));
                break;
        }
        */
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("state", state);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        state = (StatisticsState) savedInstanceState.getSerializable("state");
    }

    private void highlightState(int color1, int color2, int color3)
    {
        OneWeek.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color1));
        TwoWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color2));
        FourWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color3));
    }
}
