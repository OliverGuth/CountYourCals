package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.Food;
import fhdw.bg.bfwi314b.countyourcals.Models.Meal;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;

/**
 * Created by Oliver Guth on 29.01.2017.
 */

public class RowFactory {

    DialogFactory dialogFactory;

    public RowFactory()
    {
        dialogFactory = new DialogFactory();
    }

    public void FillDiaryTableLayout(TableLayout table, final List<DiaryEntry> diaryEntries, final Context context)
    {
        for(int i = 0; i < diaryEntries.size(); i++)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow row = (TableRow) inflater.inflate(R.layout.row_diary_entry,null);
            TextView number = (TextView) row.getChildAt(0);
            TextView name = (TextView) row.getChildAt(1);
            TextView calories = (TextView) row.getChildAt(2);
            RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

            ((ImageView)edit.getChildAt(0)).setImageResource((R.drawable.edit));
            edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));



            number.setText(i+1 + ".");
            name.setText(diaryEntries.get(i).getConsumedName());
            calories.setText(diaryEntries.get(i).getConsumedKCal().toString());

            edit.setClickable(true);
            row.setLongClickable(true);

            row.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    dialogFactory.CreateEditLineDialog(context);
                return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateEditLineDialog(context);
                    }

            });
            table.addView(row);
        }
    }

    public void FillMealTableLayout(TableLayout table, final List<Meal> meals, final Context context)
    {
        for(int i = 0; i < meals.size(); i++)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow row = (TableRow) inflater.inflate(R.layout.row_meal ,null);
            TextView number = (TextView) row.getChildAt(0);
            TextView name = (TextView) row.getChildAt(1);
            TextView calories = (TextView) row.getChildAt(2);
            RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

            ((ImageView)edit.getChildAt(0)).setImageResource((R.drawable.edit));
            edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));



            number.setText(i+1 + ".");
            name.setText(meals.get(i).getName());
            calories.setText(meals.get(i).getName());

            edit.setClickable(true);
            row.setLongClickable(true);

            row.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    dialogFactory.CreateEditLineDialog(context);
                    return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateEditLineDialog(context);
                }

            });
            table.addView(row);
        }
    }

}
