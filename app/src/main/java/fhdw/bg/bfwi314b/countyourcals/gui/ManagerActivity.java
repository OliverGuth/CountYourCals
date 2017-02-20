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

    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    private ManagerState state;

    private User user;
    private Button food;
    private Button meal;
    private Button unit;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initializeActivity();
    }

    private void initializeActivity()
    {
        user = (User) this.getIntent().getSerializableExtra("user");
        rowFactory = new RowFactory(this);
        dialogFactory = new DialogFactory(this);

        food = (Button) findViewById(R.id.ManagerButtonFood);
        meal = (Button) findViewById(R.id.ManagerButtonMeal);
        unit = (Button) findViewById(R.id.ManagerButtonUnit);
        table = (TableLayout)this.findViewById(R.id.ManagerTableLayout);
        Button newEntry = (Button) findViewById(R.id.ManagerButtonNewEntry);

        if (state == null) state = MealsState;


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
                        dialogFactory.CreateNewFoodDialog(rowFactory, user);
                        break;
                    case MealsState:
                        dialogFactory.CreateNewMealDialog(rowFactory, user);
                        break;
                    case UnitsState:
                        dialogFactory.CreateNewUnitDialog(user);
                        break;
                }
            }});
        updateView();
    }

    public void updateView()
    {
        table.removeAllViews();
        switch (state)
        {
            case FoodState:
                highlightState(R.color.BayerGreen, R.color.BayerBlue, R.color.BayerBlue);
                rowFactory.FillFoodTableLayout(table, user);
            break;
            case MealsState:
                highlightState(R.color.BayerBlue, R.color.BayerGreen, R.color.BayerBlue);
                rowFactory.FillMealTableLayout(table, user);
            break;
            case UnitsState:
                highlightState(R.color.BayerBlue, R.color.BayerBlue, R.color.BayerGreen);
                rowFactory.FillUnitTableLayout(table, user);
            break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("state", state);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = (ManagerState) savedInstanceState.getSerializable("state");
    }

    private void highlightState(int color1, int color2, int color3)
    {
        food.setBackgroundColor(ManagerActivity.this.getResources().getColor(color1));
        meal.setBackgroundColor(ManagerActivity.this.getResources().getColor(color2));
        unit.setBackgroundColor(ManagerActivity.this.getResources().getColor(color3));
    }
}
