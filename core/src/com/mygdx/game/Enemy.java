package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;




public class Enemy {

    public Vector2 position;
    public int health;
    final long startTime;


    public Enemy(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();

        health = 1;

    }

    public void update(float delta) {
        position.y -= delta * 20;


    }

    public void render(SpriteBatch batch) {
        final Texture texture = new Texture("pinkzombie(LOW Q).png");
        batch.draw(texture, position.x ,position.y,60, 90);

    }



}
