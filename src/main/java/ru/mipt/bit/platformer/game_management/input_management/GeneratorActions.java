package ru.mipt.bit.platformer.game_management.input_management;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;

import java.util.Random;

@Component
public class GeneratorActions {
    public void getCommand(CommandQueue receivedCommands, Level level) {
        if (level.getTanks().isEmpty()) {
            return;
        }
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        int i = random.nextInt(level.moveNodesSize())%level.moveNodesSize();
        int k = 0;
        TankMoveModel tank = null;
        for (TankMoveModel tankMoveModel : level.getTanks().keySet()) {
            if (k == i) {
                if (!level.isPlayerKilled() && tankMoveModel.getCoordinates() == level.getPlayerTank().getCoordinates()) {
                    return;
                }
                tank = tankMoveModel;
                break;
            }
            ++k;
        }
        if (randomNumber == 0) {
            MoveAction moveAction = new MoveAction(
                    tank,
                    new Direction(Command.UP)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber == 1) {
            MoveAction moveAction = new MoveAction(
                    tank,
                    new Direction(Command.LEFT)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber == 2) {
            MoveAction moveAction = new MoveAction(
                    tank,
                    new Direction(Command.DOWN)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber == 3) {
            MoveAction moveAction = new MoveAction(
                    tank,
                    new Direction(Command.RIGHT)
            );
            receivedCommands.addAction(moveAction);
        } else
            if (randomNumber == 4) {
            Direction direction = new Direction(
                    tank.getRotation());
            GridPoint2 coord = new GridPoint2(
                    tank.getCoordinates().cpy().x + direction.getVector().x,
                    tank.getCoordinates().cpy().y + direction.getVector().y);
            BulletMoveModel bulletMoveModel = new BulletMoveModel(coord, direction, direction.getRotation());
            level.putBulletInLevel(bulletMoveModel);

            ShootAction shootAction = new ShootAction(
                    tank, bulletMoveModel);
            receivedCommands.addAction(shootAction);
        }
//        else {
//            MoveAction moveAction = new MoveAction(
//                    level.getMoveNodes().get(i).getMoveModel(),
//                    new Direction(Command.NONE)
//            );
//            receivedCommands.addMoveAction(moveAction);
//        }
    }
}
