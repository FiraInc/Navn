package com.fira.navn;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Random;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class OpponentCreatureInfo {

    static int level = 0;
    static int health = 0;

    static String Attack1;
    static String Attack2;
    static String Attack3;
    static String Attack4;

    static Drawable creatureBody;
    static Drawable creatureEyes;
    static Drawable creatureEyebrows;
    static Drawable creatureMouth;
    static Drawable creatureProps;

    static int bodyNumber;
    static int eyeNumber;
    static int eyebrowNumber;
    static int mouthNumber;

    public static void generateRandom(Context context) {
        Random random = new Random();
        int randomNumber;
        level = 10;
        health = 100;
        Attack1 = "TestAttack";
        Attack2 = "TestAttack";
        Attack3 = "TestAttack";
        Attack4 = "TestAttack";

        if (PlayerInfo.BattleSearcherLevel == 1 || PlayerInfo.BattleSearcherLevel == 0) {
            randomNumber = random.nextInt(4) + 10;
        }else if (PlayerInfo.BattleSearcherLevel == 2) {
            randomNumber = random.nextInt(4) + 20;
        }else {
            randomNumber = 1;
        }
        level = randomNumber;

        randomNumber = random.nextInt(9);
        bodyNumber = randomNumber;


        randomNumber = random.nextInt(9);
        eyeNumber = randomNumber;


        randomNumber = random.nextInt(4);
        eyebrowNumber = randomNumber;


        randomNumber = random.nextInt(12);
        mouthNumber = randomNumber;

        getGraphicsParts(context);

    }

    public static void getGraphicsParts(Context context) {
        BodyParts.getBody(context, bodyNumber);
        creatureBody = BodyParts.body;

        BodyParts.getEyes(context, eyeNumber);
        creatureEyes = BodyParts.eyes;

        BodyParts.getEyebrows(context, eyebrowNumber);
        creatureEyebrows = BodyParts.eyebrows;

        BodyParts.getMouth(context, mouthNumber);
        creatureMouth = BodyParts.mouth;
    }

}
