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

public class DialogFactoryFood {
    private DataStorageController controller;
    private Context context;

    //Contructor
    public DialogFactoryFood(Context context) {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    //Creates add new food dialog
    public void CreateNewFoodDialog(final RowFactory rowFactory, final User user) {
        //initialize dialog and find its children by id
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_food_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewFoodSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewFoodName);
        final EditText cals = (EditText) view.findViewById(R.id.DialogNewFoodCalories);
        final Button newUnit = (Button) view.findViewById(R.id.DialogNewFoodAddNewUnitButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewFoodTableView);

        List<Unit> unitList = controller.getUnitList(user); //get list of all units from controller

        if (controller.getUnitList(user).size() > 0) { //only create dialog if units are saved

            //handle click event for creating new Unit line
            newUnit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    rowFactory.AddRowFoodDialogTableLayout(table, user);//add unit row to food dialog
                }
            });

            //handle click event for saving food
            saveEntry.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    boolean readyForSaving = true;
                    List<Food> foodList = controller.getFoodList(user);//get all existing foods from controller
                    boolean foodExists = false;

                    //find out if food already exists by comparing names
                    if (!name.getText().toString().equals("") && !cals.getText().toString().equals("") && table.getChildCount() > 1) {
                        if (foodList != null) {
                            for (Food food : foodList) {
                                if (name.getText().toString().trim().equals(food.getName())) {
                                    Toast.makeText(context, "Eintrag " + food.getName() + " existiert bereits", Toast.LENGTH_LONG).show();
                                    foodExists = true;
                                    break;
                                }
                            }
                        }
                        if (foodExists == false) {  //continue if food doesn't exist
                            //Create new unit object for each table line and check it before adding to the final unit list
                            List<Unit> unitList = new ArrayList<Unit>();
                            for (int i = 0; i < table.getChildCount() - 1; i++) {
                                EditText quantity = (EditText) ((TableRow) table.getChildAt(i)).getChildAt(0);
                                Spinner unitSpinner = (Spinner) ((TableRow) table.getChildAt(i)).getChildAt(1);
                                if (!quantity.getText().toString().equals("")) {
                                    Unit newUnit = new Unit(((Unit) unitSpinner.getSelectedItem()).getUnit(), ((Unit) unitSpinner.getSelectedItem()).getUnitShort(), Integer.parseInt(quantity.getText().toString()));
                                    for (Unit unit : unitList) {
                                        if (unit.getUnit().equals(newUnit.getUnit())) {
                                            readyForSaving = false;
                                            Toast.makeText(context, "Entsprechung " + unit.getUnit() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    if (readyForSaving) unitList.add(newUnit);

                                }
                            }
                            //Create final food object and save it via the controller
                            Food food = new Food(name.getText().toString(), (ArrayList<Unit>) unitList, Integer.parseInt(cals.getText().toString()));
                            controller.addFood(food, user);
                            ((ManagerActivity) context).updateView(); //update the managerActivity's view
                            Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                            dialog.cancel();//close dialog
                        }
                    }
                }
            });
            dialog.show(); //show dialog to the user
        } else    //if no unit exists
            Toast.makeText(context, "Bitte erst eine Einheit anlegen", Toast.LENGTH_LONG).show();
    }

    //Dialog to edit and save food
    public void CreateEditFoodDialog(final Food food, final RowFactory rowFactory, final User user) {
        //initialize dialog and find its children by id
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_food_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewFoodSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewFoodName);
        final EditText cals = (EditText) view.findViewById(R.id.DialogNewFoodCalories);
        final Button newUnit = (Button) view.findViewById(R.id.DialogNewFoodAddNewUnitButton);
        final TableLayout table = (TableLayout) view.findViewById(R.id.DialogNewFoodTableView);

        //set information of the object to be edited to the view
        name.setText(food.getName());
        cals.setText(food.getKCal().toString());

        for (Unit unit : food.getUnits()) {
            rowFactory.AddRowFoodDialogEditTableLayout(table, unit, user);//Add all existing items as rows to the dialog
        }

        //handle click event for creating new Unit line
        newUnit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rowFactory.AddRowFoodDialogTableLayout(table, user);
            }
        });

        //handle click event for saving food
        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean readyForSaving = true;
                List<Food> foodList = controller.getFoodList(user);//get list of foods from controller
                List<Unit> unitList = new ArrayList<Unit>();
                boolean foodExists = false;
                if (!name.getText().toString().equals("") && !cals.getText().toString().equals("") && table.getChildCount() > 1) //if mandatory information is provided, continue
                {
                    //Create unit obejcts for every tablechild if possible and add it to a list
                    for (int i = 0; i < table.getChildCount() - 1; i++) {
                        EditText quantity = (EditText) ((TableRow) table.getChildAt(i)).getChildAt(0);
                        Spinner unitSpinner = (Spinner) ((TableRow) table.getChildAt(i)).getChildAt(1);

                        if (quantity.getText().toString().equals("")) {
                            readyForSaving = false;
                            Toast.makeText(context, "Menge nicht angegeben", Toast.LENGTH_LONG).show();
                        } else {
                            Unit newUnit = new Unit(((Unit) unitSpinner.getSelectedItem()).getUnit(), ((Unit) unitSpinner.getSelectedItem()).getUnitShort(), Integer.parseInt(quantity.getText().toString()));
                            for (Unit unit : unitList) {
                                if (unit.getUnit().equals(newUnit.getUnit())) {
                                    readyForSaving = false;
                                    Toast.makeText(context, "Entsprechung " + unit.getUnit() + " kommt mehrmals vor", Toast.LENGTH_LONG).show();
                                }
                            }
                            if (readyForSaving) unitList.add(newUnit);
                        }
                    }
                }
                //save new food via controller if possible
                if (table.getChildCount() == 1) {
                    readyForSaving = false;
                    Toast.makeText(context, "keine Entsprechung angegeben", Toast.LENGTH_LONG).show();
                }
                if (readyForSaving) {
                    Food newFood = new Food(name.getText().toString(), (ArrayList<Unit>) unitList, Integer.parseInt(cals.getText().toString())); //create new food object
                    controller.editFood(food, newFood, user);   //save new food object via the controller
                    ((ManagerActivity) context).updateView();    //update ManagerActivity view with latest changes
                    Toast.makeText(context, "Eintrag " + food.getName() + " editiert", Toast.LENGTH_LONG).show();
                    dialog.cancel();//close dialog
                }
            }
        });
        dialog.show();//Show dialog to user
    }

    //show options dialog to interact with a food row
    public void CreateFoodLineDialog(final Food food, final RowFactory rowFactory, final User user) {
        //initialize dialog and its children.
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_manager_entry, null);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        //Handle actions on button click
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open edit food dialog and close this dialog
                CreateEditFoodDialog(food, rowFactory, user);
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //delete food, update the ManagerActivity-View and close this dialog
                controller.deleteFood(food, user);
                ((ManagerActivity) context).updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        dialog.show(); //Show this dialog to user
    }
}
