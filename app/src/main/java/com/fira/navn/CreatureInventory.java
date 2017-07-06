package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Johannett321 on 03.03.2017.
 */

public class CreatureInventory extends Activity {

    ArrayList<CreatureInventoryItems> arrayOfItems;
    static CreatureInventoryAdapter adapter;
    GridView grid;

    static int currentItem = 0;

    String titleToBeAdded;
    Bitmap bitmapToBeAdded;
    int healthStatusToBeAdded;
    int foodStatusToBeAdded;

    File file;
    StringBuilder text;

    ProgressBar progressBar;

    static Boolean inBattle = false;

    int beforeCreature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creature_inventory);

        beforeCreature = CreatureInfo.currentCreature;

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        arrayOfItems = new ArrayList<CreatureInventoryItems>();

        adapter = new CreatureInventoryAdapter(this, arrayOfItems);

        grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (inBattle) {
                    backToBattle(adapter.getItem(position).ItemFolderNumber);
                }else {
                    setDefaultCreature(adapter.getItem(position).ItemFolderNumber);
                }
            }
        });

        /*

        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //if (inBattle) {
                    //CreatureInfo.inBattle = true;
                //}
                CreatureInfo.currentCreature = adapter.getItem(position).ItemFolderNumber;
                showInfo();

                return true;
            }
        });
        */

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadItems();
            }
        }, 500);
    }

    private void setDefaultCreature(int folderNumber) {
        CreatureInfo.loadCreature(this, folderNumber);
        homeGo();
    }

    private void backToBattle(int Position) {
        currentItem = 0;
        CreatureInfo.loadCreature(this, Position);
        super.onBackPressed();
    }

    private void loadItems() {
        if (!ReadWrite.read(this, "creatureName" + String.valueOf(currentItem) + ".txt").equals("0")) {
            CreatureInfo.loadCreature(this, currentItem);
            titleToBeAdded = CreatureInfo.name;
            bitmapToBeAdded = CreatureInfo.drawableToBitmap(CreatureInfo.CreatureImage);
            foodStatusToBeAdded = CreatureInfo.food;
            healthStatusToBeAdded = CreatureInfo.health;
            addItem();
            currentItem  = currentItem + 1;
            loadItems();
        } else {
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    private void addItem() {
        try {
            CreatureInventoryItems newItem = new CreatureInventoryItems(titleToBeAdded, bitmapToBeAdded, currentItem, healthStatusToBeAdded, foodStatusToBeAdded);
            adapter.add(newItem);
        }catch (Exception e) {
            e.printStackTrace();
        }
        adapter.getCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentItem = 0;
                adapter.clear();
                loadItems();
            }
        },500);
    }

    @Override
    public void onBackPressed() {
        if (inBattle) {
            //backToBattle(Battle.currentCreature);
        }else {
            CreatureInfo.currentCreature = beforeCreature;
            CreatureInfo.loadCreature(this, beforeCreature);
            homeGo();
        }
    }

    private void homeGo() {
        currentItem = 0;
        super.onBackPressed();
    }
}
