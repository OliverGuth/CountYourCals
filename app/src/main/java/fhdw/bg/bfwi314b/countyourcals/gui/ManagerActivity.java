package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;

import fhdw.bg.bfwi314b.countyourcals.R;

import static fhdw.bg.bfwi314b.countyourcals.gui.ManagerState.*;

public class ManagerActivity extends Activity {

    public List<Food> foods;
    public List<Meal> meals;
    public List<Unit> units;
    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    private ManagerState state;

    private User user;
    private Button food;
    private Button meal;
    private Button unit;
    private TableLayout table;

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        outState.putBoolean("RESTART", true);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        user = (User) this.getIntent().getSerializableExtra("user");
        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();
        units = new ArrayList<Unit>();
        rowFactory = new RowFactory(this);
        dialogFactory = new DialogFactory(this);

        food = (Button) findViewById(R.id.ManagerButtonFood);
        meal = (Button) findViewById(R.id.ManagerButtonMeal);
        unit = (Button) findViewById(R.id.ManagerButtonUnit);
        table = (TableLayout)this.findViewById(R.id.ManagerTableLayout);
        Button newEntry = (Button) findViewById(R.id.ManagerButtonNewEntry);

        if (savedInstanceState == null) state = MealsState;


        food.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = FoodState;
                updateView();
            }});
        meal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = MealsState;
                updateView();
            }});
        unit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = UnitsState;
                updateView();
            }});

        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (state)
                {
                    case FoodState:
                        dialogFactory.CreateNewFoodDialog(ManagerActivity.this, user);
                        break;
                    case MealsState:
                        dialogFactory.CreateNewMealDialog(ManagerActivity.this, user);
                        break;
                    case UnitsState:
                        dialogFactory.CreateNewUnitDialog(ManagerActivity.this, user);
                        break;
                }
            }});

        //foods.add(new Food("Apfel", 200, "Gramm", 400));
        //foods.add(new Food("Hähnchenfleisch", 100, "Gramm", 200));
        //foods.add(new Food("Brot", 1, "Scheibe", 200));

        //meals.add(new Meal("Currywurst Pommes", 1, 200, "Gramm", 400, new ArrayList<String>(){"Wurst", "Pommes"}, ))
        updateView();
    }

    public void updateView()
    {
        table.removeAllViews();
        switch (state)
        {
            case FoodState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                rowFactory.FillFoodTableLayout(table, user, ManagerActivity.this);
                break;
            case MealsState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                rowFactory.FillMealTableLayout(table, user, ManagerActivity.this);
                break;
            case UnitsState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                rowFactory.FillUnitTableLayout(table, user, ManagerActivity.this);
                break;
        }
        //DiaryEntry entryFood = new DiaryEntry(Calendar.getInstance().getTime(), new Food("Apfel"), 200, 1);
        //DiaryEntry entry = new DiaryEntry(Calendar.getInstance().getTime(), new Meal("Apfel", 2), 200, 1);

    }

    private void highlightState(int color1, int color2, int color3)
    {
        food.setBackgroundColor(ManagerActivity.this.getResources().getColor(color1));
        meal.setBackgroundColor(ManagerActivity.this.getResources().getColor(color2));
        unit.setBackgroundColor(ManagerActivity.this.getResources().getColor(color3));
    }
}
