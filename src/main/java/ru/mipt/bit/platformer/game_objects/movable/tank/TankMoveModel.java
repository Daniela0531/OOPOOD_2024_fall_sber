package ru.mipt.bit.platformer.game_objects.movable.tank;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_management.commands.Command;
import ru.mipt.bit.platformer.game_objects.LevelNode;
import ru.mipt.bit.platformer.game_objects.NodeType;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;


// progress = 0f; - нет действий
// progress = 1f; - действие завершилось


public class TankMoveModel implements LevelNode {
    private static final float MOVEMENT_SPEED = 0.4f;
    private GridPoint2 coordinates;
    private float progress;
    private boolean isMoving = false;
    private Direction direction;
    private NodeType nodeType;
    private Command executableCommand;
    public TankMoveModel(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.progress = 0f;
        this.nodeType = NodeType.TANK;
        this.direction = new Direction(new GridPoint2(0,0), rotation);
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
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void finishMovement() {
        coordinates.x += direction.getVector().x;
        coordinates.y += direction.getVector().y;
        direction.setVector(new GridPoint2(0, 0));
    }

    public void setRotation(float newPlayerRotation) {
        this.direction.setRotation(newPlayerRotation);
    }
    @Override
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

    public void setMovingStatus(boolean status) {
        this.isMoving = status;
    }
}
