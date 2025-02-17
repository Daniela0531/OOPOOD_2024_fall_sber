package ru.mipt.bit.platformer.game.commands_management.objects;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.model.MoveAction;
import ru.mipt.bit.platformer.game.model.objects.Direction;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

import java.util.ArrayList;

public class CommandQueueHandler {
    private ArrayList<Command> receivedCommands;
    private ArrayList<MoveAction> actions;
    private TankMoveModel playerTank;

    public CommandQueueHandler() {
        this.receivedCommands = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public void add(Command command) {
        receivedCommands.add(command);
        if (command == Command.UP) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
            actions.add(moveAction);
        }
        if (command == Command.LEFT) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
            actions.add(moveAction);
        }
        if (command == Command.DOWN) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
            actions.add(moveAction);
        }
        if (command == Command.RIGHT) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
            actions.add(moveAction);
        }
    }

    public GridPoint2 process(Command command) {
        if (command == Command.UP) {
            return new GridPoint2(0, 1);
        }
        if (command == Command.LEFT) {
            return new GridPoint2(-1, 0);
        }
        if (command == Command.DOWN) {
            return new GridPoint2(0, -1);
        }
        if (command == Command.RIGHT) {
            return new GridPoint2(1, 0);
        }
        return new GridPoint2(0, 0);
    }

    public Command get() {
        Command command = receivedCommands.get(0);
//        receivedCommands.remove(0);
        return command;
    }

    public MoveAction getMoveAction() {
        return actions.get(0);
    }

    public void popMoveAction() {
        actions.remove(0);
    }

    public Command pop() {
        return receivedCommands.remove(0);
    }

    public boolean isEmpty() {
        return receivedCommands.isEmpty();
    }
}
