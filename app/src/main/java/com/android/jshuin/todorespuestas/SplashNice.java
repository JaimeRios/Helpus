package com.android.jshuin.todorespuestas;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

//extends AwesomeSplash!
public class SplashNice extends AwesomeSplash {

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!

    public static final String DROID_LOGO = "M 149.22,22.00\n" +
            "           C 148.23,20.07 146.01,16.51 146.73,14.32\n" +
            "             148.08,10.21 152.36,14.11 153.65,16.06\n" +
            "             153.65,16.06 165.00,37.00 165.00,37.00\n" +
            "             194.29,27.24 210.71,27.24 240.00,37.00\n" +
            "             240.00,37.00 251.35,16.06 251.35,16.06\n" +
            "             252.64,14.11 256.92,10.21 258.27,14.32\n" +
            "             258.99,16.51 256.77,20.08 255.78,22.00\n" +
            "             252.53,28.28 248.44,34.36 246.00,41.00\n" +
            "             252.78,43.16 258.78,48.09 263.96,52.85\n" +
            "             281.36,68.83 289.00,86.62 289.00,110.00\n" +
            "             289.00,110.00 115.00,110.00 115.00,110.00\n" +
            "             115.00,110.00 117.66,91.00 117.66,91.00\n" +
            "             120.91,76.60 130.30,62.72 141.04,52.85\n" +
            "             146.22,48.09 152.22,43.16 159.00,41.00\n" +
            "             159.00,41.00 149.22,22.00 149.22,22.00 Z\n" +
            "           M 70.80,56.00\n" +
            "           C 70.80,56.00 97.60,100.00 97.60,100.00\n" +
            "             101.34,106.21 108.32,116.34 110.21,123.00\n" +
            "             113.76,135.52 103.90,147.92 91.00,147.92\n" +
            "             78.74,147.92 74.44,139.06 69.00,130.00\n" +
            "             69.00,130.00 39.80,82.00 39.80,82.00\n" +
            "             35.73,75.29 28.40,66.08 29.20,58.00\n" +
            "             30.26,47.20 38.61,40.47 49.00,39.72\n" +
            "             61.22,40.24 64.96,46.28 70.80,56.00 Z\n" +
            "           M 375.80,58.00\n" +
            "           C 376.60,66.08 369.27,75.29 365.20,82.00\n" +
            "             365.20,82.00 336.00,130.00 336.00,130.00\n" +
            "             330.71,138.82 326.73,147.24 315.00,147.89\n" +
            "             301.74,148.63 291.14,135.87 294.79,123.00\n" +
            "             296.68,116.34 303.66,106.21 307.40,100.00\n" +
            "             307.40,100.00 333.00,58.00 333.00,58.00\n" +
            "             339.02,47.98 342.23,40.92 355.00,39.72\n" +
            "             365.83,40.00 374.69,46.77 375.80,58.00 Z\n" +
            "           M 289.00,116.00\n" +
            "           C 289.00,116.00 289.00,239.00 289.00,239.00\n" +
            "             288.98,249.72 285.92,257.31 275.00,261.10\n" +
            "             265.22,264.50 258.37,259.56 255.02,264.43\n" +
            "             253.78,266.24 254.00,269.84 254.00,272.00\n" +
            "             254.00,272.00 254.00,298.00 254.00,298.00\n" +
            "             254.00,304.85 254.77,310.07 250.36,315.93\n" +
            "             242.35,326.68 226.84,326.49 218.80,315.93\n" +
            "             215.07,311.00 215.01,306.83 215.00,301.00\n" +
            "             215.00,301.00 215.00,262.00 215.00,262.00\n" +
            "             215.00,262.00 190.00,262.00 190.00,262.00\n" +
            "             190.00,262.00 190.00,301.00 190.00,301.00\n" +
            "             189.99,306.83 189.93,311.00 186.20,315.93\n" +
            "             178.16,326.49 162.65,326.68 154.64,315.93\n" +
            "             151.09,311.22 151.01,307.61 151.00,302.00\n" +
            "             151.00,302.00 151.00,272.00 151.00,272.00\n" +
            "             151.00,269.84 151.22,266.24 149.98,264.43\n" +
            "             146.53,259.42 138.97,264.76 129.00,260.86\n" +
            "             118.39,256.72 116.02,248.29 116.00,238.00\n" +
            "             116.00,238.00 116.00,116.00 116.00,116.00\n" +
            "             116.00,116.00 289.00,116.00 289.00,116.00 Z";

