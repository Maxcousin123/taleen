package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameplayScreen extends ScreenAdapter {

    Level level;
    ExtendViewport viewport;
    SpriteBatch batch;
    Texture background;
    OnScreenControls onScreenControls;


    @Override
    public void render(float delta) {

        level.update(delta);
        viewport.apply();
        Gdx.gl.glClearColor(
               1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background,0,0,viewport.getWorldWidth(),viewport.getWorldHeight());
        batch.end();
        batch.begin();
        level.render(batch);
        batch.end();
        onScreenControls.touchDown(delta);

        onScreenControls.render(batch);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        onScreenControls.viewport.update(width, height, true);
        onScreenControls.recalculateButtonPositions();
    }

    @Override
    public void show() {
        viewport = new ExtendViewport(400,300);
        batch = new SpriteBatch();
        level = new Level(viewport);
        background = new Texture("BACKGROUND.jpg");
        onScreenControls = new OnScreenControls();
        Gdx.input.setInputProcessor(onScreenControls);

        }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }
    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
