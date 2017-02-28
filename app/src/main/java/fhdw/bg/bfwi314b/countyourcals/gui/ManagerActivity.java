package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;

import static fhdw.bg.bfwi314b.countyourcals.gui.ManagerState.*;

/**
 * Created by Katja Müller
 */

public class ManagerActivity extends Activity {

    private RowFactory rowFactory;
    private DialogFactory dialogFactory;
    private ManagerState state;

    private User user;
    private Button food;
    private Button meal;
    private Button unit;
    private TableLayout table;

    //onCreate() get's called on start of the activity. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
    }

    //onResume() get's called on start of the activity after the onCreate Method. Furthermore it is called when the devices gets tilted.
    @Override
    protected void onResume() {
        super.onResume();
        initializeActivity();//initialize all elements of gui
    }

    //initialize all elements of gui
    private void initializeActivity() {
        user = (User) this.getIntent().getSerializableExtra("user");
        rowFactory = new RowFactory(this);
        dialogFactory = new DialogFactory(this);
        food = (Button) findViewById(R.id.ManagerButtonFood);
        meal = (Button) findViewById(R.id.ManagerButtonMeal);
        unit = (Button) findViewById(R.id.ManagerButtonUnit);
        table = (TableLayout) this.findViewById(R.id.ManagerTableLayout);
        Button newEntry = (Button) findViewById(R.id.ManagerButtonNewEntry);

        //state is only set to default if the activity was called by main Activity and not when the device gets tilted
        if (state == null) state = MealsState;

        //Set click handlers for the state buttons. If a button is clicked the state is changed and view elements will be updated
        food.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = FoodState;
                updateView();
            }
        });
        meal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = MealsState;
                updateView();
            }
        });
        unit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = UnitsState;
                updateView();
            }
        });

        //Create the right dialog according in which state the activity is
        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (state) {
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
            }
        });
        updateView();   //view will be updated to show the latest changes
    }

    //view will be updated to show the latest changes
    public void updateView() {
        table.removeAllViews(); //delete all rows to fill table with new ones
        //set Buttoncolors and fill rows according to which state is selected
        switch (state) {
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

    //this method will be called if the activity instance will be recreated. Some values need to be reloaded in the new activity instance. Here they will be loaded.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("state", state);
    }

    //if the activities which were called with return value return a value it will be processed here
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = (ManagerState) savedInstanceState.getSerializable("state");
    }

    //changes colors of buttons in one place
    private void highlightState(int color1, int color2, int color3) {
        food.setBackgroundColor(ManagerActivity.this.getResources().getColor(color1));
        meal.setBackgroundColor(ManagerActivity.this.getResources().getColor(color2));
        unit.setBackgroundColor(ManagerActivity.this.getResources().getColor(color3));
    }
}