    public static final String OUR_LOGO = "M 138.00,112.29\n" +
            "           C 155.28,110.04 172.25,123.39 172.96,141.00\n" +
            "             173.16,145.84 172.08,153.59 170.10,158.00\n" +
            "             164.13,171.27 146.68,182.53 132.00,181.96\n" +
            "             132.00,181.96 114.00,179.90 114.00,179.90\n" +
            "             93.61,173.05 81.24,148.51 81.00,128.00\n" +
            "             80.52,86.87 106.68,54.06 145.00,40.69\n" +
            "             160.50,35.29 171.97,34.98 188.00,35.00\n" +
            "             206.44,35.03 231.62,43.05 247.00,53.10\n" +
            "             253.55,57.38 268.78,71.80 273.21,78.00\n" +
            "             281.01,88.89 287.84,108.51 288.00,122.00\n" +
            "             288.32,149.99 272.75,172.05 252.00,189.56\n" +
            "             236.79,202.39 212.29,217.72 203.90,236.00\n" +
            "             201.34,241.58 200.79,247.00 200.00,253.00\n" +
            "             191.44,251.46 182.69,250.18 174.00,251.43\n" +
            "             162.09,253.14 163.41,255.54 154.00,256.00\n" +
            "             150.89,236.24 162.87,216.46 173.72,201.00\n" +
            "             176.16,197.52 183.32,186.76 187.04,185.70\n" +
            "             189.89,184.89 193.42,187.01 196.00,188.14\n" +
            "             203.02,191.21 210.33,195.01 216.87,188.78\n" +
            "             220.67,185.15 220.05,183.07 221.68,179.00\n" +
            "             223.66,174.07 225.02,174.56 227.29,166.00\n" +
            "             227.29,166.00 228.29,159.21 228.29,159.21\n" +
            "             229.38,155.39 233.48,154.83 233.25,150.00\n" +
            "             233.06,146.23 227.24,137.02 226.71,132.00\n" +
            "             226.24,127.53 228.89,125.77 229.00,117.00\n" +
            "             229.07,110.85 228.74,103.80 226.53,98.00\n" +
            "             223.89,91.07 219.51,85.59 213.96,80.75\n" +
            "             194.73,64.00 170.80,63.98 148.00,72.81\n" +
            "             140.13,75.86 132.17,80.12 126.17,86.17\n" +
            "             115.45,96.97 113.00,109.41 113.00,124.00\n" +
            "             121.92,118.00 126.86,113.74 138.00,112.29 Z\n" +
            "           M 213.47,287.00\n" +
            "           C 217.15,293.47 217.03,299.86 217.00,307.00\n" +
            "             216.95,317.29 213.76,324.83 205.99,331.82\n" +
            "             196.75,340.12 189.57,340.14 178.00,340.00\n" +
            "             153.64,339.70 140.29,313.26 148.47,292.00\n" +
            "             154.10,277.34 164.27,271.57 179.00,269.44\n" +
            "             192.46,268.36 206.71,275.10 213.47,287.00 Z";


    @Override
    public void initSplash(ConfigSplash configSplash) {

            /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        /*
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(1000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)
        */

        //Customize Path
        configSplash.setPathSplash(OUR_LOGO); //set path String
        //configSplash.setPathSplash(SyncStateContract.Constants.DATA); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.iron); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(1000);
        configSplash.setPathSplashFillColor(R.color.iron); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("HelpUs");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(45f); //float value
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        configSplash.setTitleFont("fonts/DINPro-Light_13935.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {

        //transit to another activity here or do whatever you want
        Intent intent = new Intent(SplashNice.this,MainActivity.class);
        startActivity(intent);
    }
}