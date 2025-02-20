package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.game_management.actions.Action;
import ru.mipt.bit.platformer.game_management.execution.MainGraphicRender;
import ru.mipt.bit.platformer.game_management.execution.MovementsExecutor;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.level.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class MainCommandExecutor {
    private MovementsExecutor movementCommandExecuter;
    private MainGraphicRender graphicRender;
    private HashMap<TankMoveModel, Action> executingActions;
    public MainCommandExecutor(GraphicProperties graphicProperties, Level level) {
        this.graphicRender = new MainGraphicRender(graphicProperties, level);
        this.movementCommandExecuter = new MovementsExecutor();
        this.executingActions = new HashMap<>();
    }

    private void tryToCatchNewCommand(CommandQueueHandler commandQueueHandler, Level level) {
        if (commandQueueHandler.isEmpty()) {
            return;
        }
        for (Action action : commandQueueHandler.getActions()) {
//            System.out.println("tank atatus is moving: " + action.getModel().isMoving());
            if (!executingActions.containsKey(action.getModel())) {
                action.getModel().setDirection(action.getDirection());
                action.getModel().setMovingStatus(true);
                executingActions.put(action.getModel(), action);
//                System.out.println("tryToCatchNewCommand, tank: ");
//                printQueue();
            }
        }
        commandQueueHandler.clear();
    }

    private void removeFinishedActions() {
        Collection<TankMoveModel> allActions = new ArrayList<>();
        allActions.addAll(executingActions.keySet());
        for(TankMoveModel action : allActions) {
            if (executingActions.get(action).isFinished()) {
                executingActions.remove(action);
            }
        }
    }

    public void executeAllCommands(
            float deltaTime,
            CommandQueueHandler commandQueueHandler,
            Level level
    ) {
        graphicRender.clear();
        tryToCatchNewCommand(commandQueueHandler, level);
        movementCommandExecuter.executeMoveActions(deltaTime, executingActions, level);
        graphicRender.render(deltaTime, level);
        level.removeKilledTanks();
        removeFinishedActions();
//        printQueue();
    }

    public void dispose(Level level) {
        graphicRender.dispose(level);
    }

    private void printQueue() {
        System.out.println("my queue:");
        for(Action action : executingActions.values()) {
            System.out.println("    " + action.getActionType() +
                    ": coord " + action.getModel().getCoordinates() +
                    "  direction" + action.getDirection().getVector());
        }
    }
}
