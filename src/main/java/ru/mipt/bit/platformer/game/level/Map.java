package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.math.GridPoint2;
import lombok.Getter;

import java.util.ArrayList;

public class Map {
    @Getter
    private ArrayList<GridPoint2> obstaclesCoordinates;
    @Getter
    private ArrayList<GridPoint2> tanksCoordinates;
    @Getter
    private GridPoint2 playerCoordinates;

//    private ArrayList<Rectangle> rectangles;
    public Map(ArrayList<GridPoint2> obstaclesCoordinates, ArrayList<GridPoint2> tanksCoordinates, GridPoint2 playerCoordinates) {
        this.obstaclesCoordinates = obstaclesCoordinates;
        this.tanksCoordinates = tanksCoordinates;
        this.playerCoordinates = playerCoordinates;
    }
//    public ArrayList<GridPoint2> getObstacles() {
//        return obstaclesCoordinates;
//    }

    public ArrayList<GridPoint2> getObstaclesCoordinates() {
        return obstaclesCoordinates;
    }

    public ArrayList<GridPoint2> getTanksCoordinates() {
        return tanksCoordinates;
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
    }
}
