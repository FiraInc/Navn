package com.fira.navn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Johannett321 on 19.03.2017.
 */

public class BuyMoney extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_money);
    }

    public void addCoinsClicked(View view) {
        PlayerInfo.addCoins(this, 500);
    }

    public void addDiamondsClicked(View view) {
        PlayerInfo.addDiamonds(this, 2);
    }
}
