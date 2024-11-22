package ru.mipt.bit.platformer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.graphics.GraphicModel;
import ru.mipt.bit.platformer.model.MovementLogic;
import ru.mipt.bit.platformer.model.MovementModel;

import static com.badlogic.gdx.Input.Keys.*;

public class ButtonPressHandler {
    private MovementLogic movement;

    public ButtonPressHandler() {
        movement = new MovementLogic(1f);
    }
    public void UP(MovementModel tank, GraphicModel obstacle) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            movement.doStep(new GridPoint2(0, 1), tank, obstacle);
        }
    }
    public void LEFT(MovementModel tank, GraphicModel obstacle) {
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            movement.doStep(new GridPoint2(-1, 0), tank, obstacle);
        }
    }
    public void DOWN(MovementModel tank, GraphicModel obstacle) {
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            movement.doStep(new GridPoint2(0, -1), tank, obstacle);
        }
    }
    public void RIGHT(MovementModel tank, GraphicModel obstacle) {
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            movement.doStep(new GridPoint2(1, 0), tank, obstacle);
        }
    }
}
