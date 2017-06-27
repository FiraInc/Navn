package com.fira.navn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class Items {

    static String itemNameToBeAdded;
    static int itemPrice;
    static int itemAmountToBeAdded;
    static int itemAmountBuy;
    static BitmapDrawable bitmapToBeAdded;
    static String itemDescription;
    static String itemCategory;


    public static void getItem (Context mContext, String item) {
        if (item.equals("Bread")) {
            itemNameToBeAdded = item;
            itemPrice = 350;
            itemAmountToBeAdded = loadAmount(mContext, item);
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
            bitmapToBeAdded = new BitmapDrawable(mContext.getResources(), bm);
            itemCategory = "Food";
            itemDescription = "Well, it's not much";
            itemAmountBuy = 1;

            Log.e("YOUJSB", itemNameToBeAdded);
        }else if (item.equals("Egg")) {
            itemNameToBeAdded = item;
            itemPrice = 7500;
            itemAmountToBeAdded = loadAmount(mContext, item);
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
            bitmapToBeAdded = new BitmapDrawable(mContext.getResources(), bm);
            itemCategory = "Food";
            itemDescription = "Hatch a new egg";
            itemAmountBuy = 1;
        }
    }

    public static int loadAmount(Context context, String item) {
        return Integer.parseInt(ReadWrite.read(context, item + "ItemAmount.txt"));
    }
}
