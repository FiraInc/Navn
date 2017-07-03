package com.fira.navn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class ValueChanger extends Activity {

    EditText name;
    EditText type;
    EditText level;
    EditText xp;
    EditText food;
    EditText thirsty;
    EditText health;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valuechanger);
        findViews();
        loadValues();
    }



    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        type = (EditText) findViewById(R.id.type);
        level = (EditText) findViewById(R.id.level);
        xp = (EditText) findViewById(R.id.xp);
        food = (EditText) findViewById(R.id.food);
        thirsty = (EditText) findViewById(R.id.thirsty);
        health = (EditText) findViewById(R.id.health);
    }

    private void loadValues() {
        name.setText(CreatureInfo.name);
        type.setText(String.valueOf(CreatureInfo.type));
        level.setText(String.valueOf(CreatureInfo.level));
        xp.setText(String.valueOf(CreatureInfo.xp));
        food.setText(String.valueOf(CreatureInfo.food));
        thirsty.setText(String.valueOf(CreatureInfo.thirsty));
        health.setText(String.valueOf(CreatureInfo.health));
    }

    public void saveChanges(View view) {
        CreatureInfo.name = name.getText().toString();
        CreatureInfo.type = Integer.parseInt(type.getText().toString());
        CreatureInfo.level = Integer.parseInt(level.getText().toString());
        CreatureInfo.xp = Integer.parseInt(xp.getText().toString());
        CreatureInfo.food = Integer.parseInt(food.getText().toString());
        CreatureInfo.thirsty = Integer.parseInt(thirsty.getText().toString());
        CreatureInfo.health = Integer.parseInt(health.getText().toString());
        CreatureInfo.saveCreature(this);
        Toast.makeText(this, "SAVED!", Toast.LENGTH_SHORT).show();
    }

    public void tenFood (View view) {
        CreatureInfo.feed(this, 10);
    }

    public void UpdateValues(View view) {
        loadValues();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void Uninstall(View view) {
        ReadWrite.write(this, "InstallCheck.txt", "0");
        ReadWrite.write(this, "eggFirstTime.txt", "0");
        ReadWrite.write(this, "eggStartDate.txt", String.valueOf(0));
        ReadWrite.write(this, "eggStartHour.txt", String.valueOf(0));
        ReadWrite.write(this, "eggCrackTime.txt", "1");
        Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();
    }
}
