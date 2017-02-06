package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
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

    public DialogFactory(DiaryActivity diaryActivity)
    {
        this.diaryActivity = diaryActivity;
    }

    public DialogFactory(AccountActivity accountActivity)
    {
        this.accountActivity = accountActivity;
    }

    public DialogFactory(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public DialogFactory(ManagerActivity managerActivity)
    {
        this.managerActivity = managerActivity;
    }


    public void CreateNewDiaryEntryDialog(final Context context, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
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
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                //Eintrag überprüfen und Speichern
            }
        });
        dialog.show();
    }

    public void CreateNewFoodDialog(final Context context, List<Unit> units)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_food_entry, null);
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
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_SHORT).show();
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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_meal_entry, null);
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
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_SHORT).show();
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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewUnitSaveButton);
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUnitName);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Eintrag " + name.getText() + " gespeichert", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                //Eintrag überprüfen und Speichern
            }
        });
        dialog.show();
    }

    public void CreateEditDiaryEntryDialog(final Context context, DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);

        //load data from DiaryEntry

        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);

        saveEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_SHORT).show();

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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_SHORT).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                diaryActivity.entries.remove(diaryEntry);
                diaryActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void CreateEditLineDialog(final Context context, final Food food)
    {
        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_SHORT).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerActivity.foods.remove(food);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_SHORT).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerActivity.meals.remove(meal);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "copy", Toast.LENGTH_SHORT).show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                managerActivity.units.remove(unit);
                managerActivity.updateView();
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void CreateLoginDialog(final Context context)
    {
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_login, null);
        final EditText loginUserName = (EditText) view.findViewById(R.id.LoginNameValue);
        Button login = (Button) view.findViewById(R.id.LoginButton);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(loginUserName.getText().toString().trim().equals("Oliver"))
                    Toast.makeText(context, "login successful", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "login failed", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setView(view);
        android.app.AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}
