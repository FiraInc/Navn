package com.fira.navn;

import android.graphics.Bitmap;

/**
 * Created by Johannett321 on 04.03.2017.
 */

public class CreatureInventoryItems {

    public String Title;
    public int ItemFolderNumber;
    public Bitmap ImageBitmap;
    public int FoodStatus;
    public int HealthStatus;

    public CreatureInventoryItems(String title, Bitmap imageBitmap, int itemFolderNumber, int healthStatus, int foodStatus) {
        this.Title = title;
        this.ImageBitmap = imageBitmap;
        this.ItemFolderNumber = itemFolderNumber;
        this.FoodStatus = foodStatus;
        this.HealthStatus = healthStatus;

    }

}
