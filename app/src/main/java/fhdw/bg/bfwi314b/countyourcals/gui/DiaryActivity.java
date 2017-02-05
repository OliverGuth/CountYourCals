package fhdw.bg.bfwi314b.countyourcals.gui;

import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;

import static fhdw.bg.bfwi314b.countyourcals.gui.DiaryState.*;

public class DiaryActivity extends Activity {

    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    private List<DiaryEntry> entries;
    private List<DiaryEntry> shwownEntries;
    private List<Food> foods;
    private List<Meal> meals;
    private User user;
    private DiaryState diaryState;
    private Date selectedDate;
    private Button day;
    private Button week;
    private Button month;
    private Button all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        rowFactory = new RowFactory();
        dialogFactory = new DialogFactory();

        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();

        day = (Button) findViewById(R.id.DiaryButtonDays);
        week = (Button) findViewById(R.id.DiaryButtonWeeks);
        month = (Button) findViewById(R.id.DiaryButtonMonths);
        all = (Button) findViewById(R.id.DiaryButtonAll);
        final Button next = (Button) findViewById(R.id.DiaryRightArrowButton);
        final Button before = (Button) findViewById(R.id.DiaryLeftArrowButton);

        diaryState = DayState;
        day.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diaryState = DayState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        week.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diaryState = WeekState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        month.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diaryState = MonthState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        all.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diaryState = AllState;
                selectedDate = Calendar.getInstance().getTime();
                updateView();
            }});
        before.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                switch(diaryState)
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
                switch(diaryState)
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

        //Bundle bundle = getIntent().getExtras();
        //user = bundle.getParcelable("user");
        user = new User("Oliver", 'M', 2500, "Deutsch");

        Button newEntry = (Button)findViewById(R.id.DiaryButtonNewEntry);
        LinearLayout maxDiff = (LinearLayout) findViewById(R.id.DiaryMaxDiffLine);
        final TextView maxText = (TextView)findViewById(R.id.DiaryMaxText);

        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { dialogFactory.CreateNewDiaryEntryDialog(DiaryActivity.this, foods, meals); }});


        maxDiff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(maxText.getText().equals("Max:")) maxText.setText("Diff.:");
                else if(maxText.getText().equals("Diff.:")) maxText.setText("Max:");
                updateSumMaxFields();
            }});


        entries = new ArrayList<DiaryEntry>();
        shwownEntries = new ArrayList<DiaryEntry>();

        entries.add(new DiaryEntry("04.02.2017", "Wrap", 100, "Gramm", 200, 1));
        entries.add(new DiaryEntry("05.02.2017", "mein Gericht", 100, "Gramm", 200, 2));
        entries.add(new DiaryEntry("05.02.2017", "Pommes", 100, "Gramm", 500, 3));
        entries.add(new DiaryEntry("05.02.2017", "Pizza", 100, "Gramm", 723, 4));
        entries.add(new DiaryEntry("05.02.2017", "Salat", 100, "Gramm", 100, 5));
        entries.add(new DiaryEntry("06.02.2017", "Wrap", 100, "Gramm", 200, 6));
        entries.add(new DiaryEntry("06.02.2017", "Brot", 100, "Gramm", 500, 7));
        entries.add(new DiaryEntry("07.02.2017", "Kaffee", 1, "Tasse", 200, 8));
        entries.add(new DiaryEntry("08.02.2017", "Salat", 100, "Gramm", 100, 9));

/*
        foods = new ArrayList<String>();

        foods.add(new Food("Pommes mit Schnitzel", 200, "Gramm", 400).getName());
        foods.add(new Food("Hünersuppe", 100, "Milliliter", 200).getName());
        foods.add(new Food("Halver Hahn", 1, "Halbes Brötchen", 200).getName());

        //foods

        final Spinner foodSpinner = (Spinner) findViewById(R.id.DialogNewEntryFoodSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, foods);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(adapter);
*/
        selectedDate = Calendar.getInstance().getTime();
        updateView();
    }
        public void updateView()
        {
            TextView date = (TextView) findViewById(R.id.DiaryTextDate);
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(selectedDate);
            Calendar cal = Calendar.getInstance();

            shwownEntries.clear();

            switch(diaryState)
            {
                case DayState:
                    highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue);
                    for (DiaryEntry entry:entries) { if(timeStamp.equals(entry.getTimeStamp())) shwownEntries.add(entry); }
                    date.setText(timeStamp);
                break;
                case WeekState:
                    highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);

                    cal.setTime(selectedDate);
                    int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);

                    for (DiaryEntry entry:entries)
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        try {
                            cal.setTime(sdf.parse(entry.getTimeStamp()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        int entryWeek = cal.get(Calendar.WEEK_OF_YEAR);
                        if(currentWeek == entryWeek) shwownEntries.add(entry);
                    }

                    date.setText("KW "+ currentWeek);
                break;
                case MonthState:
                    highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);

                    cal.setTime(selectedDate);
                    int currentMonth = cal.get(Calendar.MONTH);

                    for (DiaryEntry entry:entries)
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        try {
                            cal.setTime(sdf.parse(entry.getTimeStamp()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        int entrymonth = cal.get(Calendar.MONTH);
                        if(currentMonth == entrymonth) shwownEntries.add(entry);
                    }
                    date.setText(new SimpleDateFormat("MMMM").format(selectedDate));
                break;
                case AllState:
                    highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                    shwownEntries.addAll(entries);
                    date.setText("");
                break;
            }
            rowFactory.FillDiaryTableLayout((TableLayout)this.findViewById(R.id.DiaryTableLayout), shwownEntries, DiaryActivity.this);
            updateSumMaxFields();
        }
    private void updateSumMaxFields()
    {
        int i = 0;
        TextView sum = (TextView)findViewById(R.id.DiarySumValue);
        TextView maxText = (TextView)findViewById(R.id.DiaryMaxText);
        TextView maxValue = (TextView)findViewById(R.id.DiaryMaxValue);

        for(DiaryEntry entry: shwownEntries) i = i + entry.getConsumedKCal();
        sum.setText(i + " kcal");

        if(maxText.getText().equals("Max:")) maxValue.setText(user.getMaxKCal() + " kcal");
        else if(maxText.getText().equals("Diff.:")) maxValue.setText((user.getMaxKCal()-i) + " kcal");
    }

    private void highlightState(int color1, int color2, int color3, int color4)
    {
        day.setBackgroundColor(DiaryActivity.this.getResources().getColor(color1));
        week.setBackgroundColor(DiaryActivity.this.getResources().getColor(color2));
        month.setBackgroundColor(DiaryActivity.this.getResources().getColor(color3));
        all.setBackgroundColor(DiaryActivity.this.getResources().getColor(color4));
    }
}
