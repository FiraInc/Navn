package com.fira.navn;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class Attacks {

    static int attackDamage = 0;
    static Drawable AttackImage1;
    static Drawable AttackImage2;
    static Drawable AttackImage3;
    static Drawable AttackImage4;
    static Drawable AttackImage5;
    static Drawable AttackImage6;

    static MediaPlayer AttackSound;

    public static void calculateAttacks(Context context, String Attack_ID) {

        if (Attack_ID.equals("TestAttack")) {
            attackDamage = 5 * CreatureInfo.level;
            AttackImage1 = context.getResources().getDrawable(R.drawable.test);
            AttackImage2 = context.getResources().getDrawable(R.drawable.test);
            AttackImage3 = context.getResources().getDrawable(R.drawable.test);
            AttackImage4 = context.getResources().getDrawable(R.drawable.test);
            AttackImage5 = context.getResources().getDrawable(R.drawable.test);
            AttackImage6 = context.getResources().getDrawable(R.drawable.test);
            AttackSound = MediaPlayer.create(context, R.raw.slurpelyder);
        }
    }
}
