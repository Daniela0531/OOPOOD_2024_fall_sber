package ru.mipt.bit.platformer.game.model.tank;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import ru.mipt.bit.platformer.game.model.Node;
import ru.mipt.bit.platformer.game.model.NodeType;
import ru.mipt.bit.platformer.game.model.objects.Direction;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.game.util.GdxGameUtils.continueProgress;


// progress = 0f; - нет действий
// progress = 1f; - действие завершилось

//@Getter
//@Setter
public class TankMoveModel implements Node {
    private static final float MOVEMENT_SPEED = 0.4f;
    @Getter
    private GridPoint2 coordinates;
    @Getter
    private float progress;
    private boolean isMoving = false;
    private Direction direction;
    private NodeType nodeType;
    public TankMoveModel(GridPoint2 coordinates, float progress) {
        this.coordinates = coordinates;
        this.progress = progress;
        this.nodeType = NodeType.TANK;
        this.direction = new Direction(new GridPoint2(0,0), 0f);
    }

    @Override
    public NodeType getType() {
        return nodeType;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public GridPoint2 getDestination() {
        return new GridPoint2(coordinates.x + direction.getVector().x, coordinates.y + direction.getVector().y);
    }

    public Direction getDirection() {
        return direction;
    }

    public void updateProgress(float deltaTime) {
        progress = continueProgress(progress, deltaTime, MOVEMENT_SPEED);
        if (progress >= 1f) {
//            entity.setCoordinates(getDestination());
//            coordinates = getDestination();
//            progress -= 1f;
            progress = 0f;
            isMoving = false;
        }
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void move(Direction direction) {
        if (isEqual(getProgress(), 1f)) {
            coordinates.x += direction.getVector().x;
            coordinates.y += direction.getVector().y;
        }
//        destinationCoordinates.y += step.y;
//        destinationCoordinates.x += step.x;
    }

    public void setRotation(float newPlayerRotation) {
        this.direction.setRotation(newPlayerRotation);
    }

//    public void doStepGlobal(GridPoint2 step) {
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

//    public boolean movementIsPossible(GridPoint2 step, ) {
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

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public float getProgress() {
        return progress;
    }
    public float getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
    public float getRotation() {
        return direction.getRotation();
    }
}
