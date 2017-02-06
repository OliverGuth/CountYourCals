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
import fhdw.bg.bfwi314b.countyourcals.Models.Unit;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.Models.DiaryEntry;

/**
 * Created by Oliver Guth on 29.01.2017.
 */

public class RowFactory {

    DialogFactory dialogFactory;

    public RowFactory(AccountActivity accountActivity)
    {
        dialogFactory = new DialogFactory(accountActivity);
    }

    public RowFactory(MainActivity mainActivity)
    {
        dialogFactory = new DialogFactory(mainActivity);
    }

    public RowFactory(ManagerActivity managerActivity)
    {
        dialogFactory = new DialogFactory(managerActivity);
    }

    public RowFactory(DiaryActivity diaryActivity)
    {
        dialogFactory = new DialogFactory(diaryActivity);
    }

    public void FillDiaryTableLayout(TableLayout table, final List<DiaryEntry> diaryEntries, final Context context)
    {
        table.removeAllViews();
        for(int i = 0; i < diaryEntries.size(); i++)
        {
            final DiaryEntry diaryEntry = diaryEntries.get(i);
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
            calories.setText(diaryEntries.get(i).getConsumedKCal().toString() + " kcal");

            edit.setClickable(true);
            row.setLongClickable(true);

            row.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, diaryEntry);
                return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, diaryEntry);
                    }

            });
            table.addView(row);
        }
    }

    public void FillMealTableLayout(TableLayout table, final List<Meal> meals, final Context context)
    {
        for(int i = 0; i < meals.size(); i++)
        {
            final Meal meal = meals.get(i);
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
                    dialogFactory.CreateEditLineDialog(context, meal);
                    return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, meal);
                }

            });
            table.addView(row);
        }
    }

    public void FillFoodTableLayout(TableLayout table, final List<Food> foods, final Context context)
    {
        for(int i = 0; i < foods.size(); i++)
        {
            final Food food = foods.get(i);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow row = (TableRow) inflater.inflate(R.layout.row_food ,null);
            TextView number = (TextView) row.getChildAt(0);
            TextView name = (TextView) row.getChildAt(1);
            RelativeLayout edit = (RelativeLayout) row.getChildAt(2);

            ((ImageView)edit.getChildAt(0)).setImageResource((R.drawable.edit));
            edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));



            number.setText(i+1 + ".");
            name.setText(foods.get(i).getName());

            edit.setClickable(true);
            row.setLongClickable(true);

            row.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, food);
                    return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, food);
                }

            });
            table.addView(row);
        }
    }

    public void FillUnitTableLayout(TableLayout table, final List<Unit> units, final Context context)
    {
        for(int i = 0; i < units.size(); i++)
        {
            final Unit unit = units.get(i);
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow row = (TableRow) inflater.inflate(R.layout.row_meal ,null);
            TextView number = (TextView) row.getChildAt(0);
            TextView name = (TextView) row.getChildAt(1);
            TextView calories = (TextView) row.getChildAt(2);
            RelativeLayout edit = (RelativeLayout) row.getChildAt(3);

            ((ImageView)edit.getChildAt(0)).setImageResource((R.drawable.edit));
            edit.setBackgroundColor(context.getResources().getColor(R.color.Grey));



            number.setText(i+1 + ".");
            name.setText(units.get(i).getUnit());
            calories.setText(units.get(i).getUnit());

            edit.setClickable(true);
            row.setLongClickable(true);

            row.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, unit);
                    return true;
                }

            });

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialogFactory.CreateEditLineDialog(context, unit);
                }

            });
            table.addView(row);
        }
    }
}
