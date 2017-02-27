package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth
 */

public class DialogFactoryUnit {
    private DataStorageController controller;
    private Context context;

    //Controller
    public DialogFactoryUnit(Context context)
    {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    //Create new unit dialog
    public void CreateNewUnitDialog(final User user)
    {
        //initialize dialog and find its children by id
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewUnitSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUnitName);
        final EditText nameShort = (EditText) view.findViewById(R.id.DialogNewUnitNameShort);

        //specify click action for save button
        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Unit> unitList = controller.getUnitList(user); //get all existing units from controller
                boolean unitExists = false;

                //check if unit already exists
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
                    //if not exists: save unit via the controller, update the view and close this dialog
                    if (unitExists == false) {
                        Unit unit = new Unit(name.getText().toString(), nameShort.getText().toString(), 0);
                        controller.addUnit(unit, user);
                        ((ManagerActivity)context).updateView();
                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.cancel();    //close dialog
                    }
                }
            }
        });
        dialog.show(); //show dialog to the user
    }

    //edit existing unit dialog
    public void CreateEditUnitDialog(final Unit oldUnit, final User user)
    {
        //initialize dialog and find its children by id
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
        dialogBuilder.setView(view);

        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewUnitSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUnitName);
        final EditText nameShort = (EditText) view.findViewById(R.id.DialogNewUnitNameShort);

        //set known information from unit to be edited
        name.setText(oldUnit.getUnit());
        nameShort.setText(oldUnit.getUnitShort());

        //handle click on save button
        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<Unit> unitList = controller.getUnitList(user); //get unitlist from controller
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
                        //if not exists: save unit via the controller, update the view and close this dialog
                        Unit newUnit = new Unit(name.getText().toString(), nameShort.getText().toString(), 0);
                        controller.editUnit(oldUnit, newUnit, user);
                        ((ManagerActivity)context).updateView();
                        Toast.makeText(context, "Eintrag " + oldUnit.getUnit() + " geändert", Toast.LENGTH_LONG).show();
                        dialog.dismiss(); //close this dialog
                    }
                }
            }
        });
        dialog.show();  //show dialog to the user
    }

    //show options dialog to interact with a unit row
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
                ((ManagerActivity)context).updateView();
                Toast.makeText(context, "Eintrag " + unit.getUnit() + " gelöscht", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
