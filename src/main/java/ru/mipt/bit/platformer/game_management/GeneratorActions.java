package ru.mipt.bit.platformer.game_management;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_management.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.game_management.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.game_management.commands.Command;
import ru.mipt.bit.platformer.game_objects.movable.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.level.Level;

import java.util.Random;

@Component
public class GeneratorActions {
    public void getCommand(CommandQueueHandler receivedCommands, Level level) {
        Random random = new Random();
        int randomNumber = random.nextInt(50);
        int i = random.nextInt(level.moveNodesSize())%level.moveNodesSize();
        if (randomNumber%30 < 5) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.UP)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber%30 < 10) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.LEFT)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber%30 < 15) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.DOWN)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber%30 < 20) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.RIGHT)
            );
            receivedCommands.addAction(moveAction);
        } else if (randomNumber%30 > 22) {
            GridPoint2 coord = level.getMoveNodes().get(i).getMoveModel().getCoordinates().cpy();
            Direction direction = new Direction(
                    level.getMoveNodes().get(i).getMoveModel().getDirection().getVector().cpy(),
                    level.getMoveNodes().get(i).getMoveModel().getRotation());

            BulletMoveModel bulletMoveModel = new BulletMoveModel(coord, direction, direction.getRotation());
            level.createBullet(bulletMoveModel);

            ShootAction shootAction = new ShootAction(
                    (TankMoveModel) level.getMoveNodes().get(i).getMoveModel(), bulletMoveModel);
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
