package ru.mipt.bit.platformer.game.level;

import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.game.model.Node;
import ru.mipt.bit.platformer.game.util.TileMovement;

import java.util.ArrayList;

public class Level {
    private TiledMap level; // уровень
    private MapRenderer levelRenderer; // отрисовщик
    private TileMovement tileMovement; // движение плитки
    private Map map;

    private ArrayList<Node> nodes;

    public Level(TiledMap level, MapRenderer levelRenderer, TileMovement tileMovement) {
        this.level = level;
        this.levelRenderer = levelRenderer;
        this.tileMovement = tileMovement;
    }

    public MapRenderer getLevelRenderer() {
        return levelRenderer;
    }

    public TiledMap getLevel() {
        return level;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void removeKilledTanks() {
    }
}
