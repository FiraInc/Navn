package com.fira.navn;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class Battle extends Activity {

    ImageView image1;

    Boolean yourTurn = true;

    Boolean againstComputer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
        findViews();

        image1.setVisibility(View.INVISIBLE);


        OpponentCreatureInfo.generateRandom();
    }

    private void findViews() {
        image1 = (ImageView) findViewById(R.id.image1);
    }

    public void Attack1(View view) {
        if (yourTurn) {
            Attack("TestAttack");
        }
    }

    public void Attack2(View view) {

    }


    public void Attack(String attackID) {
        Attacks.calculateAttacks(this, attackID);
        Attacks.AttackSound.start();

        image1.setImageDrawable(Attacks.AttackImage1);
        image1.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                image1.setImageDrawable(Attacks.AttackImage2);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image1.setImageDrawable(Attacks.AttackImage3);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                image1.setImageDrawable(Attacks.AttackImage4);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        image1.setImageDrawable(Attacks.AttackImage5);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                image1.setImageDrawable(Attacks.AttackImage6);
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                                        v.vibrate(10);
                                                        image1.setVisibility(View.INVISIBLE);
                                                        endAttack();
                                                    }
                                                }, 100);
                                            }
                                        }, 100);
                                    }
                                }, 100);
                            }
                        }, 100);
                    }
                }, 100);
            }
        }, 100);
    }

    private void endAttack() {
        int OldHealth;
        int NowHealth;
        if (yourTurn) {
            OldHealth = OpponentCreatureInfo.health;
        }else {
            OldHealth = CreatureInfo.health;
        }

        if (yourTurn) {
            OpponentCreatureInfo.health = OpponentCreatureInfo.health - (Attacks.attackDamage / OpponentCreatureInfo.level);
            NowHealth = OpponentCreatureInfo.health;
        }else {
            CreatureInfo.health = CreatureInfo.health - (Attacks.attackDamage / CreatureInfo.level);
            NowHealth = CreatureInfo.health;
        }

        Log.e("MyLevel", String.valueOf(CreatureInfo.level));
        Log.e("YourLevel", String.valueOf(OpponentCreatureInfo.level));
        if (OldHealth <= NowHealth) {
            if (yourTurn) {
                OpponentCreatureInfo.health = OldHealth - 1;
            }else {
                CreatureInfo.health = OldHealth - 1;
            }
        }

        Log.e("OPPOHEALTH", String.valueOf(OpponentCreatureInfo.health));

        if (yourTurn) {
            if (OpponentCreatureInfo.health <= 0) {
                Toast.makeText(this, "CONGRATS!", Toast.LENGTH_LONG).show();
            }
        }else {
            if (CreatureInfo.health <= 0) {
                Toast.makeText(this, "NOOOO!", Toast.LENGTH_LONG).show();
            }
        }




        if (yourTurn) {
            yourTurn = false;
        }else {
            yourTurn = true;
        }
        if (againstComputer && !yourTurn) {
            ComputersTurn();
        }
    }


    private void ComputersTurn() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Attack("TestAttack");
            }
        }, 2000);
    }
}
