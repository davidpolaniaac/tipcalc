package website.davidpolania.android.tipcal;

import android.app.Application;

/**
 * Created by PC on 29/06/2016.
 */
public class TipCalcApp extends Application{
    private final String ABOUT_URL="http://davidpolania.website";

    public String getABOUT_URL() {
        return ABOUT_URL;
    }
}
