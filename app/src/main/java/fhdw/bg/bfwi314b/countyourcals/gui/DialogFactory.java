package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class DialogFactory {
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

    public void CreateNewFoodDialog(final Context context, final User user)
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
                List<Unit> unitList = controller.getUnitList(user.getName());
                boolean unitExists = false;
                if(!name.getText().toString().equals("") && !nameShort.getText().toString().equals("")) {
                    if (unitList != null) {
                        for (Unit unit : unitList) {
                            if (name.getText().toString().trim().equals(unit.getUnit())) {
                                Toast.makeText(context, "Eintrag " + user.getName() + " existiert bereits", Toast.LENGTH_LONG).show();
                                unitExists = true;
                                break;
                            }
                        }
                    }
                    if (unitExists == false) {
                        controller.addUnit(user.getName(), name.getText().toString(), nameShort.getText().toString(), 0);

                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        */
        dialog.show();
    }

    public void CreateNewMealDialog(final Context context, final User user)
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
                List<Unit> unitList = controller.getUnitList(user.getName());
                boolean unitExists = false;
                if(!name.getText().toString().equals("") && !nameShort.getText().toString().equals("")) {
                    if (unitList != null) {
                        for (Unit unit : unitList) {
                            if (name.getText().toString().trim().equals(unit.getUnit())) {
                                Toast.makeText(context, "Eintrag " + user.getName() + " existiert bereits", Toast.LENGTH_LONG).show();
                                unitExists = true;
                                break;
                            }
                        }
                    }
                    if (unitExists == false) {
                        controller.addUnit(user.getName(), name.getText().toString(), nameShort.getText().toString(), 0);

                        Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }
            }
        });
        */
        dialog.show();
    }

    public void CreateNewUnitDialog(final Context context, final User user)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_unit_entry, null);
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

    public void CreateEditDiaryEntryDialog(final Context context, DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);

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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_diary_entry_column, null);
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

    public void CreateEditLineDialog(final Context context, final Food food)
    {
        //DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_diary_entry_column, null);
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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_diary_entry_column, null);
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
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_diary_entry_column, null);
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
