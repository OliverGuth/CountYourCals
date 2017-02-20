package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth on 20.02.2017.
 */

public class DialogFactoryDiaryEntry {
    private DataStorageController controller;
    private Context context;

    public DialogFactoryDiaryEntry(Context context)
    {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    public void CreateNewDiaryEntryDialog(final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);
        final DatePicker date = (DatePicker) view.findViewById(R.id.DialogNewEntryDatePicker);
        final Spinner foodSpinner = (Spinner) view.findViewById(R.id.DialogNewEntryFoodSpinner);
        final Spinner mealSpinner = (Spinner) view.findViewById(R.id.DialogNewEntryMealSpinner);
        final EditText quantity = (EditText) view.findViewById(R.id.DialogNewEntryQuanitityValue);
        final Spinner unitSpinner = (Spinner) view.findViewById(R.id.DialogNewEntryUnitSpinner);
        final EditText calories = (EditText) view.findViewById(R.id.DialogNewEntryCaloriesValue);

        final List<Food> foodList = controller.getFoodList(user);
        final List<Meal> mealList = controller.getMealList(user);

        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foodList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodSpinner.setAdapter(adapter);

        adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mealList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mealSpinner.setAdapter(adapter);

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

        mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (!(foodList == null || mealList == null)) {

                        if (!quantity.getText().toString().equals("")) {
                            if (Integer.parseInt(quantity.getText().toString()) > 0)


                            Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }

                }
            }
        });
        dialog.show();

    }

    public void CreateDiaryEntryLineDialog(final DiaryEntry diaryEntry)
    {
/*
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
        */
    }

    public void CreateEditDiaryEntryDialog(DiaryEntry diaryEntry)
    {
        /*
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
        */
    }
}
