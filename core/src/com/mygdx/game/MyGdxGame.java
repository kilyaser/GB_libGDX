package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	//Texture imgAim;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		//imgAim = new Texture("aim.png");
		Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		//float x = Gdx.input.getX() - imgAim.getWidth()/2;
		//float y = Gdx.graphics.getHeight() - Gdx.input.getY() - imgAim.getHeight()/2;

		batch.begin();
		batch.draw(img, 0, 0);
		//batch.draw(imgAim, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		//imgAim.dispose();
	}
}
