package ru.mipt.bit.platformer.game_objects;

import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level_map.MapNode;

public class LevelNodeImpl {
    private Graphics graphics;
    private TankMoveModel tankMoveModel;
    private MapNode mapNode;

    public LevelNodeImpl(TankMoveModel tankMoveModel, Graphics graphics, MapNode mapNode) {
        this.tankMoveModel = tankMoveModel;
        this.graphics = graphics;
        this.mapNode = mapNode;
    }

    public TankMoveModel getMoveModel() {
        return tankMoveModel;
    }

    public Graphics getGraphics() {
        return graphics;
    }
    public MapNode getMapNode() {
        return mapNode;
    }
    public void setMapNode(MapNode mapNode) {
        this.mapNode = mapNode;
    }
}
