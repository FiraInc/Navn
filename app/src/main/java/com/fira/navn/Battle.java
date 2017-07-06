package com.fira.navn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Johannett321 on 26.06.2017.
 */

public class Battle extends Activity {

    ImageView opponentAttackEffect;
    ImageView youAttackEffect;

    ImageView OpponentCreatureBody;
    ImageView OpponentCreatureEyes;
    ImageView OpponentCreatureEyebrows;
    ImageView OpponentCreatureMouth;

    ImageView yourCreatureBody;

    static Boolean yourTurn = true;

    Boolean againstComputer = true;
    ImageView image1;

    TextView OpponentHealth;
    TextView YourHealth;
    TextView OpponentLevel;
    TextView YourLevel;

    ImageView blackScreen;
    TextView middleText;
    ImageView bushLeft;
    ImageView bushRight;

    LinearLayout AttackMenu;
    RelativeLayout AttacksButton;
    Boolean AttackMenuVisible = false;

    static Boolean badgeBattle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);


        findViews();

        yourTurn = true;
        againstComputer = true;

        opponentAttackEffect.setVisibility(View.INVISIBLE);
        youAttackEffect.setVisibility(View.INVISIBLE);


        PlayerInfo.refreshBattleSearcher(this);

        if (!badgeBattle) {
            OpponentCreatureInfo.generateRandom(this);
        }else {
            OpponentCreatureInfo.getGraphicsParts(this);
        }

        loadYourCreatureGraphics();
        loadOpponentCreatureGraphics();

        OpponentHealth.setText("OppoHLT: " + String.valueOf(OpponentCreatureInfo.health));
        YourHealth.setText("YourHLT: " + String.valueOf(CreatureInfo.health));
        OpponentLevel.setText("OppoLvl: " + String.valueOf(OpponentCreatureInfo.level));
        YourLevel.setText("YourLvl: " + String.valueOf(CreatureInfo.level));

        if (badgeBattle) {
            startInAnimation();
        }else {
            searchForOpponent();
        }

    }

    private void findViews() {
        opponentAttackEffect = (ImageView) findViewById(R.id.opponentAttackEffect);
        youAttackEffect = (ImageView) findViewById(R.id.youAttackEffect);

        OpponentHealth = (TextView) findViewById(R.id.OpponentHealth);
        YourHealth = (TextView) findViewById(R.id.YourHealth);
        OpponentLevel = (TextView) findViewById(R.id.OpponentLevel);
        YourLevel = (TextView) findViewById(R.id.YourLevel);

        OpponentCreatureBody = (ImageView) findViewById(R.id.opponentCreatureBody);
        OpponentCreatureEyes = (ImageView) findViewById(R.id.opponentCreatureEyes);
        OpponentCreatureEyebrows = (ImageView) findViewById(R.id.opponentCreatureEyeBrows);
        OpponentCreatureMouth = (ImageView) findViewById(R.id.opponentCreatureMouth);

        yourCreatureBody = (ImageView) findViewById(R.id.yourCreatureBody);

        blackScreen = (ImageView) findViewById(R.id.blackScreen);
        middleText = (TextView) findViewById(R.id.middleText);
        bushLeft = (ImageView) findViewById(R.id.bushLeft);
        bushRight = (ImageView) findViewById(R.id.bushRight);

        AttackMenu = (LinearLayout) findViewById(R.id.AttackMenu);
        AttacksButton = (RelativeLayout) findViewById(R.id.AttacksButton);
    }

    @Override
    public void onBackPressed() {
        if (AttackMenuVisible) {
            AttackMenuVisible = false;
            AttackMenu.setVisibility(View.GONE);
            AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }else {
            super.onBackPressed();
        }
    }

    int randomNumber;
    Boolean visibleSwitch = false;

    private void searchForOpponent () {
        Random random = new Random();
        randomNumber = random.nextInt(10);
        if (randomNumber == 2) {
            startInAnimation();
        }else {
            if (!visibleSwitch) {
                visibleSwitch = true;
                middleText.setVisibility(View.VISIBLE);
            }else {
                visibleSwitch = false;
                middleText.setVisibility(View.INVISIBLE);
            }
            blackScreen.setVisibility(View.VISIBLE);
            middleText.setText("Searching for opponent...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchForOpponent();
                }
            }, 500);
        }

    }

    private void startInAnimation() {
        middleText.setText("Found opponent!");
        blackScreen.setVisibility(View.VISIBLE);
        middleText.setVisibility(View.VISIBLE);
        bushLeft.setVisibility(View.VISIBLE);
        bushRight.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                middleText.setVisibility(View.INVISIBLE);
                Animation fadeOutAnimation = AnimationUtils.loadAnimation(Battle.this, R.anim.fadeout);
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        leavesAnimation();
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        blackScreen.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                blackScreen.startAnimation(fadeOutAnimation);
            }
        }, 1500);
    }

    private void leavesAnimation() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        TranslateAnimation animation = new TranslateAnimation(0, size.x, 0, 0);
        TranslateAnimation animation2 = new TranslateAnimation(0, -size.x, 0, 0);
        animation.setDuration(2000);
        animation2.setDuration(2000);
        animation.setFillAfter(false);
        animation2.setFillAfter(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bushLeft.clearAnimation();
                bushRight.clearAnimation();
                bushLeft.setVisibility(View.GONE);
                bushRight.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        bushLeft.startAnimation(animation2);
        bushRight.startAnimation(animation);
    }

    private void loadYourCreatureGraphics() {
        yourCreatureBody.setImageDrawable(CreatureInfo.CreatureImage);
    }

    private void loadOpponentCreatureGraphics() {
        OpponentCreatureBody.setImageDrawable(OpponentCreatureInfo.creatureBody);
        OpponentCreatureEyes.setImageDrawable(OpponentCreatureInfo.creatureEyes);
        OpponentCreatureMouth.setImageDrawable(OpponentCreatureInfo.creatureMouth);
        OpponentCreatureEyebrows.setImageDrawable(OpponentCreatureInfo.creatureEyebrows);
    }

    public void Attack1(View view) {
        if (yourTurn) {
            Attack("TestAttack");
            AttackMenuVisible = false;
            AttackMenu.setVisibility(View.GONE);
            AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
    }

    public void Attack2(View view) {
        if (yourTurn) {
            Attack("TestAttack2");
            AttackMenuVisible = false;
            AttackMenu.setVisibility(View.GONE);
            AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
    }

    public void Attack3(View view) {
        if (yourTurn) {
            Attack("TestAttack3");
            AttackMenuVisible = false;
            AttackMenu.setVisibility(View.GONE);
            AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
    }

    public void Attack4(View view) {
        if (yourTurn) {
            Attack("TestAttack3");
            AttackMenuVisible = false;
            AttackMenu.setVisibility(View.GONE);
            AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
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
                yourTurn = false;
                Toast.makeText(this, "CONGRATS!", Toast.LENGTH_LONG).show();
                OpponentCreatureInfo.health = 0;

                int levelAmount;
                if (CreatureInfo.level > OpponentCreatureInfo.level) {
                    levelAmount = ((CreatureInfo.xpNeeded / (CreatureInfo.level - OpponentCreatureInfo.level)) / CreatureInfo.level)*2;
                }else if (CreatureInfo.level == OpponentCreatureInfo.level) {
                    levelAmount = ((CreatureInfo.xpNeeded + (CreatureInfo.xpNeeded/3)) / CreatureInfo.level)*2;
                }else {
                    levelAmount = (CreatureInfo.xpNeeded / CreatureInfo.level) + (CreatureInfo.xpNeeded * (OpponentCreatureInfo.level - CreatureInfo.level) /5);
                }
                int oldLevel = CreatureInfo.level;
                CreatureInfo.leveling(this, levelAmount);

                if (CreatureInfo.level > oldLevel) {
                    //todo level up
                    Toast.makeText(this, "Grattis!!!", Toast.LENGTH_SHORT).show();
                    CreatureInfo.saveCreature(this);
                }

                if (badgeBattle) {
                    finishBadgeBattle();
                }
            }
        }else {
            if (CreatureInfo.health <= 0) {
                Toast.makeText(this, "NOOOO!", Toast.LENGTH_LONG).show();
                CreatureInfo.health = 0;
                CreatureInfo.saveCreature(this);
            }
        }

        OpponentHealth.setText("OppoHLT: " + String.valueOf(OpponentCreatureInfo.health));
        YourHealth.setText("YourHLT: " + String.valueOf(CreatureInfo.health));
        CreatureInfo.saveCreature(this);




        if (yourTurn) {
            yourTurn = false;
        }else if (CreatureInfo.health > 0){
            yourTurn = true;
        }
        if (againstComputer && !yourTurn && OpponentCreatureInfo.health > 0) {
            ComputersTurn();
        }
    }

    private void finishBadgeBattle() {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(Battle.this, R.anim.fadein);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                blackScreen.setVisibility(View.VISIBLE);
                middleText.setVisibility(View.VISIBLE);
                middleText.setText("CONGRATULATIONS!");
                Badges.battleSuccess = true;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Battle.this, Badges.class);
                        startActivity(intent);
                        finish();
                    }
                }, 4000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        blackScreen.startAnimation(fadeOutAnimation);
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

    public void showAttacks(View view) {
        if (AttackMenuVisible) {
            AttackMenuVisible = false;
            AttackMenu.setVisibility(View.GONE);
            AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }else {
            if (yourTurn) {
                AttackMenuVisible = true;
                AttackMenu.setVisibility(View.VISIBLE);
                AttacksButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
            }
        }
    }

    int newCreatureNumber = 0;

    public void Catch(View view) {
        updateNewCreatureNumber();

        ReadWrite.write(this, "creatureHasCustomBody" + String.valueOf(newCreatureNumber) + ".txt", "1");
        ReadWrite.write(this, "creatureBody" + String.valueOf(newCreatureNumber) + ".txt", String.valueOf(OpponentCreatureInfo.bodyNumber));
        ReadWrite.write(this, "creatureEyebrows" + String.valueOf(newCreatureNumber) + ".txt", String.valueOf(OpponentCreatureInfo.eyebrowNumber));
        ReadWrite.write(this, "creatureEyes" + String.valueOf(newCreatureNumber) + ".txt", String.valueOf(OpponentCreatureInfo.eyeNumber));
        ReadWrite.write(this, "creatureMouth" + String.valueOf(newCreatureNumber) + ".txt", String.valueOf(OpponentCreatureInfo.mouthNumber));

        CreatureInfo.name = "No name";
        CreatureInfo.level = OpponentCreatureInfo.level;
        CreatureInfo.xp = 0;
        CreatureInfo.xpNeeded = OpponentCreatureInfo.level;
        CreatureInfo.type = 0;
        CreatureInfo.food = 90;
        CreatureInfo.thirsty = 100;
        CreatureInfo.health = OpponentCreatureInfo.health;
        CreatureInfo.currentCreature = newCreatureNumber;
        CreatureInfo.feed(this, 10);

        CreatureInfo.saveCreature(this);
    }

    private void updateNewCreatureNumber() {
        if (!ReadWrite.read(this, "creatureName" + String.valueOf(newCreatureNumber) + ".txt").equals("0")) {
            newCreatureNumber = newCreatureNumber+1;
            updateNewCreatureNumber();
        }
    }
}
