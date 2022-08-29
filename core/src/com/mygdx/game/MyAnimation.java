package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAnimation {
    private Texture img;
    private TextureAtlas atlas;
    private Animation<TextureRegion> animation;
    private float time;

//    private int xPosition = 0;
//    private int yPosition = 0;

    public MyAnimation(String name, Animation.PlayMode playMode){
//        img = new Texture(name);
//        TextureRegion region0 = new TextureRegion(img);
//        int xCnt = region0.getRegionWidth() / col;
//        int yCnt = region0.getRegionHeight() / row;
//        TextureRegion[][] regions0 = region0.split(xCnt, yCnt);
//        TextureRegion[] regions = new TextureRegion[regions0.length * regions0[0].length];
//        int cnt = 0;
//        for(int i = 0; i < regions0.length; i++){
//            for(int j = 0; j < regions0[0].length; j++){
//                regions[cnt++] = regions0[i][j];
//            }
//        }
        atlas = new TextureAtlas("atlas/vervolfTS.atlas");
        animation = new Animation<TextureRegion>(1f/15, atlas.findRegions(name));
        animation.setPlayMode(playMode);

        time = 0;

    }


    public TextureRegion getFrame(){
        return animation.getKeyFrame(time, true);
    }
    public void setTime(float time){
        this.time = time;
    }
    public void zeroTime(){this.time = 0;}
    public boolean isAnimationOver(){
        return animation.isAnimationFinished(time);
    }
    public void setMode(Animation.PlayMode playMode){
        animation.setPlayMode(playMode);
    }
    public void dispose(){
        img.dispose();
        atlas.dispose();
    }
//    public int getX(){
//        return xPosition;
//    }
//    public  int getY(){
//        return yPosition;
//    }
//    public void incrementX(){
//        this.xPosition++;
//    }
//    public void decrementX(){
//        this.xPosition--;
//    }
}
