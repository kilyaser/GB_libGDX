package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.GameScreen;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

public class MyContList implements ContactListener {
    public boolean isOnGround() {
        return counter > 0;
    }

    private int counter;

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a.getUserData() != null && b.getUserData() != null) {
            String tmpA = (String) a.getUserData();
            String tmpB = (String) b.getUserData();

            if ((tmpA.equals("foots") && tmpB.equals("enemy"))) {
                GameScreen.bodies.add(b.getBody());

            }
            if ((tmpB.equals("foots") && tmpA.equals("enemy"))) {
                GameScreen.bodies.add(a.getBody());
            }
            if ((tmpA.equals("foots") && tmpB.equals("ground"))) {
                counter++;
            }
            if ((tmpB.equals("foots") && tmpA.equals("ground"))) {
                counter++;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        if (a.getUserData() != null && b.getUserData() != null) {
            String tmpA = (String) a.getUserData();
            String tmpB = (String) b.getUserData();

            if ((tmpA.equals("foots") && tmpB.equals("ground"))) {
                counter--;
            }
            if ((tmpB.equals("foots") && tmpA.equals("ground"))) {
                counter--;
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
