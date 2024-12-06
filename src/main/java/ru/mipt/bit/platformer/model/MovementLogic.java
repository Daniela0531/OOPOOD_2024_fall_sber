package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.bit.platformer.graphics.GraphicModel;

import static com.badlogic.gdx.math.MathUtils.isEqual;

@Getter
@Setter
public class MovementLogic {
//    private GridPoint2 coordinates;
    private float moveProgress;
//    private float rotation;


    public MovementLogic(float moveProgress) {
        this.moveProgress = moveProgress;
    }
    private boolean checkNoCollisionWithObstacles(GridPoint2 step, MovementModel tank, GraphicModel obstacle) {
        GridPoint2 newCoordinates = tank.getCoordinates();
        newCoordinates.x += step.x;
        newCoordinates.y += step.y;
        return !obstacle.getCoordinates().equals(newCoordinates);
    }
    public void doStep(GridPoint2 step, MovementModel tank, GraphicModel obstacle) {
        if (isEqual(moveProgress, 1f)) {
            // check potential player destination for collision with obstacles
            if (checkNoCollisionWithObstacles(step, tank, obstacle)) {
                tank.getDestinationCoordinates().y += step.y;
                tank.getDestinationCoordinates().x += step.x;
                moveProgress = 0f;
            }
            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
            tank.setRotation(newPlayerRotation);
        }
    }

//    public float getMoveProgress() {
//        return moveProgress;
//    }

//    public float getRotation() {
//        return rotation;
//    }

//    public GridPoint2 getDestinationCoordinates() {
//        return destinationCoordinates;
//    }


//    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
//        this.destinationCoordinates = destinationCoordinates;
//    }

//    public void setMoveProgress(float moveProgress) {
//        this.moveProgress = moveProgress;
//    }

//    public void setRotation(float rotation) {
//        this.rotation = rotation;
//    }
}
