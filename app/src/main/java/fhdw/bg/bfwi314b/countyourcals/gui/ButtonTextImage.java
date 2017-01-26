package fhdw.bg.bfwi314b.countyourcals.gui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import fhdw.bg.bfwi314b.countyourcals.R;

public class ButtonTextImage extends RelativeLayout {

    public ButtonTextImage(Context context, int id, int color)
    {
        super(context);

        // if our context is not Activity we can't get View supplied by id
        if (!(context instanceof Activity))
            return;

        // find relative layout by id
        View v = ((Activity)context).findViewById(id);

        // is it RelativeLayout ?
        if (!(v instanceof RelativeLayout))
            return;

        //cast it to relative layout
        RelativeLayout layout = (RelativeLayout)v;

        // copy layout parameters
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        this.setLayoutParams(params);

        // here I am using temporary instance of Button class
        // to get standard button background and to get button text color

        Button bt = new Button(context);

        // copy all child from relative layout to this button
        while (layout.getChildCount() > 0)
        {
            View vchild = layout.getChildAt(0);
            layout.removeViewAt(0);
            this.addView(vchild);

            // if child is textView set its color to standard buttong text colors
            // using temporary instance of Button class
            if (vchild instanceof TextView  )
            {
                ((TextView)vchild).setTextColor(Color.WHITE);
            }

            // just to be sure that child views can't be clicked and focused
            vchild.setClickable(false);
            vchild.setFocusable(false);
            vchild.setFocusableInTouchMode(false);
        }

        // remove all view from layout (maybe it's not necessary)
        layout.removeAllViews();

        // set that this button is clickable, focusable, ...
        this.setClickable(true);
        this.setFocusable(true);
        this.setFocusableInTouchMode(false);
        this.setBackgroundColor(color);

        // replace relative layout in parent with this one modified to looks like button
        ViewGroup vp = (ViewGroup)layout.getParent();
        int index = vp.indexOfChild(layout);
        vp.removeView(layout);
        vp.addView(this,index);

        this.setId(id);

    }

    // method for setting texts for the text views
    public void setText(int id, CharSequence text)
    {
        View v = findViewById(id);
        if (null != v && v instanceof TextView)
        {
            ((TextView)v).setText(text);
        }

    }
    // method for setting drawable for the images
    public void setImageDrawable(int id, Drawable drawable)
    {

        View v = findViewById(id);
        if (null != v && v instanceof ImageView)
        {
            ((ImageView)v).setImageDrawable(drawable);
        }

    }

    public void setBackgroundColor(int id, int color)
    {

        View v = findViewById(id);
        if (null != v && v instanceof RelativeLayout)
        {
            ((RelativeLayout)v).setBackgroundColor(color);
        }

    }

    // method for setting images by resource id
    public void setImageResource(int id, int image_resource_id)
    {

        View v = findViewById(id);
        if (null != v && v instanceof ImageView)
        {
            ((ImageView)v).setImageResource(image_resource_id);
        }

    }

}