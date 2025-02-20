package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.game_objects.LevelNodeImpl;
import ru.mipt.bit.platformer.game_objects.NodeType;
import ru.mipt.bit.platformer.game_objects.movable.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level_map.MapNode;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Level {
    private Map map;
    private ArrayList<LevelNodeImpl> obstacles;
    private ArrayList<LevelNodeImpl> nodes;
    private HashMap<BulletMoveModel, Graphics> bullets;
//    private GridPoint2 playerCoordinates;
    private LevelNodeImpl player;
    private GraphicProperties graphicProperties;


    public Level(Map map, GraphicProperties graphicProperties) {
        this.graphicProperties = graphicProperties;
        this.map = map;
        TankMoveModel tankMoveModel = new TankMoveModel(map.getPlayer().getCoordinates(), 0f);
        Graphics graphics = new Graphics(graphicProperties.getTankTexture(), map.getPlayer().getCoordinates(), 0f);

        this.player = new LevelNodeImpl(tankMoveModel, graphics);
        this.obstacles = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.bullets = new HashMap<>();

        for (MapNode mapNode : map.getNodes()) {
            if (mapNode.getCoordinates() == map.getPlayer().getCoordinates()) {
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TANK)) {
                tankMoveModel = new TankMoveModel(mapNode.getCoordinates(), 0f);
                graphics = new Graphics(graphicProperties.getTankTexture(), mapNode.getCoordinates(), 0f);
                nodes.add(new LevelNodeImpl(tankMoveModel, graphics));
            }
            if (mapNode.getNodeType().equals(NodeType.TREE)) {
                graphics = new Graphics(graphicProperties.getTreeTexture(), mapNode.getCoordinates(), 0f);
                obstacles.add(new LevelNodeImpl(null, graphics));
            }
        }

    }

    public void removeKilledTanks() {
        for(int i = 0; i < nodes.size(); ++i) {
            if (nodes.get(i).getMoveModel().getHealth() <= 0) {
                GridPoint2 coord = nodes.get(i).getMoveModel().getCoordinates();
                nodes.remove(i);
                MapNode mapNode = new MapNode(coord, NodeType.TANK);
                map.getNodes().remove(mapNode);
            }
        }
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
    public void createBullet(BulletMoveModel bulletMoveModel) {
//        BulletMoveModel bullet = new BulletMoveModel();
        Texture texture = graphicProperties.getBulletTexture();

        Graphics graphics = new Graphics(texture, new GridPoint2(1, 1), 0f);
        LevelNodeImpl levelNode = new LevelNodeImpl(bulletMoveModel, graphics);
        bullets.put(bulletMoveModel, graphics);
    }
    public void removeBullet(BulletMoveModel bullet) {
        bullets.remove(bullet);
    }

    public HashMap<BulletMoveModel, Graphics> getBullets() {
        return bullets;
    }
}