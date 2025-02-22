package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_input_management.CommandQueue;
import ru.mipt.bit.platformer.graphics.MainGraphicRender;
import ru.mipt.bit.platformer.graphics_properties.GraphicProperties;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_execution.LogicExecutor;

@Component
public class MainCommandExecutor {
    private LogicExecutor movementCommandExecuter;
    private MainGraphicRender graphicRender;
//    private HashMap<Model, Action> executingActions;
    private ExecutingActionsQueue executingActions;
    public MainCommandExecutor(GraphicProperties graphicProperties, Level level) {
        this.graphicRender = new MainGraphicRender(graphicProperties, level);
        this.movementCommandExecuter = new LogicExecutor();
//        this.executingActions = new HashMap<>();
        this.executingActions = new ExecutingActionsQueue();
    }

    public void executeAllCommands(
            float deltaTime,
            CommandQueue commandQueue,
            Level level
    ) {
//        graphicRender.clear();
//        tryToCatchNewCommand(commandQueueHandler, level);
//        movementCommandExecuter.executeActions(deltaTime, executingActions, level);
//        graphicRender.render(deltaTime, level);
//        level.removeInvalidEntities();
//        removeFinishedActions();
        graphicRender.clear();
        executingActions.catchingNewActions(commandQueue);
        level.update(deltaTime);
        movementCommandExecuter.executeActions(deltaTime, executingActions, level);
        graphicRender.render(deltaTime, level);
        level.removeInvalidEntities();
        executingActions.removeFinishedActions();
//        removeFinishedActions();
    }

    public void dispose(Level level) {
        graphicRender.dispose(level);
    }

//    private void tryToCatchNewCommand(CommandQueue commandQueueHandler, Level level) {
//        if (commandQueueHandler.isEmpty()) {
//            return;
//        }
//        for (Action action : commandQueueHandler.getActions()) {
//            if (!executingActions.containsKey(action.getModel())) {
//                if (action instanceof MoveAction) {
//                    ((MoveModel)action.getModel()).setDirection((((MoveAction) action).getDirection()));
//                    ((MoveModel)action.getModel()).setMovingStatus(true);
//                    executingActions.put(action.getModel(), action);
//                }
//                if (action instanceof ShootAction) {
//                    ((ShootAction) action).getBullet().setMovingStatus(true);
//                    executingActions.put(action.getModel(), action);
//                }
//                if (action instanceof SwitchHealthBar) {
//                    System.out.println("CATCH SWITCHING BAR");
//                    executingActions.put(action.getModel(), action);
//                }
//            }
//        }
//        commandQueueHandler.clear();
//    }

//    private void removeFinishedActions() {
//        Collection<Model> allActions = new ArrayList<>();
//        allActions.addAll(executingActions.keySet());
//        for(Model action : allActions) {
//            if (executingActions.get(action).getActionType() == ActionType.SWITCHING_HEALTH_BAR) {
//                System.out.println(
//                        "ActionType.SWITCHING_HEALTH_BAR exist for: " + action.getCoordinates() + "\n" +
//                        "    status is finished: " + executingActions.get(action).isFinished());
//            }
//            if (executingActions.get(action).isFinished()) {
//                executingActions.remove(action);
//            }
//        }
//    }

//    private void printQueue() {
//        System.out.println("my queue:");
//        for(Action action : executingActions.values()) {
//            System.out.println("    " + action.getActionType() +
//                    ": coord " + action.getModel().getCoordinates() +
//                    "  direction" + ((MoveModel)action.getModel()).getDirection().getVector() +
//                    "  progres: " + ((MoveModel)action.getModel()).getProgress());
//        }
//    }
}
