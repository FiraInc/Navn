package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Johannett321 on 03.03.2017.
 */

public class Inventory extends Activity {

    ArrayList<InventoryItems> arrayOfItems;
    static InventoryAdapter adapter;
    ListView listView;

    String itemNameToBeAdded;
    String itemAmountToBeAdded;
    String itemDescriptionToBeAdded;
    BitmapDrawable bitmapToBeAdded;

    File file;

    ProgressBar progressBar;
    static String category = "Food";

    static Boolean inBattle = false;

    TextView categoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        createOn();


    }

    private void createOn() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        arrayOfItems = new ArrayList<InventoryItems>();

        adapter = new InventoryAdapter(this, arrayOfItems);

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        categoryText = (TextView) findViewById(R.id.categoryText);
        categoryText.setText(category);
    }

    private void newItemLoader() {
        adapter.clear();
        if (category.equals("Food")) {
            itemNameToBeAdded = "Bread";
            Items.getItem(this, itemNameToBeAdded);
            itemAmountToBeAdded = String.valueOf(Items.itemAmountToBeAdded);
            itemDescriptionToBeAdded = Items.itemDescription;
            bitmapToBeAdded = Items.bitmapToBeAdded;
            addItem();
        }else if (category.equals("Special")) {
            itemNameToBeAdded = "Egg";
            Items.getItem(this, itemNameToBeAdded);
            itemAmountToBeAdded = String.valueOf(Items.itemAmountToBeAdded);
            itemDescriptionToBeAdded = Items.itemDescription;
            bitmapToBeAdded = Items.bitmapToBeAdded;
            addItem();
        }

        progressBar.setVisibility(View.INVISIBLE);

    }

    private void addItem() {
        Log.e("HEELEDLAJKDKLPS", itemNameToBeAdded);

        try {
            InventoryItems newItem = new InventoryItems(itemNameToBeAdded, bitmapToBeAdded, itemAmountToBeAdded, itemDescriptionToBeAdded);
            adapter.add(newItem);
            Log.e("HEELEDLAJKDKLPS", itemNameToBeAdded + "Done");
        }catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("HEELEDLAJKDKLPS", itemNameToBeAdded + "Done2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("HEI", "RESUMED");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newItemLoader();
            }
        }, 200);
    }

    public void GoHome(View view) {
        homeGo();
    }

    private void homeGo() {
        super.onBackPressed();
    }

    public void storeClicked(View view) {
        Intent intent = new Intent(this, Store.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    public void changeCategory(View view) {
        //setContentView(R.layout.category_browser);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);
    }

    public void closeChangeCategory() {
        setContentView(R.layout.inventory);
        categoryText = (TextView) findViewById(R.id.categoryText);
        categoryText.setText(category);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createOn();
                newItemLoader();
            }
        }, 500);
    }
}
