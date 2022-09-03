package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Myhero {
    MyAnimation heroAnimation;
    private boolean dir;

    public boolean getDir() {
        return dir;
    }

    public Myhero () {
        heroAnimation = new MyAnimation("atlas/unnamed.atlas", "stay", Animation.PlayMode.LOOP);
    }

    public void run() {
        heroAnimation = new MyAnimation("atlas/unnamed.atlas", "run", Animation.PlayMode.LOOP);
    }
    public void stayDown() {

    }

    public void jump() {
        heroAnimation = new MyAnimation("atlas/unnamed.atlas", "jump", Animation.PlayMode.NORMAL);
    }

    public void bite() {

    }

    public void hitLeg() {

    }
    public void setDir(Boolean bool) {
        dir = bool;
    }
    public void stay() {
        heroAnimation = new MyAnimation("atlas/unnamed.atlas", "stay", Animation.PlayMode.LOOP);
    }
    public MyAnimation getAnimation() {
        return heroAnimation;
    }

    public void dispose() {
        heroAnimation.dispose();
    }
}
