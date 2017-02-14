package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.content.DialogInterface;
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

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth on 02.02.2017.
 */

public class DialogFactory
{
    private DiaryActivity diaryActivity;
    private AccountActivity accountActivity;
    private MainActivity mainActivity;
    private ManagerActivity managerActivity;
    private DataStorageController controller;

    private boolean result;

    public DialogFactory(DiaryActivity diaryActivity)
    {
        this.diaryActivity = diaryActivity;
        controller = new DataStorageController(this.diaryActivity.getBaseContext());
    }

    public DialogFactory(AccountActivity accountActivity)
    {
        this.accountActivity = accountActivity;
        controller = new DataStorageController(this.accountActivity.getBaseContext());
    }

    public DialogFactory(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
        controller = new DataStorageController(this.mainActivity.getBaseContext());
    }

    public DialogFactory(ManagerActivity managerActivity)
    {
        this.managerActivity = managerActivity;
        controller = new DataStorageController(this.managerActivity.getBaseContext());
    }



    //---- Diary ----

    public void CreateNewDiaryEntryDialog(final Context context, final User user)
    {
        /*AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);
        final DatePicker date = (DatePicker) view.findViewById(R.id.DialogNewEntryDatePicker);
        final Spinner food = (Spinner) view.findViewById(R.id.DialogNewEntryFoodSpinner);
        final Spinner meal = (Spinner) view.findViewById(R.id.DialogNewEntryMealSpinner);
        final EditText quantity = (EditText) view.findViewById(R.id.DialogNewEntryQuanitityValue);
        final Spinner unit = (Spinner) view.findViewById(R.id.DialogNewEntryUnitSpinner);
        final EditText calories = (EditText) view.findViewById(R.id.DialogNewEntryCaloriesValue);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Food> foodList = controller.getFoodList(user.getName());
                List<Meal> unitList = controller.getMealList(user.getName());
                if(((food.getSelectedItemPosition() != 0 && meal.getSelectedItemPosition() == 0) || (food.getSelectedItemPosition() == 0 && meal.getSelectedItemPosition() != 0)) && !(food.getSelectedItemPosition() == 0 && meal.getSelectedItemPosition() == 0))
                {
                    if(!quantity.getText().toString().equals(""))
                    {
                        if(Integer.parseInt(quantity.getText().toString()) > 0)
                            controller.addDiaryEntry(user.getName(), name.getText().toString(), nameShort.getText().toString(), 0);

                            Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
        */
    }

    public void CreateDiaryEntryLineDialog(final Context context, final DiaryEntry diaryEntry)
    {

        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_diary_entry_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Zum aktuellen Tag kopiert", Toast.LENGTH_LONG).show();
                //diaryActivity.entries.add(new DiaryEntry(Calendar.getInstance().getTime(),diaryEntry.getConsumedName(), diaryEntry.getConsumedQuantity(), diaryEntry.getConsumedUnit(), diaryEntry.getConsumedKCal(), 1000));

                diaryActivity.updateView();
                dialog.dismiss();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edited", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BeShureDialog(context);
                if(result == true) {
                    diaryActivity.entries.remove(diaryEntry);
                    diaryActivity.updateView();
                    Toast.makeText(context, "deleted", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }

    public void CreateEditDiaryEntryDialog(final Context context, DiaryEntry diaryEntry)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);

        //load data from DiaryEntry

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_LONG).show();

