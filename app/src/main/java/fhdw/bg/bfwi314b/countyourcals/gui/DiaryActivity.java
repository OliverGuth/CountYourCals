package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;

import static fhdw.bg.bfwi314b.countyourcals.gui.DiaryState.*;

public class DiaryActivity extends Activity {

    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    public List<DiaryEntry> entries;
    private List<DiaryEntry> shownEntries;
    private List<Food> foods;
    private List<Meal> meals;
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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initializeActivity();
    }

    private void initializeActivity()
    {
        rowFactory = new RowFactory(this);
        dialogFactory = new DialogFactory(this);

        user = (User) this.getIntent().getSerializableExtra("user");

        Button newEntry = (Button)findViewById(R.id.DiaryButtonNewEntry);
        LinearLayout maxDiff = (LinearLayout) findViewById(R.id.DiaryMaxDiffLine);
        day = (Button) findViewById(R.id.DiaryButtonDays);
        week = (Button) findViewById(R.id.DiaryButtonWeeks);
        month = (Button) findViewById(R.id.DiaryButtonMonths);
        all = (Button) findViewById(R.id.DiaryButtonAll);
        table = (TableLayout) findViewById(R.id.DiaryTableLayout);
        next = (Button) findViewById(R.id.DiaryRightArrowButton);
        before = (Button) findViewById(R.id.DiaryLeftArrowButton);
        Button statistics = (Button) findViewById(R.id.DiaryButtonMoreStatistics);
        sum = (TextView)findViewById(R.id.DiarySumValue);
        maxText = (TextView)findViewById(R.id.DiaryMaxText);
        maxValue = (TextView)findViewById(R.id.DiaryMaxValue);

        if (state == null)state = DayState;
        day.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = DayState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        week.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = WeekState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        month.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = MonthState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = AllState;
                updateView();
            }});
        before.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                switch(state)
                {
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
                updateView();
            }});
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                switch(state)
                {
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
                updateView();
            }});

        statistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, StatisticsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }});

        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                dialogFactory.CreateNewDiaryEntryDialog(user);
            }});

        maxDiff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(maxText.getText().equals("Max:")) maxText.setText("Diff.:");
                else if(maxText.getText().equals("Diff.:")) maxText.setText("Max:");
                updateSumMaxFields();
            }});

        selectedDate = Calendar.getInstance().getTime();
        updateView();
    }

    public void updateView()
    {
        TextView date = (TextView) findViewById(R.id.DiaryTextDate);
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(selectedDate);
        Calendar cal = Calendar.getInstance();
        int currentWeek;
        int currentMonth;
        int currentYear;

        shownEntries.clear();
        switch(state)
        {
            case DayState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue);
                ArrowsVisible(true);
                maxDiffVisible(true);
                date.setText(timeStamp);
                rowFactory.FillDiaryTableLayout(table, state, selectedDate, user, DiaryActivity.this);
            break;
            case WeekState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                ArrowsVisible(true);
                maxDiffVisible(false);
                cal.setTime(selectedDate);
                currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
                currentYear = cal.get(Calendar.YEAR);
                date.setText("KW "+ currentWeek + " " + currentYear);
                rowFactory.FillDiaryTableLayout(table, state, selectedDate, user, DiaryActivity.this);
                break;
            case MonthState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                ArrowsVisible(true);
                maxDiffVisible(false);
                cal.setTime(selectedDate);
                currentMonth = cal.get(Calendar.MONTH);
                currentYear = cal.get(Calendar.YEAR);
                date.setText(new SimpleDateFormat("MMMM").format(selectedDate) + " " + currentYear);
                rowFactory.FillDiaryTableLayout(table, state, selectedDate, user, DiaryActivity.this);
                break;
            case AllState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                ArrowsVisible(false);
                maxDiffVisible(false);
                shownEntries.addAll(entries);
                date.setText("");
                rowFactory.FillDiaryTableLayout(table, state, selectedDate, user, DiaryActivity.this);
                break;
            }
            updateSumMaxFields();
        }

    private void updateSumMaxFields()
    {
        int i = 0;
        int max = 0;

        for(DiaryEntry entry: shownEntries) i = i + entry.getConsumedKCal();
        sum.setText(i + " kcal");

        if (maxText.getText().equals("Max:")) maxValue.setText(user.getMaxKCal() + " kcal");
        else if (maxText.getText().equals("Diff.:")) maxValue.setText((user.getMaxKCal() - i) + " kcal");
    }

    private void ArrowsVisible(boolean visible)
    {
        if(visible)
        {
            before.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
        else
        {
            before.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
        }
    }

    private void maxDiffVisible(boolean visible)
    {
        if(visible)
        {
            maxText.setVisibility(View.VISIBLE);
            maxValue.setVisibility(View.VISIBLE);
        }
        else
        {
            maxText.setVisibility(View.INVISIBLE);
            maxValue.setVisibility(View.INVISIBLE);
        }
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
        state = (DiaryState) savedInstanceState.getSerializable("state");
    }

    private void highlightState(int color1, int color2, int color3, int color4)
    {
        day.setBackgroundColor(DiaryActivity.this.getResources().getColor(color1));
        week.setBackgroundColor(DiaryActivity.this.getResources().getColor(color2));
        month.setBackgroundColor(DiaryActivity.this.getResources().getColor(color3));
        all.setBackgroundColor(DiaryActivity.this.getResources().getColor(color4));
    }
}
