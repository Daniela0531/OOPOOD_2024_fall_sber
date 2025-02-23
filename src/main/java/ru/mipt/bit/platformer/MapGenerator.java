package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level_map.MapNode;
import ru.mipt.bit.platformer.level_map.NodeType;

import java.util.ArrayList;

//@Component
public class MapGenerator implements MapLouder {
    private LevelMap levelMap;
    @Override
    public void loadLevelMap() {
        ArrayList<MapNode> obstacles = new ArrayList<>();
        obstacles.add(new MapNode(new GridPoint2(1, 3), NodeType.TREE));
        obstacles.add(new MapNode(new GridPoint2(1, 5), NodeType.TREE));
        obstacles.add(new MapNode(new GridPoint2(5, 1), NodeType.TREE));
        obstacles.add(new MapNode(new GridPoint2(8, 3), NodeType.TREE));
        obstacles.add(new MapNode(new GridPoint2(5, 5), NodeType.TANK));
        obstacles.add(new MapNode(new GridPoint2(0, 0), NodeType.TANK));
        obstacles.add(new MapNode(new GridPoint2(6, 1), NodeType.TANK));
        MapNode player = new MapNode(new GridPoint2(1, 1), NodeType.TANK);

        this.levelMap = new LevelMap(obstacles, player);
    }
    @Override
    public LevelMap getLevelMap() {
        return levelMap;
    }
}
