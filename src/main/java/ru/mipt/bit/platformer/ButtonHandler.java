package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.objects.Command;

import static com.badlogic.gdx.Input.Keys.*;

public class ButtonHandler {
    public Command getCommand() {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            return Command.UP;
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            return Command.LEFT;
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            return Command.DOWN;
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            return Command.RIGHT;
        }
        return null;
    }
    public GridPoint2 action(Command command) {
        if (command == Command.UP) {
            return new GridPoint2(0, 1);
        }
        if (command == Command.LEFT) {
            return new GridPoint2(-1, 0);
        }
        if (command == Command.DOWN) {
            return new GridPoint2(0, -1);
        }
        if (command == Command.RIGHT) {
            return new GridPoint2(1, 0);
        }
        return new GridPoint2(0, 0);
    }
}
