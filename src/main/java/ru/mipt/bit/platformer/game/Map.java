package ru.mipt.bit.platformer.game;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Map {
    private ArrayList<GridPoint2> coordinates;
    private ArrayList<Rectangle> rectangles;
    public Map(ArrayList<GridPoint2> coordinates) {
        this.coordinates = coordinates;
    }
    public ArrayList<GridPoint2> getMap() {
        return coordinates;
    }
}
