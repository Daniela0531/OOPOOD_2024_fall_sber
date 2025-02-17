package ru.mipt.bit.platformer.game_management.execution.graphics;

import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level.Level;

//@Component
public class MovementGraphicRender {
    public void render(Level tiles, TankMoveModel playerTank, Graphics tankGraphics) {
        tiles.getTileMovement().moveRectangleBetweenTileCenters(
                    tankGraphics.getRectangle(),
                    playerTank.getCoordinates(),
                    playerTank.getDestination(),
                    playerTank.getProgress()
            );
    }
}