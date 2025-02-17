package ru.mipt.bit.platformer.game_management.execution;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_management.CommandQueueHandler;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.Map;
import ru.mipt.bit.platformer.game_objects.movable.properties.Direction;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.isEqual;


@Component
public class MovementsExecutor {
    private CommandQueueHandler commandQueueHandler;
    private ArrayList<Direction> movementCommands;
    private Map map;
    private Level level;

    private int upBound = 5;
    private int leftBound = 0;
    private int lowBound = 0;
    private int rightBound = 9;

    public MovementsExecutor(Map map) {
        this.movementCommands = new ArrayList<>();
        this.map = map;
    }

    public boolean movementIsPossible(GridPoint2 coordinates) {
        GridPoint2 newCoordinates = coordinates.cpy();
//        newCoordinates.x += direction.getVector().x;
//        newCoordinates.y += direction.getVector().y;
        if (newCoordinates.x < leftBound || newCoordinates.x > rightBound || newCoordinates.y < lowBound || newCoordinates.y > upBound) {
            return false;
        }
        for(GridPoint2 obstacleCoordinates : map.getObstaclesCoordinates()) {
            if (obstacleCoordinates.equals(newCoordinates)) {
//                System.out.println(coordinates);
                return false;
            }
        }
        return true;
    }

    public void tryToCatchNewCommand(CommandQueueHandler commandQueueHandler, TankMoveModel playerTank) {
        if (!commandQueueHandler.isEmpty()) {
            // System.out.println("            getMoveAction: " + commandQueueHandler.getMoveAction().getDestinationCoordinates());
            Direction direction = commandQueueHandler.getMoveAction().getDirection();
            if (!commandQueueHandler.getMoveAction().getDestinationCoordinates().equals(playerTank.getDestination())) {
//                if (isEqual(playerTank.getProgress(), 0f)) {
                    playerTank.setDirection(direction);
//                } else {
//                    commandQueueHandler.pop();
//                }
                // System.out.println("            catch");
            }
        }
    }

    public void tryToFinishMovement(TankMoveModel playerTank) {
        if (isEqual(playerTank.getProgress(), 1f)) {
            playerTank.finishMovement();
            playerTank.setProgress(0f);
            // System.out.println("                finish:" + playerTank.getDestination());
        }
    }

    public void executeAllMoveCommands(float deltaTime, CommandQueueHandler commandQueue, TankMoveModel playerTank) {
//        // System.out.println("        tryToCatchNewCommand");
        tryToCatchNewCommand(commandQueue, playerTank);
//        // System.out.println("        tryToMove");
        if (movementIsPossible(playerTank.getDestination())) {
            playerTank.updateProgress(deltaTime);
        } else {
            playerTank.setProgress(0f);
        }
        playerTank.setRotation(playerTank.getDirection().getRotation());
//        // System.out.println("            tryToFinishMovement:");
        tryToFinishMovement(playerTank);
    }
}
