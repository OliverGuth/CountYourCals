package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;
import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.R;

/**
 * Created by Oliver Guth on 02.02.2017.
 */

public class DialogFactory {

    public void CreateNewDiaryEntryDialog(final Context context, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
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

    public void CreateEditDiaryEntryDialog(final Context context, DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
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

    public void CreateNewDiaryEntryDialog(final Context context, DiaryEntry diaryEntry, List<Food> foods, List<Meal> meals)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
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
}
