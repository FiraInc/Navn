package com.fira.navn;

import android.content.Context;
import android.content.Intent;
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

    static Drawable creatureBody;
    static Drawable creatureEyes;
    static Drawable creatureEyebrows;
    static Drawable creatureMouth;
    static Drawable creatureProps;

    static int bodyNumber = 0;
    static int eyeNumber;
    static int eyebrowNumber;
    static int mouthNumber;

    public static void loadCreature(Context context) {
        bodyNumber = Integer.parseInt(ReadWrite.read(context, "creatureBodyNumber.txt"));
        eyeNumber = Integer.parseInt(ReadWrite.read(context, "creatureEyeNumber.txt"));
        eyebrowNumber = Integer.parseInt(ReadWrite.read(context, "creatureEyeBrowNumber.txt"));
        mouthNumber = Integer.parseInt(ReadWrite.read(context, "creatureMouthNumber.txt"));

        BodyParts.getBody(context, bodyNumber);
        creatureBody = BodyParts.body;

        BodyParts.getEyes(context, eyeNumber);
        creatureEyes = BodyParts.eyes;

        BodyParts.getEyebrows(context, eyebrowNumber);
        creatureEyebrows = BodyParts.eyebrows;

        BodyParts.getMouth(context, mouthNumber);
        creatureMouth = BodyParts.mouth;


        creatureProps = context.getResources().getDrawable(R.drawable.body_1);


        name = ReadWrite.read(context, "creatureName.txt");
        level = Integer.parseInt(ReadWrite.read(context, "creatureLevel.txt"));
        xp = Integer.parseInt(ReadWrite.read(context, "creatureXP.txt"));
        type = Integer.parseInt(ReadWrite.read(context, "creatureType.txt"));

        calculateFood(context);
        thirsty = Integer.parseInt(ReadWrite.read(context, "creatureThirsty.txt"));

        health = Integer.parseInt(ReadWrite.read(context, "creatureHealth.txt"));


    }

    public static void saveCreature(Context context) {
        //Legg til if creature name = null, take me to a screen where i can give my creature a name
        ReadWrite.write(context, "creatureName.txt", name);

        if (level < 0) {
            level = 1;
        }

        ReadWrite.write(context, "creatureLevel.txt", String.valueOf(level));
        ReadWrite.write(context, "creatureXP.txt", String.valueOf(xp));
        ReadWrite.write(context, "creatureXPNeeded.txt", String.valueOf(xpNeeded));
        ReadWrite.write(context, "creatureType.txt", String.valueOf(type));
        ReadWrite.write(context, "creatureFood.txt", String.valueOf(food));
        ReadWrite.write(context, "creatureThirsty.txt", String.valueOf(thirsty));
        ReadWrite.write(context, "creatureHealth.txt", String.valueOf(health));

        ReadWrite.write(context, "creatureBodyNumber.txt", String.valueOf(bodyNumber));
        ReadWrite.write(context, "creatureEyeNumber.txt", String.valueOf(eyeNumber));
        ReadWrite.write(context, "creatureEyeBrowNumber.txt", String.valueOf(eyebrowNumber));
        ReadWrite.write(context, "creatureMouthNumber.txt", String.valueOf(mouthNumber));
    }


    private static void calculateFood(Context context) {
        try {
            food = Integer.parseInt(ReadWrite.read(context, "creatureFood.txt"));
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
            lastFoodDate = Integer.parseInt(ReadWrite.read(context, "lastFoodDate.txt"));
        }catch (Exception e) {
            Toast.makeText(context, "OH NO!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        try {
            lastFoodHour = Integer.parseInt(ReadWrite.read(context, "lastFoodHour.txt"));
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

            ReadWrite.write(context, "lastFoodDate.txt", String.valueOf(nowDate));
            ReadWrite.write(context, "lastFoodHour.txt", String.valueOf(nowHour));
            ReadWrite.write(context, "creatureFood.txt", String.valueOf(food));
        }else {
            food = 100;
            ReadWrite.write(context, "lastFoodDate.txt", String.valueOf(nowDate));
            ReadWrite.write(context, "lastFoodHour.txt", String.valueOf(nowHour));
            ReadWrite.write(context, "creatureFood.txt", String.valueOf(food));
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
        ReadWrite.write(context, "lastFoodDate.txt", String.valueOf(nowDate));
        ReadWrite.write(context, "lastFoodHour.txt", String.valueOf(nowHour));
        ReadWrite.write(context, "creatureFood.txt", String.valueOf(food));
    }

    public static void leveling (Context context, int ExperiencePoints) {
        xp = xp + ExperiencePoints;
        xpNeeded = 0;

        if (xp > xpNeeded) {
            level = level + 1;
            xp = xp-xpNeeded;
            xpNeeded = xpNeeded + (xpNeeded/2);
        }

        ReadWrite.write(context, "creatureLevel.txt", String.valueOf(level));
        ReadWrite.write(context, "creatureXP.txt", String.valueOf(xp));
        ReadWrite.write(context, "creatureXPNeeded.txt", String.valueOf(xpNeeded));
    }
}
