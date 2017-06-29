package com.fira.navn;

import android.content.Context;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class PlayerInfo {

    static String name;


    static int coins = 0;
    static int diamonds = 0;

    static int BattleSearcherLevel = 1;

    public static void refreshCurrency (Context context){
        coins = Integer.parseInt(ReadWrite.read(context, "CoinsAmount.txt"));
        diamonds = Integer.parseInt(ReadWrite.read(context, "DiamondsAmount.txt"));
    }

    public static void addCoins(Context context, int current) {
        coins = coins + current;
        ReadWrite.write(context, "CoinsAmount.txt", String.valueOf(coins));
    }

    public static void addDiamonds(Context context, int current) {
        diamonds = diamonds + current;
        ReadWrite.write(context, "DiamondsAmount.txt", String.valueOf(diamonds));
    }

    public static void diamondsToCoinsConverter(Context context, int diamonds) {

        /*
        int outputCoins;
        if (diamonds <= getCurrentDiamonds(context)) {
            outputCoins = diamonds* 75;
            addDiamonds(context, -diamonds);
            addCoins(context, outputCoins);
        }else {
            //todo play error sound
        }
        */
    }

}
