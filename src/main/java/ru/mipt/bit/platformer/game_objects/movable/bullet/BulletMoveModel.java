package ru.mipt.bit.platformer.game_objects.movable.bullet;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_objects.MoveModel;
import ru.mipt.bit.platformer.game_objects.NodeType;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class BulletMoveModel implements MoveModel {

    private static final float MOVEMENT_SPEED = 0.2f;
    private GridPoint2 coordinates;
    private float progress;
    private boolean isMoving = false;
    private Direction direction;
    private NodeType nodeType;
    private float damage;
    private float rotation;
    public BulletMoveModel(GridPoint2 coordinates, Direction direction, float rotation) {
        this.coordinates = coordinates;
        this.progress = 0f;
        this.nodeType = NodeType.BULLET;
        this.direction = direction;
        this.damage = 0.5f;
        this.rotation = rotation;
    }

    @Override
    public NodeType getType() {
        return nodeType;
    }

//    public boolean isMoving() {
//        return isMoving;
//    }

    public GridPoint2 getDestination() {
        return new GridPoint2(coordinates.x + direction.getVector().x, coordinates.y + direction.getVector().y);
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void updateProgress(float deltaTime) {
        progress = continueProgress(progress, deltaTime, MOVEMENT_SPEED);
    }

    @Override
    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public void finishMovement() {
        if (progress >= 1) {
            coordinates.x += direction.getVector().x;
            coordinates.y += direction.getVector().y;
            progress = 0f;
        }
//        direction.setVector(new GridPoint2(0, 0));
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }

    @Override
    public void setRotation(float newRotation) {
        this.direction.setRotation(newRotation);
        this.rotation = newRotation;
    }
    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    @Override
    public float getProgress() {
        return progress;
    }
//    @Override
    public float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
    public float getRotation() {
        return direction.getRotation();
    }

    @Override
    public void setMovingStatus(boolean b) {
        this.isMoving = b;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public float getHealth() {
        return 0;
    }

    @Override
    public void damage(float damage) {

    }

    @Override
    public float getDamage() {
        return damage;
    }

}

