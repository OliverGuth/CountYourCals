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
    private DialogFactoryFood dialogFactoryFood;
    private DialogFactoryMeal dialogFactoryMeal;
    private DialogFactoryUnit dialogFactoryUnit;
    private Activity caller;
    private Context context;

    public DialogFactory(Context context)
    {
        this.dialogFactoryUser = new DialogFactoryUser(context);
        this.dialogFactoryDiaryEntry = new DialogFactoryDiaryEntry(context);
        this.dialogFactoryFood = new DialogFactoryFood(context);
        this.dialogFactoryMeal = new DialogFactoryMeal(context);
        this.dialogFactoryUnit = new DialogFactoryUnit(context);
        this.controller = new DataStorageController(context);
        this.context = context;
        this.caller = (Activity)context;
    }

    //---- Diary ----

    public void CreateNewDiaryEntryDialog(final User user)
    {
        dialogFactoryDiaryEntry.CreateNewDiaryEntryDialog(user);
    }

    public void CreateDiaryEntryLineDialog(final DiaryEntry diaryEntry, final User user)
    {
        dialogFactoryDiaryEntry.CreateDiaryEntryLineDialog(diaryEntry, user);
    }

    //---- Meal ----

    public void CreateNewMealDialog(final RowFactory rowFactory, final User user)
    {
        dialogFactoryMeal.CreateNewMealDialog(rowFactory, user);
    }

    public void CreateMealLineDialog(final Meal meal, final RowFactory rowFactory, final User user)
    {
        dialogFactoryMeal.CreateMealLineDialog(meal, rowFactory, user);
    }

    //---- Food ----

    public void CreateNewFoodDialog(final RowFactory rowFactory, final User user)
    {
        dialogFactoryFood.CreateNewFoodDialog(rowFactory, user);
    }

    public void CreateFoodLineDialog(final Food food, final RowFactory rowFactory, final User user)
    {
        dialogFactoryFood.CreateFoodLineDialog(food, rowFactory, user);
    }

    //---- Unit ----

    public void CreateNewUnitDialog(final User user)
    {
        dialogFactoryUnit.CreateNewUnitDialog(user);
    }

    public void CreateUnitLineDialog(final Unit unit, final User user)
    {
        dialogFactoryUnit.CreateUnitLineDialog(unit, user);
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
                ((SettingsActivity)caller).factoryReset();
            }
        });
        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void CreateCreditsDialog()
    {
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_credits, null);
        dialogBuilder.setView(view);
        final android.app.AlertDialog dialog = dialogBuilder.create();
        Button close = (Button) view.findViewById(R.id.DialogCreditsButtonClose);
        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }
}
