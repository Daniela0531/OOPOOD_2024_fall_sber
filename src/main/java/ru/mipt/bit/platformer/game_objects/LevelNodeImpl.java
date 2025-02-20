package ru.mipt.bit.platformer.game_objects;

import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;

public class LevelNodeImpl {
    private Graphics graphics;
    private TankMoveModel tankMoveModel;

    public LevelNodeImpl(TankMoveModel tankMoveModel, Graphics graphics) {
        this.tankMoveModel = tankMoveModel;
        this.graphics = graphics;
    }

    public TankMoveModel getMoveModel() {
        return tankMoveModel;
    }

    public Graphics getGraphics() {
        return graphics;
    }

}
