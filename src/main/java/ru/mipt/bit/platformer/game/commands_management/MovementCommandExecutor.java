package ru.mipt.bit.platformer.game.commands_management;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game.commands_management.objects.CommandQueueHandler;
import ru.mipt.bit.platformer.game.level.Level;
import ru.mipt.bit.platformer.game.level.Map;
import ru.mipt.bit.platformer.game.model.objects.Direction;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

import java.util.ArrayList;


@Component
public class MovementCommandExecutor {
    private CommandQueueHandler commandQueueHandler;
    private ArrayList<Direction> movementCommands;
    private Map map;
    private Level level;

    public MovementCommandExecutor(Map map) {
        this.movementCommands = new ArrayList<>();
        this.map = map;
    }

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
        System.out.println("playerTank:");
        playerTank.updateProgress(deltaTime);
        System.out.println("    progress: " + playerTank.getProgress());

        if (commandQueue.isEmpty()) {
            return;
        }
        Direction direction = commandQueue.getMoveAction().getDirection();
        System.out.println("    direction: " + direction.getVector());
//        if (isEqual(playerTank.getProgress(), 1f)) {
        if (playerTank.getProgress() < 1f) {
            if (movementIsPossible(direction, playerTank)) {
                playerTank.move(direction);
                playerTank.setProgress(1f);
            }
            float newPlayerRotation = direction.getRotation();
            playerTank.setRotation(newPlayerRotation);
        }
    }
}
