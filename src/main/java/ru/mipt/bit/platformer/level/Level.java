package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.LevelMap;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.graphics_objects.GraphicsForLivable;
import ru.mipt.bit.platformer.graphics_objects.GraphicsInterface;
import ru.mipt.bit.platformer.level_map.MapNode;
import ru.mipt.bit.platformer.level_map.NodeType;
import ru.mipt.bit.platformer.level_properties.GraphicProperties;
import ru.mipt.bit.platformer.level_properties.LogicProperties;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class Level {
    private HashMap<TreeMoveModel, GraphicsInterface> trees;
    private HashMap<TankMoveModel, GraphicsInterface> tanks;
    private HashMap<BulletMoveModel, GraphicsInterface> bullets;
    private TankMoveModel playerTank;
    private GraphicsForLivable playerGraphics;
    private boolean playerKilled;
    private GraphicProperties graphicProperties;
    private LogicProperties logicProperties;
    private TiledMap tiledMap;
    private final int leftBound;
    private final int rightBound;
    private final int lowBound;
    private final int upBound;


    public Level(
            LevelMap map,
            GraphicProperties graphicProperties,
            LogicProperties logicProperties) {
        this.leftBound = map.getLeftBound();
        this.rightBound = map.getRightBound();
        this.lowBound = map.getLowBound();
        this.upBound = map.getUpBound();
        this.playerKilled = false;
        this.graphicProperties = graphicProperties;
        this.tiledMap = graphicProperties.getTiledMap();
        this.logicProperties = logicProperties;
        this.playerTank = new TankMoveModel(
                map.getPlayer().getCoordinates(),
                0f,
                logicProperties.getTankMaxHealth(),
                logicProperties.getTankSpeed());
        this.playerGraphics = new GraphicsForLivable(
                graphicProperties.getTankTexture(),
                graphicProperties.getHealthBarDecorator());

        this.trees = new HashMap<>();
        this.tanks = new HashMap<>();
        this.bullets = new HashMap<>();

        for (MapNode mapNode : map.getNodes()) {
            if (mapNode.getCoordinates() == map.getPlayer().getCoordinates()) {
                TankMoveModel tankMoveModel = new TankMoveModel(
                        mapNode.getCoordinates(),
                        0f,
                        logicProperties.getTankMaxHealth(),
                        logicProperties.getTankSpeed());
                GraphicsForLivable graphics = new GraphicsForLivable(
                        graphicProperties.getTankTexture(),
                        graphicProperties.getHealthBarDecorator());
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TANK)) {
                TankMoveModel tankMoveModel = new TankMoveModel(
                        mapNode.getCoordinates(),
                        0f,
                        logicProperties.getTankMaxHealth(),
                        logicProperties.getTankSpeed());
                GraphicsForLivable graphics = new GraphicsForLivable(
                        graphicProperties.getTankTexture(),
                        graphicProperties.getHealthBarDecorator());
                tanks.put(tankMoveModel, graphics);
                continue;
            }
            if (mapNode.getNodeType().equals(NodeType.TREE)) {
                TreeMoveModel treeMoveModel = new TreeMoveModel(mapNode.getCoordinates(), 0f);
                Graphics graphics = new Graphics(graphicProperties.getTreeTexture());
                trees.put(treeMoveModel, graphics);
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
        return trees;
    }

    public HashMap<TankMoveModel, GraphicsInterface> getTanks() {
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
        for (Map.Entry<TankMoveModel, GraphicsInterface> entry : tanks.entrySet()) {
            entry.getKey().mainUpdateProgress(deltaTime);
        }
        playerTank.mainUpdateProgress(deltaTime);
    }
    public void drow(Batch batch) {
        for (Map.Entry<TreeMoveModel, GraphicsInterface> entry : trees.entrySet()) {
            entry.getValue().draw(batch, entry.getKey().getRotation());
        }
        for (Map.Entry<TankMoveModel, GraphicsInterface> entry : tanks.entrySet()) {
            entry.getValue().draw(batch, entry.getKey().getRotation());
            if (entry.getKey().isHealthBarRaise()) {
                ((GraphicsForLivable)entry.getValue()).drowHealthBar(batch, entry.getKey().getMaxHealth(), entry.getKey().getHealth());
            }
        }
        for (Map.Entry<BulletMoveModel, GraphicsInterface> entry : bullets.entrySet()) {
            entry.getValue().draw(batch, entry.getKey().getRotation());
        }
        if (!playerKilled) {
            playerGraphics.draw(batch, playerTank.getRotation());
            if (playerTank.isHealthBarRaise()) {
                playerGraphics.drowHealthBar(batch, playerTank.getMaxHealth(), playerTank.getHealth());
            }
        }
    }

    public void movementDrow(TileMovement tileMovement) {
        for (Map.Entry<TankMoveModel, GraphicsInterface> entry : tanks.entrySet()) {
            movementRender(tileMovement, entry.getKey(), entry.getValue().getRectangle());
            if (entry.getKey().isHealthBarRaise()) {
//                ((GraphicsForLivable)entry.getValue()).drowHealthBar(batch, entry.getKey().getMaxHealth(), entry.getKey().getHealth());
//                entry.getValue().drawMovement(tileMovement, entry.getKey(), entry.);
                movementRender(tileMovement, entry.getKey(), ((GraphicsForLivable)entry.getValue()).getHealthBarRectangle());
            }
        }
        for (Map.Entry<BulletMoveModel, GraphicsInterface> entry : bullets.entrySet()) {
            movementRender(tileMovement, entry.getKey(), entry.getValue().getRectangle());
        }
        movementRender(tileMovement, playerTank, playerGraphics.getRectangle());
    }
    private void movementRender(TileMovement tileMovement, MoveModel node, Rectangle rectangle) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                node.getCoordinates(),
                node.getDestination(),
                node.getProgress()
        );
    }
    public void dispose() {
        tiledMap.dispose();
        for(GraphicsInterface graphics : trees.values()) {
            graphics.getTexture().dispose();
        }
        for(GraphicsInterface graphics : tanks.values()) {
            graphics.getTexture().dispose();
        }
        for(GraphicsInterface graphics : bullets.values()) {
            graphics.getTexture().dispose();
        }
        if (!playerKilled) {
            playerGraphics.getTexture().dispose();
            if (playerTank.isHealthBarRaise()) {
                playerGraphics.disposeHealthBar();
            }
        }
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }
    public int getUpBound() {
        return upBound;
    }
    public int getLowBound() {
        return lowBound;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }
}