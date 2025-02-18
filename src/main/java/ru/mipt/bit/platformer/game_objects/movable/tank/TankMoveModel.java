package ru.mipt.bit.platformer.game_objects.movable.tank;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import ru.mipt.bit.platformer.game_objects.Node;
import ru.mipt.bit.platformer.game_objects.NodeType;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;


// progress = 0f; - нет действий
// progress = 1f; - действие завершилось

//@Getter
//@Setter
//@Component
public class TankMoveModel implements Node {
    private static final float MOVEMENT_SPEED = 0.4f;
    @Getter
    private GridPoint2 coordinates;
    @Getter
    private float progress;
    private boolean isMoving = false;
    private Direction direction;
    private NodeType nodeType;
//    private GridPoint2 dest;
    public TankMoveModel(GridPoint2 coordinates, float progress) {
        this.coordinates = coordinates;
        this.progress = progress;
        this.nodeType = NodeType.TANK;
        this.direction = new Direction(new GridPoint2(0,0), 0f);
    }

    @Override
    public NodeType getType() {
        return nodeType;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public GridPoint2 getDestination() {
        return new GridPoint2(coordinates.x + direction.getVector().x, coordinates.y + direction.getVector().y);
    }

    public Direction getDirection() {
        return direction;
    }

    public void updateProgress(float deltaTime) {
        progress = continueProgress(progress, deltaTime, MOVEMENT_SPEED);
//        if (progress >= 1f) {
//            entity.setCoordinates(getDestination());
//            coordinates = getDestination();
//            progress -= 1f;
//            progress = 0f;
//            isMoving = false;
//        }
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void finishMovement() {
//        if (isEqual(getProgress(), 1f)) {
            coordinates.x += direction.getVector().x;
            coordinates.y += direction.getVector().y;
            direction.setVector(new GridPoint2(0, 0));
//        }
    }

    public void setRotation(float newPlayerRotation) {
        this.direction.setRotation(newPlayerRotation);
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public float getProgress() {
        return progress;
    }
    public float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
    public float getRotation() {
        return direction.getRotation();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
