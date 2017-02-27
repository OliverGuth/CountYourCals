package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Oliver Guth
 */

public class ButtonTextImage extends RelativeLayout {

    //Constructor
    public ButtonTextImage(Context context, int id, int color) {
        super(context);
        if (!(context instanceof Activity)) //if context is not Activity the View can't supplied with ID's
            return;

        View v = ((Activity) context).findViewById(id);//find relative layout by id

        if (!(v instanceof RelativeLayout)) //is it RelativeLayout ?
            return;

        RelativeLayout layout = (RelativeLayout) v;//cast it to relative layout

        //copy layout parameters
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        this.setLayoutParams(params);

        //Create base-button
        Button bt = new Button(context);
        bt.setBackgroundColor(color);
        this.setBackgroundDrawable(bt.getBackground());

        //copy all children from relative layout to this button
        while (layout.getChildCount() > 0) {
            View vchild = layout.getChildAt(0);
            layout.removeViewAt(0);
            this.addView(vchild);

            //if child is textView set its color to white
            if (vchild instanceof TextView) {
                ((TextView) vchild).setTextColor(Color.WHITE);
            }

            //child views can't be clicked and focused
            vchild.setClickable(false);
            vchild.setFocusable(false);
            vchild.setFocusableInTouchMode(false);
        }

        //remove all view from layout
        layout.removeAllViews();
        bt.setBackgroundColor(color);

        //set that this button is clickable, focusable, ...
        this.setBackgroundColor(getResources().getColor(color));
        this.setClickable(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(false);


        //replace relative layout in parent with this modified version
        ViewGroup vp = (ViewGroup) layout.getParent();
        int index = vp.indexOfChild(layout);
        vp.removeView(layout);
        vp.addView(this, index);

        this.setId(id);
    }

    //set text for the text view
    public void setText(int id, CharSequence text) {
        View v = findViewById(id);
        if (null != v && v instanceof TextView) {
            ((TextView) v).setText(text);
        }
    }

    //assign background to relative layout
    public void setBackgroundColor(int id, int color) {
        View v = findViewById(id);
        if (null != v && v instanceof RelativeLayout) {
            ((RelativeLayout) v).getChildAt(0).setBackgroundResource(color);
        }
    }

    //set image by resource id
    public void setImageResource(int id, int image_resource_id) {
        View v = findViewById(id);
        if (null != v && v instanceof ImageView) {
            ((ImageView) v).setImageResource(image_resource_id);
        }
    }
}