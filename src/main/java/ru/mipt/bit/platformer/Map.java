package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.level_map.MapNode;

import java.util.ArrayList;

public class Map {
//    @Getter
//    private ArrayList<GridPoint2> obstaclesCoordinatesTrees;
//    @Getter
//    private ArrayList<GridPoint2> tanksCoordinates;
//    @Getter
    private MapNode player;
    private ArrayList<MapNode> nodesCoordinates;

//    private ArrayList<Rectangle> rectangles;
    public Map(ArrayList<MapNode> nodesCoordinates, MapNode player) {
//        this.obstaclesCoordinatesTrees = obstaclesCoordinates;
//        this.tanksCoordinates = tanksCoordinates;
//        this.playerCoordinates = playerCoordinates;
        this.nodesCoordinates = nodesCoordinates;
        this.player = player;
    }
//    public ArrayList<GridPoint2> getObstacles() {
//        return obstaclesCoordinates;
//    }

//    public ArrayList<GridPoint2> getObstaclesCoordinatesTrees() {
//        return obstaclesCoordinatesTrees;
//    }

//    public ArrayList<GridPoint2> getTanksCoordinates() {
//        return tanksCoordinates;
//    }

//    public GridPoint2 getPlayerCoordinates() {
//        return playerCoordinates;
//    }

    public ArrayList<MapNode> getNodes() {
        return nodesCoordinates;
    }
    public void print() {
        for (MapNode mapNode : nodesCoordinates) {
            System.out.println(mapNode.getNodeType() + ": " + mapNode.getCoordinates());
        }
    }

    public MapNode getPlayer() {
        return player;
    }
}
