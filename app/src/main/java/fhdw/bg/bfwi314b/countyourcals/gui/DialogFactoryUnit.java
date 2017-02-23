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
 * Created by Oliver Guth on 23.02.2017.
 */

public class DialogFactoryUnit {
    private DataStorageController controller;
    private Context context;

    public DialogFactoryUnit(Context context)
    {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

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
                        ((ManagerActivity)context).updateView();
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
                        ((ManagerActivity)context).updateView();
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
                ((ManagerActivity)context).updateView();
                Toast.makeText(context, "Eintrag " + unit.getUnit() + " gelöscht", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
