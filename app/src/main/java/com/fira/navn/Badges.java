package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by johannett321 on 04.07.2017.
 */

public class Badges extends Activity {

    ImageView badge1;
    ImageView badge2;
    ImageView badge3;
    ImageView badge4;

    TextView badgeText1;
    TextView badgeText2;
    TextView badgeText3;
    TextView badgeText4;

    TextView badgesUnlocked;

    int badgeNumber = 0;

    static Boolean battleSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badges);
        findViews();
        loadBadges();
    }

    private void findViews() {
        badge1 = (ImageView) findViewById(R.id.badge1);
        badge2 = (ImageView) findViewById(R.id.badge2);
        badge3 = (ImageView) findViewById(R.id.badge3);
        badge4 = (ImageView) findViewById(R.id.badge4);

        badgeText1 = (TextView) findViewById(R.id.badgeText1);
        badgeText2 = (TextView) findViewById(R.id.badgeText2);
        badgeText3 = (TextView) findViewById(R.id.badgeText3);
        badgeText4 = (TextView) findViewById(R.id.badgeText4);

        badgesUnlocked = (TextView) findViewById(R.id.badgesUnlocked);
    }

    private void loadBadges() {
        badgeNumber = Integer.parseInt(ReadWrite.read(this, "BadgesUnlocked.txt"));

        if (badgeNumber == 1) {
            badge1.setImageDrawable(getResources().getDrawable(R.drawable.badge_1));
            badgeText1.setText("Michael");
        }

        badgesUnlocked.setText(String.valueOf(badgeNumber));
    }

    public void badgeClicked1(View view) {
        if (badgeNumber == 0) {
            Battle.badgeBattle = true;

            OpponentCreatureInfo.Attack1 = "TestAttack";
            OpponentCreatureInfo.Attack2 = "TestAttack";
            OpponentCreatureInfo.Attack3 = "TestAttack";
            OpponentCreatureInfo.Attack4 = "TestAttack";
            OpponentCreatureInfo.level = 20;
            OpponentCreatureInfo.health = 100;
            OpponentCreatureInfo.bodyNumber = 1;
            OpponentCreatureInfo.eyebrowNumber = 1;
            OpponentCreatureInfo.eyeNumber = 1;
            OpponentCreatureInfo.mouthNumber = 1;

            Intent intent = new Intent(this, Battle.class);
            startActivity(intent);
        }
    }

    public void badgeClicked2(View view) {
        if (badgeNumber == 1) {
            Battle.badgeBattle = true;

            OpponentCreatureInfo.Attack1 = "TestAttack";
            OpponentCreatureInfo.Attack2 = "TestAttack";
            OpponentCreatureInfo.Attack3 = "TestAttack";
            OpponentCreatureInfo.Attack4 = "TestAttack";
            OpponentCreatureInfo.level = 30;
            OpponentCreatureInfo.health = 100;
            OpponentCreatureInfo.bodyNumber = 2;
            OpponentCreatureInfo.eyebrowNumber = 1;
            OpponentCreatureInfo.eyeNumber = 1;
            OpponentCreatureInfo.mouthNumber = 1;

            Intent intent = new Intent(this, Battle.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (battleSuccess) {
            Battle.badgeBattle = false;
            badgeNumber = 1;
            ReadWrite.write(this, "BadgesUnlocked.txt", String.valueOf(badgeNumber));
            loadBadges();
        }
    }
}
