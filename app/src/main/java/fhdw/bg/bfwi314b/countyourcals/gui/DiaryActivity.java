package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Intent;
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
    public List<DiaryEntry> entries;
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
    private Button next;
    private Button before;
    TextView sum;
    TextView maxText;
    TextView maxValue;
    private TableLayout table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        rowFactory = new RowFactory(this);
        dialogFactory = new DialogFactory(this);

        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();
        user = new User("Oliver", 'M', 2500, "Deutsch");

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

        diaryState = DayState;
        day.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diaryState = DayState;
                selectedDate = Calendar.getInstance().getTime();
                ArrowsVisible(true);
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

        statistics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, StatisticsActivity.class);
                startActivity(intent);
            }});

        //Bundle bundle = getIntent().getExtras();
        //user = bundle.getParcelable("user");


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
        Calendar cal = Calendar.getInstance();
        /*entries.add(new DiaryEntry(cal.set(2017, 1,25));
        try {
            new SimpleDateFormat("dd.MM.yyyy").parse("24.01.2017"), "Wrap", 100, "Gramm", 600, 1
        } catch (ParseException e) {
            e.printStackTrace();
        }));
        entries.add(new DiaryEntry(new Date("25.01.2017"), "mein Gericht", 100, "Gramm", 200, 2));
        entries.add(new DiaryEntry(new Date("25.01.2017"), "Pommes", 100, "Gramm", 500, 3));
        entries.add(new DiaryEntry(new Date("25.01.2017"), "Pizza", 100, "Gramm", 723, 4));
        entries.add(new DiaryEntry(new Date("25.01.2017"), "Salat", 100, "Gramm", 100, 5));
        entries.add(new DiaryEntry(new Date("26.01.2017"), "Wrap", 100, "Gramm", 600, 6));
        entries.add(new DiaryEntry(new Date("26.01.2017"), "Brot", 100, "Gramm", 500, 7));
        entries.add(new DiaryEntry(new Date("27.01.2017"), "Kaffee", 1, "Tasse", 200, 8));
        entries.add(new DiaryEntry(new Date("28.01.2017"), "Salat", 100, "Gramm", 100, 9));
        entries.add(new DiaryEntry(new Date("29.01.2017"), "Wrap", 100, "Gramm", 600, 10));
        entries.add(new DiaryEntry(new Date("29.01.2017"), "Wrap", 100, "Gramm", 600, 11));
        entries.add(new DiaryEntry(new Date("29.01.2017"), "Pommes", 100, "Gramm", 500, 12));
        entries.add(new DiaryEntry(new Date("30.01.2017"), "Wrap", 100, "Gramm", 600, 13));
        entries.add(new DiaryEntry(new Date("31.01.2017"), "Wrap", 100, "Gramm", 600, 15));
        entries.add(new DiaryEntry(new Date("31.01.2017"), "Wrap", 100, "Gramm", 600, 16));
        entries.add(new DiaryEntry(new Date("01.02.2017"), "Pizza", 100, "Gramm", 723, 17));
        entries.add(new DiaryEntry(new Date("01.02.2017"), "Wrap", 100, "Gramm", 600, 18));
        entries.add(new DiaryEntry(new Date("01.02.2017"), "Wrap", 100, "Gramm", 600, 19));
        entries.add(new DiaryEntry(new Date("02.02.2017"), "Pizza", 100, "Gramm", 723, 20));
        entries.add(new DiaryEntry(new Date("02.02.2017"), "Wrap", 100, "Gramm", 600, 21));
        entries.add(new DiaryEntry(new Date("03.02.2017"), "Pommes", 100, "Gramm", 500, 22));
        entries.add(new DiaryEntry(new Date("04.02.2017"), "Wrap", 100, "Gramm", 600, 23));
        entries.add(new DiaryEntry(new Date("05.02.2017"), "mein Gericht", 100, "Gramm", 200, 24));
        entries.add(new DiaryEntry(new Date("05.02.2017"), "Pommes", 100, "Gramm", 500, 25));
        entries.add(new DiaryEntry(new Date("05.02.2017"), "Pizza", 100, "Gramm", 723, 26));
        entries.add(new DiaryEntry(new Date("05.02.2017"), "Salat", 100, "Gramm", 100, 27));
        entries.add(new DiaryEntry(new Date("06.02.2017"), "Wrap", 100, "Gramm", 600, 28));
        entries.add(new DiaryEntry(new Date("06.02.2017"), "Brot", 100, "Gramm", 500, 29));
        entries.add(new DiaryEntry(new Date("07.02.2017"), "Kaffee", 1, "Tasse", 200, 30));
        entries.add(new DiaryEntry(new Date("08.02.2017"), "Salat", 100, "Gramm", 100, 31));



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
                ArrowsVisible(true);
                for (DiaryEntry entry:entries) { if(timeStamp.equals(entry.getTimeStamp())) shwownEntries.add(entry); }
                date.setText(timeStamp);
                rowFactory.FillDiaryTableLayout(table, shwownEntries, DiaryActivity.this);
            break;
            case WeekState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                ArrowsVisible(true);

                cal.setTime(selectedDate);

                int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
                int currentYear = cal.get(Calendar.YEAR);

                for (DiaryEntry entry:entries)
                    {
                        cal.setTime(entry.getTimeStamp());

                        int entryWeek = cal.get(Calendar.WEEK_OF_YEAR);
                        int entryYear = cal.get(Calendar.YEAR);
                        if(currentWeek == entryWeek && currentYear == entryYear) shwownEntries.add(entry);
                    }

                    date.setText("KW "+ currentWeek + " " + currentYear);

                    rowFactory.FillDiaryTableDateLayout(table, shwownEntries, DiaryActivity.this);
                break;
                case MonthState:
                    highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                    ArrowsVisible(true);

                    cal.setTime(selectedDate);
                    int currentMonth = cal.get(Calendar.MONTH);
                    currentYear = cal.get(Calendar.YEAR);

                    for (DiaryEntry entry:entries)
                    {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        cal.setTime(entry.getTimeStamp());
                        int entrymonth = cal.get(Calendar.MONTH);
                        int entryYear = cal.get(Calendar.YEAR);
                        if(currentMonth == entrymonth && currentYear == entryYear) shwownEntries.add(entry);
                    }
                    date.setText(new SimpleDateFormat("MMMM").format(selectedDate) + " " + currentYear);
                    rowFactory.FillDiaryTableDateLayout(table, shwownEntries, DiaryActivity.this);
                break;
                case AllState:
                    highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                    ArrowsVisible(false);

                    shwownEntries.addAll(entries);
                    date.setText("");
                    rowFactory.FillDiaryTableDateLayout(table, shwownEntries, DiaryActivity.this);
                break;
            }
            updateSumMaxFields();
        }
    private void updateSumMaxFields()
    {
        int i = 0;
        int max = 0;

        for(DiaryEntry entry: shwownEntries) i = i + entry.getConsumedKCal();
        sum.setText(i + " kcal");

        if(diaryState == DayState)
        {
            if (maxText.getText().equals("Max:")) maxValue.setText(user.getMaxKCal() + " kcal");
            else if (maxText.getText().equals("Diff.:")) maxValue.setText((user.getMaxKCal() - i) + " kcal");
        }
        else
        {
            maxText.setVisibility(View.INVISIBLE);
            maxValue.setVisibility(View.INVISIBLE);
        }
    }

    private void highlightState(int color1, int color2, int color3, int color4)
    {
        day.setBackgroundColor(DiaryActivity.this.getResources().getColor(color1));
        week.setBackgroundColor(DiaryActivity.this.getResources().getColor(color2));
        month.setBackgroundColor(DiaryActivity.this.getResources().getColor(color3));
        all.setBackgroundColor(DiaryActivity.this.getResources().getColor(color4));
    }
}
