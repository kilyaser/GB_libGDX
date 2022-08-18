package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	MyAnimation myAnimation;
	boolean dir;
	float time;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		myAnimation = new MyAnimation("monster.png", 8, 3, Animation.PlayMode.LOOP);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		time += Gdx.graphics.getDeltaTime();
		myAnimation.setTime(time);
		//float x = Gdx.input.getX() - myAnimation.getFrame().getRegionWidth() / 2;
		//float y = Gdx.graphics.getHeight() - Gdx.input.getY() - myAnimation.getFrame().getRegionHeight() / 2 ;

		if(Gdx.input.isButtonJustPressed(Input.Keys.L)) dir = true;
		if(Gdx.input.isButtonJustPressed(Input.Keys.R)) dir = false;
		if(!myAnimation.getFrame().isFlipX() && !dir) myAnimation.getFrame().flip(true, false);
		if(myAnimation.getFrame().isFlipX() && dir) myAnimation.getFrame().flip(false, false);
		batch.begin();
		batch.draw(myAnimation.getFrame(), 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		myAnimation.dispose();

	}
}
