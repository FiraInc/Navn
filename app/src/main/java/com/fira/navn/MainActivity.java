package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    ImageView creatureImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        CreatureInfo.loadCreature(this);
    }

    private void findViews() {
        creatureImage = (ImageView) findViewById(R.id.creatureImage);
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
