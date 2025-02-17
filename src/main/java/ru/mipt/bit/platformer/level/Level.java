package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import ru.mipt.bit.platformer.game_objects.Node;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

public class Level {
    private TiledMap level; // уровень
    private MapRenderer mapRenderer; // отрисовщик
    private TileMovement tileMovement; // движение плитки
    private Map map;

    private Graphics tanksGraphics;
    private ArrayList<Node> nodes;

    public Level(TiledMap level, MapRenderer levelRenderer, TileMovement tileMovement) {
        this.level = level;
        this.mapRenderer = levelRenderer;
        this.tileMovement = tileMovement;

        Texture tankTexture = new Texture("images/tank_blue.png");
        TextureRegion tankGraphics = new TextureRegion(tankTexture);
        this.tanksGraphics = new Graphics(tankTexture, tankGraphics);
    }

    public MapRenderer getMapRenderer() {
        return mapRenderer;
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

//    public Level(String levelPath) {
//        this.levelPath = levelPath;
//    }
//
//    public void create(Batch batch) {
//        this.batch = batch;
//
//        // load level tiles
//        level = new TmxMapLoader().load(levelPath);
//        levelRenderer = createSingleLayerMapRenderer(level, batch);
//        groundLayer = getSingleLayer(level);
//        tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
//    }
//
//    public void dispose() {
//        level.dispose();
//    }
}