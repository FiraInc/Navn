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

    ImageView Wallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        loadHouse();

        CreatureInfo.loadCreature(this);
    }

    private void loadHouse() {
        Items.getItem(this, ReadWrite.read(this, "CustomWallpaper.txt"));
        Wallpaper.setBackground(Items.wallpaper);
    }

    @Override
    protected void onResume() {
        loadHouse();
        super.onResume();
    }

    private void findViews() {
        creatureBody = (ImageView) findViewById(R.id.creatureBody);
        creatureEyes = (ImageView) findViewById(R.id.creatureEyes);
        creatureEyeBrows = (ImageView) findViewById(R.id.creatureEyeBrows);
        creatureMouth = (ImageView) findViewById(R.id.creatureMouth);
        creatureProps = (ImageView) findViewById(R.id.creatureProps);

        Wallpaper = (ImageView) findViewById(R.id.Wallpaper);

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

    public void openInventory(View view) {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }
}
