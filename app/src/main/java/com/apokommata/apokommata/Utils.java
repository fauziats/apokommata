package com.apokommata.apokommata;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.EditText;

/**
 * Created by dito on 28/03/17.
 */

public class Utils {
    public static int dpToPx(int dp, Context context) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static boolean isEmpty(EditText editText){
        if (editText.getText().toString().trim().isEmpty())
            return true;
        return false;
    }
}
