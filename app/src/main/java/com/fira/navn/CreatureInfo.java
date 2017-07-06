package com.fira.navn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class CreatureInfo {

    static String name = "No name";

    static int level = 15;
    static int xp = 0;
    static int xpNeeded = 0;

    static int type = 0;
    static int food = 0;
    static int thirsty = 0;
    static int health = 0;

    static Drawable CreatureImage;

    static int currentCreature = 0;
    static Bitmap result;

    public static void loadCreature(Context context, int creatureNumber) {
        currentCreature = creatureNumber;
        name = ReadWrite.read(context, "creatureName" + String.valueOf(creatureNumber) + ".txt");
        level = Integer.parseInt(ReadWrite.read(context, "creatureLevel" + String.valueOf(creatureNumber) + ".txt"));
        xp = Integer.parseInt(ReadWrite.read(context, "creatureXP" + String.valueOf(creatureNumber) + ".txt"));
        xpNeeded = Integer.parseInt(ReadWrite.read(context, "creatureXPNeeded" + String.valueOf(creatureNumber) + ".txt"));
        if (xpNeeded == 0) {
            xpNeeded = 100;
            ReadWrite.write(context, "creatureXPNeeded" + String.valueOf(creatureNumber) + ".txt", String.valueOf(xpNeeded));
        }
        Log.e("XPNEEDED", "XP: " + String.valueOf(xpNeeded));
        type = Integer.parseInt(ReadWrite.read(context, "creatureType" + String.valueOf(creatureNumber) + ".txt"));

        calculateFood(context);
        thirsty = Integer.parseInt(ReadWrite.read(context, "creatureThirsty" + String.valueOf(creatureNumber) + ".txt"));

        health = Integer.parseInt(ReadWrite.read(context, "creatureHealth" + String.valueOf(creatureNumber) + ".txt"));

        CreatureImage = context.getResources().getDrawable(R.drawable.test_creature);


        if (ReadWrite.read(context, "creatureHasCustomBody" + String.valueOf(creatureNumber) + ".txt").equals("1")) {
            BodyParts.getBody(context, Integer.parseInt(ReadWrite.read(context, "creatureBody" + String.valueOf(creatureNumber) + ".txt")));
            BodyParts.getEyebrows(context, Integer.parseInt(ReadWrite.read(context, "creatureEyebrows" + String.valueOf(creatureNumber) + ".txt")));
            BodyParts.getEyes(context, Integer.parseInt(ReadWrite.read(context, "creatureEyes" + String.valueOf(creatureNumber) + ".txt")));
            BodyParts.getMouth(context, Integer.parseInt(ReadWrite.read(context, "creatureMouth" + String.valueOf(creatureNumber) + ".txt")));
            Drawable d = new BitmapDrawable(overlay(overlay(drawableToBitmap(BodyParts.body), drawableToBitmap(BodyParts.eyes)),overlay(drawableToBitmap(BodyParts.eyebrows),drawableToBitmap(BodyParts.mouth))));
            //Drawable d = new BitmapDrawable(overlay(drawableToBitmap(OpponentCreatureInfo.creatureBody), drawableToBitmap(OpponentCreatureInfo.creatureEyes)));

            CreatureImage = d;
        }




    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

    public static void saveCreature(Context context) {
        //Legg til if creature name = null, take me to a screen where i can give my creature a name
        ReadWrite.write(context, "creatureName" + String.valueOf(currentCreature) + ".txt", name);

        if (level < 0) {
            level = 1;
        }

        ReadWrite.write(context, "creatureLevel" + String.valueOf(currentCreature) + ".txt", String.valueOf(level));
        ReadWrite.write(context, "creatureXP" + String.valueOf(currentCreature) + ".txt", String.valueOf(xp));
        ReadWrite.write(context, "creatureXPNeeded" + String.valueOf(currentCreature) + ".txt", String.valueOf(xpNeeded));
        ReadWrite.write(context, "creatureType" + String.valueOf(currentCreature) + ".txt", String.valueOf(type));
        ReadWrite.write(context, "creatureFood" + String.valueOf(currentCreature) + ".txt", String.valueOf(food));
        ReadWrite.write(context, "creatureThirsty" + String.valueOf(currentCreature) + ".txt", String.valueOf(thirsty));
        ReadWrite.write(context, "creatureHealth" + String.valueOf(currentCreature) + ".txt", String.valueOf(health));
    }


    private static void calculateFood(Context context) {
        try {
            food = Integer.parseInt(ReadWrite.read(context, "creatureFood" + String.valueOf(currentCreature) + ".txt"));
        }catch (Exception e) {
            Toast.makeText(context, "OH NO!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        int nowDate = c.get(Calendar.DAY_OF_YEAR);
        int nowHour = c.get(Calendar.HOUR_OF_DAY);
        int lastFoodDate = 0;
        int lastFoodHour = 0;

        try {
            lastFoodDate = Integer.parseInt(ReadWrite.read(context, "lastFoodDate" + String.valueOf(currentCreature) + ".txt"));
        }catch (Exception e) {
            Toast.makeText(context, "OH NO!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        try {
            lastFoodHour = Integer.parseInt(ReadWrite.read(context, "lastFoodHour" + String.valueOf(currentCreature) + ".txt"));
        }catch (Exception e) {
            Toast.makeText(context, "OH NO!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        int hoursGone = 0;

        if (lastFoodDate > 0 && lastFoodHour > 0) {
            if (nowDate == lastFoodDate) {
                if (nowHour == lastFoodHour) {
                    hoursGone = 0;
                }else {
                    hoursGone = nowHour - lastFoodHour;
                }
            }else if (nowDate > lastFoodDate) {
                int daysGone = nowDate-lastFoodDate;
                int hoursGone2 = 0;
                if (daysGone > 1) {
                    hoursGone2 = (daysGone * 24) - 24;
                }
                hoursGone = 24-lastFoodHour + nowHour;
                hoursGone = hoursGone + hoursGone2;
            }

            food = food - (hoursGone*3);

            ReadWrite.write(context, "lastFoodDate" + String.valueOf(currentCreature) + ".txt", String.valueOf(nowDate));
            ReadWrite.write(context, "lastFoodHour" + String.valueOf(currentCreature) + ".txt", String.valueOf(nowHour));
            ReadWrite.write(context, "creatureFood" + String.valueOf(currentCreature) + ".txt", String.valueOf(food));
        }else {
            food = 100;
            ReadWrite.write(context, "lastFoodDate" + String.valueOf(currentCreature) + ".txt", String.valueOf(nowDate));
            ReadWrite.write(context, "lastFoodHour" + String.valueOf(currentCreature) + ".txt", String.valueOf(nowHour));
            ReadWrite.write(context, "creatureFood" + String.valueOf(currentCreature) + ".txt", String.valueOf(food));
        }
    }

    public static void feed (Context context, int amount) {
        food = food + amount;

        if (food > 100) {
            food = 100;
        }

        Calendar c = Calendar.getInstance();
        int nowDate = c.get(Calendar.DAY_OF_YEAR);
        int nowHour = c.get(Calendar.HOUR_OF_DAY);
        ReadWrite.write(context, "lastFoodDate" + String.valueOf(currentCreature) + ".txt", String.valueOf(nowDate));
        ReadWrite.write(context, "lastFoodHour" + String.valueOf(currentCreature) + ".txt", String.valueOf(nowHour));
        ReadWrite.write(context, "creatureFood" + String.valueOf(currentCreature) + ".txt", String.valueOf(food));

        if (amount > 0) {
            health = health + amount;
            if (health > 100) {
                health = 100;
            }
            ReadWrite.write(context, "creatureHealth" + String.valueOf(currentCreature) + ".txt", String.valueOf(health));
        }
    }

    public static void fillHealth (Context context, int amount) {
        health = health + amount;
        if (health > 100) {
            health = 100;
        }
        saveCreature(context);
    }

    public static void leveling (Context context, int ExperiencePoints) {
        xp = xp + ExperiencePoints;

        if (xp > xpNeeded) {
            level = level + 1;
            xp = xp-xpNeeded;
            xpNeeded = xpNeeded + (xpNeeded/2);
            leveling(context, 0);
        }

        ReadWrite.write(context, "creatureLevel" + String.valueOf(currentCreature) + ".txt", String.valueOf(level));
        ReadWrite.write(context, "creatureXP" + String.valueOf(currentCreature) + ".txt", String.valueOf(xp));
        ReadWrite.write(context, "creatureXPNeeded" + String.valueOf(currentCreature) + ".txt", String.valueOf(xpNeeded));
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
