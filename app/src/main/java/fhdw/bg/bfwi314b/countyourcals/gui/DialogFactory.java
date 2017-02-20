package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
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
    private DataStorageController controller;
    private DialogFactoryUser dialogFactoryUser;
    private DialogFactoryDiaryEntry dialogFactoryDiaryEntry;
    private Activity caller;
    private Context context;

    private boolean result;

    public DialogFactory(Context context)
    {
        this.dialogFactoryUser = new DialogFactoryUser(context);
        this.dialogFactoryDiaryEntry = new DialogFactoryDiaryEntry(context);
        this.controller = new DataStorageController(context);
        this.context = context;
        this.caller = (Activity)context;
    }

    //---- Diary ----

    public void CreateNewDiaryEntryDialog(final User user)
    {
        dialogFactoryDiaryEntry.CreateNewDiaryEntryDialog(user);
    }

    public void CreateEditDiaryEntryDialog(DiaryEntry diaryEntry)
    {
        dialogFactoryDiaryEntry.CreateEditDiaryEntryDialog(diaryEntry);
    }

    public void CreateDiaryEntryLineDialog(final DiaryEntry diaryEntry)
    {
        dialogFactoryDiaryEntry.CreateDiaryEntryLineDialog(diaryEntry);
    }

    //---- Meal ----

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
                        ((ManagerActivity)caller).updateView();
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
                    ((ManagerActivity)caller).updateView();
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
                //managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    //---- Food ----

    public void CreateNewFoodDialog(final RowFactory rowFactory, final User user)
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
                rowFactory.AddRowFoodDialogTableLayout(table, user);
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
                        ((ManagerActivity)caller).updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateEditFoodDialog(final Food food, final RowFactory rowFactory, final User user)
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
            rowFactory.AddRowFoodDialogEditTableLayout(table, unit, user);
        }

        newUnit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowFoodDialogTableLayout(table, user);
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

                if(table.getChildCount() == 1)
                {
                    readyForSaving = false;
                    Toast.makeText(context, "keine Entsprechung angegeben", Toast.LENGTH_LONG).show();
                }
                if(readyForSaving)
                {
                    Food newFood = new Food(name.getText().toString(), (ArrayList<Unit>) unitList, Integer.parseInt(cals.getText().toString()));
                    controller.editFood(food, newFood, user);
                    ((ManagerActivity)caller).updateView();
                    Toast.makeText(context, "Eintrag " + food.getName() + " editiert", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    public void CreateFoodLineDialog(final Food food, final RowFactory rowFactory, final User user)
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
                CreateEditFoodDialog(food, rowFactory, user);
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteFood(food, user);
                //managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    //---- Unit ----

    public void CreateNewUnitDialog(final User user)
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
                        ((ManagerActivity)caller).updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateEditUnitDialog(final Unit oldUnit, final User user)
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
                        ((ManagerActivity)caller).updateView();
                        Toast.makeText(context, "Eintrag " + oldUnit.getUnit() + " geändert", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        dialog.show();
    }

    public void CreateUnitLineDialog(final Unit unit, final User user)
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
                CreateEditUnitDialog(unit, user);
                dialog.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteUnit(unit, user);
                //managerActivity.updateView();
                Toast.makeText(context, "Eintrag " + unit.getUnit() + " gelöscht", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    //---- User ----

    public void CreateLoginDialog()
    {
        dialogFactoryUser.CreateLoginDialog((MainActivity)caller, context);
    }


    //---- Other ----

    public void BeShureDialog()
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
