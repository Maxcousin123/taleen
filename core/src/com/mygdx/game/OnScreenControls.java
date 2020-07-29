package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.TextArea;

public class OnScreenControls {

    public final Viewport viewport;
    public Taleen taleen;
    private Vector2 moveLeftCenter = new Vector2();
    private Vector2 moveRightCenter = new Vector2();
    private Vector2 fireCenter = new Vector2();
    private Texture moveleft;
    private Texture moveright ;
    private Texture firetexture ;
    public OnScreenControls() {
        this.viewport = new ExtendViewport(200,200);
        moveleft  = new Texture("LEFT.png");
        moveright = new Texture("RIGHT.png");
        firetexture = new Texture("FIRE.png");



        recalculateButtonPositions();



    }

    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(moveleft, 10 ,15, 35,35);
        batch.draw(moveright, 55 ,15, 35,35);
        batch.draw(firetexture, viewport.getWorldWidth() - 45 ,25, 35,35);
        batch.end();
    }
        public void touchDown(float delta) {
            Vector2 viewportPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());

            if (viewportPosition.dst(new Vector2(10,15)) < 35) {
                taleen.moveleft(delta);
            } else if (viewportPosition.dst(new Vector2(55,15)) < 35) {
                taleen.moveright(delta);
            } else if (viewportPosition.dst(new Vector2(viewport.getWorldWidth() - 45, 25)) < 35) {
                taleen.shoot();
            }


        }




    public void recalculateButtonPositions() {


    }


}
