package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Level {

    private Viewport viewport;
    private  Taleen taleen;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Enemy> enemies;

    public Level(Viewport viewport) {
        this.viewport = viewport;
        taleen = new Taleen(new Vector2(200,20) , this, viewport);
        bullets = new DelayedRemovalArray<>();
        enemies = new DelayedRemovalArray<>(false, 5);



            }




    public void update(float delta) {
        if (MathUtils.random() < delta * 1) {
            Vector2 newEnemyPosition = new Vector2(MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight());
            Enemy newEnemy = new Enemy(newEnemyPosition);
            enemies.add(newEnemy);
        }
        for (Enemy enemy : enemies) {
            enemy.update(delta);
        }




        taleen.update(delta);
        bullets.begin();
        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (!bullet.active) {
                bullets.removeValue(bullet, false);
            }
        }
        bullets.end();

        enemies.begin();
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
            if (enemy.health < 1) {
                enemies.removeIndex(i);
            }

        }
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.position.y < -100) {
                enemies.removeIndex(i);
            }
        }
        enemies.end();



    }

    public void render(SpriteBatch batch) {

        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
        taleen.render(batch);

        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

    }



    public Viewport getViewport() {
        return viewport;
    }
    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public void spawnBullet(Vector2 position) {
        bullets.add(new Bullet(this, position));
    }

}
