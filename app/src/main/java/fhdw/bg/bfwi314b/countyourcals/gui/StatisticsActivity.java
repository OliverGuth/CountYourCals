package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

import static fhdw.bg.bfwi314b.countyourcals.gui.StatisticsState.*;

/**
 * Created by Sina Sander
 */

public class StatisticsActivity extends Activity {

    private DataStorageController controller;
    private StatisticsState state;
    private Button OneWeek;
    private Button TwoWeeks;
    private Button FourWeeks;
    private TextView averageCalories;
    private TextView highestCalories;
    private TextView lowestCalories;
    private TextView date;
    private User user;

    //set time to beginning of the current day
    private static Calendar setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    //set time to end of current date
    private static Calendar setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    //onResume() get's called on start of the activity after the onCreate Method. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onResume() {
        super.onResume();

        initializeActivity();
    }

    //initialize all elements of gui
    private void initializeActivity() {
        controller = new DataStorageController(StatisticsActivity.this);
        OneWeek = (Button) findViewById(R.id.StatisticsOneWeekButton);
        TwoWeeks = (Button) findViewById(R.id.StatisticsTwoWeeksButton);
        FourWeeks = (Button) findViewById(R.id.StatisticsFourWeeksButton);
        averageCalories = (TextView) findViewById(R.id.StatisticsAverageCaloriesValue);
        highestCalories = (TextView) findViewById(R.id.StatisticsEntryHighestCaloriesValue);
        lowestCalories = (TextView) findViewById(R.id.StatisticsEntryLowestCaloriesValue);
        date = (TextView) findViewById(R.id.StatisticsDate);

        //state & user are only set to default if the activity was called by the parent activity and not when the device gets tilted
        if (state == null) state = OneWeekState;
        if (user == null) user = (User) this.getIntent().getSerializableExtra("user");

        //Set click handlers for the state buttons. If a button is clicked the state is changed and view elements will be updated
        OneWeek.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = OneWeekState;
                updateView();
            }
        });
        TwoWeeks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = TwoWeeksState;
                updateView();
            }
        });
        FourWeeks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = FourWeeksState;
                updateView();
            }
        });

        updateView(); //view will be updated to show the latest changes
    }

    //view will be updated to show the latest changes
    public void updateView() {
        int caloriesSum = 0;
        DiaryEntry caloriesMax = null;
        DiaryEntry caloriesMin = null;
        int entryCount = 0;
        Date timeFrameStart = null;
        Date timeFrameEnd = Calendar.getInstance().getTime();
        List<DiaryEntry> entries = controller.getDiaryEntryList(user); //get all entries from context
        Calendar cal = Calendar.getInstance();

        //according to which state is selected, highlight buttons and set the selected timeframe start
        switch (state) {
            case OneWeekState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                cal.add(Calendar.WEEK_OF_YEAR, -1);
                setTimeToBeginningOfDay(cal);
                timeFrameStart = cal.getTime();
                break;
            case TwoWeeksState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                cal.add(Calendar.WEEK_OF_YEAR, -2);
                setTimeToBeginningOfDay(cal);
                timeFrameStart = cal.getTime();
                break;
            case FourWeeksState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                cal.add(Calendar.WEEK_OF_YEAR, -4);
                setTimeToBeginningOfDay(cal);
                timeFrameStart = cal.getTime();
                break;
        }

        //calculate statistics
        for (DiaryEntry entry : entries) {
            //if entry is in the timeframe
            if (entry.getTimeStamp().compareTo(timeFrameStart) >= 0 && entry.getTimeStamp().compareTo(timeFrameEnd) <= 0) {
                //get the highest calories
                if (caloriesMax != null) {
                    if (caloriesMax.getConsumedKCal() < entry.getConsumedKCal())
                        caloriesMax = entry;
                } else
                    caloriesMax = entry;

                //get the lowest calories
                if (caloriesMin != null) {
                    if (caloriesMin.getConsumedKCal() > entry.getConsumedKCal())
                        caloriesMin = entry;
                } else
                    caloriesMin = entry;

                //calculate calories sum
                caloriesSum = caloriesSum + entry.getConsumedKCal();
                entryCount++;
            }
        }
        //write statistics into the fields in the user interface
        if (highestCalories != null) highestCalories.setText(caloriesMax.toString());
        if (lowestCalories != null) lowestCalories.setText(caloriesMin.toString());
        if (averageCalories != null) averageCalories.setText((caloriesSum / entryCount) + " kcal");
        //Write date into corresponding field
        date.setText(new SimpleDateFormat("dd.MM.yyyy").format(timeFrameStart) + " - " + new SimpleDateFormat("dd.MM.yyyy").format(timeFrameEnd));
    }

    //this method will be called if the activity instance will be recreated. Some values need to be reloaded in the new activity instance. Here they will be loaded.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("state", state);
        savedInstanceState.putSerializable("user", user);
    }

    //if the activities which were called with return value return a value it will be processed here
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = (StatisticsState) savedInstanceState.getSerializable("state");
        user = (User) savedInstanceState.getSerializable("user");
    }

    //changes colors of buttons in one place
    private void highlightState(int color1, int color2, int color3) {
        OneWeek.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color1));
        TwoWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color2));
        FourWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color3));
    }
}
