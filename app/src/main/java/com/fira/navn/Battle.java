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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class Battle extends Activity {

    ImageView opponentAttackEffect;
    ImageView youAttackEffect;

    static Boolean yourTurn = true;

    Boolean againstComputer = true;
    ImageView image1;

    TextView OpponentHealth;
    TextView YourHealth;
    TextView OpponentLevel;
    TextView YourLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);
        findViews();

        yourTurn = true;
        againstComputer = true;

        opponentAttackEffect.setVisibility(View.INVISIBLE);
        youAttackEffect.setVisibility(View.INVISIBLE);


        OpponentCreatureInfo.generateRandom();

        OpponentHealth.setText("OppoHLT: " + String.valueOf(OpponentCreatureInfo.health));
        YourHealth.setText("YourHLT: " + String.valueOf(CreatureInfo.health));
        OpponentLevel.setText("OppoLvl: " + String.valueOf(OpponentCreatureInfo.level));
        YourLevel.setText("YourLvl: " + String.valueOf(CreatureInfo.level));
    }

    private void findViews() {
        opponentAttackEffect = (ImageView) findViewById(R.id.opponentAttackEffect);
        youAttackEffect = (ImageView) findViewById(R.id.youAttackEffect);

        OpponentHealth = (TextView) findViewById(R.id.OpponentHealth);
        YourHealth = (TextView) findViewById(R.id.YourHealth);
        OpponentLevel = (TextView) findViewById(R.id.OpponentLevel);
        YourLevel = (TextView) findViewById(R.id.YourLevel);
    }

    public void Attack1(View view) {
        if (yourTurn) {
            Attack("TestAttack");
        }
    }

    public void Attack2(View view) {
        if (yourTurn) {
            Attack("TestAttack2");
        }
    }


    public void Attack(String attackID) {
        Attacks.calculateAttacks(this, attackID);
        Attacks.AttackSound.start();

        if (yourTurn) {
            image1 = opponentAttackEffect;
        }else {
            image1 = youAttackEffect;
        }

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
                OpponentCreatureInfo.health = 0;
            }
        }else {
            if (CreatureInfo.health <= 0) {
                Toast.makeText(this, "NOOOO!", Toast.LENGTH_LONG).show();
                CreatureInfo.health = 0;
            }
        }

        OpponentHealth.setText("OppoHLT: " + String.valueOf(OpponentCreatureInfo.health));
        YourHealth.setText("YourHLT: " + String.valueOf(CreatureInfo.health));




        if (yourTurn) {
            yourTurn = false;
        }else {
            yourTurn = true;
        }
        if (againstComputer && !yourTurn && OpponentCreatureInfo.health > 0) {
            ComputersTurn();
        }
    }


    private void ComputersTurn() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int randomNumber = random.nextInt(4);
                if (randomNumber == 0) {
                    Attack(OpponentCreatureInfo.Attack1);
                }else if (randomNumber == 1) {
                    Attack(OpponentCreatureInfo.Attack2);
                }else if (randomNumber == 2) {
                    Attack(OpponentCreatureInfo.Attack3);
                }else if (randomNumber == 3) {
                    Attack(OpponentCreatureInfo.Attack4);
                }else {
                    Attack(OpponentCreatureInfo.Attack1);
                }
                Toast.makeText(Battle.this, "Random: " + String.valueOf(randomNumber), Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
