package ru.mipt.bit.platformer.game_management.execution.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level.Map;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class LevelGraphicRender {
    private final Level tiles;
    private ArrayList<Graphics> treeGraphics;
    private TiledMapTileLayer groundLayer;
    private Graphics tanksGraphics;

    public LevelGraphicRender(Batch batch, Map map, GraphicProperties graphicProperties) {

        TiledMap tiledMap = graphicProperties.getTiledMap();
        groundLayer = getSingleLayer(tiledMap);

        tiles = new Level(tiledMap, createSingleLayerMapRenderer(tiledMap, batch), new TileMovement(groundLayer, Interpolation.smooth));

        Texture treeTexture = graphicProperties.getTreeTexture();
        TextureRegion textureRegion = new TextureRegion(treeTexture);
        this.treeGraphics = new ArrayList<>();

        for(int i = 0; i < map.getObstaclesCoordinates().size(); ++i) {
            Graphics treeGraphic = new Graphics(treeTexture, textureRegion);
            moveRectangleAtTileCenter(groundLayer, treeGraphic.getRectangle(), map.getObstaclesCoordinates().get(i));
            this.treeGraphics.add(treeGraphic);
        }

        Texture tankTexture = graphicProperties.getTankTexture();
        TextureRegion tankGraphics = new TextureRegion(tankTexture);
        this.tanksGraphics = new Graphics(tankTexture, tankGraphics);
    }

    public Graphics getTanksGraphics() {
        return tanksGraphics;
    }

    public Level getTiles() {
        return tiles;
    }

    public void render(Batch batch, TankMoveModel playerTank) {
        for (Graphics treeGraphic : treeGraphics) {
            drawTextureRegionUnscaled(batch, treeGraphic.getTextureRegion(), treeGraphic.getRectangle(), 0f);
        }
        drawTextureRegionUnscaled(batch, tanksGraphics.getTextureRegion(), tanksGraphics.getRectangle(), playerTank.getDirection().getRotation());

    }
    public void dispose() {
        for(Graphics treeGraphic : treeGraphics) {
            treeGraphic.getTexture().dispose();
        }
        tiles.getLevel().dispose();
        tanksGraphics.getTexture().dispose();
    }
}