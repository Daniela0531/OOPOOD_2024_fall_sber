package ru.mipt.bit.platformer.level;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.game_objects.LevelNodeImpl;
import ru.mipt.bit.platformer.game_objects.NodeType;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level_map.MapNode;

import java.util.ArrayList;

@Component
public class Level {
    private Map map;
    private ArrayList<LevelNodeImpl> obstacles;
    private ArrayList<LevelNodeImpl> nodes;
//    private GridPoint2 playerCoordinates;
    private LevelNodeImpl player;


    public Level(Map map, GraphicProperties graphicProperties) {
        this.map = map;
        TankMoveModel tankMoveModel = new TankMoveModel(map.getPlayer().getCoordinates(), 0f);
        Graphics graphics = new Graphics(graphicProperties.getTankTexture(), map.getPlayer().getCoordinates(), 0f);

        this.player = new LevelNodeImpl(tankMoveModel, graphics, map.getPlayer());
        this.obstacles = new ArrayList<>();
        this.nodes = new ArrayList<>();

        for (MapNode mapNode : map.getNodes()) {
            if (mapNode.getCoordinates() == map.getPlayer().getCoordinates()) {
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TANK)) {
                tankMoveModel = new TankMoveModel(mapNode.getCoordinates(), 0f);
                graphics = new Graphics(graphicProperties.getTankTexture(), mapNode.getCoordinates(), 0f);
                nodes.add(new LevelNodeImpl(tankMoveModel, graphics, mapNode));
            }
            if (mapNode.getNodeType().equals(NodeType.TREE)) {
                graphics = new Graphics(graphicProperties.getTreeTexture(), mapNode.getCoordinates(), 0f);
                obstacles.add(new LevelNodeImpl(null, graphics, mapNode));
            }
        }
    }

    public void removeKilledTanks() {
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<LevelNodeImpl> getEnvirenmentNodes() {
        return obstacles;
    }

    public ArrayList<LevelNodeImpl> getMoveNodes() {
        return nodes;
    }

    public LevelNodeImpl getPlayerTank() {
        return player;
    }

    public int moveNodesSize() {
        return nodes.size();
    }
}