package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.OldModels.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.OldModels.Food;
import fhdw.bg.bfwi314b.countyourcals.OldModels.Meal;
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.R;

/**
 * Created by Oliver Guth on 02.02.2017.
 */

public class DialogFactory {
    private DiaryActivity diaryActivity;
    private AccountActivity accountActivity;
    private MainActivity mainActivity;
    private ManagerActivity managerActivity;

    private boolean result;

    public DialogFactory(DiaryActivity diaryActivity)
    {
        this.diaryActivity = diaryActivity;
    }

    public DialogFactory(AccountActivity accountActivity) { this.accountActivity = accountActivity; }

    public DialogFactory(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public DialogFactory(ManagerActivity managerActivity) { this.managerActivity = managerActivity; }


    public void CreateNewDiaryEntryDialog(final Context context, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
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
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //Eintrag überprüfen und Speichern
            }
        });
        dialog.show();
    }

    public void CreateNewFoodDialog(final Context context, List<Unit> units)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_food_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();
/*
        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);
        final DatePicker date = (DatePicker) view.findViewById(R.id.DialogNewEntryDatePicker);
        final Spinner food = (Spinner) view.findViewById(R.id.DialogNewEntryFoodSpinner);
        final Spinner meal = (Spinner) view.findViewById(R.id.DialogNewEntryMealSpinner);
        final EditText quantity = (EditText) view.findViewById(R.id.DialogNewEntryQuanitityValue);
        final Spinner unit = (Spinner) view.findViewById(R.id.DialogNewEntryUnitSpinner);
        final EditText calories = (EditText) view.findViewById(R.id.DialogNewEntryCaloriesValue);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //Eintrag überprüfen und Speichern
            }
        });
        */
        dialog.show();
    }

    public void CreateNewMealDialog(final Context context, List<Food> foods)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();
/*
        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);
        final DatePicker date = (DatePicker) view.findViewById(R.id.DialogNewEntryDatePicker);
        final Spinner food = (Spinner) view.findViewById(R.id.DialogNewEntryFoodSpinner);
        final Spinner meal = (Spinner) view.findViewById(R.id.DialogNewEntryMealSpinner);
        final EditText quantity = (EditText) view.findViewById(R.id.DialogNewEntryQuanitityValue);
        final Spinner unit = (Spinner) view.findViewById(R.id.DialogNewEntryUnitSpinner);
        final EditText calories = (EditText) view.findViewById(R.id.DialogNewEntryCaloriesValue);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //Eintrag überprüfen und Speichern
            }
        });
        */
        dialog.show();
    }

    public void CreateNewUnitDialog(final Context context)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewUnitSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUnitName);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Eintrag " + name.getText() + " gespeichert", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                //Eintrag überprüfen und Speichern
            }
        });
        dialog.show();
    }

    public void CreateEditDiaryEntryDialog(final Context context, DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals)
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

    public void CreateEditLineDialog(final Context context, final DiaryEntry diaryEntry)
    {

        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Zum aktuellen Tag kopiert", Toast.LENGTH_LONG).show();
                diaryActivity.entries.add(new DiaryEntry(Calendar.getInstance().getTime(),diaryEntry.getConsumedName(), diaryEntry.getConsumedQuantity(), diaryEntry.getConsumedUnit(), diaryEntry.getConsumedKCal(), 1000));
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

    public void CreateEditLineDialog(final Context context, final Food food)
    {
        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_LONG).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerActivity.foods.remove(food);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void CreateEditLineDialog(final Context context, final Meal meal)
    {
        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_LONG).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerActivity.meals.remove(meal);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void CreateEditLineDialog(final Context context, final Unit unit)
    {
        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_LONG).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerActivity.units.remove(unit);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

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
                if(loginUserName.getText().toString().trim().equals("Oliver"))
                {
                    Toast.makeText(context, "login successful", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(context, "login failed", Toast.LENGTH_LONG).show();
                    CreateNewUserDialog(context, loginUserName.getText().toString());
                    dialog.dismiss();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    CreateNewUserDialog(context, loginUserName.getText().toString());
                    dialog.dismiss();
                }});
        dialog.setCancelable(false);
        dialog.show();
    }

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
                Toast.makeText(context, "User created", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public void BeShureDialog(final Context context)
    {
        final String s = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Sind Sie sich sicher?");
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
