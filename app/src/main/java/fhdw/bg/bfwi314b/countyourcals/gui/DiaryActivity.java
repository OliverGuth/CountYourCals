package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

import static fhdw.bg.bfwi314b.countyourcals.gui.DiaryState.*;

/**
 * Created by Katja Müller
 */

public class DiaryActivity extends Activity {

    private DataStorageController controller;
    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    private User user;
    private DiaryState state;
    private Date selectedDate;
    private Button day;
    private Button week;
    private Button month;
    private Button all;
    private Button next;
    private Button before;
    private TextView sum;
    private TextView maxText;
    private TextView maxValue;
    private TableLayout table;

    //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
    }

    //onResume() get's called on start of the activity after the onCreate Method. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onResume() {
        super.onResume();
        initializeActivity(); //initialize all elements of gui
    }

    //initialize all elements of gui
    private void initializeActivity() {
        rowFactory = new RowFactory(this);
        dialogFactory = new DialogFactory(this);
        controller = new DataStorageController(DiaryActivity.this);
        Button newEntry = (Button) findViewById(R.id.DiaryButtonNewEntry);
        LinearLayout maxDiff = (LinearLayout) findViewById(R.id.DiaryMaxDiffLine);
        day = (Button) findViewById(R.id.DiaryButtonDays);
        week = (Button) findViewById(R.id.DiaryButtonWeeks);
        month = (Button) findViewById(R.id.DiaryButtonMonths);
        all = (Button) findViewById(R.id.DiaryButtonAll);
        table = (TableLayout) findViewById(R.id.DiaryTableLayout);
        next = (Button) findViewById(R.id.DiaryRightArrowButton);
        before = (Button) findViewById(R.id.DiaryLeftArrowButton);
        Button statistics = (Button) findViewById(R.id.DiaryButtonMoreStatistics);
        sum = (TextView) findViewById(R.id.DiarySumValue);
        maxText = (TextView) findViewById(R.id.DiaryMaxText);
        maxValue = (TextView) findViewById(R.id.DiaryMaxValue);

        //get user object from main activity intent
        user = (User) this.getIntent().getSerializableExtra("user");

        //state is only set to default if the activity was called by main Activity and not when the device gets tilted
        if (state == null) state = DayState;

        //Set click handlers for the state buttons. If a button is clicked the state is changed, the date will be set to today and all other view elements will be updated
        day.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = DayState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }
        });
        week.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = WeekState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = MonthState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = AllState;
                updateView();
            }
        });

        //Set click listeners for back and next buttons
        before.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //According to active state the appropriate amount of days will be subtracted off the selected date.
                Calendar calendar = Calendar.getInstance();
                switch (state) {
                    case DayState:
                        calendar.setTime(selectedDate);
                        calendar.add(Calendar.DATE, -1);
                        selectedDate = calendar.getTime();
                        break;
                    case WeekState:
                        calendar.setTime(selectedDate);
                        calendar.add(Calendar.DATE, -7);
                        selectedDate = calendar.getTime();
                        break;
                    case MonthState:
                        calendar.setTime(selectedDate);
                        calendar.add(Calendar.DATE, -30);
                        selectedDate = calendar.getTime();
                        break;
                    case AllState:
                        //nothing to do
                        break;
                }
                updateView();// view will be updated to show the latest changes
            }
        });

        //Set click listeners for back and next buttons
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //According to active state the appropriate amount of days will be added to the selected date.
                Calendar calendar = Calendar.getInstance();
                switch (state) {
                    case DayState:
                        calendar.setTime(selectedDate);
                        calendar.add(Calendar.DATE, 1);
                        selectedDate = calendar.getTime();
                        break;
                    case WeekState:
                        calendar.setTime(selectedDate);
                        calendar.add(Calendar.DATE, 7);
                        selectedDate = calendar.getTime();
                        break;
                    case MonthState:
                        calendar.setTime(selectedDate);
                        calendar.add(Calendar.DATE, 30);
                        selectedDate = calendar.getTime();
                        break;
                    case AllState:
                        //Nothing to do
                        break;
                }
                updateView();// view will be updated to show the latest changes
            }
        });

        //statistics activity will be called on click if diary entries exist.
        statistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<DiaryEntry> entries = controller.getDiaryEntryList(user);
                if (entries != null) {
                    if (entries.size() > 0) {
                        Intent intent = new Intent(DiaryActivity.this, StatisticsActivity.class);
                        intent.putExtra("user", user);  //user object will be passed to the statistics activity
                        startActivity(intent);
                    }
                    Toast.makeText(DiaryActivity.this, "Kein Tagebucheintrag vorhanden", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(DiaryActivity.this, "Kein Tagebucheintrag vorhanden", Toast.LENGTH_LONG).show();
            }
        });

        //Create dialog on click to add new diary entry to diary
        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogFactory.CreateNewDiaryEntryDialog(user);
            }
        });

        //change the value and text from max calories per day to whats left for the day
        maxDiff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (maxText.getText().equals("Max:")) maxText.setText("Diff.:");
                else if (maxText.getText().equals("Diff.:")) maxText.setText("Max:");
                updateSumMaxFields(); //update the values according to the text set above
            }
        });

        //date is only set to default if the activity was called by main Activity and not when the device gets tilted
        if (selectedDate == null) selectedDate = Calendar.getInstance().getTime();
        updateView(); //view will be updated
    }

    //according to the selected state the view will be set to meet the needs und to show the matching data
    public void updateView() {
        TextView date = (TextView) findViewById(R.id.DiaryTextDate);
        Calendar cal = Calendar.getInstance();
        Date start;
        Date end;

        switch (state) {
            case DayState:
                //set button colors, make the arrows & Max/Diff fields visible, write the date and fill the table layout
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue);
                ArrowsVisible(true);
                maxDiffVisible(true);
                date.setText(new SimpleDateFormat("dd.MM.yyyy").format(selectedDate));
                cal.setTime(selectedDate);
                rowFactory.FillDiaryTableLayout(table, state, setTimeToBeginningOfDay(cal).getTime(), setTimeToEndofDay(cal).getTime(), user);
                break;
            case WeekState:
                //set button colors, make the arrows visible and the Max/Diff fields invisible, write the date and fill the table layout
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                ArrowsVisible(true);
                maxDiffVisible(false);
                cal.setTime(selectedDate);
                date.setText("KW " + cal.get(Calendar.WEEK_OF_YEAR) + " " + cal.get(Calendar.YEAR));

                //calculate start and end date for the diary entries shown
                cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                setTimeToBeginningOfDay(cal);
                start = cal.getTime();
                cal.add(Calendar.WEEK_OF_YEAR, 1);
                cal.add(Calendar.DATE, -1);
                setTimeToEndofDay(cal);
                end = cal.getTime();

                rowFactory.FillDiaryTableLayout(table, state, start, end, user);
                break;
            case MonthState:
                //set button colors, make the arrows visible and the Max/Diff fields invisible, write the date and fill the table layout
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                ArrowsVisible(true);
                maxDiffVisible(false);
                cal.setTime(selectedDate);
                date.setText(new SimpleDateFormat("MMMM").format(selectedDate) + " " + cal.get(Calendar.YEAR));

                //calculate start and end date for the diary entries shown
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
                setTimeToBeginningOfDay(cal);
                start = cal.getTime();
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                setTimeToEndofDay(cal);
                end = cal.getTime();

                rowFactory.FillDiaryTableLayout(table, state, start, end, user);
                break;
            case AllState:
                //set button colors, make the arrows & Max/Diff fields invisible, erase the date and fill the table layout
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                ArrowsVisible(false);
                maxDiffVisible(false);
                date.setText("");

                //set start and end date for the diary entries shown to min/max
                cal.set(Calendar.YEAR, cal.getActualMinimum(Calendar.YEAR));
                setTimeToBeginningOfDay(cal);
                start = cal.getTime();
                cal.set(Calendar.YEAR, cal.getActualMaximum(Calendar.YEAR));
                setTimeToEndofDay(cal);
                end = cal.getTime();

                rowFactory.FillDiaryTableLayout(table, state, start, end, user);
                break;
        }
        updateSumMaxFields(); //update the values according to the text set above
    }

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

    //update sum/max fields according to what needs to shown
    private void updateSumMaxFields() {
        int cals = 0;

        if (table.getChildCount() > 0) {
            //calculate calories sum of all shown entries
            for (int i = 0; i < table.getChildCount(); i++) {
                String s = ((TextView) ((TableRow) table.getChildAt(i)).getChildAt(2)).getText().toString().split(" ")[0];
                cals = cals + Integer.parseInt(s);
            }
            sum.setText(cals + " kcal");//show calculated sum

            //calculate and/or show the max calories per day or whats left for the day
            if (maxText.getText().equals("Max:")) maxValue.setText(user.getMaxKCal() + " kcal");
            else if (maxText.getText().equals("Diff.:"))
                maxValue.setText((user.getMaxKCal() - cals) + " kcal");
        } else {
            sum.setText(0 + " kcal"); //if no entry is shown, sum will be set to zero
            maxValue.setText(user.getMaxKCal() + " kcal");
        }
    }

    //makes arrows visible or not
    private void ArrowsVisible(boolean visible) {
        if (visible) {
            before.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        } else {
            before.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
        }
    }

    //makes sum/max fields visible or not
    private void maxDiffVisible(boolean visible) {
        if (visible) {
            maxText.setVisibility(View.VISIBLE);
            maxValue.setVisibility(View.VISIBLE);
        } else {
            maxText.setVisibility(View.INVISIBLE);
            maxValue.setVisibility(View.INVISIBLE);
        }
    }

    //this method will be called if the activity instance will be destroyed and recreated in landscape mode. Some values need to be saved and reloaded in the new activity instance. Here they will be saved.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("state", state);
        savedInstanceState.putString("date", new SimpleDateFormat("dd.MM.yyyy").format(selectedDate));
    }

    //this method will be called if the activity instance will be recreated. Some values need to be reloaded in the new activity instance. Here they will be loaded.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = (DiaryState) savedInstanceState.getSerializable("state");
        try {
            selectedDate = new SimpleDateFormat("dd.MM.yyyy").parse(savedInstanceState.getString("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //changes colors of buttons in one place
    private void highlightState(int color1, int color2, int color3, int color4) {
        day.setBackgroundColor(DiaryActivity.this.getResources().getColor(color1));
        week.setBackgroundColor(DiaryActivity.this.getResources().getColor(color2));
        month.setBackgroundColor(DiaryActivity.this.getResources().getColor(color3));
        all.setBackgroundColor(DiaryActivity.this.getResources().getColor(color4));
    }
}
