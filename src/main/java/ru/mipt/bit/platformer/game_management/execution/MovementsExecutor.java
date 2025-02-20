package ru.mipt.bit.platformer.game_management.execution;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.game_management.CommandQueueHandler;
import ru.mipt.bit.platformer.game_management.actions.impl_action.MoveAction;
import ru.mipt.bit.platformer.game_objects.LevelNodeImpl;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level_map.MapNode;

import static com.badlogic.gdx.math.MathUtils.isEqual;


@Component
public class MovementsExecutor {
    private int upBound = 5;
    private int leftBound = 0;
    private int lowBound = 0;
    private int rightBound = 9;

    public MovementsExecutor() {
//        this.movementCommands = new ArrayList<>();
//        this.map = map;
    }

    public boolean movementIsPossible(GridPoint2 destCoordinates, Map map) {
        GridPoint2 newCoordinates = destCoordinates.cpy();
        if (newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound) {
            return false;
        }
        for(MapNode obstacleCoordinates : map.getNodes()) {
            if (obstacleCoordinates.getCoordinates().equals(newCoordinates)) {
                return false;
            }
        }
        return true;
    }

    public void tryToCatchNewCommand(CommandQueueHandler commandQueueHandler, Level level) {
//        commandQueueHandler.printQueue();
        if (commandQueueHandler.isEmpty()) {
            return;
        }
//        System.out.println("new commands:");
        for (MoveAction moveAction : commandQueueHandler.getActions()) {
//            System.out.println("is move:" + moveAction.getModel().isMoving());
            if (!moveAction.getModel().isMoving()) {
                moveAction.getModel().setDirection(moveAction.getDirection());
                moveAction.getModel().setMovingStatus(true);
//                System.out.println("    " + moveAction.getActionType() +
//                        ": " + moveAction.getModel().getCoordinates() +
//                        "  " + moveAction.getDirection().getVector());
            }
        }
        commandQueueHandler.clear();
//        System.out.println("clear, size: " + commandQueueHandler.size());
    }

    public void tryToFinishMovement(TankMoveModel tankMoveModel) {
        if (isEqual(tankMoveModel.getProgress(), 1f)) {
            tankMoveModel.finishMovement();
            tankMoveModel.setProgress(0f);
            tankMoveModel.setMovingStatus(false);
        }
    }

    public void executeMovement(float deltaTime, TankMoveModel tankMoveModel, Map map) {
        if (tankMoveModel.isMoving()) {
            if (movementIsPossible(tankMoveModel.getDestination(), map)) {
                //            level.getPlayerTank().updateProgress(deltaTime);
                tankMoveModel.updateProgress(deltaTime);
                //            System.out.println("tank: " + tankMoveModel.getCoordinates() + " progres: " + tankMoveModel.getProgress());
            } else {
                tankMoveModel.setProgress(0f);
                tankMoveModel.setMovingStatus(false);
                //            level.getPlayerTank().setProgress(0f);
            }
        }
        tankMoveModel.setRotation(tankMoveModel.getDirection().getRotation());
        tryToFinishMovement(tankMoveModel);
    }

    public void executeAllMoveCommands(float deltaTime, CommandQueueHandler commandQueue, Level level) {
        tryToCatchNewCommand(commandQueue, level);
//        for (LevelNodeImpl levelNode : level.getMoveNodes()) {
        executeMovement(deltaTime, level.getPlayerTank().getMoveModel(), level.getMap());

        for (LevelNodeImpl levelNode : level.getMoveNodes()) {
//            System.out.println("assert: is move " + levelNode.getMoveModel().isMoving() + " progress: " + levelNode.getMoveModel().getProgress());
//            assert(!levelNode.getMoveModel().isMoving() || levelNode.getMoveModel().getProgress() > 0);
            executeMovement(deltaTime, levelNode.getMoveModel(), level.getMap());
//            System.out.println("assert: is move " + levelNode.getMoveModel().isMoving() + " progress: " + levelNode.getMoveModel().getProgress());
        }
//        if (movementIsPossible(commandQueue.getMoveAction().getModel().getDestination(), level.getMap())) {
////            level.getPlayerTank().updateProgress(deltaTime);
//            commandQueue.getMoveAction().getModel().updateProgress(deltaTime);
//        } else {
//            commandQueue.getMoveAction().getModel().setProgress(0f);
////            level.getPlayerTank().setProgress(0f);
//        }
//        commandQueue.getMoveAction().getModel().setRotation(level.getPlayerTank().getDirection().getRotation());
//        tryToFinishMovement(commandQueue.getMoveAction().getModel());
    }
}
