package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import lombok.Getter;
import lombok.Setter;
import ru.mipt.bit.platformer.Model;
import ru.mipt.bit.platformer.Obstacle;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.HashSet;
import java.util.Set;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

@Getter
@Setter
public class Level {
    private TiledMap level; // уровень
    private MapRenderer levelRenderer; // отрисовщик
    private TileMovement tileMovement; // движение плитки
    private Batch batch;
    private TiledMapTileLayer groundLayer;

    private Set<Model> models = new HashSet<>();
    private Set<Obstacle> obstacles = new HashSet<>();

    private final String gdxMapPath;
    public Level(String filePath) {
//        models, obstacles, new GridPoint2(width, height)
        gdxMapPath = filePath;
        batch = new SpriteBatch();
        level = new TmxMapLoader().load(filePath);
        groundLayer = getSingleLayer(level);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }

    public void load(PreProcessedLevel preProcessedLevel) {
        models = preProcessedLevel.getModels();
        obstacles = preProcessedLevel.getObstacles();
    }

//    public void getLevel(LevelLoader levelLoader) {
//        models = levelLoader
//        BaseLevel level = mapLoader.load();
//        GdxLevel gdxLevel = new GdxLevel(gdxMapPath, level);
//        level.setUpperRightSize(gdxLevel.getLevelSize());
//        return gdxLevel;
//    }

//    public Level loadFromFile(String filePath) {
//        MapLoader loader = new MapFileLoader(filePath);
//        return loadLevel(loader);
//    }

    public void loadByRandom() {
//        GridPoint2 upperRightLimit = GdxLevel.getLevelSizeFromFile(gdxMapPath);
//        MapLoader loader = new FileLevelLouder(upperRightLimit);
    }

//    private GdxLevel loadLevel(MapLoader mapLoader) {
//        BaseLevel level = mapLoader.load();
//        GdxLevel gdxLevel = new GdxLevel(gdxMapPath, level);
//        level.setUpperRightSize(gdxLevel.getLevelSize());
//        return gdxLevel;
//    }

}
