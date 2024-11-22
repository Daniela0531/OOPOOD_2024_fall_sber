package ru.mipt.bit.platformer.model;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;
import lombok.Setter;
//import lombok.Getter;
//import lombok.Setter;

@Getter
@Setter
public class Direction {
       public enum Trend {
          UP,
          DOWN,
          RIGHT,
          LEFT
       }
    GridPoint2 vector;
    private float rotation;
    private GridPoint2 destinationCoordinates;

    public Direction(float rotation, GridPoint2 destinationCoordinates) {
        this.rotation = rotation;
        this.destinationCoordinates = destinationCoordinates;
    }

//    public float getRotation() {
//        return rotation;
//    }

//    public GridPoint2 getVector() {
//        return vector;
//    }

//    public void setRotation(float rotation) {
//        this.rotation = rotation;
//    }
//    public void setDestinationCoordinates(GridPoint2 destinationCoordinates) {
//        this.destinationCoordinates = destinationCoordinates;
//    }
//    public GridPoint2 getDestinationCoordinates() {
//        return destinationCoordinates;
//    }

//    public void setVector(GridPoint2 vector) {
//        this.vector = vector;
//    }
}
