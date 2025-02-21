package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.graphics_properties.GraphicProperties;
//import ru.mipt.bit.platformer.logic_objects.LevelNodeImpl;
import ru.mipt.bit.platformer.logic_objects.NodeType;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level_map.MapNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class Level {
    private Map map;
    private HashMap<TreeMoveModel, Graphics> obstacles;
    private HashMap<TankMoveModel, Graphics> tanks;
    private HashMap<BulletMoveModel, Graphics> bullets;
//    private GridPoint2 playerCoordinates;
    private TankMoveModel player;
//    private GridPoint2 playerCoord;
    private GraphicProperties graphicProperties;


    public Level(Map map, GraphicProperties graphicProperties) {
        this.graphicProperties = graphicProperties;
        this.map = map;
        TankMoveModel tankMoveModel = new TankMoveModel(map.getPlayer().getCoordinates(), 0f);
        Graphics graphics = new Graphics(graphicProperties.getTankTexture(), map.getPlayer().getCoordinates(), 0f);

        this.player = tankMoveModel;
        this.obstacles = new HashMap<>();
        this.tanks = new HashMap<>();
        this.bullets = new HashMap<>();

        for (MapNode mapNode : map.getNodes()) {
            if (mapNode.getCoordinates() == map.getPlayer().getCoordinates()) {
                tankMoveModel = new TankMoveModel(mapNode.getCoordinates(), 0f);
                Graphics graphics = new Graphics(graphicProperties.getTankTexture(), mapNode.getCoordinates(), 0f);
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TANK)) {
                tankMoveModel = new TankMoveModel(mapNode.getCoordinates(), 0f);
                Graphics graphics = new Graphics(graphicProperties.getTankTexture(), mapNode.getCoordinates(), 0f);
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TREE)) {
                TreeMoveModel treeMoveModel = new TreeMoveModel(mapNode.getCoordinates(), 0f);
                Graphics graphics = new Graphics(graphicProperties.getTreeTexture(), mapNode.getCoordinates(), 0f);
                obstacles.put(treeMoveModel, graphics);
                continue;
            }
        }

    }

    public void removeKilledTanks() {
        for(TankMoveModel tank : tanks.keySet()) {
            if (tank.getHealth() <= 0) {
//                MapNode removedNode = new MapNode(tank.getCoordinates(), NodeType.TANK);
//                if (map.getNodes().remove(removedNode)) {
//                    System.out.println("remove tank");
//                }
                tanks.remove(tank);
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public HashMap<TreeMoveModel, Graphics> getTrees() {
        return obstacles;
    }

    public HashMap<TankMoveModel, Graphics> getTanks() {
        return tanks;
    }

    public TankMoveModel getPlayerTank() {
        for (TankMoveModel tankMoveModel : tanks.keySet()) {
            if (tankMoveModel.getCoordinates() == playerCoord) {
                return tankMoveModel;
            }
        }
        return null;
    }

    public int moveNodesSize() {
        return tanks.size();
    }
    public void createBullet(BulletMoveModel bulletMoveModel) {
//        BulletMoveModel bullet = new BulletMoveModel();
        Texture texture = graphicProperties.getBulletTexture();

//        System.out.println("create bullet: " + bulletMoveModel.getCoordinates());
        Graphics graphics = new Graphics(texture, bulletMoveModel.getCoordinates(), bulletMoveModel.getRotation());
//        LevelNodeImpl levelNode = new LevelNodeImpl(bulletMoveModel, graphics);
        bullets.put(bulletMoveModel, graphics);
    }
//    private void removeBullet(BulletMoveModel bullet) {
//        bullets.remove(bullet);
//    }

    public HashMap<BulletMoveModel, Graphics> getBullets() {
        return bullets;
    }

    public void removeFinishedBullets() {
        Collection<BulletMoveModel> allBullets = new ArrayList<>();
        allBullets.addAll(bullets.keySet());
        for(BulletMoveModel bulletMoveModel : allBullets) {
            if (!bulletMoveModel.isMoving()) {
                bullets.remove(bulletMoveModel);
            }
        }
    }
}