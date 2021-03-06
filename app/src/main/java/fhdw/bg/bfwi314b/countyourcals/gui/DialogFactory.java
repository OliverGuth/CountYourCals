package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;

/**
 * Created by Oliver Guth
 */

public class DialogFactory {
    private DialogFactoryUser dialogFactoryUser;
    private DialogFactoryDiaryEntry dialogFactoryDiaryEntry;
    private DialogFactoryFood dialogFactoryFood;
    private DialogFactoryMeal dialogFactoryMeal;
    private DialogFactoryUnit dialogFactoryUnit;
    private Activity caller;
    private Context context;

    //Constructor
    public DialogFactory(Context context) {
        this.dialogFactoryUser = new DialogFactoryUser(context);
        this.dialogFactoryDiaryEntry = new DialogFactoryDiaryEntry(context);
        this.dialogFactoryFood = new DialogFactoryFood(context);
        this.dialogFactoryMeal = new DialogFactoryMeal(context);
        this.dialogFactoryUnit = new DialogFactoryUnit(context);
        this.context = context;
        this.caller = (Activity) context;
    }

    //---- Diary ----

    //Calls method to create new diary entry dialog
    public void CreateNewDiaryEntryDialog(final User user) {
        dialogFactoryDiaryEntry.CreateNewDiaryEntryDialog(user);
    }

    //Calls method to create options dialog for diary entry line
    public void CreateDiaryEntryLineDialog(final DiaryEntry diaryEntry, final User user) {
        dialogFactoryDiaryEntry.CreateDiaryEntryLineDialog(diaryEntry, user);
    }

    //---- Meal ----

    //Calls method to create new meal dialog
    public void CreateNewMealDialog(final RowFactory rowFactory, final User user) {
        dialogFactoryMeal.CreateNewMealDialog(rowFactory, user);
    }

    //Calls method to create options dialog for meal line
    public void CreateMealLineDialog(final Meal meal, final RowFactory rowFactory, final User user) {
        dialogFactoryMeal.CreateMealLineDialog(meal, rowFactory, user);
    }

    //---- Food ----

    //Calls method to create new food dialog
    public void CreateNewFoodDialog(final RowFactory rowFactory, final User user) {
        dialogFactoryFood.CreateNewFoodDialog(rowFactory, user);
    }

    //Calls method to create options dialog for food line
    public void CreateFoodLineDialog(final Food food, final RowFactory rowFactory, final User user) {
        dialogFactoryFood.CreateFoodLineDialog(food, rowFactory, user);
    }

    //---- Unit ----

    //Calls method to create new unit dialog
    public void CreateNewUnitDialog(final User user) {
        dialogFactoryUnit.CreateNewUnitDialog(user);
    }

    //Calls method to create options dialog for unit line
    public void CreateUnitLineDialog(final Unit unit, final User user) {
        dialogFactoryUnit.CreateUnitLineDialog(unit, user);
    }

    //---- User ----

    //Calls method to create dialog to be authenticate the user.
    public void CreateLoginDialog() {
        dialogFactoryUser.CreateLoginDialog((MainActivity) caller, context);
    }

    //---- Other ----

    //Creates dialog to confirm factory reset
    public void BeSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Set message and Buttons
        builder.setMessage("Sind Sie sicher?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {    //Action for confirm-button
                ((SettingsActivity) caller).factoryReset();  //perform factory reset
            }
        });
        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {   //Action for decline-button
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing in decline
            }
        });

        //Create dialog and show it on screen
        AlertDialog alert = builder.create();
        alert.show();
    }

    //Creates dialog to be compliant to icons licenses
    public void CreateCreditsDialog() {
        //Create dialog according to corresponding layout file
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_credits, null);
        dialogBuilder.setView(view);
        final android.app.AlertDialog dialog = dialogBuilder.create();
        Button close = (Button) view.findViewById(R.id.DialogCreditsButtonClose);   //find close-button by id
        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.cancel();
            }
        }); //set action for close-button
        dialog.setCancelable(true); //dialog should be disposable
        dialog.show();  //show dialog on screen
    }
}
