package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {

    private final Level level;
    public boolean active;
    private Vector2 position;
    Texture img;
    private Vector2 enemyPosition;
    public Bullet(Level level, Vector2 position) {
        this.level = level;
        this.position = position;
        active = true;
        img = new Texture("ammo.png");
        enemyPosition = new Vector2();
    }

    public void update(float delta) {

        position.y += delta * 500;

        for (Enemy enemy : level.getEnemies()) {
            enemyPosition = new Vector2(enemy.position.x + 40 , enemy.position.y + 35);
            if (position.dst(enemyPosition) < 40) {
                active = false;
                enemy.health -= 1;
            }
        }

        final float worldHeight = level.getViewport().getWorldHeight();

        if (position.y > worldHeight) {
            active = false;
        }






    }

    public void render(SpriteBatch batch) {

        batch.draw(img,position.x,position.y, 6,14);
    }


}
