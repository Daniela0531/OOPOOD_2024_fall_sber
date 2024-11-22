package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.bit.platformer.util.TileMovement;

@Getter
@Setter
public class Level {
    private TiledMap level; // уровень
    private MapRenderer levelRenderer; // отрисовщик
    private TileMovement tileMovement; // движение плитки

    public Level(TiledMap level, MapRenderer levelRenderer, TileMovement tileMovement) {
        this.level = level;
        this.levelRenderer = levelRenderer;
        this.tileMovement = tileMovement;
    }

//    public MapRenderer getLevelRenderer() {
//        return levelRenderer;
//    }

//    public TiledMap getLevel() {
//        return level;
//    }

//    public TileMovement getTileMovement() {
//        return tileMovement;
//    }
}
