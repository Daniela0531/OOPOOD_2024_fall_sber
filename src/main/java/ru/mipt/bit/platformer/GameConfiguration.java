package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mipt.bit.platformer.graphics_properties.GraphicProperties;
import ru.mipt.bit.platformer.util.TileMovement;

import java.io.FileNotFoundException;

import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

// здесь должны быть входные параметры игры

@Configuration
@ComponentScan
public class GameConfiguration {
    private FileLoader fileLoader = new FileLoader();
    @Bean
    public LevelMap map() throws FileNotFoundException {
        return fileLoader.createMapFromFile("/Users/daniela/Desktop/1_sem/my_repo/src/main/resources/file.txt");
//        ArrayList<MapNode> obstacles = new ArrayList<>();
//        obstacles.add(new MapNode(new GridPoint2(1, 3), NodeType.TREE));
//        obstacles.add(new MapNode(new GridPoint2(1, 5), NodeType.TREE));
//        obstacles.add(new MapNode(new GridPoint2(5, 1), NodeType.TREE));
//        obstacles.add(new MapNode(new GridPoint2(8, 3), NodeType.TREE));
//        obstacles.add(new MapNode(new GridPoint2(5, 5), NodeType.TANK));
//        obstacles.add(new MapNode(new GridPoint2(0, 0), NodeType.TANK));
//        obstacles.add(new MapNode(new GridPoint2(6, 1), NodeType.TANK));
//        MapNode player = new MapNode(new GridPoint2(1, 1), NodeType.TANK);
//
//        return new LevelMap(obstacles, player);
    }

    @Bean
    public GraphicProperties graphicProperties() {
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");
        return new GraphicProperties(tiledMap);
    }

    @Bean
    public Batch batch() {
        return new SpriteBatch();
    }

    @Bean
    public TiledMap tiledMap() {
        return new TiledMap();
    }

    @Bean
    public TiledMapTileLayer tiledMapTileLayer() {
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");
        return getSingleLayer(tiledMap);
    }

    @Bean
    public TileMovement tileMovement() {
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer tiledMapTileLayer = getSingleLayer(tiledMap);
        return new TileMovement(tiledMapTileLayer, Interpolation.smooth);
    }
}
