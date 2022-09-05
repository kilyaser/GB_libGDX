package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysX {

    private final World world;
    private final Box2DDebugRenderer dDebugRenderer;
    public static final float PPM = 100;
    public final MyContList myContList;

    public PhysX() {
        world = new World(new Vector2(0, -9.81f), true);
        myContList = new MyContList();
        world.setContactListener(myContList);
        this.dDebugRenderer = new Box2DDebugRenderer();
    }

    public Body addObject(RectangleMapObject object) {
        Rectangle rec = object.getRectangle();
        String type = (String) object.getProperties().get("BodyType");

        BodyDef def = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        def.type = BodyDef.BodyType.StaticBody;
        if (type.equals("StaticBody")) def.type = BodyDef.BodyType.StaticBody;
        if (type.equals("DynamicBody")) def.type = BodyDef.BodyType.DynamicBody;

        def.position.set((rec.x + rec.width / 2) / PPM, (rec.y + rec.height / 2) / PPM);
        def.gravityScale = (float) object.getProperties().get("gravityScale");  // отношение к гравитации

        polygonShape.setAsBox(rec.width / 2 / PPM, rec.height / 2 / PPM);
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 0; //  cкольжение
        fixtureDef.density = (float) object.getProperties().get("density");;    // плотность
        fixtureDef.restitution = (float) object.getProperties().get("restitution");;         //прыгучесть

//        world.createBody(def).createFixture(fixtureDef).setUserData("стена");
        Body body;
        body = world.createBody(def);
        String name = object.getName();
        body.createFixture(fixtureDef).setUserData(name);
        if (name != null && name.equals("hero")) {
            polygonShape.setAsBox(rec.width / 3 / PPM, rec.height / 12 / PPM, new Vector2(0, -rec.width / 2 / PPM), 0);
            body.createFixture(fixtureDef).setUserData("foots");
            body.getFixtureList().get(body.getFixtureList().size - 1).setSensor(true);
        }

        polygonShape.dispose();
        return body;
    }

    public void setGravity(Vector2 gravity) {
     world.setGravity(gravity);
    }

    public void debugDraw(OrthographicCamera camera) {
        dDebugRenderer.render(world, camera.combined);
    }

    public void step() {
        world.step(1/60.0f, 3, 3 );
    }

    public void dispose() {
        world.dispose();
        dDebugRenderer.dispose();

    }
}
