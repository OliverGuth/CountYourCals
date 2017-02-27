package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.Models.User;
import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.controller.DataStorageController;

/**
 * Created by Oliver Guth
 */

public class DialogFactoryUser {
    private DataStorageController controller;
    private Context context;

    //Constructor
    public DialogFactoryUser(Context context) {
        this.controller = new DataStorageController(context);
        this.context = context;
    }

    //create new user via dialog
    public void CreateNewUserDialog(String username) {
        //initialize dialog and find its children by id
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_new_user, null);
        dialogBuilder.setView(view);
        final android.app.AlertDialog dialog = dialogBuilder.create();
        final EditText name = (EditText) view.findViewById(R.id.DialogNewUserName);
        final EditText maxCals = (EditText) view.findViewById(R.id.DialogNewUserMaxCals);
        final Spinner gender = (Spinner) view.findViewById(R.id.DialogNewUserGenderSpinner);
        final Spinner language = (Spinner) view.findViewById(R.id.DialogNewUserLanguageSpinner);
        Button create = (Button) view.findViewById(R.id.DialogNewUserCreateButton);

        //set name from login dialog
        name.setText(username);

        //Handle click on create button
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<User> userlist = controller.getUserList(); //get existing user list from controller

                //check if user already exists
                boolean userExists = false;
                if (!name.getText().toString().equals("") && !maxCals.getText().toString().equals("")) {
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
                        //if not already exists, create user object and save it via the controller
                        User user = new User(name.getText().toString(), gender.getSelectedItem().toString().charAt(0), Integer.parseInt(maxCals.getText().toString()), language.getSelectedItem().toString());
                        controller.addUser(user);
                        Toast.makeText(context, "User angelegt", Toast.LENGTH_LONG).show();
                        dialog.cancel();    //close dialog
                    }
                }
            }
        });
        dialog.show(); //show dialog to the user
    }

    //Create login dialog to authenticate user at Start
    public void CreateLoginDialog(final MainActivity mainActivity, final Context context) {
        //initialize dialog and find its children by id
        android.app.AlertDialog.Builder dialogBuilder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_login, null);
        dialogBuilder.setView(view);
        final android.app.AlertDialog dialog = dialogBuilder.create();
        final EditText loginUserName = (EditText) view.findViewById(R.id.LoginNameValue);
        Button login = (Button) view.findViewById(R.id.LoginButton);
        Button register = (Button) view.findViewById(R.id.LoginRegisterButton);

        //handle click on login button
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<User> userlist = controller.getUserList();//get existing user list from controller
                if (userlist != null) {
                    for (User user : userlist) {
                        if (loginUserName.getText().toString().trim().equals(user.getName())) {
                            //if user is found in user list
                            Toast.makeText(context, "Eingeloggt als " + user.getName(), Toast.LENGTH_LONG).show();
                            dialog.setCancelable(true);
                            dialog.cancel();
                            mainActivity.user = user;
                            break;
                        }
                    }
                }
                if (mainActivity.user == null) {
                    Toast.makeText(context, "login failed", Toast.LENGTH_LONG).show();
                    CreateNewUserDialog(loginUserName.getText().toString());
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CreateNewUserDialog(loginUserName.getText().toString());
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
