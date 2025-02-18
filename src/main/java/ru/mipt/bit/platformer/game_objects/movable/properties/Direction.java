package ru.mipt.bit.platformer.game_objects.movable.properties;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.bit.platformer.game_management.commands.Command;

//@Entity
//@Table(name = "stories")
//@Component
public class Direction {
    @Getter
    @Setter
    private GridPoint2 vector = new GridPoint2(0, 0);
    @Getter
    @Setter
    private float rotation = 0f;

    public Direction(Command command) {
        if (command == Command.UP) {
            this.vector = new GridPoint2(0, 1);
            this.rotation = 90f;
        }
        if (command == Command.LEFT) {
            this.vector = new GridPoint2(-1, 0);
            this.rotation = 180f;
        }
        if (command == Command.DOWN) {
            this.vector = new GridPoint2(0, -1);
            this.rotation = 270f;
        }
        if (command == Command.RIGHT) {
            this.vector = new GridPoint2(1, 0);
            this.rotation = 0f;
        }
    }
    public Direction(GridPoint2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }

    public GridPoint2 getVector() {
        return vector;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public void setVector(GridPoint2 vector) {
        this.vector = vector;
    }
}
