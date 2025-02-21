package ru.mipt.bit.platformer.level;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.LevelMap;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.graphics_objects.GraphicsInterface;
import ru.mipt.bit.platformer.graphics_properties.GraphicProperties;
import ru.mipt.bit.platformer.level_map.MapNode;
import ru.mipt.bit.platformer.logic_objects.NodeType;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Component
public class Level {
//    private Map map;
    private HashMap<TreeMoveModel, GraphicsInterface> obstacles;
    private HashMap<TankMoveModel, GraphicsInterface> tanks;
    private HashMap<BulletMoveModel, GraphicsInterface> bullets;
//    private GridPoint2 playerCoordinates;
//    private Map.Entry<TreeMoveModel, Graphics> player;
    private TankMoveModel playerTank;
    private GraphicsInterface playerGraphics;
    private boolean playerKilled;
//    private GridPoint2 playerCoord;
    private GraphicProperties graphicProperties;


    public Level(LevelMap map, GraphicProperties graphicProperties) {
        this.playerKilled = false;
        this.graphicProperties = graphicProperties;
//        this.map = map;
        this.playerTank = new TankMoveModel(map.getPlayer().getCoordinates(), 0f);
        this.playerGraphics = new Graphics(graphicProperties.getTankTexture());

//        this.player = new Map.Entry();
        this.obstacles = new HashMap<>();
        this.tanks = new HashMap<>();
        this.bullets = new HashMap<>();

//        player.put(playerTankMoveModel, playerGraphics);

        for (MapNode mapNode : map.getNodes()) {
            if (mapNode.getCoordinates() == map.getPlayer().getCoordinates()) {
                TankMoveModel tankMoveModel = new TankMoveModel(mapNode.getCoordinates(), 0f);
                GraphicsInterface graphics = new Graphics(graphicProperties.getTankTexture());
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TANK)) {
                TankMoveModel tankMoveModel = new TankMoveModel(mapNode.getCoordinates(), 0f);
                GraphicsInterface graphics = new Graphics(graphicProperties.getTankTexture());
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TREE)) {
                TreeMoveModel treeMoveModel = new TreeMoveModel(mapNode.getCoordinates(), 0f);
                GraphicsInterface graphics = new Graphics(graphicProperties.getTreeTexture());
                obstacles.put(treeMoveModel, graphics);
                continue;
            }
        }

    }

    private void removeKilledTanks() {
        Collection<TankMoveModel> allTanks = new ArrayList<>();
        allTanks.addAll(tanks.keySet());
        for(TankMoveModel tank : allTanks) {
            if (tank.getHealth() <= 0) {
                tanks.remove(tank);
            }
        }
        if (playerTank.getHealth() <= 0) {
            playerKilled = true;
        }
    }

    public HashMap<TreeMoveModel, GraphicsInterface> getTrees() {
        return obstacles;
    }

    public HashMap<TankMoveModel, GraphicsInterface> getTanks() {
        return tanks;
    }

    public TankMoveModel getPlayerTank() {
        return playerTank;
    }
    public GraphicsInterface getPlayerGraphics() {
        return playerGraphics;
    }

    public int moveNodesSize() {
        return tanks.size();
    }
    public void putBulletInLevel(BulletMoveModel bulletMoveModel) {
        GraphicsInterface graphics = new Graphics(graphicProperties.getBulletTexture());
        bullets.put(bulletMoveModel, graphics);
    }

    public HashMap<BulletMoveModel, GraphicsInterface> getBullets() {
        return bullets;
    }

    private void removeFinishedBullets() {
        Collection<BulletMoveModel> allBullets = new ArrayList<>();
        allBullets.addAll(bullets.keySet());
        for(BulletMoveModel bulletMoveModel : allBullets) {
            if (!bulletMoveModel.isMoving()) {
                bullets.remove(bulletMoveModel);
            }
        }
    }

    public void removeInvalidEntities() {
        removeFinishedBullets();
        removeKilledTanks();
    }

    public boolean isPlayerKilled() {
        return playerKilled;
    }
}