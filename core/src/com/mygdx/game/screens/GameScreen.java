package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Main;
import com.mygdx.game.MyAnimation;

public class GameScreen implements Screen {

    private Main game;
    private SpriteBatch batch;
//    private Texture img;
    MyAnimation myAnimation;
    private TextureAtlas atlasQuit;
    float time;
    boolean rotation = true;
    private Rectangle exitRect;
    private ShapeRenderer shapeRenderer;
    public GameScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
//      img = new Texture("frog.jpg");
        myAnimation = new MyAnimation("monster.png", 8, 3, Animation.PlayMode.LOOP);
        atlasQuit = new TextureAtlas("atlas/unnamed.atlas");
        exitRect = new Rectangle(0, Gdx.graphics.getHeight() - atlasQuit.findRegion("quit").getRegionHeight(), 100, 50);

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BROWN);
        time += Gdx.graphics.getDeltaTime();
        myAnimation.setTime(time);

        if(myAnimation.getFrame().isFlipX()) myAnimation.getFrame().flip(!rotation, false);
        if(!myAnimation.getFrame().isFlipX()) myAnimation.getFrame().flip(rotation, false);

        batch.begin();
        batch.draw(myAnimation.getFrame(), myAnimation.getX(), myAnimation.getY());
        batch.draw(atlasQuit.findRegion("quit"), exitRect.getX(), exitRect.getY(), exitRect.getWidth(), exitRect.getHeight());
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
              myAnimation.incrementX();
              rotation = true;
        }

        if(Gdx.graphics.getWidth() - (myAnimation.getX() + myAnimation.getX()/2) < 0){
             rotation =false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
             myAnimation.decrementX();
             rotation = false;
        }

        if((myAnimation.getX() - myAnimation.getX() / 2) < 0){
             rotation = true;
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(exitRect.x, exitRect.y, exitRect.width, exitRect.height);
        shapeRenderer.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (exitRect.contains(x, y)) {
                dispose();
                game.setScreen(new MenuScreen(game));
            }
       }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.batch.dispose();
        this.myAnimation.dispose();
        this.atlasQuit.dispose();
        this.shapeRenderer.dispose();
    }
}
