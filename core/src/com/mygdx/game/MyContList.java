package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class MyContList implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a.getUserData() != null && b.getUserData() != null) {
            if((a.isSensor() || b.isSensor())) {
                String tmpA = (String) a.getUserData();
                String tmpB = (String) b.getUserData();

                if ((tmpA.equals("foots") && tmpB.equals("Земля"))) {
                      GameScreen.bodies.add(b.getBody());
                }
                if ((tmpB.equals("foots") && tmpA.equals("Земля"))) {
                    GameScreen.bodies.add(a.getBody());
                }
            }
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
