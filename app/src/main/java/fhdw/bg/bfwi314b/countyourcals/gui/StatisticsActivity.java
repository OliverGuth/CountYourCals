package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.OldModels.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.R;

import static fhdw.bg.bfwi314b.countyourcals.gui.StatisticsState.*;

public class StatisticsActivity extends Activity {

    private StatisticsState state;
    private Button OneWeek;
    private Button TwoWeeks;
    private Button FourWeeks;
    private TextView averageCalories;
    private List<DiaryEntry> entries;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        OneWeek = (Button) findViewById(R.id.StatisticsOneWeekButton);
        TwoWeeks = (Button) findViewById(R.id.StatisticsTwoWeeksButton);
        FourWeeks = (Button) findViewById(R.id.StatisticsFourWeeksButton);
        averageCalories = (TextView) findViewById(R.id.StatisticsAverageCaloriesValue);
        date = (TextView) findViewById(R.id.StatisticsDate);

        state = OneWeekState;

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

        entries = new ArrayList<DiaryEntry>();
        entries.add(new DiaryEntry(new Date("24.01.2017"), "Wrap", 100, "Gramm", 600, 1));
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

        updateView();
    }

    public void updateView()
    {
        int calories = 0;
        int entryCount = 0;
        Date timeFrameStart;
        Date timeFrameEnd = Calendar.getInstance().getTime();
        Date entryDate;
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
    }

    private void highlightState(int color1, int color2, int color3)
    {
        OneWeek.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color1));
        TwoWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color2));
        FourWeeks.setBackgroundColor(StatisticsActivity.this.getResources().getColor(color3));
    }
}
