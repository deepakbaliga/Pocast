package com.deepakbaliga.pocast;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by deezdroid on 23/12/15.
 */
public class PocastApp extends Application {

    public  static Typeface robotoBlack;
    public  static Typeface robotoBold;
    public  static Typeface robotoLight;
    public  static Typeface robotoMedium;
    public  static Typeface robotoRegular;
    public  static Typeface robotoThin;

    @Override
    public void onCreate() {
        super.onCreate();

        initTypeface();
    }

    private void initTypeface(){

            robotoBlack = Typeface.createFromAsset(getAssets(), "font/roboto_black.ttf");
            robotoBold = Typeface.createFromAsset(getAssets(), "font/roboto_bold.ttf");
            robotoLight = Typeface.createFromAsset(getAssets(), "font/roboto_light.ttf");
            robotoMedium = Typeface.createFromAsset(getAssets(), "font/roboto_medium.ttf");
            robotoRegular = Typeface.createFromAsset(getAssets(), "font/roboto_regular.ttf");
            robotoThin = Typeface.createFromAsset(getAssets(), "font/roboto_thin.ttf");
    }
}
