package ru.mipt.bit.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game.Map;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.isEqual;

//@Getter
//@Setter
@Component
public class Movement {
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private float progress = 1f;
    private float rotation;

    private Map map;

    public Movement(GridPoint2 playerDestinationCoordinates, float playerRotation, GridPoint2 tankCoordinates, ArrayList<GridPoint2> obstacleCoordinates) {
        this.coordinates = tankCoordinates;
        this.rotation = playerRotation;
        this.destinationCoordinates = playerDestinationCoordinates;
        this.map = new Map(obstacleCoordinates);
    }

    public float getProgress() {
        return progress;
    }

    public float getRotation() {
        return rotation;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void doStep(GridPoint2 step) {
        if (isEqual(progress, 1f)) {
            if (checkNoCollisionWithObstacles(step)) {
                destinationCoordinates.y += step.y;
                destinationCoordinates.x += step.x;
                progress = 0f;
            }
            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
            rotation = newPlayerRotation;
        }
    }

    public boolean checkNoCollisionWithObstacles(GridPoint2 step) {
        GridPoint2 newCoordinates = coordinates;
        newCoordinates.x += step.x;
        newCoordinates.y += step.y;
        for(GridPoint2 obstacleCoordinates : map.getMap()) {
            if (obstacleCoordinates.equals(newCoordinates)) {
                return false;
            }
        }
        return true;
    }
}