                //Eintrag überprüfen und Speichern
            }
        });
        dialogBuilder.setView(view);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    //---- Meal ----

    public void CreateNewMealDialog(final Context context, final RowFactory rowFactory, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewMealSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewMealName);
        final EditText cals = (EditText) view.findViewById(R.id.DialogNewMealCalories);
        final Button newFood = (Button) view.findViewById(R.id.DialogNewMealAddNewFoodButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewMealTableView);

        newFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowMealDialogTableLayout(table, user, context);
            }
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Meal> mealList = controller.getMealList(user);
                boolean mealExists = false;
                if(!name.getText().toString().equals("") && !cals.getText().toString().equals("") && table.getChildCount() >1) {
                    if (mealList != null) {
                        for (Meal meal : mealList) {
                            if (name.getText().toString().trim().equals(meal.getName())) {
                                //Toast.makeText(context, "Eintrag " + food.getUnit() + " existiert bereits", Toast.LENGTH_LONG).show();
                                mealExists = true;
                                break;
                            }
                        }
                    }
                    if (mealExists == false)
                    {
                        List<Food> foodList = new ArrayList<Food>();
                        for(int i = 0; i<table.getChildCount()-1; i++) {
                            EditText quantity = (EditText)((TableRow)table.getChildAt(i)).getChildAt(0);
                            Spinner unitSpinner = (Spinner)((TableRow)table.getChildAt(i)).getChildAt(1);
                            if(!quantity.getText().toString().equals(""))
                            {
                                Unit newUnit = new Unit(((Unit) unitSpinner.getSelectedItem()).getUnit(), ((Unit) unitSpinner.getSelectedItem()).getUnitShort(), Integer.parseInt(quantity.getText().toString()));
                                for (Unit unit : unitList)
                                {
                                    if (unit.getUnit().equals(newUnit.getUnit())) {
                                        readyForSaving = false;
                                        Toast.makeText(context, "Entsprechung " + unit.getUnit() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                    }
                                }
                                if (readyForSaving) unitList.add(newUnit);

                            }
                        }
                        Food food = new Food(name.getText().toString(), (ArrayList<Unit>)unitList, Integer.parseInt(cals.getText().toString()));
                        controller.addFood(food, user);
                        managerActivity.updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateMealLineDialog(final Context context, final Meal meal, final RowFactory rowFactory, final User user)
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
                CreateEditMealDialog(context, meal, rowFactory, user);
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteMeal(meal, user);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    public void CreateEditMealDialog(final Context context, final Meal meal, final RowFactory rowFactory, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewMealSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewMealName);
        final EditText cals = (EditText) view.findViewById(R.id.DialogNewMealCalories);
        final Button newFood = (Button) view.findViewById(R.id.DialogNewMealAddNewFoodButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewMealTableView);

        name.setText(meal.getName());
        cals.setText(meal.getKCal().toString());

        for (Food food : meal.getIngredients())
        {
            rowFactory.AddRowMealDialogTableLayout(table, food, user, context);
        }

        newFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowMealDialogTableLayout(table, user, context);
            }
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Food> foodList = controller.getFoodList(user);
                List<Unit> unitList = new ArrayList<Unit>();
                boolean foodExists = false;
                if(!name.getText().toString().equals("") && !cals.getText().toString().equals("") && table.getChildCount() >1)
                {
                    for(int i = 0; i<table.getChildCount()-1; i++)
                    {
                        EditText quantity = (EditText)((TableRow)table.getChildAt(i)).getChildAt(0);
                        Spinner unitSpinner = (Spinner)((TableRow)table.getChildAt(i)).getChildAt(1);

                        if(quantity.getText().toString().equals(""))
                        {
                            readyForSaving = false;
                            Toast.makeText(context, "Menge nicht angegeben", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Unit newUnit = new Unit(((Unit) unitSpinner.getSelectedItem()).getUnit(), ((Unit) unitSpinner.getSelectedItem()).getUnitShort(), Integer.parseInt(quantity.getText().toString()));
                            for (Unit unit : unitList) {
                                if (unit.getUnit().equals(newUnit.getUnit()))
                                {
                                    readyForSaving = false;
                                    Toast.makeText(context, "Entsprechung " + unit.getUnit() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                }
                            }
                            if (readyForSaving) unitList.add(newUnit);
                        }
                    }
                }
                if(readyForSaving)
                {
                    Food newFood = new Food(name.getText().toString(), (ArrayList<Unit>) unitList, Integer.parseInt(cals.getText().toString()));
                    controller.editFood(food, newFood, user);
                    managerActivity.updateView();
                    Toast.makeText(context, "Eintrag " + food.getName() + " editiert", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    //---- Food ----

    public void CreateNewFoodDialog(final Context context, final RowFactory rowFactory, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_food_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewFoodSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewFoodName);
        final EditText cals = (EditText) view.findViewById(R.id.DialogNewFoodCalories);
        final Button newUnit = (Button) view.findViewById(R.id.DialogNewFoodAddNewUnitButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewFoodTableView);

        newUnit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowFoodDialogTableLayout(table, user, context);
            }
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Food> foodList = controller.getFoodList(user);
                boolean foodExists = false;
                if(!name.getText().toString().equals("") && !cals.getText().toString().equals("") && table.getChildCount() >1) {
                    if (foodList != null) {
                        for (Food food : foodList) {
                            if (name.getText().toString().trim().equals(food.getName())) {
                                //Toast.makeText(context, "Eintrag " + food.getUnit() + " existiert bereits", Toast.LENGTH_LONG).show();
                                foodExists = true;
                                break;
                            }
                        }
                    }
                    if (foodExists == false)
                    {
                        List<Unit> unitList = new ArrayList<Unit>();
                        for(int i = 0; i<table.getChildCount()-1; i++) {
                            EditText quantity = (EditText)((TableRow)table.getChildAt(i)).getChildAt(0);
                            Spinner unitSpinner = (Spinner)((TableRow)table.getChildAt(i)).getChildAt(1);
                            if(!quantity.getText().toString().equals(""))
                            {
                                Unit newUnit = new Unit(((Unit) unitSpinner.getSelectedItem()).getUnit(), ((Unit) unitSpinner.getSelectedItem()).getUnitShort(), Integer.parseInt(quantity.getText().toString()));
                                for (Unit unit : unitList)
                                {
                                    if (unit.getUnit().equals(newUnit.getUnit())) {
                                        readyForSaving = false;
                                        Toast.makeText(context, "Entsprechung " + unit.getUnit() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                    }
                                }
                                if (readyForSaving) unitList.add(newUnit);

                            }
                        }
                        Food food = new Food(name.getText().toString(), (ArrayList<Unit>)unitList, Integer.parseInt(cals.getText().toString()));
                        controller.addFood(food, user);
                        managerActivity.updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateEditFoodDialog(final Context context, final Food food, final RowFactory rowFactory, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_food_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewFoodSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewFoodName);
        final EditText cals = (EditText) view.findViewById(R.id.DialogNewFoodCalories);
        final Button newUnit = (Button) view.findViewById(R.id.DialogNewFoodAddNewUnitButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewFoodTableView);

        name.setText(food.getName());
        cals.setText(food.getKCal().toString());

        for (Unit unit : food.getUnits())
        {
            rowFactory.AddRowFoodDialogTableLayout(table, unit, user, context);
        }

        newUnit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowFoodDialogTableLayout(table, user, context);
            }
        });

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Food> foodList = controller.getFoodList(user);
                List<Unit> unitList = new ArrayList<Unit>();
                boolean foodExists = false;
                if(!name.getText().toString().equals("") && !cals.getText().toString().equals("") && table.getChildCount() >1)
                {
                    for(int i = 0; i<table.getChildCount()-1; i++)
                    {
                        EditText quantity = (EditText)((TableRow)table.getChildAt(i)).getChildAt(0);
                        Spinner unitSpinner = (Spinner)((TableRow)table.getChildAt(i)).getChildAt(1);

                        if(quantity.getText().toString().equals(""))
                        {
                            readyForSaving = false;
                            Toast.makeText(context, "Menge nicht angegeben", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Unit newUnit = new Unit(((Unit) unitSpinner.getSelectedItem()).getUnit(), ((Unit) unitSpinner.getSelectedItem()).getUnitShort(), Integer.parseInt(quantity.getText().toString()));
                            for (Unit unit : unitList) {
                                if (unit.getUnit().equals(newUnit.getUnit()))
                                {
                                    readyForSaving = false;
                                    Toast.makeText(context, "Entsprechung " + unit.getUnit() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                }
                            }
                            if (readyForSaving) unitList.add(newUnit);
                        }
                    }
                }
                if(readyForSaving)
                {
                    Food newFood = new Food(name.getText().toString(), (ArrayList<Unit>) unitList, Integer.parseInt(cals.getText().toString()));
                    controller.editFood(food, newFood, user);
                    managerActivity.updateView();
                    Toast.makeText(context, "Eintrag " + food.getName() + " editiert", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    public void CreateFoodLineDialog(final Context context, final Food food, final RowFactory rowFactory, final User user)
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
                CreateEditFoodDialog(context, food, rowFactory, user);
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteFood(food, user);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    //---- Unit ----

    public void CreateNewUnitDialog(final Context context, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewUnitSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUnitName);
        final EditText nameShort = (EditText) view.findViewById(R.id.DialogNewUnitNameShort);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Unit> unitList = controller.getUnitList(user);
                boolean unitExists = false;
                if(!name.getText().toString().equals("") && !nameShort.getText().toString().equals("")) {
                    if (unitList != null) {
                        for (Unit unit : unitList) {
                            if (name.getText().toString().trim().equals(unit.getUnit())) {
                                Toast.makeText(context, "Eintrag " + unit.getUnit() + " existiert bereits", Toast.LENGTH_LONG).show();
                                unitExists = true;
                                break;
                            }
                        }
                    }
                    if (unitExists == false) {
                        Unit unit = new Unit(name.getText().toString(), nameShort.getText().toString(), 0);
                        controller.addUnit(unit, user);
                        managerActivity.updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateEditUnitDialog(final Context context, final Unit oldUnit, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
        dialogBuilder.setView(view);


        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewUnitSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUnitName);
        final EditText nameShort = (EditText) view.findViewById(R.id.DialogNewUnitNameShort);

        name.setText(oldUnit.getUnit());
        nameShort.setText(oldUnit.getUnitShort());

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Unit> unitList = controller.getUnitList(user);
                boolean unitExists = false;
                if(!name.getText().toString().equals("") && !nameShort.getText().toString().equals("")) {
                    if (unitList != null) {
                        for (Unit unit : unitList) {
                            if (name.getText().toString().trim().equals(unit.getUnit()) && !name.getText().toString().equals(oldUnit.getUnit())) {
                                Toast.makeText(context, "Anderer Eintrag " + unit.getUnit() + " existiert bereits", Toast.LENGTH_LONG).show();
                                unitExists = true;
                                break;
                            }
                        }
                    }
                    if (unitExists == false) {
                        Unit newUnit = new Unit(name.getText().toString(), nameShort.getText().toString(), 0);
                        controller.editUnit(oldUnit, newUnit, user);
                        managerActivity.updateView();
                        Toast.makeText(context, "Eintrag " + oldUnit.getUnit() + " geändert", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateUnitLineDialog(final Context context, final Unit unit, final User user)
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
                CreateEditUnitDialog(context, unit, user);
                dialog.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteUnit(unit, user);
                managerActivity.updateView();
                Toast.makeText(context, "Eintrag " + unit.getUnit() + " gelöscht", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //---- User ----

    public void CreateNewUserDialog(final Context context, String username)
    {
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_user, null);
        dialogBuilder.setView(view);
        final android.app.AlertDialog dialog = dialogBuilder.create();
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUserName);
        final EditText maxCals = (EditText) view.findViewById(R.id.DialogNewUserMaxCals);
        final Spinner gender = (Spinner) view.findViewById(R.id.DialogNewUserGenderSpinner);
        final Spinner language = (Spinner) view.findViewById(R.id.DialogNewUserLanguageSpinner);

        name.setText(username);
        Button create = (Button) view.findViewById(R.id.DialogNewUserCreateButton);

        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<User> userlist = controller.getUserList();
                boolean userExists = false;
                if(!name.getText().toString().equals("") && !maxCals.getText().toString().equals("")) {
                    if (userlist != null) {
                        for (User user : userlist) {
                            if (name.getText().toString().trim().equals(user.getName())) {
                                Toast.makeText(context, "User " + user.getName() + " existiert bereits", Toast.LENGTH_LONG).show();
                                userExists = true;
                                break;
                            }
                        }
                    }
                    if (userExists == false) {
                        User user = new User(name.getText().toString(), gender.getSelectedItem().toString().charAt(0), Integer.parseInt(maxCals.getText().toString()), language.getSelectedItem().toString());
                        controller.addUser(user);

                        Toast.makeText(context, "User angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    //---- Other ----

    public void CreateLoginDialog(final Context context)
    {
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_login, null);
        dialogBuilder.setView(view);
        final android.app.AlertDialog dialog = dialogBuilder.create();
        final EditText loginUserName = (EditText) view.findViewById(R.id.LoginNameValue);
        Button login = (Button) view.findViewById(R.id.LoginButton);
        Button register = (Button) view.findViewById(R.id.LoginRegisterButton);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<User> userlist = controller.getUserList();
                if(userlist != null) {
                    for (User user : userlist) {
                        if (loginUserName.getText().toString().trim().equals(user.getName())) {
                            Toast.makeText(context, "Eingeloggt als " + user.getName(), Toast.LENGTH_LONG).show();
                            dialog.setCancelable(true);
                            dialog.cancel();
                            if (mainActivity != null)
                                mainActivity.user = user;
                            if (accountActivity != null) {
                                accountActivity.user = user;
                                accountActivity.fillUserData();
                            }
                            break;
                        }
                    }
                }
                if(mainActivity != null)
                {
                    if(mainActivity.user == null)
                    {
                        Toast.makeText(context, "login failed", Toast.LENGTH_LONG).show();
                        CreateNewUserDialog(context, loginUserName.getText().toString());
                    }
                }
                if(accountActivity != null)
                {
                    if(accountActivity.user == null)
                    {
                        Toast.makeText(context, "login failed", Toast.LENGTH_LONG).show();
                        CreateNewUserDialog(context, loginUserName.getText().toString());
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    CreateNewUserDialog(context, loginUserName.getText().toString());

                }});
        dialog.setCancelable(false);
        dialog.show();
    }

    public void BeShureDialog(final Context context)
    {
        final String s = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Sind Sie sicher?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = true;
            }
        });

        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result = false;
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
