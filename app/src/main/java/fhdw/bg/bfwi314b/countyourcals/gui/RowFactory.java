package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth on 29.01.2017.
 */

public class RowFactory {

    private DialogFactory dialogFactory;
    private DataStorageController controller;
    private Context context;


    public RowFactory(Context context) {
        this.dialogFactory = new DialogFactory(context);
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    public void FillDiaryTableLayout(TableLayout table, DiaryState state, Date beginningDate, Date endDate, User user) {
        table.removeAllViews();
        List<DiaryEntry> entriesAll = controller.getDiaryEntryList(user);
        List<DiaryEntry> entriesShown = new ArrayList<DiaryEntry>();

        for (DiaryEntry entry : entriesAll)
        {
            if (entry.getTimeStamp().compareTo(beginningDate)>= 0 && entry.getTimeStamp().compareTo(endDate)<= 0)
                entriesShown.add(entry);
        }



        for (int i = 0; i < entriesShown.size(); i++) {
            final DiaryEntry diaryEntry = entriesShown.get(i);
            TableRow row;
            TextView number;
            TextView name;
            TextView calories;
            TextView date;
            RelativeLayout edit;

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (state == DiaryState.DayState)
            {
                row = (TableRow) inflater.inflate(R.layout.row_diary_entry_no_date, null);
                edit = (RelativeLayout) row.getChildAt(3);
                date = null;
            }
            else
            {
                row = (TableRow) inflater.inflate(R.layout.row_diary_entry_date, null);
                date = (TextView) row.getChildAt(3);
                edit = (RelativeLayout) row.getChildAt(4);
            }
            number = (TextView) row.getChildAt(0);
            name = (TextView) row.getChildAt(1);
            calories = (TextView) row.getChildAt(2);

            number.setText(i + 1 + ".");
            name.setText(diaryEntry.getConsumedName());
            calories.setText(diaryEntry.getConsumedKCal().toString() + " kcal");
            ////////if(date != null) date.setText(diaryEntry.getConsumedKCal().toString() + " kcal");
            ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
            edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));

            edit.setClickable(true);
            row.setLongClickable(true);

            row.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    dialogFactory.CreateDiaryEntryLineDialog(diaryEntry);
                    return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateDiaryEntryLineDialog(diaryEntry);
                }

            });
            table.addView(row);
        }
    }

    public void FillMealTableLayout(TableLayout table, final User user) {
        final RowFactory rowFactory = this;
        final List<Meal> meals = controller.getMealList(user);
        if (meals != null) {
            for (int i = 0; i < meals.size(); i++) {
                final Meal meal = meals.get(i);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableRow row = (TableRow) inflater.inflate(R.layout.row_meal, null);
                TextView number = (TextView) row.getChildAt(0);
                TextView name = (TextView) row.getChildAt(1);
                TextView calories = (TextView) row.getChildAt(2);
                RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));


                number.setText(i + 1 + ".");
                name.setText(meal.getName());
                calories.setText(meal.getKCal().toString() + " kcal");

                edit.setClickable(true);
                row.setLongClickable(true);

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
                table.addView(row);
            }
        }
    }

    public void FillFoodTableLayout(TableLayout table, final User user) {
        final List<Food> foods = controller.getFoodList(user);
        final RowFactory rowFactory = this;
        if (foods != null) {
            for (int i = 0; i < foods.size(); i++) {
                final Food food = foods.get(i);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableRow row = (TableRow) inflater.inflate(R.layout.row_food, null);
                TextView number = (TextView) row.getChildAt(0);
                TextView name = (TextView) row.getChildAt(1);
                TextView calories = (TextView) row.getChildAt(2);
                RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));


                number.setText(i + 1 + ".");
                name.setText(foods.get(i).getName());
                calories.setText(foods.get(i).getKCal().toString() + " kcal");

                edit.setClickable(true);
                row.setLongClickable(true);

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
                table.addView(row);
            }
        }
    }

    public void FillUnitTableLayout(TableLayout table, final User user) {
        final List<Unit> units = controller.getUnitList(user);
        if (units != null) {
            for (int i = 0; i < units.size(); i++) {
                final Unit unit = units.get(i);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TableRow row = (TableRow) inflater.inflate(R.layout.row_unit, null);
                TextView number = (TextView) row.getChildAt(0);
                TextView name = (TextView) row.getChildAt(1);
                RelativeLayout edit = (RelativeLayout) row.getChildAt(2);

                ((ImageView) edit.getChildAt(0)).setImageResource((R.drawable.edit));
                edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));


                number.setText(i + 1 + ".");
                name.setText(unit.getUnit());

                edit.setClickable(true);
                row.setLongClickable(true);

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
                table.addView(row);
            }
        }
    }

    public void AddRowFoodDialogTableLayout(final TableLayout table, final User user) {
        List<Unit> units = controller.getUnitList(user);
        List<Unit> usedUnits = new ArrayList<Unit>();

        if (table.getChildCount() > 1)
            for (int i = 0; i < (table.getChildCount() - 1); i++)
                usedUnits.add((Unit) ((Spinner) ((TableRow) table.getChildAt(i)).getChildAt(1)).getSelectedItem());
        units.removeAll(usedUnits);

        if (units != null) {
            if ((table.getChildCount() - 1) < units.size()) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final TableRow row = (TableRow) inflater.inflate(R.layout.row_food_unit, null);
                EditText quantity = (EditText) row.getChildAt(0);
                Spinner unit = (Spinner) row.getChildAt(1);
                RelativeLayout delete = (RelativeLayout) row.getChildAt(2);

                ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
                delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

                quantity.setText("");


                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, units);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unit.setAdapter(adapter);

                delete.setClickable(true);

                delete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        table.removeView(row);
                        Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                    }

                });
                table.addView(row, table.getChildCount() - 1);
            } else
                Toast.makeText(context, "Bitte weitere Einheiten hinzufügen", Toast.LENGTH_LONG).show();
        }
    }

    public void AddRowFoodDialogEditTableLayout(final TableLayout table, Unit unit, final User user) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TableRow row = (TableRow) inflater.inflate(R.layout.row_food_unit, null);
        EditText quantity = (EditText) row.getChildAt(0);
        Spinner unitSpinner = (Spinner) row.getChildAt(1);
        RelativeLayout delete = (RelativeLayout) row.getChildAt(2);

        ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
        delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

        quantity.setText(unit.getQuantity().toString());
        List<Unit> units = new ArrayList<Unit>();
        units.add(unit);

        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, units);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        delete.setClickable(true);

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                table.removeView(row);
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
            }

        });
        table.addView(row, table.getChildCount() - 1);
    }

    public void AddRowMealDialogTableLayout(final TableLayout table, final User user) {
        List<Food> foods = controller.getFoodList(user);
        List<Food> usedFoods = new ArrayList<Food>();

        if (table.getChildCount() > 1)
            for (int i = 0; i < (table.getChildCount() - 1); i++)
                usedFoods.add((Food) ((Spinner) ((TableRow) table.getChildAt(i)).getChildAt(0)).getSelectedItem());
        foods.removeAll(usedFoods);

        if (foods != null) {
            if ((table.getChildCount() - 1) < foods.size()) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final TableRow row = (TableRow) inflater.inflate(R.layout.row_meal_food, null);
                final Spinner foodSpinner = (Spinner) row.getChildAt(0);
                EditText quantity = (EditText) row.getChildAt(1);
                final Spinner unitSpinner = (Spinner) row.getChildAt(2);
                RelativeLayout delete = (RelativeLayout) row.getChildAt(3);

                ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
                delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

                quantity.setText("");

                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foods);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodSpinner.setAdapter(adapter);


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

                delete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        table.removeView(row);
                        Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                    }

                });
                table.addView(row, table.getChildCount() - 1);
            } else
                Toast.makeText(context, "Bitte weitere Lebensmittel hinzufügen", Toast.LENGTH_LONG).show();
        }
    }

    public void AddRowMealDialogEditTableLayout(final TableLayout table, final Food food, final Unit unit, final User user) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final TableRow row = (TableRow) inflater.inflate(R.layout.row_meal_food, null);
        final Spinner foodSpinner = (Spinner) row.getChildAt(0);
        EditText quantity = (EditText) row.getChildAt(1);
        final Spinner unitSpinner = (Spinner) row.getChildAt(2);
        RelativeLayout delete = (RelativeLayout) row.getChildAt(3);

        ((ImageView) delete.getChildAt(0)).setImageResource((R.drawable.delete));
        delete.setBackgroundColor(context.getResources().getColor(R.color.Red));

        quantity.setText(food.getUnits().get(0).getQuantity().toString());

        List<Food> foods = new ArrayList<Food>();
        foods.add(food);
        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(adapter);

        foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                List<Unit> units = new ArrayList<Unit>();
                units.add(unit);
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item,  units);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitSpinner.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                unitSpinner.setAdapter(null);
            }
        });

        delete.setClickable(true);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                table.removeView(row);
                Toast.makeText(context, "Lebensmittel entfernt", Toast.LENGTH_LONG).show();
            }
        });
        table.addView(row, table.getChildCount() - 1);
    }
}
