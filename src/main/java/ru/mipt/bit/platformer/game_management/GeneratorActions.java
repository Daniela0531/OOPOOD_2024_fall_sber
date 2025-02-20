package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_management.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.game_management.commands.Command;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;
import ru.mipt.bit.platformer.level.Level;

import java.util.Random;

@Component
public class GeneratorActions {
    public void getCommand(CommandQueueHandler receivedCommands, Level level) {
        Random random = new Random();
        int randomNumber = random.nextInt(50);
        int i = random.nextInt(level.moveNodesSize())%level.moveNodesSize();
        if (randomNumber%20 < 5) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.UP)
            );
            receivedCommands.addMoveAction(moveAction);
        } else if (randomNumber%20 < 10) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.LEFT)
            );
            receivedCommands.addMoveAction(moveAction);
        } else if (randomNumber%20 < 15) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.DOWN)
            );
            receivedCommands.addMoveAction(moveAction);
        } else if (randomNumber%20 >= 15) {
            MoveAction moveAction = new MoveAction(
                    level.getMoveNodes().get(i).getMoveModel(),
                    new Direction(Command.RIGHT)
            );
            receivedCommands.addMoveAction(moveAction);
        }
//        if (randomNumber%20 == 4) {
//            MoveAction moveAction = new MoveAction(
//                    level.getMoveNodes().get(i).getMoveModel(),
//                    new Direction(Command.SHOOT)
//            );
//            receivedCommands.addMoveAction(moveAction);
//        }
//        else {
//            MoveAction moveAction = new MoveAction(
//                    level.getMoveNodes().get(i).getMoveModel(),
//                    new Direction(Command.NONE)
//            );
//            receivedCommands.addMoveAction(moveAction);
//        }
    }
}
