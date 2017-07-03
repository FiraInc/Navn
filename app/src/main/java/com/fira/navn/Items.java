package com.fira.navn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
    static int itemFeedAmount;
    static BitmapDrawable bitmapToBeAdded;
    static String itemDescription;
    static String itemCategory;
    static Drawable wallpaper;


    public static void getItem (Context mContext, String item) {
        wallpaper = null;
        if (item.equals("Bread")) {
            itemNameToBeAdded = item;
            itemPrice = 5;
            itemAmountToBeAdded = loadAmount(mContext, item);
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
            bitmapToBeAdded = new BitmapDrawable(mContext.getResources(), bm);
            itemCategory = "Food";
            itemDescription = "Well, it's not much";
            itemAmountBuy = 1;
            itemFeedAmount = 20;
        }else if (item.equals("BattleSearcher")) {
            itemNameToBeAdded = item;
            PlayerInfo.refreshBattleSearcher(mContext);
            itemPrice = (100 * PlayerInfo.BattleSearcherLevel) * 2;
            itemAmountToBeAdded = loadAmount(mContext, item);
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
            bitmapToBeAdded = new BitmapDrawable(mContext.getResources(), bm);
            itemCategory = "Special";
            itemDescription = "Find higher leveled creatures";
            itemAmountBuy = 1;
        }else if (item.equals("White wall")) {
            itemNameToBeAdded = item;
            itemPrice = 10;
            itemAmountToBeAdded = loadAmount(mContext, item);
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
            bitmapToBeAdded = new BitmapDrawable(mContext.getResources(), bm);
            itemCategory = "Wallpaper";
            itemDescription = "The standard wall color";
            itemAmountBuy = 1;
            wallpaper = mContext.getResources().getDrawable(R.drawable.background1);
        }else if (item.equals("Wooden wall")) {
            itemNameToBeAdded = item;
            itemPrice = 200;
            itemAmountToBeAdded = loadAmount(mContext, item);
            Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.test);
            bitmapToBeAdded = new BitmapDrawable(mContext.getResources(), bm);
            itemCategory = "Wallpaper";
            itemDescription = "Wooden wall";
            itemAmountBuy = 1;
        }
    }

    public static int loadAmount(Context context, String item) {
        return Integer.parseInt(ReadWrite.read(context, item + "ItemAmount.txt"));
    }
}
