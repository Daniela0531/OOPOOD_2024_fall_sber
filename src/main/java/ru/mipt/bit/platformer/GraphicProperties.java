package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

//@Component
public class GraphicProperties {
    Texture tankTexture = new Texture("images/tank_blue.png");
    Texture treeTexture = new Texture("images/greenTree.png");
    TiledMap tiledMap = new TmxMapLoader().load("level.tmx");

    public GraphicProperties(Texture tankTexture, Texture treeTexture, TiledMap tiledMap) {
        this.tankTexture = tankTexture;
        this.treeTexture = treeTexture;
        this.tiledMap = tiledMap;
    }

    public Texture getTankTexture() {
        return tankTexture;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public Texture getTreeTexture() {
        return treeTexture;
    }
}
