package fhdw.bg.bfwi314b.countyourcals.gui;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;

public class DiaryActivity extends Activity {

    private RowFactory rowFactroy;
    private DialogFactory dialogFactory;
    private List<DiaryEntry> entries;
    private List<Food> foods;
    private List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        rowFactroy = new RowFactory();
        dialogFactory = new DialogFactory();

        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();

        Button newEntry = (Button)findViewById(R.id.DiaryButtonNewEntry);

        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialogFactory.CreateNewDiaryEntryDialog(DiaryActivity.this, foods, meals);
            }});



        entries = new ArrayList<DiaryEntry>();

        entries.add(new DiaryEntry("01.01.1900", "mein Gericht", 100, "Gramm", 200, 1));
        entries.add(new DiaryEntry("01.01.1900", "Pommes", 100, "Gramm", 500, 2));
        entries.add(new DiaryEntry("01.01.1900", "Pizza", 100, "Gramm", 700, 3));
        entries.add(new DiaryEntry("01.01.1900", "Salat", 100, "Gramm", 100, 4));

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

        rowFactroy.FillDiaryTableLayout((TableLayout)this.findViewById(R.id.DiaryTableLayout), entries, DiaryActivity.this);


    }
}
