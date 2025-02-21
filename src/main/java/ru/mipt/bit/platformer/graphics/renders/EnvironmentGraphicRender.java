package ru.mipt.bit.platformer.graphics.renders;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.graphics_objects.Graphics;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

@Component
public class EnvironmentGraphicRender {
//    private final Level tiles;
    private ArrayList<Graphics> treeGraphics;
    private TiledMapTileLayer groundLayer;
    private MapRenderer mapRenderer;

    public EnvironmentGraphicRender(Batch batch) {

//        TiledMap tiledMap = graphicProperties.getTiledMap();
//        groundLayer = getSingleLayer(tiledMap);
//
//        mapRenderer = createSingleLayerMapRenderer(tiledMap, batch);
//        tiles = new Level(tiledMap, new TileMovement(groundLayer, Interpolation.smooth));
//
//        Texture treeTexture = graphicProperties.getTreeTexture();
//        TextureRegion textureRegion = new TextureRegion(treeTexture);
//        this.treeGraphics = new ArrayList<>();
//
//        for(int i = 0; i < map.getObstaclesCoordinatesTrees().size(); ++i) {
//            Graphics treeGraphic = new Graphics(treeTexture, textureRegion, 0f);
//            moveRectangleAtTileCenter(groundLayer, treeGraphic.getRectangle(), map.getObstaclesCoordinatesTrees().get(i));
//            this.treeGraphics.add(treeGraphic);
//        }
    }

//    public Level getTiles() {
//        return tiles;
//    }

    public void renderTiles() {
        mapRenderer.render();
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
//        tiles.getLevel().dispose();
    }
}