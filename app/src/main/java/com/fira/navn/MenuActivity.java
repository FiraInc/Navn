package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by johannett321 on 04.07.2017.
 */

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void battle(View view) {
        Battle.badgeBattle = false;
        Intent intent = new Intent(this, Battle.class);
        startActivity(intent);
    }

    public void badgeLoader(View view) {
        Intent intent = new Intent(this, Badges.class);
        startActivity(intent);
    }
}
