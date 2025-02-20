package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

//@Component
public class GraphicProperties {
    private String tankTextureImg = "images/tank_blue.png";
    private String treeTextureImg = "images/greenTree.png";
    private String tiledMapImg = "level.tmx";
    private TiledMapTileLayer tiledMapTileLayer = getSingleLayer(new TmxMapLoader().load("level.tmx"));
//    private TiledMap

    public GraphicProperties(Texture tankTexture, Texture treeTexture, TiledMap tiledMap) {
//        this.tankTexture = tankTexture;
//        this.treeTexture = treeTexture;
//        this.tiledMap = tiledMap;
        this.tiledMapTileLayer = getSingleLayer(tiledMap);
//        this.tileMovement = new TileMovement(tiledMapTileLayer, Interpolation.smooth);
    }

    public Texture getTankTexture() {
        return new Texture(tankTextureImg);
    }

    public TiledMap getTiledMap() {
        return new TmxMapLoader().load(tiledMapImg);
    }

    public Texture getTreeTexture() {
        return new Texture(treeTextureImg);
    }

//    public TileMovement getTileMovement() {
//        return tileMovement;
//    }

    public TiledMapTileLayer getTiledMapTileLayer() {
        return tiledMapTileLayer;
    }

    public TextureRegion getTankTextureRegion() {
        return new TextureRegion(getTankTexture());
    }

    public Rectangle getTankRectangle() {
        return createBoundingRectangle(getTankTextureRegion());
    }

    public Rectangle getTreeRectangle() {
        return createBoundingRectangle(getTreeTextureRegion());
    }

    public TextureRegion getTreeTextureRegion() {
        return new TextureRegion(getTreeTexture());
    }

//    public Graphics getTankGraphics() {
//        return new Graphics(getTankTexture(), getTankTextureRegion(), 0f);
//    }
//    public Graphics getTreeGraphics() {
//        return new Graphics(getTreeTexture(), getTreeTextureRegion(), 0f);
//    }
}
