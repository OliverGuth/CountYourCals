package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth
 */

public class RowFactory {

    private DialogFactory dialogFactory;
    private DataStorageController controller;
    private Context context;

    //Constructor
    public RowFactory(Context context) {
        this.dialogFactory = new DialogFactory(context);
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    //fills diary activity table layout with the appropriate entries
    public void FillDiaryTableLayout(TableLayout table, DiaryState state, Date beginningDate, Date endDate, final User user) {
        table.removeAllViews(); //remove all existing rows in table layout
        List<DiaryEntry> entriesAll = controller.getDiaryEntryList(user); //get all diary entries from controller
        List<DiaryEntry> entriesShown = new ArrayList<DiaryEntry>();

        //filter all entries to just the entries which should be shown
        if (entriesAll != null) {
            for (DiaryEntry entry : entriesAll) {
                if (entry.getTimeStamp().compareTo(beginningDate) >= 0 && entry.getTimeStamp().compareTo(endDate) <= 0)
                    entriesShown.add(entry);
            }

            //Create row for each entry to be shown
            for (int i = 0; i < entriesShown.size(); i++) {
                final DiaryEntry diaryEntry = entriesShown.get(i);
                TableRow row;
                TextView number;
                TextView name;
                TextView calories;
                TextView date;
                RelativeLayout edit;

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                //decide which row layout should be used
                if (state == DiaryState.DayState) {
                    row = (TableRow) inflater.inflate(R.layout.row_diary_entry_no_date, null);
                    edit = (RelativeLayout) row.getChildAt(3);
                    date = null;
                } else {
                    row = (TableRow) inflater.inflate(R.layout.row_diary_entry_date, null);
                    date = (TextView) row.getChildAt(3);
                    edit = (RelativeLayout) row.getChildAt(4);
                }
                //set values to row cells
                number = (TextView) row.getChildAt(0);
                name = (TextView) row.getChildAt(1);
                calories = (TextView) row.getChildAt(2);

                number.setText(i + 1 + ".");
                name.setText(diaryEntry.getConsumedName());
                calories.setText(diaryEntry.getConsumedKCal().toString() + " kcal");
                if (date != null)
                    date.setText(new SimpleDateFormat("dd.MM.yyyy").format(diaryEntry.getTimeStamp()));

                //set image and background for edit button
                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));

                edit.setClickable(true);
                row.setLongClickable(true); //make row able to react to a long press

                //long press on row and edit button in row do the same thing on click: Create an edit dialog
                row.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        dialogFactory.CreateDiaryEntryLineDialog(diaryEntry, user);
                        return true;
                    }

                });

                edit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialogFactory.CreateDiaryEntryLineDialog(diaryEntry, user);
                    }

                });
                table.addView(row); //row will be added to the table layout
            }
        }
    }

    //fills manager activity table layout with the appropriate meals
    public void FillMealTableLayout(TableLayout table, final User user) {
        final RowFactory rowFactory = this;
        final List<Meal> meals = controller.getMealList(user); //get all existing meals from controller
        if (meals != null) { //if there are meals, continue
            for (int i = 0; i < meals.size(); i++) {
                //create row for each meal
                final Meal meal = meals.get(i);
                //fill rows with meal data
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableRow row = (TableRow) inflater.inflate(R.layout.row_meal, null);
                TextView number = (TextView) row.getChildAt(0);
                TextView name = (TextView) row.getChildAt(1);
                TextView calories = (TextView) row.getChildAt(2);
                RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

                //set image and background for edit button
                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));


                number.setText(i + 1 + ".");
                name.setText(meal.getName());
                calories.setText(meal.getKCal().toString() + " kcal");

                edit.setClickable(true);
                row.setLongClickable(true); //make row able to react to a long press

                //long press on row and edit button in row do the same thing on click: Create an edit dialog
                row.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        dialogFactory.CreateMealLineDialog(meal, rowFactory, user);
                        return true;
                    }

                });

                edit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialogFactory.CreateMealLineDialog(meal, rowFactory, user);
                    }

                });
                table.addView(row);//row will be added to the table layout
            }
        }
    }

    //fills manager activity table layout with the appropriate foods
    public void FillFoodTableLayout(TableLayout table, final User user) {
        final List<Food> foods = controller.getFoodList(user); //get existing foods from controller
        final RowFactory rowFactory = this;
        if (foods != null) {
            //if foods exist
            for (int i = 0; i < foods.size(); i++) {
                //create row for each food
                final Food food = foods.get(i);
                //fill rows with food data
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableRow row = (TableRow) inflater.inflate(R.layout.row_food, null);
                TextView number = (TextView) row.getChildAt(0);
                TextView name = (TextView) row.getChildAt(1);
                TextView calories = (TextView) row.getChildAt(2);
                RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

                //set image and background for edit button
                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));

                number.setText(i + 1 + ".");
                name.setText(foods.get(i).getName());
                calories.setText(foods.get(i).getKCal().toString() + " kcal");

                edit.setClickable(true);
                row.setLongClickable(true); //make row able to react to a long press

                //long press on row and edit button in row do the same thing on click: Create an edit dialog
                row.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        dialogFactory.CreateFoodLineDialog(food, rowFactory, user);
                        return true;
                    }

                });

                edit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialogFactory.CreateFoodLineDialog(food, rowFactory, user);
                    }

                });
                table.addView(row);//row will be added to the table layout
            }
        }
    }

    //fills manager activity table layout with the appropriate units
    public void FillUnitTableLayout(TableLayout table, final User user) {
        final List<Unit> units = controller.getUnitList(user); //get all existing units from controller
        if (units != null) {
            //if units exist
            for (int i = 0; i < units.size(); i++) {
                //Create a row for each unit
                final Unit unit = units.get(i);
                //fill rows with food data
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableRow row = (TableRow) inflater.inflate(R.layout.row_unit, null);
                TextView number = (TextView) row.getChildAt(0);
                TextView name = (TextView) row.getChildAt(1);
                RelativeLayout edit = (RelativeLayout) row.getChildAt(2);

                //set image and background for edit button
                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));

                number.setText(i + 1 + ".");
                name.setText(unit.getUnit());

                edit.setClickable(true);
                row.setLongClickable(true);//make row able to react to a long press

                //long press on row and edit button in row do the same thing on click: Create an edit dialog
                row.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        dialogFactory.CreateUnitLineDialog(unit, user);
                        return true;
                    }

                });

                edit.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialogFactory.CreateUnitLineDialog(unit, user);
                    }

                });
                table.addView(row); //row will be added to the table layout
            }
        }
    }

    //Add new unit row to food dialog
    public void AddRowFoodDialogTableLayout(final TableLayout table, final User user) {
        List<Unit> units = controller.getUnitList(user);    //get existing units from controller
        List<Unit> usedUnits = new ArrayList<Unit>();

        //if unit is already used, exclude it
        if (table.getChildCount() > 1)
            for (int i = 0; i < (table.getChildCount() - 1); i++)
                usedUnits.add((Unit) ((Spinner) ((TableRow) table.getChildAt(i)).getChildAt(1)).getSelectedItem());
        units.removeAll(usedUnits);

        if (units != null) {
            if ((table.getChildCount() - 1) < units.size()) {//the amount of unit rows can't be higher than actual units are there
                //initialize row
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final TableRow row = (TableRow) inflater.inflate(R.layout.row_food_unit, null);
                EditText quantity = (EditText) row.getChildAt(0);
                Spinner unit = (Spinner) row.getChildAt(1);
                RelativeLayout delete = (RelativeLayout) row.getChildAt(2);

                //set image and background for delete button
                ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
                delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

                quantity.setText("");

                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, units); //set unit list as dropdown elements

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unit.setAdapter(adapter);

                delete.setClickable(true);

                //on click on delete button the row will be deleted
                delete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        table.removeView(row);
                        Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                    }

                });
                table.addView(row, table.getChildCount() - 1); //add row to dialog
            } else
                Toast.makeText(context, "Bitte weitere Einheiten hinzufügen", Toast.LENGTH_LONG).show();
        }
    }

    //Add existing unit row to food dialog
    public void AddRowFoodDialogEditTableLayout(final TableLayout table, Unit unit, final User user) {
        //initialize row
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TableRow row = (TableRow) inflater.inflate(R.layout.row_food_unit, null);
        EditText quantity = (EditText) row.getChildAt(0);
        Spinner unitSpinner = (Spinner) row.getChildAt(1);
        RelativeLayout delete = (RelativeLayout) row.getChildAt(2);

        //set image and background for delete button
        ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
        delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

        //set information of existing unit
        quantity.setText(unit.getQuantity().toString());
        List<Unit> units = new ArrayList<Unit>();
        units.add(unit);

        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, units); //set unit list as dropdown elements

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        delete.setClickable(true);

        //on click on delete button the row will be deleted
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                table.removeView(row);
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
            }

        });
        table.addView(row, table.getChildCount() - 1); //add row to dialog
    }

    //Add new food row to meal dialog
    public void AddRowMealDialogTableLayout(final TableLayout table, final User user) {
        List<Food> foods = controller.getFoodList(user);//get existing foods from controller
        List<Food> usedFoods = new ArrayList<Food>();

        //if food is already used, exclude it
        if (table.getChildCount() > 1)
            for (int i = 0; i < (table.getChildCount() - 1); i++)
                usedFoods.add((Food) ((Spinner) ((TableRow) table.getChildAt(i)).getChildAt(0)).getSelectedItem());
        foods.removeAll(usedFoods);

        if (foods != null) {
            if ((table.getChildCount() - 1) < foods.size()) {//the amount of food rows can't be higher than actual foods are there
                //initialize row
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final TableRow row = (TableRow) inflater.inflate(R.layout.row_meal_food, null);
                final Spinner foodSpinner = (Spinner) row.getChildAt(0);
                EditText quantity = (EditText) row.getChildAt(1);
                final Spinner unitSpinner = (Spinner) row.getChildAt(2);
                RelativeLayout delete = (RelativeLayout) row.getChildAt(3);

                //set image and background for delete button
                ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
                delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

                quantity.setText("");

                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foods);//set food list as dropdown elements
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodSpinner.setAdapter(adapter);

                //if food is selected, according units will be added to the unit dropdown
                foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Food) foodSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        unitSpinner.setAdapter(adapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }

                });

                delete.setClickable(true);

                //on click on delete button the row will be deleted
                delete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        table.removeView(row);
                        Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                    }

                });
                table.addView(row, table.getChildCount() - 1);//add row to dialog
            } else
                Toast.makeText(context, "Bitte weitere Lebensmittel hinzufügen", Toast.LENGTH_LONG).show();
        }
    }

    //Add existing food row to meal dialog
    public void AddRowMealDialogEditTableLayout(final TableLayout table, final Food food, final Unit unit, final User user) {
        //initialize row
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TableRow row = (TableRow) inflater.inflate(R.layout.row_meal_food, null);
        final Spinner foodSpinner = (Spinner) row.getChildAt(0);
        EditText quantity = (EditText) row.getChildAt(1);
        final Spinner unitSpinner = (Spinner) row.getChildAt(2);
        RelativeLayout delete = (RelativeLayout) row.getChildAt(3);

        //set image and background for delete button
        ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
        delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

        //set information of existing food
        quantity.setText(food.getUnits().get(0).getQuantity().toString());

        List<Food> foods = new ArrayList<Food>();
        foods.add(food);
        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foods);//set unit list as dropdown elements
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(adapter);

        //if food is selected, according units will be added to the unit dropdown
        foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                List<Unit> units = new ArrayList<Unit>();
                units.add(unit);
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, units);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitSpinner.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                unitSpinner.setAdapter(null);
            }
        });

        delete.setClickable(true);

        //on click on delete button the row will be deleted
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                table.removeView(row);
                Toast.makeText(context, "Lebensmittel entfernt", Toast.LENGTH_LONG).show();
            }
        });
        table.addView(row, table.getChildCount() - 1);//add row to dialog
    }
}
