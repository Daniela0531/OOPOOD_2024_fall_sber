package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.Map;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelGraphics {
    private ArrayList<Graphics> treeGraphics;
    private TiledMapTileLayer groundLayer;
    private Level tiles;
    private Graphics tanksGraphics;


    public LevelGraphics(Batch batch, Map map) {

        TiledMap level = new TmxMapLoader().load("level.tmx");
        groundLayer = getSingleLayer(level);

        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));

        Texture texture = new Texture("images/greenTree.png");
        TextureRegion textureRegion = new TextureRegion(texture);
        this.treeGraphics = new ArrayList<>();

        for(int i = 0; i < map.getObstaclesCoordinates().size(); ++i) {
            Graphics treeGraphic = new Graphics(texture, textureRegion);
            moveRectangleAtTileCenter(groundLayer, treeGraphic.getRectangle(), map.getObstaclesCoordinates().get(i));
            this.treeGraphics.add(treeGraphic);
        }

        Texture tankTexture = new Texture("images/tank_blue.png");
        TextureRegion tankGraphics = new TextureRegion(tankTexture);
        this.tanksGraphics = new Graphics(tankTexture, tankGraphics);
    }

    public Graphics getTanksGraphics() {
        return tanksGraphics;
    }

    public Level getTiles() {
        return tiles;
    }


    public void dispose() {
        for(Graphics treeGraphic : treeGraphics) {
            treeGraphic.getTexture().dispose();
        }
        tiles.getLevel().dispose();
    }
}
