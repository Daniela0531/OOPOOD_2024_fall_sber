package ru.mipt.bit.platformer.game_objects;

import com.badlogic.gdx.math.GridPoint2;

public interface LevelNode {
    public NodeType getType();
    public GridPoint2 getCoordinates();

    public float getRotation();
}
