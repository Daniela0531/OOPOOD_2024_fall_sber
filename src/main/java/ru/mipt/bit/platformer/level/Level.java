package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.LevelMap;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.graphics_objects.GraphicsForLivable;
import ru.mipt.bit.platformer.graphics_objects.GraphicsForLivableInterface;
import ru.mipt.bit.platformer.graphics_objects.GraphicsInterface;
import ru.mipt.bit.platformer.level_properties.GraphicProperties;
import ru.mipt.bit.platformer.level_map.MapNode;
import ru.mipt.bit.platformer.level_map.NodeType;
import ru.mipt.bit.platformer.level_properties.LogicProperties;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class Level {
    private HashMap<TreeMoveModel, GraphicsInterface> obstacles;
    private HashMap<TankMoveModel, GraphicsForLivableInterface> tanks;
    private HashMap<BulletMoveModel, GraphicsInterface> bullets;
    private TankMoveModel playerTank;
    private GraphicsForLivable playerGraphics;
    private boolean playerKilled;
    private GraphicProperties graphicProperties;
    private LogicProperties logicProperties;


    public Level(LevelMap map, GraphicProperties graphicProperties, LogicProperties logicProperties) {
        this.playerKilled = false;
        this.graphicProperties = graphicProperties;
        this.logicProperties = logicProperties;
        this.playerTank = new TankMoveModel(
                map.getPlayer().getCoordinates(),
                0f,
                logicProperties.getTankMaxHealth(),
                logicProperties.getTankSpeed());
        this.playerGraphics = new GraphicsForLivable(graphicProperties.getTankTexture());

        this.obstacles = new HashMap<>();
        this.tanks = new HashMap<>();
        this.bullets = new HashMap<>();

        for (MapNode mapNode : map.getNodes()) {
            if (mapNode.getCoordinates() == map.getPlayer().getCoordinates()) {
                TankMoveModel tankMoveModel = new TankMoveModel(
                        mapNode.getCoordinates(),
                        0f,
                        logicProperties.getTankMaxHealth(),
                        logicProperties.getTankSpeed());
                GraphicsForLivable graphics = new GraphicsForLivable(graphicProperties.getTankTexture());
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TANK)) {
                TankMoveModel tankMoveModel = new TankMoveModel(
                        mapNode.getCoordinates(),
                        0f,
                        logicProperties.getTankMaxHealth(),
                        logicProperties.getTankSpeed());
                GraphicsForLivable graphics = new GraphicsForLivable(graphicProperties.getTankTexture());
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TREE)) {
                TreeMoveModel treeMoveModel = new TreeMoveModel(mapNode.getCoordinates(), 0f);
                Graphics graphics = new Graphics(graphicProperties.getTreeTexture());
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

    public HashMap<TankMoveModel, GraphicsForLivableInterface> getTanks() {
        return tanks;
    }

    public TankMoveModel getPlayerTank() {
        return playerTank;
    }
    public GraphicsForLivable getPlayerGraphics() {
        return playerGraphics;
    }

    public int moveNodesSize() {
        return tanks.size();
    }
    public BulletMoveModel putBulletInLevel(GridPoint2 coord, Direction direction) {
        BulletMoveModel bulletMoveModel = new BulletMoveModel(
                coord,
                direction,
                logicProperties.getBulletDamage(),
                logicProperties.getBulletSpeed());
        Graphics graphics = new Graphics(graphicProperties.getBulletTexture());
        bullets.put(bulletMoveModel, graphics);
        return bulletMoveModel;
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

    public void update(float deltaTime) {
        for (Map.Entry<TankMoveModel, GraphicsForLivableInterface> entry : tanks.entrySet()) {
            entry.getKey().mainUpdateProgress(deltaTime);
//            System.out.println(
//                    "    updateHealthBar\n" +
//                            "        cur healthBar points: " + entry.getKey().getHealthBarCur() +
//                            "        healthBar is rase: " + entry.getKey().isHealthBarRaise());
        }
        playerTank.mainUpdateProgress(deltaTime);
//        System.out.println("update level\n");
    }
}