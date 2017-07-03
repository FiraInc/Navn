package com.fira.navn;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by johannett321 on 03/07/2017.
 */

public class BodyParts {

    static Drawable body;
    static Drawable eyes;
    static Drawable eyebrows;
    static Drawable mouth;

    public static void getBody (Context context, Integer bodyNumber){
        if (bodyNumber == 0) {
            body = context.getResources().getDrawable(R.drawable.body_1);
        }else if (bodyNumber == 1) {
            body = context.getResources().getDrawable(R.drawable.body_2);
        }else if (bodyNumber == 2) {
            body = context.getResources().getDrawable(R.drawable.test);
        }
    }

    public static void getEyes (Context context, Integer eyeNumber){
        if (eyeNumber == 0) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_1);
        }else if (eyeNumber == 1) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_2);
        }
    }

    public static void getEyebrows (Context context, Integer eyebrowsNumber) {
        if (eyebrowsNumber == 0) {
            eyebrows = context.getResources().getDrawable(R.drawable.eyebrows_1);
        }
    }

    public static void getMouth (Context context, Integer mouthNumber) {
        if (mouthNumber == 0) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_1);
        }else  if (mouthNumber == 1) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_2);
        }
    }
}
