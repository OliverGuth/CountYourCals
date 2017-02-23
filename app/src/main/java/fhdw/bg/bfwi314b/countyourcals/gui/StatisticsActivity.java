package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

import static fhdw.bg.bfwi314b.countyourcals.gui.StatisticsState.*;

public class StatisticsActivity extends Activity {

    private DataStorageController controller;
    private StatisticsState state;
    private Button OneWeek;
    private Button TwoWeeks;
    private Button FourWeeks;
    private TextView averageCalories;
    private TextView highestCalories;
    private TextView lowestCalories;
    private TextView mostEaten;
    private TextView daysOverMax;
    private TextView date;
    private User user;

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
        controller = new DataStorageController(StatisticsActivity.this);

        OneWeek = (Button) findViewById(R.id.StatisticsOneWeekButton);
        TwoWeeks = (Button) findViewById(R.id.StatisticsTwoWeeksButton);
        FourWeeks = (Button) findViewById(R.id.StatisticsFourWeeksButton);
        averageCalories = (TextView) findViewById(R.id.StatisticsAverageCaloriesValue);
        highestCalories = (TextView) findViewById(R.id.StatisticsEntryHighestCaloriesValue);
        lowestCalories = (TextView) findViewById(R.id.StatisticsEntryLowestCaloriesValue);
        daysOverMax = (TextView) findViewById(R.id.StatisticsDaysOverMaxValue);

        date = (TextView) findViewById(R.id.StatisticsDate);

        if (state == null)state = OneWeekState;
        if(user == null) user = (User)this.getIntent().getSerializableExtra("user");

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
        int caloriesSum = 0;
        DiaryEntry caloriesMax = null;
        DiaryEntry caloriesMin = null;
        int entryCount = 0;
        Date timeFrameStart = null;
        Date timeFrameEnd = Calendar.getInstance().getTime();
        Date entryDate;
        List<DiaryEntry> entries = controller.getDiaryEntryList(user);
        Calendar cal = Calendar.getInstance();

        switch (state)
        {
            case OneWeekState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                cal.add(Calendar.WEEK_OF_YEAR, -1);
                setTimeToBeginningOfDay(cal);
                timeFrameStart = cal.getTime();
                break;
            case TwoWeeksState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                cal.add(Calendar.WEEK_OF_YEAR, -1);
                setTimeToBeginningOfDay(cal);
                timeFrameStart = cal.getTime();
                break;
            case FourWeeksState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                cal.add(Calendar.WEEK_OF_YEAR, -1);
                setTimeToBeginningOfDay(cal);
                timeFrameStart = cal.getTime();
                break;
        }

        for (DiaryEntry entry: entries)
        {
            if(entry.getTimeStamp().compareTo(timeFrameStart) >= 0 && entry.getTimeStamp().compareTo(timeFrameEnd) <= 0)
            {
                if(caloriesMax != null)
                {
                    if(caloriesMax.getConsumedKCal() < entry.getConsumedKCal()) caloriesMax = entry;
                }
                else
                    caloriesMax = entry;

                if(caloriesMin != null)
                {
                    if(caloriesMin.getConsumedKCal() > entry.getConsumedKCal()) caloriesMin = entry;
                }
                else
                    caloriesMin = entry;


                caloriesSum = caloriesSum + entry.getConsumedKCal();
                entryCount++;
            }
        }
        if(highestCalories != null) highestCalories.setText(caloriesMax.toString());
        if(lowestCalories != null) lowestCalories.setText(caloriesMin.toString());
        if(averageCalories != null) averageCalories.setText((caloriesSum/entryCount) + " kcal");
        date.setText(new SimpleDateFormat("dd.MM.yyyy").format(timeFrameStart) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(timeFrameEnd));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("state", state);
        savedInstanceState.putSerializable("user", user);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        state = (StatisticsState) savedInstanceState.getSerializable("state");
        user = (User) savedInstanceState.getSerializable("user");
    }

    private static Calendar setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    private static Calendar setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    private void highlightState(int color1, int color2, int color3)
    {
        OneWeek.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color1));
        TwoWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color2));
        FourWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color3));
    }
}
