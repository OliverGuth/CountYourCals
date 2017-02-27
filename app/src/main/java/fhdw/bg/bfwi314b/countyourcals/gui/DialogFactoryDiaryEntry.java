package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.*;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth
 */

public class DialogFactoryDiaryEntry {
    private DataStorageController controller;
    private Context context;

    public DialogFactoryDiaryEntry(Context context) //contructor
    {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    public void CreateNewDiaryEntryDialog(final User user)  //Create dialog to set up a new diary entry
    {
        //set layout to dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_new_diary_entry, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();

        //find views in layout by their id
        final Button saveEntry = (Button) view.findViewById(R.id.DialogNewEntrySaveButton);
        final DatePicker date = (DatePicker) view.findViewById(R.id.DialogNewEntryDatePicker);
        final Spinner foodSpinner = (Spinner) view.findViewById(R.id.DialogNewEntryFoodSpinner);
        final Spinner mealSpinner = (Spinner) view.findViewById(R.id.DialogNewEntryMealSpinner);
        final EditText quantity = (EditText) view.findViewById(R.id.DialogNewEntryQuanitityValue);
        final Spinner unitSpinner = (Spinner) view.findViewById(R.id.DialogNewEntryUnitSpinner);

        final List<Food> foodList = new ArrayList<Food>();
        final List<Meal> mealList = new ArrayList<Meal>();

        //Set default entries for food- and meal dropdown-lists
        foodList.add(new Food("Lebensmittel", new ArrayList<Unit>(), 0));
        mealList.add(new Meal("Mahlzeiten", new ArrayList<Unit>(), 0));

        //get lists of all foods and meals from the controller
        List<Food> tmpFoodList = controller.getFoodList(user);
        List<Meal> tmpMealList = controller.getMealList(user);

        //if lists are not null the food/meal objects will be added to the dropdown-lists
        if (tmpFoodList != null) foodList.addAll(tmpFoodList);
        if (tmpMealList != null) mealList.addAll(tmpMealList);

        //one List contains more than the default entries
        if (foodList.size() > 1 || mealList.size() > 1) {
            //if it is the food list to contain more than the default entries, continue
            if (foodList.size() > 1) {
                //set food-list to the dropdown
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foodList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodSpinner.setAdapter(adapter);

                //specify action if selection of food-dropdown was changed.
                foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Food) foodSpinner.getSelectedItem()).getUnits());//fill unit-dropdown with
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        if (position > 0) { //if selection is not the default selection
                            unitSpinner.setAdapter(adapter);    //fill dropdown with matching units
                            //set dropdown backgrounds
                            if (unitSpinner.getAdapter().getCount() > 0)
                                unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setSelection(0);//set default selection for other dropdown
                        } else if (mealSpinner.getSelectedItemPosition() == 0)  //if both dropdowns are on default position
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        } else {
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            } else    //if the food list contains only the default entry, continue
            {
                foodSpinner.setVisibility(Spinner.INVISIBLE);
            }

