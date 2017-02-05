package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.R;

import static fhdw.bg.bfwi314b.countyourcals.gui.ManagerState.*;

public class ManagerActivity extends Activity {

    private List<Food> foods;
    private List<Meal> meals;
    private List<Unit> units;
    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    private ManagerState state;

    private Button food;
    private Button meal;
    private Button unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();
        rowFactory = new RowFactory();
        dialogFactory = new DialogFactory();

        food = (Button) findViewById(R.id.ManagerButtonFood);
        meal = (Button) findViewById(R.id.ManagerButtonMeal);
        unit = (Button) findViewById(R.id.ManagerButtonUnit);

        Button newEntry = (Button) findViewById(R.id.ManagerButtonNewEntry);

        state = FoodState;

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



        foods.add(new Food("Pommes mit Schnitzel", 200, "Gramm", 400));
        foods.add(new Food("Hünersuppe", 100, "Milliliter", 200));
        foods.add(new Food("Halver Hahn", 1, "Halbes Brötchen", 200));

        //meals.add(new Meal("Currywurst Pommes", 1, 200, "Gramm", 400, new ArrayList<String>(){"Wurst", "Pommes"}, ))
    }

    private void updateView()
    {
        switch (state)
        {
            case FoodState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                rowFactory.FillFoodTableLayout((TableLayout)this.findViewById(R.id.ManagerTableLayout), foods, ManagerActivity.this);
                break;
            case MealsState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                rowFactory.FillMealTableLayout((TableLayout)this.findViewById(R.id.DiaryTableLayout), meals, ManagerActivity.this);
                break;
            case UnitsState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                rowFactory.FillUnitTableLayout((TableLayout)this.findViewById(R.id.DiaryTableLayout), units, ManagerActivity.this);
                break;
        }
    }

    private void highlightState(int color1, int color2, int color3)
    {
        food.setBackgroundColor(ManagerActivity.this.getResources().getColor(color1));
        meal.setBackgroundColor(ManagerActivity.this.getResources().getColor(color2));
        unit.setBackgroundColor(ManagerActivity.this.getResources().getColor(color3));
    }
}
