package ru.mipt.bit.platformer.logic_objects.bullet;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic_objects.DamageDealerModel;
import ru.mipt.bit.platformer.logic_objects.Model;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.level_map.NodeType;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;

import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class BulletMoveModel implements MoveModel, DamageDealerModel {
    private static final float MOVEMENT_SPEED = 0.1f;
    private GridPoint2 coordinates;
    private float progress;
    private boolean isMoving = false;
    private Direction direction;
    private NodeType nodeType;
    private int damage;
    private float rotation;
    public BulletMoveModel(GridPoint2 coordinates, Direction direction, float rotation) {
        this.coordinates = coordinates;
        this.progress = 0f;
        this.nodeType = NodeType.BULLET;
        this.direction = direction;
        this.damage = 1;
        this.rotation = rotation;
    }

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
    public int getDamage() {
        return damage;
    }
    @Override
    public boolean equalsTo(Model model) {
        if (model instanceof BulletMoveModel) {
            return coordinates == ((BulletMoveModel) model).getCoordinates();
        }
        return false;
    }

    @Override
    public void mainUpdateProgress(float deltaTime) {

    }
}

