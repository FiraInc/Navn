package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageView creatureBody;
    ImageView creatureEyes;
    ImageView creatureEyeBrows;
    ImageView creatureMouth;
    ImageView creatureProps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        CreatureInfo.loadCreature(this);
    }

    private void findViews() {
        creatureBody = (ImageView) findViewById(R.id.creatureBody);
        creatureEyes = (ImageView) findViewById(R.id.creatureEyes);
        creatureEyeBrows = (ImageView) findViewById(R.id.creatureEyeBrows);
        creatureMouth = (ImageView) findViewById(R.id.creatureMouth);
        creatureProps = (ImageView) findViewById(R.id.creatureProps);

        CreatureInfo.loadCreature(this);
        loadCreature();
    }

    private void loadCreature() {
        creatureBody.setImageDrawable(CreatureInfo.creatureBody);
        creatureEyes.setImageDrawable(CreatureInfo.creatureEyes);
        creatureEyeBrows.setImageDrawable(CreatureInfo.creatureEyebrows);
        creatureMouth.setImageDrawable(CreatureInfo.creatureMouth);
        creatureProps.setImageDrawable(CreatureInfo.creatureProps);
        creatureProps.setVisibility(View.INVISIBLE);
    }

    Boolean aBoolean = false;

    public void swap (View view) {
        if (aBoolean) {
            CreatureInfo.creatureEyes = getResources().getDrawable(R.drawable.eyes_1);
            CreatureInfo.creatureMouth = getResources().getDrawable(R.drawable.mouth_1);
            aBoolean = false;
        }else {
            CreatureInfo.creatureEyes = getResources().getDrawable(R.drawable.eyes_2);
            CreatureInfo.creatureMouth = getResources().getDrawable(R.drawable.mouth_2);
            aBoolean = true;
        }

        loadCreature();
    }

    @Override
    protected void onPause() {
        CreatureInfo.saveCreature(this);
        super.onPause();
    }

    public void valuesInfo(View view) {
        Intent intent = new Intent(this, ValueChanger.class);
        startActivity(intent);
    }

    public void openStore(View view) {
        Intent intent = new Intent(this, Store.class);
        startActivity(intent);
    }

    public void battle(View view) {
        Intent intent = new Intent(this, Battle.class);
        startActivity(intent);
    }
}
