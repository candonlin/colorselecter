package denny.colorselecter;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by DONG on 2017/10/6.
 * 功能描述:
 */

public class utils
{
    public static int convertDpToPixel(float dp, Context context) {

        Resources res = context.getResources();
        DisplayMetrics metrics  = res.getDisplayMetrics();

       float px = dp * (metrics.densityDpi / 160f);

        return (int)px;
    }

}
