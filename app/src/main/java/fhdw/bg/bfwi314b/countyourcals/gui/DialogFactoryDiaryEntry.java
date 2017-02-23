package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        final List<Food> foodList = new ArrayList<Food>();
        final List<Meal> mealList = new ArrayList<Meal>();

        foodList.add(new Food("Lebensmittel", new ArrayList<Unit>(), 0));
        mealList.add(new Meal("Mahlzeiten", new ArrayList<Unit>(), 0));

        List<Food> tmpFoodList = controller.getFoodList(user);
        List<Meal> tmpMealList = controller.getMealList(user);

        if(tmpFoodList != null) foodList.addAll(tmpFoodList);
        if(tmpMealList != null) mealList.addAll(tmpMealList);

        if(foodList.size() > 1 || mealList .size() > 1)
        {
            if (foodList.size() > 1)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foodList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodSpinner.setAdapter(adapter);

                foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Food) foodSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        if(position > 0) {
                            unitSpinner.setAdapter(adapter);
                            if(unitSpinner.getAdapter().getCount()>0) unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setSelection(0);
                        }
                        else if(mealSpinner.getSelectedItemPosition() ==0)
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        }
                        else {
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            }
            else
            {
                foodSpinner.setVisibility(Spinner.INVISIBLE);
            }

            if (mealList.size() > 1)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mealList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mealSpinner.setAdapter(adapter);

                mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Meal) mealSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        if(position > 0) {
                            unitSpinner.setAdapter(adapter);
                            if(unitSpinner.getAdapter().getCount()>0) unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setSelection(0);
                        }
                        else if(foodSpinner.getSelectedItemPosition() ==0)
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        }
                        else {
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            }
            else
            {
                mealSpinner.setVisibility(Spinner.INVISIBLE);
            }

            saveEntry.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    if (foodSpinner.getSelectedItemPosition() > 0 ||  mealSpinner.getSelectedItemPosition() > 0  && !(foodSpinner.getSelectedItemPosition() > 0 &&  mealSpinner.getSelectedItemPosition() > 0)) {
                        if (!quantity.getText().toString().equals("")) {
                            if (Integer.parseInt(quantity.getText().toString()) > 0)
                            {
                                Calendar cal = Calendar.getInstance();
                                cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
                                Unit unit = (Unit)unitSpinner.getSelectedItem();
                                unit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                if(foodSpinner.getSelectedItemPosition() > 0)
                                {
                                    List<Food> tmpFoodList = controller.getFoodList(user);
                                    float x = ((float)((Food)foodSpinner.getSelectedItem()).getKCal());
                                    float y = 0;
                                    for (Unit u : tmpFoodList.get(foodSpinner.getSelectedItemPosition()-1).getUnits())
                                        if(u.getUnit().equals(unit.getUnit()))
                                            y = (float)u.getQuantity();
                                    float z = ((float)(Integer.parseInt(quantity.getText().toString())));
                                    float cals = (z / y) * x;
                                    controller.addDiaryEntry(new DiaryEntry(cal.getTime(), (Food)foodSpinner.getSelectedItem(), unit, (int)cals), user);
                                }
                                else if(mealSpinner.getSelectedItemPosition() > 0)
                                {
                                    int cals = ((((Meal)mealSpinner.getSelectedItem()).getKCal() / ((Unit)unitSpinner.getSelectedItem()).getQuantity()) * Integer.parseInt(quantity.getText().toString()));
                                    controller.addDiaryEntry(new DiaryEntry(cal.getTime(), (Meal)mealSpinner.getSelectedItem(), unit, cals), user);
                                }
                                ((DiaryActivity)context).updateView();
                                Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        }
                    }
                }
            });
            dialog.show();
        }
        else
            Toast.makeText(context, "Bitte erst Lebensmittel oder Mahlzeiten anlegen", Toast.LENGTH_SHORT).show();
    }

    public void CreateEditDiaryEntryDialog(final DiaryEntry diaryEntry, final User user)
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

        final List<Food> foodList = new ArrayList<Food>();
        final List<Meal> mealList = new ArrayList<Meal>();

        foodList.add(new Food("Lebensmittel", new ArrayList<Unit>(), 0));
        mealList.add(new Meal("Mahlzeiten", new ArrayList<Unit>(), 0));

        if(diaryEntry.getConsumedFood() != null)
            foodList.add(diaryEntry.getConsumedFood());
        else if(diaryEntry.getConsumedMeal() != null)
            mealList.add(diaryEntry.getConsumedMeal());

        Calendar cal = Calendar.getInstance();
        cal.setTime(diaryEntry.getTimeStamp());
        date.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        quantity.setText(diaryEntry.getConsumedUnit().getQuantity().toString());

        if(foodList.size() > 1 || mealList .size() > 1)
        {
            if (foodList.size() > 1)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foodList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodSpinner.setAdapter(adapter);
                foodSpinner.setSelection(1);

                foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Food) foodSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        if(position > 0) {
                            unitSpinner.setAdapter(adapter);
                            if(unitSpinner.getAdapter().getCount()>0) unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setSelection(0);
                        }
                        else if(mealSpinner.getSelectedItemPosition() ==0)
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        }
                        else {
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            }
            else
            {
                foodSpinner.setVisibility(Spinner.INVISIBLE);
            }

            if (mealList.size() > 1)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mealList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mealSpinner.setAdapter(adapter);
                mealSpinner.setSelection(1);

                mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Meal) mealSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        if(position > 0) {
                            unitSpinner.setAdapter(adapter);
                            if(unitSpinner.getAdapter().getCount()>0) unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setSelection(0);
                        }
                        else if(foodSpinner.getSelectedItemPosition() ==0)
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        }
                        else {
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            }
            else
            {
                mealSpinner.setVisibility(Spinner.INVISIBLE);
            }

            saveEntry.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    if (foodSpinner.getSelectedItemPosition() > 0 || mealSpinner.getSelectedItemPosition() > 0 && !(foodSpinner.getSelectedItemPosition() > 0 && mealSpinner.getSelectedItemPosition() > 0)) {
                        if (!quantity.getText().toString().equals("")) {
                            if (Integer.parseInt(quantity.getText().toString()) > 0) {
                                Calendar cal = Calendar.getInstance();
                                cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
                                if (foodSpinner.getSelectedItemPosition() > 0) {
                                    int cals = (((Food) foodSpinner.getSelectedItem()).getKCal() / Integer.parseInt(quantity.getText().toString()) * ((Unit) unitSpinner.getSelectedItem()).getQuantity());
                                    controller.editDiaryEntry(new DiaryEntry(cal.getTime(), (Food) foodSpinner.getSelectedItem(), (Unit) unitSpinner.getSelectedItem(), cals), diaryEntry, user);
                                } else if (mealSpinner.getSelectedItemPosition() > 0) {
                                    int cals = (((Meal) mealSpinner.getSelectedItem()).getKCal() / Integer.parseInt(quantity.getText().toString()) * ((Unit) unitSpinner.getSelectedItem()).getQuantity());
                                    controller.editDiaryEntry(new DiaryEntry(cal.getTime(), (Meal) mealSpinner.getSelectedItem(), (Unit) unitSpinner.getSelectedItem(), cals), diaryEntry, user);
                                }
                                ((DiaryActivity)context).updateView();
                                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        }
                    }
                }
            });
            dialog.show();
        }
        else
            Toast.makeText(context, "Bitte erst Lebensmittel oder Mahlzeiten anlegen", Toast.LENGTH_SHORT).show();
    }

    public void CreateDiaryEntryLineDialog(final DiaryEntry diaryEntry, final User user)
    {
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

                if(diaryEntry.getConsumedFood() == null)
                    controller.addDiaryEntry(new DiaryEntry(Calendar.getInstance().getTime(), diaryEntry.getConsumedMeal(), diaryEntry.getConsumedUnit(), diaryEntry.getConsumedKCal()), user);
                else
                    controller.addDiaryEntry(new DiaryEntry(Calendar.getInstance().getTime(), diaryEntry.getConsumedFood(), diaryEntry.getConsumedUnit(), diaryEntry.getConsumedKCal()), user);
                Toast.makeText(context, "Zum aktuellen Tag kopiert", Toast.LENGTH_LONG).show();
                ((DiaryActivity)context).updateView();
                dialog.cancel();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CreateEditDiaryEntryDialog(diaryEntry, user);
                dialog.cancel();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //BeShureDialog(context);
                //if(result == true) {
                controller.deleteDiaryEntry(diaryEntry, user);
                ((DiaryActivity)context).updateView();
                Toast.makeText(context, "deleted", Toast.LENGTH_LONG).show();
                dialog.cancel();
                //}
            }
        });

        dialog.show();
    }
}