package com.fira.navn;

import android.app.Activity;
import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by johannett321 on 01/07/2017.
 */

public class FirePlace extends Activity {

    static Boolean isBurning = false;
    static int temperature = 0;
    static int coalAmount = 0;

    public TextView coalAmountText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fire_place);

        findViews();

        calculateFire(this);

        coalAmount = Integer.parseInt(ReadWrite.read(this, "FireBurnTime.txt"));

        coalAmountText.setText(String.valueOf(coalAmount));

    }

    private void findViews() {
        coalAmountText = (TextView) findViewById(R.id.coalAmount);
    }

    public static void calculateFire(Context context) {
        int startHour;
        int startDay;
        int burnTime;
        int tempBeforeBurn;

        startHour = Integer.parseInt(ReadWrite.read(context, "FireStartHour.txt"));
        startDay = Integer.parseInt(ReadWrite.read(context, "FireStartDate.txt"));
        int hoursGone = ReadWrite.calculateHours(startDay, startHour);
        burnTime = Integer.parseInt(ReadWrite.read(context, "FireBurnTime.txt"));
        tempBeforeBurn = Integer.parseInt(ReadWrite.read(context, "FireTempBeforeBurn.txt"));

        if (startDay == 0 && startHour == 0) {
            isBurning = false;
            temperature = 0;
            Log.e("HEIHS", "1");
        }else {
            if (burnTime <= hoursGone) {
                isBurning = false;
                Log.e("HEIHS", "2");
            }else {
                isBurning = true;
                Log.e("HEIHS", "3");
            }
            if (isBurning) {
                temperature = hoursGone*15 + tempBeforeBurn;
                Log.e("HEIHS", "4");
            }else {
                temperature = (burnTime*15 + tempBeforeBurn) - ((hoursGone-burnTime)*10);
                Log.e("HEIHS", "5");
            }
        }
    }

    public void addCoal(View view) {
        coalAmount = coalAmount + 1;
        coalAmountText.setText(String.valueOf(coalAmount));
        if (!isBurning) {
            isBurning = true;
        }

        Calendar c = Calendar.getInstance();

        ReadWrite.write(this, "FireTempBeforeBurn.txt", String.valueOf(temperature));
        ReadWrite.write(this, "FireBurnTime.txt", String.valueOf(coalAmount));
        ReadWrite.write(this, "FireStartHour.txt", String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
        ReadWrite.write(this, "FireStartDate.txt", String.valueOf(c.get(Calendar.DAY_OF_YEAR)));
    }

    public void RESET(View view) {
        ReadWrite.write(this, "FireTempBeforeBurn.txt", String.valueOf(0));
        ReadWrite.write(this, "FireBurnTime.txt", String.valueOf(0));
        Calendar c = Calendar.getInstance();
        ReadWrite.write(this, "FireStartHour.txt", String.valueOf(c.get(Calendar.HOUR_OF_DAY)));
        ReadWrite.write(this, "FireStartDate.txt", String.valueOf(c.get(Calendar.DAY_OF_YEAR)));
    }
}
