package ru.mipt.bit.platformer.game_objects;

import ru.mipt.bit.platformer.graphics_objects.Graphics;

public class LevelNodeImpl {
    private Graphics graphics;
    private MoveModel tankMoveModel;

    public LevelNodeImpl(MoveModel tankMoveModel, Graphics graphics) {
        this.tankMoveModel = tankMoveModel;
        this.graphics = graphics;
    }

    public MoveModel getMoveModel() {
        return tankMoveModel;
    }

    public Graphics getGraphics() {
        return graphics;
    }

}
