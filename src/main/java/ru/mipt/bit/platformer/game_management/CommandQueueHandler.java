package ru.mipt.bit.platformer.game_management;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_management.commands.Command;
import ru.mipt.bit.platformer.game_management.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

import java.util.ArrayList;

public class CommandQueueHandler {
    private ArrayList<Command> receivedCommands;
    private ArrayList<MoveAction> actions;
//    private TankMoveModel playerTank;

    public CommandQueueHandler() {
//        // System.out.println("CommandQueueHandler CommandQueueHandler");
        this.receivedCommands = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public int moveActionAmount() {
        return actions.size();
    }

    public void add(Command command, TankMoveModel playerTank) {
//        // System.out.println("CommandQueueHandler add");
        receivedCommands.add(command);
        if (command == Command.UP) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
//            moveAction.setI(receivedCommands.size());
            // System.out.println("    add: Command.UP");
//            // System.out.println("    size: " + moveActionAmount());
            actions.add(moveAction);
        }
        if (command == Command.LEFT) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
//            moveAction.setI(receivedCommands.size());
            // System.out.println("    add: Command.LEFT");
            actions.add(moveAction);
        }
        if (command == Command.DOWN) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
//            moveAction.setI(receivedCommands.size());
            // System.out.println("    add: Command.DOWN");
            actions.add(moveAction);
        }
        if (command == Command.RIGHT) {
            Direction direction = new Direction(command);
            MoveAction moveAction = new MoveAction(playerTank, direction);
//            moveAction.setI(receivedCommands.size());
            // System.out.println("    add: Command.RIGHT");
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

    public void pop() {
        if (receivedCommands.isEmpty()) {
            return;
        }
        receivedCommands.remove(0);
        actions.remove(0);
    }

    public boolean isEmpty() {
        return receivedCommands.isEmpty();
    }
}
