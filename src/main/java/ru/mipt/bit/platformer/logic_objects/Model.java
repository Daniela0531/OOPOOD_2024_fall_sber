package ru.mipt.bit.platformer.logic_objects;

import com.badlogic.gdx.math.GridPoint2;

public interface Model {
    public NodeType getType();
    public GridPoint2 getCoordinates();

    boolean equalsTo(Model model);
}
