package ru.mipt.bit.platformer.logic_objects.tree;

import com.badlogic.gdx.math.GridPoint2;

public class TreeMoveModel {
    private GridPoint2 coordinates;
    private float rotation;
    public TreeMoveModel(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public float getRotation() {
        return rotation;
    }

}
