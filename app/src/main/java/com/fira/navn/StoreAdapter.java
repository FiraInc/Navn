package com.fira.navn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Johannett321 on 04.03.2017.
 */

public class StoreAdapter extends ArrayAdapter<StoreItems> {

    static ArrayList<String> creatureNumberArray;
    Context mContext;

    TextView CoinsText;
    TextView DiamondsText;

    public StoreAdapter (Context context, ArrayList<StoreItems> users, TextView coinsText, TextView diamondsText) {
        super(context, R.layout.store_item, users);
        this.mContext = context;
        this.CoinsText = coinsText;
        this.DiamondsText = diamondsText;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        final StoreItems items = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.store_item, parent, false);
        }

        ImageView itemImage = (ImageView) convertView.findViewById(R.id.itemImage);
        ImageView buyButton = (ImageView) convertView.findViewById(R.id.buyButton);
        final TextView itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
        TextView itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
        final TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        final TextView amountLeft = (TextView) convertView.findViewById(R.id.amountLeft);

        itemImage.setImageBitmap(items.ItemImage.getBitmap());
        itemTitle.setText(items.Title);
        itemDescription.setText(items.Description);
        if (Store.category.equals("Wallpaper")) {
            if (ReadWrite.read(mContext, "CustomWallpaper.txt").toString().equals(items.Title)) {
                itemPrice.setText("Current");
            }else {
                itemPrice.setText(items.Price + "$");
            }

        }else {
            itemPrice.setText(items.Price + "$");
        }

        if (!Store.category.equals("Wallpaper")) {
            amountLeft.setText(items.CurrentAmount);
        }else {
            amountLeft.setVisibility(View.INVISIBLE);
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerInfo.refreshCurrency(mContext);
                if (PlayerInfo.coins >= Integer.parseInt(items.Price)) {
                    if (!Store.category.equals("Wallpaper")) {
                        ReadWrite.write(mContext, items.Title + "ItemAmount.txt", String.valueOf(Integer.parseInt(ReadWrite.read(mContext, items.Title + "ItemAmount.txt")) + Integer.parseInt(items.Amount)));
                        items.CurrentAmount = String.valueOf(Items.loadAmount(mContext, items.Title));
                        PlayerInfo.addCoins(mContext, -Integer.parseInt(items.Price));
                        amountLeft.setText(items.CurrentAmount);
                    }else {
                        if (!ReadWrite.read(mContext, "CustomWallpaper.txt").equals(items.Title)) {
                            ReadWrite.write(mContext, "CustomWallpaper.txt", items.Title);
                            PlayerInfo.addCoins(mContext, -Integer.parseInt(items.Price));
                            ((Activity)mContext).finish();
                        }
                    }

                    DiamondsText.setText(String.valueOf(PlayerInfo.diamonds));
                    CoinsText.setText(String.valueOf(PlayerInfo.coins));

                }else {
                    Intent intent = new Intent("my-integer");
                    intent.putExtra("message", 1);
                    intent.putExtra("positioner", position);
                }
            }
        });

        return convertView;
    }
}
