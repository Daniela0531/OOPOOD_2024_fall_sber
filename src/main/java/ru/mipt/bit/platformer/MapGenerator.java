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

        // идеологически границы должны загружаться/создаваться загрузчиком карты (из файла с картой например)
        // карта плиток и границы должны уровня должны быть связаны (карта плиток генериться в соответствии с размерами?)
        // но не понятно как управлять level.tmx (TiledMap)
        // хард код
        int leftBound = 0;
        int rightBound = 9;
        int upBound = 5;
        int lowBound = 0;

        this.levelMap = new LevelMap(obstacles, player, leftBound, rightBound, lowBound, upBound);
    }
    @Override
    public LevelMap getLevelMap() {
        return levelMap;
    }

//    public LevelMap getLevelMap(int leftBound, int rightBound, int upBound, int lowBound) {
//    }
}
