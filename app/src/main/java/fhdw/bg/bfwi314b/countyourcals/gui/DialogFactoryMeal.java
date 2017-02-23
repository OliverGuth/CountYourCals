package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth on 23.02.2017.
 */

public class DialogFactoryMeal {
    private DataStorageController controller;
    private Context context;

    public DialogFactoryMeal(Context context)
    {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    public void CreateNewMealDialog(final RowFactory rowFactory, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewMealSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewMealName);
        final Button newFood = (Button) view.findViewById(R.id.DialogNewMealAddNewFoodButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewMealTableView);

        newFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowMealDialogTableLayout(table, user);
            }
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Meal> mealList = controller.getMealList(user);
                boolean mealExists = false;
                if(!name.getText().toString().equals("") && table.getChildCount() >1) {
                    if (mealList != null)
                    {
                        for (Meal meal : mealList) {
                            if (name.getText().toString().trim().equals(meal.getName())) {
                                Toast.makeText(context, "Eintrag " + meal.getName() + " existiert bereits", Toast.LENGTH_LONG).show();
                                mealExists = true;
                                break;
                            }
                        }
                    }
                    if (mealExists == false)
                    {
                        List<Food> foodList = new ArrayList<Food>();
                        List<Unit> unitList = new ArrayList<Unit>();
                        List<Food> allfoodsList = controller.getFoodList(user);
                        for(int i = 0; i<table.getChildCount()-1; i++) {
                            Spinner foodSpinner = (Spinner)((TableRow)table.getChildAt(i)).getChildAt(0);
                            EditText quantity = (EditText)((TableRow)table.getChildAt(i)).getChildAt(1);
                            Spinner unitSpinner = (Spinner)((TableRow)table.getChildAt(i)).getChildAt(2);
                            if(!quantity.getText().toString().equals(""))
                            {
                                Food newFood = (Food)foodSpinner.getSelectedItem();
                                for (Food food : foodList)
                                {
                                    if (food.getName().equals(newFood.getName())) {
                                        readyForSaving = false;
                                        Toast.makeText(context, "Lebensmittel " + food.getName() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                    }
                                }
                                if (readyForSaving)
                                {
                                    foodList.add(newFood);

                                    Unit newUnit = (Unit) unitSpinner.getSelectedItem();
                                    newUnit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                    unitList.add(newUnit);
                                }

                            }
                        }
                        int cals = 0;
                        for (int i = 0; i<foodList.size(); i++)
                        {
                            for (Food food : allfoodsList)
                            {
                                if(foodList.get(i).getName().equals(food.getName()))
                                {
                                    for (Unit newUnit : foodList.get(i).getUnits())
                                    {
                                        for (Unit unit : food.getUnits())
                                        {
                                            if (newUnit.getUnit().equals(unit.getUnit()))
                                                cals = cals + ((food.getKCal() / unit.getQuantity()) * unitList.get(i).getQuantity());
                                        }
                                    }
                                }
                            }
                        }
                        Meal meal = new Meal(name.getText().toString(), (ArrayList<Food>)foodList, (ArrayList<Unit>)unitList, cals);
                        controller.addMeal(meal, user);
                        ((ManagerActivity)context).updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateEditMealDialog(final Meal oldMeal, final RowFactory rowFactory, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewMealSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewMealName);
        final Button newFood = (Button) view.findViewById(R.id.DialogNewMealAddNewFoodButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewMealTableView);

        name.setText(oldMeal.getName());

        for (int i = 0; i < oldMeal.getIngredients().size(); i++)
        {
            rowFactory.AddRowMealDialogEditTableLayout(table, oldMeal.getIngredients().get(i), oldMeal.getUnits().get(i), user);
        }

        newFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowMealDialogTableLayout(table, user);
            }
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Meal> mealList = controller.getMealList(user);
                boolean mealExists = false;
                if (!name.getText().toString().equals("") && table.getChildCount() > 1) {

                    List<Food> foodList = new ArrayList<Food>();
                    List<Unit> unitList = new ArrayList<Unit>();
                    List<Food> allfoodsList = controller.getFoodList(user);
                    for (int i = 0; i < table.getChildCount() - 1; i++) {
                        Spinner foodSpinner = (Spinner) ((TableRow) table.getChildAt(i)).getChildAt(0);
                        EditText quantity = (EditText) ((TableRow) table.getChildAt(i)).getChildAt(1);
                        Spinner unitSpinner = (Spinner) ((TableRow) table.getChildAt(i)).getChildAt(2);
                        if (!quantity.getText().toString().equals("")) {
                            Food newFood = (Food) foodSpinner.getSelectedItem();
                            for (Food food : foodList) {
                                if (food.getName().equals(newFood.getName())) {
                                    readyForSaving = false;
                                    Toast.makeText(context, "Lebensmittel " + food.getName() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                }
                            }
                            if (readyForSaving) {
                                foodList.add(newFood);

                                Unit newUnit = (Unit) unitSpinner.getSelectedItem();
                                newUnit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                unitList.add(newUnit);
                            }

                        }
                    }
                    int cals = 0;
                    for (int i = 0; i < foodList.size(); i++) {
                        for (Food food : allfoodsList) {
                            if (foodList.get(i).getName().equals(food.getName())) {
                                for (Unit newUnit : foodList.get(i).getUnits()) {
                                    for (Unit unit : food.getUnits()) {
                                        if (newUnit.getUnit().equals(unit.getUnit()))
                                            cals = cals + ((food.getKCal() / unit.getQuantity()) * unitList.get(i).getQuantity());
                                    }
                                }
                            }
                        }
                    }
                    Meal newMeal = new Meal(name.getText().toString(), (ArrayList<Food>) foodList, (ArrayList<Unit>) unitList, cals);
                    controller.editMeal(oldMeal, newMeal, user);
                    ((ManagerActivity)context).updateView();
                    Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    public void CreateMealLineDialog(final Meal meal, final RowFactory rowFactory, final User user)
    {
        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_manager_entry, null);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CreateEditMealDialog(meal, rowFactory, user);
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteMeal(meal, user);
                ((ManagerActivity)context).updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
