package com.fira.navn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class Store extends Activity {

    ArrayList<StoreItems> arrayOfItems;
    static StoreAdapter adapter;
    ListView listView;

    BitmapDrawable imageToAdd;

    String ItemName;
    String ItemDesc;
    String ItemCat;
    String ItemAmount;
    String Price;
    String currentAmount;

    static String category = "Food";
    TextView categoryText;

    ProgressBar progressBar;

    TextView diamondsText;
    TextView coinsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        createOn();
    }

    private void createOn() {
        findViews();

        progressBar.setVisibility(View.VISIBLE);

        arrayOfItems = new ArrayList<StoreItems>();

        adapter = new StoreAdapter(this, arrayOfItems, coinsText, diamondsText);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        PlayerInfo.refreshCurrency(this);
        diamondsText.setText(String.valueOf(PlayerInfo.diamonds));
        coinsText.setText(String.valueOf(PlayerInfo.coins));
        categoryText.setText(category);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadItems();
            }
        }, 200);
    }

    private void findViews() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        diamondsText = (TextView) findViewById(R.id.diamondsText);
        coinsText = (TextView) findViewById(R.id.coinsText);
        categoryText = (TextView) findViewById(R.id.categoryText);
    }

    private void loadItems() {
        String item;

        if (category.equals("Food")) {
            itemAdder("Bread");
        }else if (category.equals("Special")) {
            itemAdder("BattleSearcher");
            itemAdder("Health Potion XSmall");
            itemAdder("Health Potion Small");
            itemAdder("Health Potion Medium");
            itemAdder("Health Potion Large");
            itemAdder("Health Potion XLarge");

            itemAdder("Experience Potion XSmall");
            itemAdder("Experience Potion Small");
            itemAdder("Experience Potion Medium");
            itemAdder("Experience Potion Large");
            itemAdder("Experience Potion XLarge");
        }else if (category.equals("Wallpaper")) {
            itemAdder("White wall");
            itemAdder("Wooden wall");
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void itemAdder(String item) {
        Items.getItem(this, item);
        if (Items.requiredLevel <= CreatureInfo.level) {
            ItemName = Items.itemNameToBeAdded;
            ItemDesc = Items.itemDescription;
            ItemCat = Items.itemCategory;
            ItemAmount = String.valueOf(Items.itemAmountBuy);
            Price = String.valueOf(Items.itemPrice);
            imageToAdd = Items.bitmapToBeAdded;
            currentAmount = String.valueOf(Items.loadAmount(this, item));
            addItem();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        diamondsText.setText(String.valueOf(PlayerInfo.diamonds));
        coinsText.setText(String.valueOf(PlayerInfo.coins));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void addItem() {
        try {
            StoreItems newItem = new StoreItems(ItemName, ItemDesc, ItemCat, ItemAmount, currentAmount,Price, imageToAdd);
            adapter.add(newItem);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyMoneyClicked(View view) {
        buyMoney();
    }

    private void buyMoney() {
        Intent intent = new Intent(this, BuyMoney.class);
        startActivity(intent);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            int yourMessage = intent.getIntExtra("message",-1/*default value*/);
            int yourInteger = intent.getIntExtra("positioner",-1/*default value*/);
            if (yourMessage == 1) {
                StoreShowNotEnoughDialog(yourInteger);
            }

        }
    };

    public void StoreShowNotEnoughDialog(int position) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Store.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_yes_no, null);
        TextView title = (TextView) mView.findViewById(R.id.dialogTitle);
        TextView positiveText = (TextView) mView.findViewById(R.id.positiveButtonText);
        TextView negativeText = (TextView) mView.findViewById(R.id.negativeButtonText);
        ImageView positive = (ImageView) mView.findViewById(R.id.positiveButton);
        ImageView negative = (ImageView) mView.findViewById(R.id.negativeButton);

        int price = Integer.parseInt(adapter.getItem(position).Price);
        final int coinsNeeded = price - PlayerInfo.coins;

        final int diamondsNeededToBuyCoins;
        diamondsNeededToBuyCoins = coinsNeeded / 75;

        title.setText("Do you want to buy " + String.valueOf(coinsNeeded) + " coins, with " + (diamondsNeededToBuyCoins + 1) + " diamonds?");
        positiveText.setText("Yes");
        negativeText.setText("No");

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (diamondsNeededToBuyCoins < PlayerInfo.diamonds) {
                    PlayerInfo.addDiamonds(Store.this, -diamondsNeededToBuyCoins);
                    PlayerInfo.addCoins(Store.this, coinsNeeded);
                    PlayerInfo.refreshCurrency(Store.this);
                    diamondsText.setText(PlayerInfo.diamonds);
                    coinsText.setText(PlayerInfo.coins);
                    dialog.dismiss();
                }else {
                    buyMoney();
                }
            }
        });

        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerInfo.refreshCurrency(Store.this);
                diamondsText.setText(String.valueOf(PlayerInfo.diamonds));
                coinsText.setText(String.valueOf(PlayerInfo.coins));
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void changeCategory(View view) {
        //todo fiks linjen under
        setContentView(R.layout.store_chooser);
    }

    public void closeChangeCategory() {
        setContentView(R.layout.store);
        categoryText = (TextView) findViewById(R.id.categoryText);
        categoryText.setText(category);
        createOn();
    }

    public void categoryFood(View view) {
        category = "Food";
        closeChangeCategory();
    }

    public void categorySpecial(View view) {
        category = "Special";
        closeChangeCategory();
    }

    public void categoryWallpaper(View view) {
        category = "Wallpaper";
        closeChangeCategory();
    }
}
