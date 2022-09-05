package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Main;

public class MenuScreen implements Screen {

    private Main game;
    private SpriteBatch batch;
    private Texture img;
    //private final TextureAtlas atlas;
    private Rectangle startRect;
    private ShapeRenderer shapeRenderer;
    private final Music music;
    private final Sound sound;

    public MenuScreen(Main game){
        this.game = game;
        batch = new SpriteBatch();
        img = new Texture("logo.png");
        //atlas = new TextureAtlas("atlas/unnamed.atlas");
        startRect = new Rectangle(0, 0, 100, 50);
        shapeRenderer = new ShapeRenderer();

        music = Gdx.audio.newMusic(Gdx.files.internal("borislav-slavov-rivellon.mp3"));
        music.setLooping(true);
        music.play();

        sound = Gdx.audio.newSound(Gdx.files.internal("voi-odinochnyii-dalekii-priglushennyii.mp3"));

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BROWN);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
       // batch.draw(atlas.findRegion("play"), 0, 0);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(startRect.x, startRect.y, startRect.width, startRect.height);
        shapeRenderer.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            if (startRect.contains(x, y)) {
                dispose();
                game.setScreen(new GameScreen(game));
            } else {
                sound.play();
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
        this.img.dispose();
        this.shapeRenderer.dispose();
        this.music.dispose();
        this.sound.dispose();
       // this.atlas.dispose();
    }
}
