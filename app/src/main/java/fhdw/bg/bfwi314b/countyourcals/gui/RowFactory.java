package fhdw.bg.bfwi314b.countyourcals.gui;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fhdw.bg.bfwi314b.countyourcals.R;
import fhdw.bg.bfwi314b.countyourcals.datastorage.DiaryEntry;

/**
 * Created by Oliver Guth on 29.01.2017.
 */

public class RowFactory {

    public void FillDiaryTableLayout(TableLayout table, List<DiaryEntry> diaryEntries, final Context context)
    {
        for(int i = 0; i < diaryEntries.size(); i++)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TableRow row = (TableRow) inflater.inflate(R.layout.table_diary_row,null);
            TextView number = (TextView) row.getChildAt(0);
            TextView name = (TextView) row.getChildAt(1);
            TextView calories = (TextView) row.getChildAt(2);
            Button edit = (Button) row.getChildAt(3);
            number.setText(i+1 + ".");
            name.setText(diaryEntries.get(i).getConsumedName());
            calories.setText(diaryEntries.get(i).getConsumedKCal());

            edit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                    View view = (View) LayoutInflater.from(context).inflate(R.layout.dialog_edit_column, null);
                    Button edit = (Button) view.findViewById(R.id.EditColumnButtonEdit);
                    Button delete = (Button) view.findViewById(R.id.EditColumnButtonDelete);

                    edit.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            table.addView(row);
        }
    }

}
