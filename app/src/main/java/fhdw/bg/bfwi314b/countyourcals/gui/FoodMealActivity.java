package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.R;

public class FoodMealActivity extends Activity {

    private List<Food> foods;
    private List<Meal> meals;
    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_meal);

        foods = new ArrayList<Food>();
        meals = new ArrayList<Meal>();
        rowFactory = new RowFactory();
        dialogFactory = new DialogFactory();

        foods.add(new Food("Pommes mit Schnitzel", 200, "Gramm", 400));
        foods.add(new Food("Hünersuppe", 100, "Milliliter", 200));
        foods.add(new Food("Halver Hahn", 1, "Halbes Brötchen", 200));

        //meals.add(new Meal("Currywurst Pommes", 1, 200, "Gramm", 400, new ArrayList<String>(){"Wurst", "Pommes"}, ))
    }
}
