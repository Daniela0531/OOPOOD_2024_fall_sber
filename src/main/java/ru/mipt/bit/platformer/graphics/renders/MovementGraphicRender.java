package ru.mipt.bit.platformer.graphics.renders;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.GraphicsInterface;
import ru.mipt.bit.platformer.level.Level;

@Component
public class MovementGraphicRender {
    public void render(Level tiles, TankMoveModel node, GraphicsInterface graphics) {
//        tiles.getTileMovement().moveRectangleBetweenTileCenters(
//                    graphics.getRectangle(),
//                    node.getCoordinates(),
//                    node.getDestination(),
//                    node.getProgress()
//            );
    }
}