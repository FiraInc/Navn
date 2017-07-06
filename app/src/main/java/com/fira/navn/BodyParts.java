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
            body = context.getResources().getDrawable(R.drawable.body_3);
        }else if (bodyNumber == 3) {
            body = context.getResources().getDrawable(R.drawable.body_4);
        }else if (bodyNumber == 4) {
            body = context.getResources().getDrawable(R.drawable.body_5);
        }else if (bodyNumber == 5) {
            body = context.getResources().getDrawable(R.drawable.body_6);
        }else if (bodyNumber == 6) {
            body = context.getResources().getDrawable(R.drawable.body_7);
        }else if (bodyNumber == 7) {
            body = context.getResources().getDrawable(R.drawable.body_8);
        }else if (bodyNumber == 8) {
            body = context.getResources().getDrawable(R.drawable.body_9);
        }
    }

    public static void getEyes (Context context, Integer eyeNumber){
        if (eyeNumber == 0) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_1);
        }else if (eyeNumber == 1) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_2);
        }else if (eyeNumber == 2) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_3);
        }else if (eyeNumber == 3) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_4);
        }else if (eyeNumber == 4) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_5);
        }else if (eyeNumber == 5) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_6);
        }else if (eyeNumber == 6) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_7);
        }else if (eyeNumber == 7) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_8);
        }else if (eyeNumber == 8) {
            eyes = context.getResources().getDrawable(R.drawable.eyes_9);
        }
    }

    public static void getEyebrows (Context context, Integer eyebrowsNumber) {
        if (eyebrowsNumber == 0) {
            eyebrows = context.getResources().getDrawable(R.drawable.eyebrows_1);
        }else if (eyebrowsNumber == 1) {
            eyebrows = context.getResources().getDrawable(R.drawable.eyebrows_2);
        }else if (eyebrowsNumber == 2) {
            eyebrows = context.getResources().getDrawable(R.drawable.eyebrows_3);
        }else if (eyebrowsNumber == 3) {
            eyebrows = context.getResources().getDrawable(R.drawable.eyebrows_4);
        }
    }

    public static void getMouth (Context context, Integer mouthNumber) {
        if (mouthNumber == 0) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_1);
        }else  if (mouthNumber == 1) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_2);
        }else  if (mouthNumber == 2) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_3);
        }else  if (mouthNumber == 3) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_4);
        }else  if (mouthNumber == 4) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_5);
        }else  if (mouthNumber == 5) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_6);
        }else  if (mouthNumber == 6) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_7);
        }else  if (mouthNumber == 7) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_8);
        }else  if (mouthNumber == 8) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_9);
        }else  if (mouthNumber == 9) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_10);
        }else  if (mouthNumber == 10) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_11);
        }else  if (mouthNumber == 11) {
            mouth = context.getResources().getDrawable(R.drawable.mouth_12);
        }
    }
}
