package ru.mipt.bit.platformer.game_management.execution.graphics.renders;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level.Level;

@Component
public class MovementGraphicRender {
    public void render(Level tiles, TankMoveModel node, Graphics graphics) {
//        tiles.getTileMovement().moveRectangleBetweenTileCenters(
//                    graphics.getRectangle(),
//                    node.getCoordinates(),
//                    node.getDestination(),
//                    node.getProgress()
//            );
    }
}