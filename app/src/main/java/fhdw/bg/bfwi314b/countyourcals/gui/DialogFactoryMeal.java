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
 * Created by Oliver Guth
 */

public class DialogFactoryMeal {
    private DataStorageController controller;
    private Context context;

    //Constructor
    public DialogFactoryMeal(Context context) {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    //Dialog to create and save new Meal
    public void CreateNewMealDialog(final RowFactory rowFactory, final User user) {
        //initialize dialog and find its children by id
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewMealSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewMealName);
        final Button newFood = (Button) view.findViewById(R.id.DialogNewMealAddNewFoodButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewMealTableView);

        List<Food> foodList = controller.getFoodList(user); //get existing foods from controller
        if (foodList != null) { //there has to be existing foods in order to create a meal
            if (foodList.size() > 0) {

                //New Food rows will be added on click
                newFood.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        rowFactory.AddRowMealDialogTableLayout(table, user);//add food row
                    }
                });

                //Handles the click event to save a meal
                saveEntry.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        boolean readyForSaving = true;
                        List<Meal> mealList = controller.getMealList(user);//get all existing meals
                        boolean mealExists = false;

                        //if meal already exists, dont continue
                        if (!name.getText().toString().equals("")) {
                            if (table.getChildCount() > 1) {
                                if (mealList != null) {
                                    for (Meal meal : mealList) {
                                        if (name.getText().toString().trim().equals(meal.getName())) {
                                            Toast.makeText(context, "Eintrag " + meal.getName() + " existiert bereits", Toast.LENGTH_LONG).show();
                                            mealExists = true;
                                            break;
                                        }
                                    }
                                }
                                if (mealExists == false) { //if meal doesn't exist
                                    //Create a food object for each table row and add them to a list
                                    List<Food> foodList = new ArrayList<Food>();
                                    List<Unit> unitList = new ArrayList<Unit>();
                                    List<Food> allfoodsList = controller.getFoodList(user);//get food list from controller
                                    for (int i = 0; i < table.getChildCount() - 1; i++) {
                                        Spinner foodSpinner = (Spinner) ((TableRow) table.getChildAt(i)).getChildAt(0);
                                        EditText quantity = (EditText) ((TableRow) table.getChildAt(i)).getChildAt(1);
                                        Spinner unitSpinner = (Spinner) ((TableRow) table.getChildAt(i)).getChildAt(2);
                                        if (!(quantity.getText().toString().equals(""))) {
                                            Food newFood = (Food) foodSpinner.getSelectedItem();
                                            for (Food food : foodList) {
                                                if (food.getName().equals(newFood.getName())) {
                                                    readyForSaving = false;
                                                    Toast.makeText(context, "Lebensmittel " + food.getName() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            if (readyForSaving) {
                                                foodList.add(newFood);

                                                //add units according to added foods
                                                Unit newUnit = (Unit) unitSpinner.getSelectedItem();
                                                newUnit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                                unitList.add(newUnit);
                                            }
                                        } else {
                                            readyForSaving = false;
                                            Toast.makeText(context, "Bitte eine Menge eingeben", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    if (readyForSaving) {
                                        //calculate calories
                                        float cals = 0;
                                        for (int i = 0; i < foodList.size(); i++) {
                                            float originalCals = (float) foodList.get(i).getKCal();
                                            float realQuantity = (float) unitList.get(i).getQuantity();
                                            float originalQuantity = 0;
                                            for (Food food : allfoodsList) {
                                                if (food.getName().equals(foodList.get(i).getName())) {
                                                    for (Unit unit : food.getUnits()) {
                                                        if (unit.getUnit().equals(unitList.get(i).getUnit()))
                                                            originalQuantity = (float) unit.getQuantity();
                                                    }
                                                }
                                            }
                                            cals = cals + ((originalCals / originalQuantity) * realQuantity);
                                        }
                                        //create meal with the gathered information and save it via the controller
                                        Meal meal = new Meal(name.getText().toString(), (ArrayList<Food>) foodList, (ArrayList<Unit>) unitList, (int) cals);
                                        controller.addMeal(meal, user);
                                        ((ManagerActivity) context).updateView(); //update the view to show latest changes in manager activity
                                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                                        dialog.cancel();//close dialog
                                    }
                                }
                            } else //if no row is added
                                Toast.makeText(context, "Bitte mindestens ein Lebensmittel auswählen", Toast.LENGTH_LONG).show();
                        } else  //if no name was entered
                            Toast.makeText(context, "Bitte einen Namen eingeben", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();//show dialog to the user
            } else //if no food exists
                Toast.makeText(context, "Bitte erst ein Lebensmittel anlegen", Toast.LENGTH_LONG).show();
        }
    }

    //Dialog to edit and save a meal
    public void CreateEditMealDialog(final Meal oldMeal, final RowFactory rowFactory, final User user) {
        //initialize dialog and find its children by id
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewMealSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewMealName);
        final Button newFood = (Button) view.findViewById(R.id.DialogNewMealAddNewFoodButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewMealTableView);

        name.setText(oldMeal.getName()); //show name of the edited meal on screen

        //for each food in meal add a row in the table and fill it
        for (int i = 0; i < oldMeal.getIngredients().size(); i++) {
            rowFactory.AddRowMealDialogEditTableLayout(table, oldMeal.getIngredients().get(i), oldMeal.getUnits().get(i), user);
        }

        //New Food rows will be added on click
        newFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowMealDialogTableLayout(table, user);//add food row
            }
        });

        //Handles the click event to save a meal
        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                if (!name.getText().toString().equals("") && table.getChildCount() > 1) {   //if name is entered and at least one food row is added
                    List<Food> foodList = new ArrayList<Food>();
                    List<Unit> unitList = new ArrayList<Unit>();
                    List<Food> allfoodsList = controller.getFoodList(user); //get existing foods from controller
                    if (table.getChildCount() > 0) {
                        //check for double entries in food rows
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
                                if (readyForSaving) { //add foodsand units to lists
                                    foodList.add(newFood);

                                    Unit newUnit = (Unit) unitSpinner.getSelectedItem();
                                    newUnit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                    unitList.add(newUnit);
                                }
                            } else//if no quantity was set
                            {
                                readyForSaving = false;
                                Toast.makeText(context, "Bitte eine Menge angeben", Toast.LENGTH_LONG).show();
                            }
                        }
                        if (readyForSaving) {
                            //calculate calories
                            float cals = 0;
                            for (int i = 0; i < foodList.size(); i++) {
                                float originalCals = (float) foodList.get(i).getKCal();
                                float realQuantity = (float) unitList.get(i).getQuantity();
                                float originalQuantity = 0;
                                for (Food food : allfoodsList) {
                                    if (food.getName().equals(foodList.get(i).getName())) {
                                        for (Unit unit : food.getUnits()) {
                                            if (unit.getUnit().equals(unitList.get(i).getUnit()))
                                                originalQuantity = (float) unit.getQuantity();
                                        }
                                    }
                                }
                                cals = cals + ((originalCals / originalQuantity) * realQuantity);
                            }
                            //create meal with the gathered information and edit the existing one via the controller
                            Meal newMeal = new Meal(name.getText().toString(), (ArrayList<Food>) foodList, (ArrayList<Unit>) unitList, (int) cals);
                            controller.editMeal(oldMeal, newMeal, user);
                            ((ManagerActivity) context).updateView();   //update view of manager activity to show latest data changes
                            Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                            dialog.cancel(); //close this dialog
                        }
                    } else    //No food exists
                        Toast.makeText(context, "Bitte mindestens ein Lebensmittel angeben", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show(); //show dialog to user
    }

    //show options dialog to interact with a meal row
    public void CreateMealLineDialog(final Meal meal, final RowFactory rowFactory, final User user) {
        //initialize dialog and find its children by id
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_manager_entry, null);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        //Open edit dialog on click of the edit button then close the dialog
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CreateEditMealDialog(meal, rowFactory, user);
                dialog.cancel();
            }
        });

        //Delete selected meal on button click
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteMeal(meal, user);
                ((ManagerActivity) context).updateView();
                Toast.makeText(context, "Eintrag " + meal.getName() + " gelöscht!", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
        dialog.show();//show dialog to the user
    }
}
