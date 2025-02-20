package ru.mipt.bit.platformer.game_objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;

public interface MoveModel {
    public NodeType getType();
    public GridPoint2 getCoordinates();

    public float getRotation();

    void setMovingStatus(boolean b);

    void finishMovement();

    boolean isMoving();

    void updateProgress(float deltaTime);

    GridPoint2 getDestination();

    void setProgress(float v);

    Direction getDirection();

    void setRotation(float rotation);

    float getProgress();

    void setDirection(Direction direction);

    float getHealth();
    void damage(float damage);
    float getDamage();

//    void finishMovement();
}
