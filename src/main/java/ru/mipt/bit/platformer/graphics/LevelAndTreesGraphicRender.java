package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Map;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelAndTreesGraphicRender {
    private Level tiles;
    private ArrayList<Graphics> treeGraphics;
    private TiledMapTileLayer groundLayer;
    private Map map;

    public LevelAndTreesGraphicRender(ArrayList<GridPoint2> obstacleCoordinates, Batch batch) {

        TiledMap level = new TmxMapLoader().load("level.tmx");
        groundLayer = getSingleLayer(level);

        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));

        Texture texture = new Texture("images/greenTree.png");
        TextureRegion textureRegion = new TextureRegion(texture);
        this.treeGraphics = new ArrayList<>();
        map = new Map(obstacleCoordinates);

        for(int i = 0; i < map.getMap().size(); ++i) {
            Graphics treeGraphic = new Graphics(texture, textureRegion);
            moveRectangleAtTileCenter(groundLayer, treeGraphic.getRectangle(), map.getMap().get(i));
            this.treeGraphics.add(treeGraphic);
        }
    }

    public ArrayList<Graphics> getTreeGraphics() {
        return treeGraphics;
    }

    public Level getTiles() {
        return tiles;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public void render(Batch batch) {
        for (Graphics treeGraphic : treeGraphics) {
            drawTextureRegionUnscaled(batch, treeGraphic.getTextureRegion(), treeGraphic.getRectangle(), 0f);
        }
    }

    public void dispose() {
        for(Graphics treeGraphic : treeGraphics) {
            treeGraphic.getTexture().dispose();
        }
        tiles.getLevel().dispose();
    }
}