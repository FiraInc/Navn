package com.fira.navn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by johannett321 on 01/07/2017.
 */

public class Launcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                createOn();
            }
        }, 2000);
    }

    public void createOn() {
        Intent intent;
        if (ReadWrite.read(this, "InstallCheck.txt").equals("1")) {
            intent = new Intent(this, MainActivity.class);
        }else {
            intent = new Intent(this, EggHatch.class);
        }
        startActivity(intent);
        finish();
    }
}
