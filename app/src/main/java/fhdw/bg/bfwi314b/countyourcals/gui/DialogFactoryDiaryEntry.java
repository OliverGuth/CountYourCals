package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.User;
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
