package com.fira.navn;

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

    public static void generateRandom() {
        Random random = new Random();
        int randomNumber;
        level = 10;
        health = 100;
        Attack1 = "TestAttack";
        Attack2 = "TestAttack";
        Attack3 = "TestAttack";
        Attack4 = "TestAttack";

        if (PlayerInfo.BattleSearcherLevel == 1) {
            randomNumber = random.nextInt(4);
            //randomNumber = randomNumber + 3;
            randomNumber = randomNumber + 10;
        }else {
            randomNumber = 1;
        }

        level = randomNumber;
    }

}
