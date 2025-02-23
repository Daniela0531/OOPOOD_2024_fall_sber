package ru.mipt.bit.platformer;

import ru.mipt.bit.platformer.level_map.MapNode;

import java.util.ArrayList;

public class LevelMap {
    private MapNode player;
    private ArrayList<MapNode> nodesCoordinates;

    public LevelMap(ArrayList<MapNode> nodesCoordinates, MapNode player) {
        this.nodesCoordinates = nodesCoordinates;
        this.player = player;
    }

    public LevelMap(MapLouder mapLoaderFromFile) {
        this.nodesCoordinates = mapLoaderFromFile.getLevelMap().nodesCoordinates;
        this.player = mapLoaderFromFile.getLevelMap().getPlayer();
    }

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
