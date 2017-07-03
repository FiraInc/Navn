package com.fira.navn;

import android.app.Activity;
import java.util.Calendar;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by johannett321 on 01/07/2017.
 */

public class EggHatch extends Activity {

    ImageView backgroundImage;
    ImageView eggImage;
    ImageView effectImage;
    TextView temperatureText;
    TextView humidityText;
    TextView eggHealthText;

    int humidity = 0;
    int eggHealth = 0;

    Boolean firstTime = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egg_hatch);
        findViews();

        if (!ReadWrite.read(this, "eggFirstTime.txt").equals("1")) {
            firstTime = true;
        }

        Calendar calendar = Calendar.getInstance();
        int currentDate = calendar.get(Calendar.DAY_OF_YEAR);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

        if (firstTime) {
            ReadWrite.write(this, "eggStartDate.txt", String.valueOf(currentDate));
            ReadWrite.write(this, "eggStartHour.txt", String.valueOf(currentHour));
            Random random = new Random();
            ReadWrite.write(this, "eggCrackTime.txt", String.valueOf(random.nextInt(15) + 15));

            Log.e("DATE", ReadWrite.read(this, "eggStartDate.txt") + "2");
            Log.e("HOUR", ReadWrite.read(this, "eggStartHour.txt") + "2");
        }

        loadStats();
    }

    private void findViews() {
        backgroundImage = (ImageView) findViewById(R.id.background);
        eggImage = (ImageView) findViewById(R.id.Egg);
        effectImage = (ImageView) findViewById(R.id.effect);
        temperatureText = (TextView) findViewById(R.id.temperature);
        humidityText = (TextView) findViewById(R.id.humidity);
        eggHealthText = (TextView) findViewById(R.id.eggHealth);
    }

    private void loadStats() {
        FirePlace.calculateFire(this);
        temperatureText.setText(String.valueOf(FirePlace.temperature));


        //todo legg in firstime
        if (FirePlace.temperature > 50 || FirePlace.temperature < 5 && !firstTime) {
            eggHealth = eggHealth-10;
        }

        int hoursGone;

        hoursGone = ReadWrite.calculateHours(Integer.parseInt(ReadWrite.read(this, "eggStartDate.txt")), Integer.parseInt(ReadWrite.read(this, "eggStartHour.txt")));

        if (hoursGone >= Integer.parseInt(ReadWrite.read(this, "eggCrackTime.txt"))) {
            //todo crack
            ReadWrite.write(this, "InstallCheck.txt", "1");
            generateCreature();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        Log.e("HOURSGONE", String.valueOf(hoursGone));
        Log.e("CRACKTIME", ReadWrite.read(this, "eggCrackTime.txt"));




        if (FirePlace.temperature >= 27 && FirePlace.temperature <= 40) {
            backgroundImage.setImageDrawable(getResources().getDrawable(R.drawable.test));
        }else if (FirePlace.temperature >= 40){
            backgroundImage.setImageDrawable(getResources().getDrawable(R.drawable.test));
        }else {
            backgroundImage.setImageDrawable(getResources().getDrawable(R.drawable.test));
        }

        ReadWrite.write(this, "eggFirstTime.txt", "1");
    }


    private void generateCreature() {
        if (FirePlace.temperature < 38) {
            CreatureInfo.level = (FirePlace.temperature / 3) + (FirePlace.temperature / 5);
            if (CreatureInfo.level <= 0) {
                CreatureInfo.level = 2;
            }else {
                CreatureInfo.level = 40 / (FirePlace.temperature /10);
            }
        }
        CreatureInfo.xp = 0;

        CreatureInfo.food = 100;
        CreatureInfo.thirsty = 100;
        CreatureInfo.health = 100;
        //todo lagre Mat tid

        OpponentCreatureInfo.generateRandom(this);
        CreatureInfo.bodyNumber = OpponentCreatureInfo.bodyNumber;
        CreatureInfo.eyeNumber = OpponentCreatureInfo.eyeNumber;
        CreatureInfo.eyebrowNumber = OpponentCreatureInfo.eyebrowNumber;
        CreatureInfo.mouthNumber = OpponentCreatureInfo.mouthNumber;

        CreatureInfo.saveCreature(this);
    }

    public void OpenFire(View view) {
        Intent intent = new Intent(this, FirePlace.class);
        startActivity(intent);
    }
}
