package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Main;


public class GameScreen implements Screen {

    private Main game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private float STEP = 12;
    private Rectangle mapSize;
    private ShapeRenderer shapeRenderer;
    private Rectangle exitRect;
    private TextureAtlas atlasQuit;

    public GameScreen(Main game) {
        this.game = game;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        map = new TmxMapLoader().load("map/map_1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        map.getLayers().get("camera_point").getObjects().getByType(RectangleMapObject.class); //выбор объекта по типу
        RectangleMapObject tmp = (RectangleMapObject) map.getLayers().get("camera_point").getObjects().get("camera");
        camera.position.x = tmp.getRectangle().x;
        camera.position.y = tmp.getRectangle().y;
        tmp = (RectangleMapObject) map.getLayers().get("camera_point").getObjects().get("border");
        mapSize = tmp.getRectangle();
        atlasQuit = new TextureAtlas("atlas/unnamed.atlas");
        exitRect = new Rectangle(0, mapSize.height -atlasQuit.findRegion("quit").getRegionHeight(), 100, 50);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && mapSize.x < camera.position.x - 1) camera.position.x -= STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mapSize.x + mapSize.width > camera.position.x + 1) camera.position.x += STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && mapSize.y < camera.position.y - 1) camera.position.y += STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && mapSize.y - mapSize.height > camera.position.x - 1) camera.position.y -= STEP;

        if(Gdx.input.isKeyPressed(Input.Keys.P)) camera.zoom += 0.1f;
        if(Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0) camera.zoom -= 0.1f;
        camera.update();
        ScreenUtils.clear(Color.BROWN);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(atlasQuit.findRegion("quit"), exitRect.getX(), exitRect.getY(), exitRect.getWidth(), exitRect.getHeight());
        batch.end();
        mapRenderer.setView(camera);
        mapRenderer.render();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(mapSize.x, mapSize.y, mapSize.width, mapSize.height);
        shapeRenderer.rect(exitRect.x, exitRect.y, exitRect.width, exitRect.height);
        shapeRenderer.end();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            System.out.println("contains: " + exitRect.contains(x, y));
            if (exitRect.contains(x, y)) {
                dispose();
                game.setScreen(new MenuScreen(game));
            }
        }


    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;

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
        this.shapeRenderer.dispose();
        this.atlasQuit.dispose();

    }
}