            if (mealList.size() > 1)  //if it is the meal list to contain more than the default entries, continue
            {
                //set meal-list to the dropdown
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mealList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mealSpinner.setAdapter(adapter);

                //if selection of meal dropdown is changed it will be handled here
                mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        List<Unit> units = new ArrayList<Unit>();
                        units.add(new Unit("Portion", "pt", 0));    //Meals should only measured in portions
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, units);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        if (position > 0) {  //if the selected item is not the default item
                            //unit dropdown is filled and backgrounds of other dropdowns are set
                            unitSpinner.setAdapter(adapter);
                            if (unitSpinner.getAdapter().getCount() > 0)
                                unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setSelection(0);//food dropdown has to show the default item
                        } else if (foodSpinner.getSelectedItemPosition() == 0)  //if both dropdowns selected the default item
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        } else {
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            } else //if the meal list contains only the default entry, continue
            {
                mealSpinner.setVisibility(Spinner.INVISIBLE);
            }

            //Handle the click on the save-button
            saveEntry.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (foodSpinner.getSelectedItemPosition() > 0 || mealSpinner.getSelectedItemPosition() > 0 && !(foodSpinner.getSelectedItemPosition() > 0 && mealSpinner.getSelectedItemPosition() > 0)) { //only one spinner not on default
                        if (!quantity.getText().toString().equals("")) {    //quantity has to be set
                            if (Integer.parseInt(quantity.getText().toString()) > 0)    //quantity has to be greater than zero
                            {
                                //get date from datepicker and provide it to calendar object
                                Calendar cal = Calendar.getInstance();
                                cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
                                //get unit
                                Unit unit = (Unit) unitSpinner.getSelectedItem();
                                unit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                if (foodSpinner.getSelectedItemPosition() > 0)//if food is the basis of the diary entry
                                {
                                    //calculate calories and save new entry
                                    List<Food> tmpFoodList = controller.getFoodList(user);
                                    float originalCals = ((float) ((Food) foodSpinner.getSelectedItem()).getKCal());
                                    float originalQuantity = 0;
                                    for (Unit u : tmpFoodList.get(foodSpinner.getSelectedItemPosition() - 1).getUnits())
                                        if (u.getUnit().equals(unit.getUnit()))
                                            originalQuantity = (float) u.getQuantity();
                                    float realQuantity = ((float) (Integer.parseInt(quantity.getText().toString())));
                                    float cals = (realQuantity / originalQuantity) * originalCals;
                                    controller.addDiaryEntry(new DiaryEntry(cal.getTime(), (Food) foodSpinner.getSelectedItem(), unit, (int) cals), user);
                                } else if (mealSpinner.getSelectedItemPosition() > 0)//if food is the basis of the diary entry
                                {
                                    //calculate calories and save new entry
                                    List<Meal> tmpMealList = controller.getMealList(user);
                                    float originalCals = ((float) ((Meal) mealSpinner.getSelectedItem()).getKCal());
                                    float realQuantity = ((float) (Integer.parseInt(quantity.getText().toString())));
                                    float cals = realQuantity * originalCals;
                                    controller.addDiaryEntry(new DiaryEntry(cal.getTime(), (Meal) mealSpinner.getSelectedItem(), unit, (int) cals), user);
                                }
                                try {
                                    ((DiaryActivity) context).updateView();  //update the diaryActivity view to show latest changed
                                } catch (Exception e) {
                                }
                                Toast.makeText(context, "Eintrag angelegt", Toast.LENGTH_LONG).show();
                                dialog.cancel(); //close dialog
                            }
                        }
                    }
                }
            });
            dialog.show();//show dialog to user
        } else//no List contains more than the default entries
            Toast.makeText(context, "Bitte erst Lebensmittel oder Mahlzeiten anlegen", Toast.LENGTH_LONG).show();
    }

    public void CreateEditDiaryEntryDialog(final DiaryEntry diaryEntry, final User user)//Create dialog to edit an existing diary entry
    {
        //set layout and initialize dialog elements
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

        //define default entries
        foodList.add(new Food("Lebensmittel", new ArrayList<Unit>(), 0));
        mealList.add(new Meal("Mahlzeiten", new ArrayList<Unit>(), 0));

        //add the right entry to the right list
        if (diaryEntry.getConsumedFood() != null)
            foodList.add(diaryEntry.getConsumedFood());
        else if (diaryEntry.getConsumedMeal() != null)
            mealList.add(diaryEntry.getConsumedMeal());

        //fill datepicker
        Calendar cal = Calendar.getInstance();
        cal.setTime(diaryEntry.getTimeStamp());
        date.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

        //set quantity value
        quantity.setText(diaryEntry.getConsumedUnit().getQuantity().toString());

        if (foodList.size() > 1 || mealList.size() > 1) //if one entry is set
        {
            if (foodList.size() > 1)//if set entry is a food
            {
                mealSpinner.setVisibility(Spinner.INVISIBLE);
                //Set food dropdown
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, foodList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                foodSpinner.setAdapter(adapter);
                foodSpinner.setSelection(1);

                //handle the event thrown if the selection of the dropdown changed
                foodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        //set units of the selected element to the dropdown
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Food) foodSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        if (position > 0) { //if selected item is not the default item
                            unitSpinner.setAdapter(adapter);
                            if (unitSpinner.getAdapter().getCount() > 0)
                                unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setSelection(0);
                        } else if (mealSpinner.getSelectedItemPosition() == 0) //if both selected items of the dropdowns are the default entries
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        } else {
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            }

            if (mealList.size() > 1)//if set entry is a meal
            {
                foodSpinner.setVisibility(Spinner.INVISIBLE);//food dropdown is not needed
                //set meal to dropdown
                ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, mealList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mealSpinner.setAdapter(adapter);
                mealSpinner.setSelection(1);

                //Handle Selection Changed Event of meal dropdown
                mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, ((Meal) mealSpinner.getSelectedItem()).getUnits());
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        if (position > 0) {//if selected entry is not the default entry
                            unitSpinner.setAdapter(adapter);
                            if (unitSpinner.getAdapter().getCount() > 0)
                                unitSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_green);
                            foodSpinner.setSelection(0);
                        } else if (foodSpinner.getSelectedItemPosition() == 0)//only default entries are selected
                        {
                            unitSpinner.setAdapter(adapter);
                            foodSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                            unitSpinner.setBackgroundResource(0);
                        } else {
                            mealSpinner.setBackgroundResource(R.drawable.spinner_border_grey);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        unitSpinner.setAdapter(null);
                    }
                });
            }

            saveEntry.setOnClickListener(new View.OnClickListener() {   //handles save-button Click event
                public void onClick(View v) {
                    if (foodSpinner.getSelectedItemPosition() > 0 || mealSpinner.getSelectedItemPosition() > 0 && !(foodSpinner.getSelectedItemPosition() > 0 && mealSpinner.getSelectedItemPosition() > 0)) { //if just one entry is selected
                        if (!quantity.getText().toString().equals("")) {//if quantity is entered
                            if (Integer.parseInt(quantity.getText().toString()) > 0) { //if entered quantity is greater than zero
                                //get date from datepicker and provide it to calendar object
                                Calendar cal = Calendar.getInstance();
                                cal.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
                                //get unit
                                Unit unit = (Unit) unitSpinner.getSelectedItem();
                                unit.setQuantity(Integer.parseInt(quantity.getText().toString()));
                                if (foodSpinner.getSelectedItemPosition() > 0)//if food is the basis of the diary entry
                                {
                                    //calculate calories and save new entry
                                    List<Food> tmpFoodList = controller.getFoodList(user);
                                    float originalCals = ((float) ((Food) foodSpinner.getSelectedItem()).getKCal());
                                    float originalQuantity = 0;
                                    for (Unit u : tmpFoodList.get(foodSpinner.getSelectedItemPosition() - 1).getUnits())
                                        if (u.getUnit().equals(unit.getUnit()))
                                            originalQuantity = (float) u.getQuantity();
                                    float realQuantity = ((float) (Integer.parseInt(quantity.getText().toString())));
                                    float cals = (realQuantity / originalQuantity) * originalCals;
                                    controller.editDiaryEntry(new DiaryEntry(cal.getTime(), (Food) foodSpinner.getSelectedItem(), unit, (int) cals), diaryEntry, user);
                                } else if (mealSpinner.getSelectedItemPosition() > 0)//if meal is the basis of the diary entry
                                {
                                    //calculate calories and save new entry
                                    List<Meal> tmpMealList = controller.getMealList(user);
                                    float x = ((float) ((Meal) mealSpinner.getSelectedItem()).getKCal());
                                    float y = 0;
                                    for (Unit u : tmpMealList.get(mealSpinner.getSelectedItemPosition() - 1).getUnits())
                                        if (u.getUnit().equals(unit.getUnit()))
                                            y = (float) u.getQuantity();
                                    float z = ((float) (Integer.parseInt(quantity.getText().toString())));
                                    float cals = (z / y) * x;
                                    controller.editDiaryEntry(new DiaryEntry(cal.getTime(), (Meal) mealSpinner.getSelectedItem(), unit, (int) cals), diaryEntry, user);
                                }
                                ((DiaryActivity) context).updateView();//update the DiaryActivity with the latest changes
                                Toast.makeText(context, "Eintrag gespeichert", Toast.LENGTH_LONG).show();
                                dialog.cancel(); //close dialog
                            }
                        }
                    }
                }
            });
            dialog.show(); //show dialog to user
        } else//if no food or meal is saved
            Toast.makeText(context, "Bitte erst Lebensmittel oder Mahlzeiten anlegen", Toast.LENGTH_SHORT).show();
    }

    public void CreateDiaryEntryLineDialog(final DiaryEntry diaryEntry, final User user) //dialog to show options regarding one particular diary entry
    {
        //Create and initialize dialog and its children
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        final android.app.AlertDialog dialog;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_diary_entry_column, null);
        final Button copy = (Button) view.findViewById(R.id.EditColumnButtonCopy);
        final Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
        final Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);
        dialogBuilder.setView(view);
        dialog = dialogBuilder.create();

        //Handles Click event für Copy-DiaryEntry-Button
        copy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Save new diary entry with same settings as the selected one. Timestamp is set to the
                if (diaryEntry.getConsumedFood() == null)
                    controller.addDiaryEntry(new DiaryEntry(Calendar.getInstance().getTime(), diaryEntry.getConsumedMeal(), diaryEntry.getConsumedUnit(), diaryEntry.getConsumedKCal()), user);
                else
                    controller.addDiaryEntry(new DiaryEntry(Calendar.getInstance().getTime(), diaryEntry.getConsumedFood(), diaryEntry.getConsumedUnit(), diaryEntry.getConsumedKCal()), user);
                Toast.makeText(context, "Zum aktuellen Tag kopiert", Toast.LENGTH_LONG).show();
                ((DiaryActivity) context).updateView();//update the DiaryActivity with the latest changes
                dialog.cancel();//close dialog
            }
        });

        //Handles Click event für Edit-DiaryEntry-Button
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CreateEditDiaryEntryDialog(diaryEntry, user);//edit entry with the edit dialog
                dialog.cancel();//close dialog
            }
        });

        //Handles Click event für Delete-DiaryEntry-Button
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.deleteDiaryEntry(diaryEntry, user); //delete the corresponding entry via the controller
                ((DiaryActivity) context).updateView();//update the DiaryActivity with the latest changes
                Toast.makeText(context, "deleted", Toast.LENGTH_LONG).show();
                dialog.cancel();//close dialog
            }
        });

        dialog.show();//show dialog to user
    }
}