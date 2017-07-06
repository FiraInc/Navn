package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageView creatureBody;
    ImageView creatureEyes;
    ImageView creatureEyeBrows;
    ImageView creatureMouth;
    ImageView creatureProps;

    RelativeLayout topBarMenu;

    ProgressBar hungerBar;
    ProgressBar healthProgress;
    ProgressBar XPProgress;

    TextView level;
    TextView creatureName;

    ImageView Wallpaper;

    Boolean topBarMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        loadHouse();

        CreatureInfo.loadCreature(this, 0);
        loadCreature();

        topBarMenu.setVisibility(View.INVISIBLE);
    }

    public void changeCreature(View view) {
        Intent intent = new Intent(this, CreatureInventory.class);
        startActivity(intent);
    }

    private void loadHouse() {
        Items.getItem(this, ReadWrite.read(this, "CustomWallpaper.txt"));
        Wallpaper.setBackground(Items.wallpaper);
    }

    @Override
    protected void onResume() {
        loadCreature();
        loadHouse();
        super.onResume();
    }

    private void findViews() {
        creatureBody = (ImageView) findViewById(R.id.creatureBody);
        creatureEyes = (ImageView) findViewById(R.id.creatureEyes);
        creatureEyeBrows = (ImageView) findViewById(R.id.creatureEyeBrows);
        creatureMouth = (ImageView) findViewById(R.id.creatureMouth);
        creatureProps = (ImageView) findViewById(R.id.creatureProps);

        topBarMenu = (RelativeLayout) findViewById(R.id.topBarMenu);

        hungerBar = (ProgressBar) findViewById(R.id.hungerProgress);
        healthProgress = (ProgressBar) findViewById(R.id.healthProgress);
        XPProgress = (ProgressBar) findViewById(R.id.XPProgress);

        Wallpaper = (ImageView) findViewById(R.id.Wallpaper);

        level = (TextView) findViewById(R.id.level);
        creatureName = (TextView) findViewById(R.id.creatureName);

    }

    private void loadCreature() {
        CreatureInfo.loadCreature(this, CreatureInfo.currentCreature);
        creatureBody.setImageDrawable(CreatureInfo.CreatureImage);
        creatureProps.setVisibility(View.INVISIBLE);


        level.setText(String.valueOf(CreatureInfo.level));
        creatureName.setText(CreatureInfo.name);

        hungerBar.setProgress(CreatureInfo.food);
        healthProgress.setProgress(CreatureInfo.health);
        XPProgress.setProgress((CreatureInfo.xp*100)/CreatureInfo.xpNeeded);
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

    public void openInventory(View view) {
        Intent intent = new Intent(this, Inventory.class);
        startActivity(intent);
    }

    public void showTopBarMenu(View view) {
        if (topBarMenuVisible) {
            topBarMenuVisible = false;
            topBarMenu.setVisibility(View.INVISIBLE);
        }else {
            topBarMenuVisible = true;
            topBarMenu.setVisibility(View.VISIBLE);
        }
    }

    public void openMenu(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (topBarMenuVisible) {
            topBarMenuVisible = false;
            topBarMenu.setVisibility(View.INVISIBLE);
        }
    }
}
