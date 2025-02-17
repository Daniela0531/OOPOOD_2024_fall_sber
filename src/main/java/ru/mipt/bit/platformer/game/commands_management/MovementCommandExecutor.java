package ru.mipt.bit.platformer.game.commands_management;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game.commands_management.objects.CommandQueueHandler;
import ru.mipt.bit.platformer.game.level.Level;
import ru.mipt.bit.platformer.game.level.Map;
import ru.mipt.bit.platformer.game.model.objects.Direction;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.isEqual;


@Component
public class MovementCommandExecutor {
    private CommandQueueHandler commandQueueHandler;
    private ArrayList<Direction> movementCommands;
    private Map map;
    private Level level;
//    private TankMoveModel playerTank;
//    private MovementLogic movementLogic;

    public MovementCommandExecutor(Map map) {
        this.movementCommands = new ArrayList<>();
        this.map = map;
    }


//    public void render() {
//        float deltaTime = Gdx.graphics.getDeltaTime();
//
////        float progress = continueProgress(movement.getProgress(), deltaTime, MOVEMENT_SPEED);
////        movement.setProgress(progress);
//
////        buttonHandler.readCommand(commandQueue);
//
//        while (!commandQueue.isEmpty()) {
//            movement.doStep(buttonHandler.action(commandQueue.get()));
//        }
//
////        graphicRender.render(movement);
//
//        if (isEqual(movement.getProgress(), 1f)) {
//            movement.getCoordinates().set(movement.getDestinationCoordinates());
//        }
//    }

//    public void getAllCommands() {
//        buttonHandler.readCommand(commandQueue);
//    }

//    private GridPoint2 getAction(Command command) {
//        if (command == Command.UP) {
//            return new GridPoint2(0, 1);
//        }
//        if (command == Command.LEFT) {
//            return new GridPoint2(-1, 0);
//        }
//        if (command == Command.DOWN) {
//            return new GridPoint2(0, -1);
//        }
//        if (command == Command.RIGHT) {
//            return new GridPoint2(1, 0);
//        }
//        return new GridPoint2(0, 0);
//    }
//
//    private Direction getAction(Command command) {
//        return new Direction(command);
//    }
//
//    public void executeAllCommands(CommandQueue commandQueue) {
////        float progress = continueProgress(movement.getProgress(), deltaTime, MOVEMENT_SPEED);
////        movement.setProgress(progress);
//        while (!commandQueue.isEmpty()) {
//            Direction direction = getAction(commandQueue.get());
//            movementCommands.add(direction);
//            Movement movement = new Movement(
//                    new GridPoint2(map.getPlayerCoordinates().x + direction.getVector().x, map.getPlayerCoordinates().y + direction.getVector().y),
//                    direction.getRotation(),
//                    map.getPlayerCoordinates(),
//                    map.getObstaclesCoordinates());
//            movement.doStep(direction.getVector());
//            if (isEqual(movement.getProgress(), 1f)) {
//                movement.getCoordinates().set(movement.getDestinationCoordinates());
//            }
//        }
//
//    }

//    public void render(float deltaTime) {
//        ArrayList<Node> gameNodes = level.getNodes();
//        for(Node node : gameNodes) {
//            if (node instanceof TankMoveModel) {
//                renderMovable((TankMoveModel) node, deltaTime);
//            }
////            if (node instanceof Obstacle) {
//////                renderRotatable((RotatableEntity) gameEntity);
////            }
//        }
//        level.removeKilledTanks();
//    }

//    private void renderMovable(TankMoveModel movableNode, float deltaTime) {
//        if (movableNode.isMoving()) {
//            movableNode.updateProgress(deltaTime);
//        }
////        movementLogic.move(movableNode);
//    }

//    public void doStep(Direction direction) {
//        if (isEqual(playerTank.getProgress(), 1f)) {
//            if (checkNoCollisionWithObstacles(step)) {
//                destinationCoordinates.y += step.y;
//                destinationCoordinates.x += step.x;
//                progress = 0f;
//            }
//            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
//            rotation = newPlayerRotation;
//        }
//    }

    public boolean movementIsPossible(Direction direction, TankMoveModel playerTank) {
        GridPoint2 newCoordinates = playerTank.getCoordinates();
        newCoordinates.x += direction.getVector().x;
        newCoordinates.y += direction.getVector().y;
        for(GridPoint2 obstacleCoordinates : map.getObstaclesCoordinates()) {
            if (obstacleCoordinates.equals(newCoordinates)) {
                return false;
            }
        }
        return true;
    }

    public void executeAllCommands(float deltaTime, CommandQueueHandler commandQueue, TankMoveModel playerTank) {
//        float deltaTime = Gdx.graphics.getDeltaTime();
//
//        float progress = continueProgress(playerTank.getProgress(), deltaTime, MOVEMENT_SPEED);
//        playerTank.setProgress(progress);
        playerTank.updateProgress(deltaTime);
//        MoveAction moveAction = commandQueue.getMoveAction();
        Direction direction = commandQueue.getMoveAction().getDirection();
        if (isEqual(playerTank.getProgress(), 1f)) {
            if (movementIsPossible(direction, playerTank)) {
//                destinationCoordinates.y += step.y;
//                destinationCoordinates.x += step.x;
//                progress = 0f;
                playerTank.move(direction);
                playerTank.setProgress(0f);
            }
            float newPlayerRotation = direction.getRotation();
            playerTank.setRotation(newPlayerRotation);
        }
    }
}
