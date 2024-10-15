package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.math.MathUtils.isEqual;

public class ButtonPressHandler {
    private boolean checkNoCollisionWithObstacles(GridPoint2 step, Model tank, Obstacle obstacle) {
        GridPoint2 newCoordinates = tank.getCoordinates();
        newCoordinates.x += step.x;
        newCoordinates.y += step.y;
        return !obstacle.getCoordinates().equals(newCoordinates);
    }
    private void doStep(GridPoint2 step, Model tank, Obstacle obstacle) {
        if (isEqual(tank.getProgress(), 1f)) {
            // check potential player destination for collision with obstacles
            if (checkNoCollisionWithObstacles(step, tank, obstacle)) {
                tank.getDestinationCoordinates().y += step.y;
                tank.getDestinationCoordinates().x += step.x;
                tank.setProgress(0f);
            }
            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
            tank.getDirection().setRotation(newPlayerRotation);
        }
    }
    public void UP(Model tank, Obstacle obstacle) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            doStep(new GridPoint2(0, 1), tank, obstacle);
        }
    }
    public void LEFT(Model tank, Obstacle obstacle) {
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            doStep(new GridPoint2(-1, 0), tank, obstacle);
        }
    }
    public void DOWN(Model tank, Obstacle obstacle) {
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            doStep(new GridPoint2(0, -1), tank, obstacle);
        }
    }
    public void RIGHT(Model tank, Obstacle obstacle) {
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            doStep(new GridPoint2(1, 0), tank, obstacle);
        }
    }
}
