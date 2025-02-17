package ru.mipt.bit.platformer.game.model.tank;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MovementLogic {
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    private float rotation;

    //    private Map map;
    private ArrayList<GridPoint2> obstacleCoordinates;

//    public Movement(GridPoint2 playerDestinationCoordinates, float playerRotation, GridPoint2 tankCoordinates, ArrayList<GridPoint2> obstacleCoordinates) {
//        this.coordinates = tankCoordinates;
//        this.rotation = playerRotation;
//        this.destinationCoordinates = playerDestinationCoordinates;
//        this.obstacleCoordinates = obstacleCoordinates;
//    }
//
//    public float getProgress() {
//        return progress;
//    }
//
//    public float getRotation() {
//        return rotation;
//    }
//
//    public GridPoint2 getCoordinates() {
//        return coordinates;
//    }
//
//    public GridPoint2 getDestinationCoordinates() {
//        return destinationCoordinates;
//    }
//
//    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
//        this.destinationCoordinates = destinationCoordinates;
//    }
//
//    public void setProgress(float progress) {
//        this.progress = progress;
//    }
//
//    public void setRotation(float rotation) {
//        this.rotation = rotation;
//    }
//
//    public void doStep(GridPoint2 step) {
//        if (isEqual(progress, 1f)) {
//            if (checkNoCollisionWithObstacles(step)) {
//                destinationCoordinates.y += step.y;
//                destinationCoordinates.x += step.x;
//                progress = 0f;
//            }
//            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
//            rotation = newPlayerRotation;
//        }
//    }
//
//    public boolean checkNoCollisionWithObstacles(GridPoint2 step) {
//        GridPoint2 newCoordinates = coordinates;
//        newCoordinates.x += step.x;
//        newCoordinates.y += step.y;
//        for(GridPoint2 obstacleCoordinates : obstacleCoordinates) {
//            if (obstacleCoordinates.equals(newCoordinates)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public void move(TankMoveModel movableNode) {
////        GridPoint2 dest = movableNode.getDestination();
////        var gdxMoveEntity = (TanksGraphicRender) level.getGraphicFromGame(movableNode);
////        if (gdxMoveEntity == null) return;
////
////        var coords = gdxMoveEntity.getGameEntity().getCoordinates();
////        tileMovement.moveRectangleBetweenTileCenters(
////                gdxMoveEntity.getTexture().getRectangle(), new GridPoint2(coords.x, coords.y), new GridPoint2(dest.x, dest.y), gdxMoveEntity.getInterpolationMethod(), entity.getProgress()
////        );
//        GridPoint2 dest = movableNode.getDestination();
//        var gdxMoveEntity = (TanksGraphicRender) level.getGraphicFromGame(movableNode);
//        if (gdxMoveEntity == null) return;
//
//        var coords = gdxMoveEntity.getGameEntity().getCoordinates();
//
//        // сейчас это в TanksGraphicRender
//        tileMovement.moveRectangleBetweenTileCenters(
//                gdxMoveEntity.getTexture().getRectangle(), new GridPoint2(coords.x, coords.y), new GridPoint2(dest.x, dest.y), gdxMoveEntity.getInterpolationMethod(), entity.getProgress()
//        );
//    }
//
//
//    private Direction getAction(Command command) {
//        return new Direction(command);
//    }
//
//    public void executeAllCommands(CommandQueueHandler commandQueue) {
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
//
////    public void render() {
////        float deltaTime = Gdx.graphics.getDeltaTime();
////
////        float progress = continueProgress(movement.getProgress(), deltaTime, MOVEMENT_SPEED);
////        movement.setProgress(progress);
////
////        buttonHandler.readCommand(commandQueue);
////
////        while (!commandQueue.isEmpty()) {
////            movement.doStep(buttonHandler.action(commandQueue.get()));
////        }
////
////        graphicRender.render(movement);
////
////        if (isEqual(movement.getProgress(), 1f)) {
////            movement.getCoordinates().set(movement.getDestinationCoordinates());
////        }
////    }
}
