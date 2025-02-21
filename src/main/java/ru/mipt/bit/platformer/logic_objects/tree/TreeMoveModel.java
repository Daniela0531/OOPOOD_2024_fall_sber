package ru.mipt.bit.platformer.logic_objects.tree;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.logic_objects.NodeType;

public class TreeMoveModel {
    private GridPoint2 coordinates;
    private NodeType nodeType;
    private float rotation;
    //    private boolean justFired = false;
    public TreeMoveModel(GridPoint2 coordinates, float rotation) {
        this.coordinates = coordinates;
        this.nodeType = NodeType.TREE;
        this.rotation = rotation;
    }

//    @Override
    public NodeType getType() {
        return nodeType;
    }

//    @Override
    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    public float getRotation() {
        return rotation;
    }

}
