package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.TextArea;

public class OnScreenControls extends InputAdapter {

    public final Viewport viewport;
    public Taleen taleen;
    private Vector2 moveLeftCenter;
    private Vector2 moveRightCenter;
    private Vector2 fireCenter;
    private int moveLeftPointer;
    private int moveRightPointer;

    private Texture moveleft;
    private Texture moveright ;
    private Texture firetexture ;
    public OnScreenControls() {
        this.viewport = new ExtendViewport(200,200);
        moveleft  = new Texture("LEFT.png");
        moveright = new Texture("RIGHT.png");
        firetexture = new Texture("FIRE.png");
        moveLeftCenter = new Vector2();
        moveRightCenter = new Vector2();
        fireCenter = new Vector2();

        recalculateButtonPositions();



    }

    public void render(SpriteBatch batch) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (!Gdx.input.isTouched(moveLeftPointer)) {
            taleen.leftButtonPressed = false;
            moveLeftPointer = 0;

        }
        if (!Gdx.input.isTouched(moveRightPointer)) {
            taleen.rightButtonPressed = false;
            moveLeftPointer = 0;
        }









        batch.draw(moveleft, 10 ,15, 35,35);
        batch.draw(moveright, 55 ,15, 35,35);
        batch.draw(firetexture, viewport.getWorldWidth() - 45 ,25, 35,35);
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (viewportPosition.dst(fireCenter) < 35) {
            taleen.shoot();
        } else if (viewportPosition.dst(moveLeftCenter) < 35) {
            moveLeftPointer = pointer;
            taleen.leftButtonPressed = true;
        } else if (viewportPosition.dst(moveRightCenter) < 35) {
            moveRightPointer = pointer;
            taleen.rightButtonPressed = true;
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < 35) {
            taleen.leftButtonPressed = false;
            taleen.rightButtonPressed = true;
            moveLeftPointer = 0;
            moveRightPointer = pointer;
        }
        if (pointer == moveLeftPointer && viewportPosition.dst(moveLeftCenter) < 35) {
            taleen.rightButtonPressed = false;
            taleen.leftButtonPressed = true;
            moveRightPointer = 0;
            moveLeftPointer = pointer;
        }



        return super.touchDragged(screenX, screenY, pointer);
    }

    public void recalculateButtonPositions() {
        moveLeftCenter.set(35 * 3 / 4, 35);
        moveRightCenter.set(35 * 2, 35 * 3 / 4);



    }


}
