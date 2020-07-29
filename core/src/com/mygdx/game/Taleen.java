package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Taleen {
    Vector2 position;
    Level level;
    Texture texture;
    Viewport viewport;
    public boolean fireButtonPressed;
    public boolean leftButtonPressed;
    public boolean rightButtonPressed;

    public Taleen(Vector2 position, Level level, Viewport viewport) {
        this.position = position;
        this.level = level;
        texture = new Texture("TOP_VIEW(LOW Q).png");
        this.viewport = viewport;
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT ) || rightButtonPressed) {
            moveright(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftButtonPressed) {
            moveleft(delta);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT) || fireButtonPressed) {
            shoot();
        }

        if (position.x < viewport.getWorldWidth() - viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - viewport.getWorldWidth() ;
        }
        if (position.x > viewport.getWorldWidth()- 45) {
            position.x = viewport.getWorldWidth() - 45;
        }





    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x,position.y, 50, 90);

    }

    public void moveleft(float delta) {
        position.x -= delta * 200;
    }
    public void moveright(float delta) {
        position.x += delta * 200;
    }


    public void shoot() {
        Vector2 bulletPosition;
        bulletPosition = new Vector2(position.x + 18.6f, position.y + 66);
        level.spawnBullet(bulletPosition);
    }


}
