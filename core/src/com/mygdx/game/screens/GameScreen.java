package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Main;
import com.mygdx.game.MyAnimation;
import com.mygdx.game.Myhero;
import com.mygdx.game.PhysX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class GameScreen implements Screen {

    private Main game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private float STEP = 12;
    private float time;
    //MyAnimation animation;
    private Myhero myhero;
    private final int[] bg;
    private final int[] l1;
    private ShapeRenderer shapeRenderer;
    private PhysX physX;
    private Body body;
    private final Rectangle heroRect;
    private  float width;
    public static ArrayList<Body> bodies;

    public GameScreen(Main game) {
        bodies = new ArrayList<>();
        this.game = game;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.zoom = 1.25f;
        myhero = new Myhero();
        // animation = new MyAnimation("stay", Animation.PlayMode.LOOP);
        map = new TmxMapLoader().load("map/map_1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        bg = new int[1];
        bg[0] = map.getLayers().getIndex("Фон");
        l1 = new int[2];
        l1[0] = map.getLayers().getIndex("Слой 1");
        l1[1] = map.getLayers().getIndex("Слой 2");
        physX = new PhysX();
        map.getLayers().get("camera_point").getObjects().getByType(RectangleMapObject.class); //выбор объекта по типу
        RectangleMapObject tmp = (RectangleMapObject) map.getLayers().get("camera_point").getObjects().get("hero");
        heroRect = tmp.getRectangle();
        body = physX.addObject(tmp);
        width = heroRect.width;
        Array<RectangleMapObject> object = map.getLayers().get("объекты").getObjects().getByType(RectangleMapObject.class);
        for (int i = 0; i < object.size; i++) {
           physX.addObject(object.get(i));
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(myhero.getAnimation().getFrame().isFlipX()) myhero.getAnimation().getFrame().flip(!myhero.getDir(), false);
		if(!myhero.getAnimation().getFrame().isFlipX()) myhero.getAnimation().getFrame().flip(myhero.getDir(), false);

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.applyForceToCenter(new Vector2(-7, 0), true);
            myhero.run();
            //myhero.setDir(false);

            if(heroRect.width == width) {
                heroRect.width += 50;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.applyForceToCenter(new Vector2(7, 0), true);
            myhero.run();
            //myhero.setDir(true);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && physX.myContList.isOnGround()) {
               body.applyForceToCenter(new Vector2(0, 250), true);
               myhero.jump();


        }  else {
            myhero.stay();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.position.y += STEP;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.position.y -= STEP;
        time += Gdx.graphics.getDeltaTime();
        myhero.getAnimation().setTime(time);
        if(Gdx.input.isKeyPressed(Input.Keys.P)) camera.zoom += 0.1f;
        if(Gdx.input.isKeyPressed(Input.Keys.O) && camera.zoom > 0) camera.zoom -= 0.1f;

        camera.position.x = body.getPosition().x * PhysX.PPM;
        camera.position.y = body.getPosition().y * PhysX.PPM;

        camera.update();
        ScreenUtils.clear(Color.BROWN);
//        batch.setProjectionMatrix(camera.combined);

        float x = Gdx.graphics.getWidth() / 2 - heroRect.getWidth()/2/camera.zoom;
        float y = Gdx.graphics.getHeight() / 2 - heroRect.getHeight()/2/camera.zoom;
        batch.begin();
        batch.draw(myhero.getAnimation().getFrame(), x, y);
        batch.end();
        mapRenderer.setView(camera);

        mapRenderer.render(bg);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {

            dispose();
            game.setScreen(new MenuScreen(game));
        }

        mapRenderer.render(l1);

        physX.step();
        physX.debugDraw(camera);
        if (bodies.size() > 0) {
            for (int i = 0; i < bodies.size(); i++) {
                myhero.setCanJump(false);
            }
        } else {
            myhero.setCanJump(true);
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
        this.myhero.dispose();
       // this.animation.dispose();
//        this.shapeRenderer.dispose();
//        this.atlasQuit.dispose();

    }
}
