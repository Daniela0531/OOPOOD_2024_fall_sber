package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

// здесь должны быть входные параметры игры

@Configuration
@ComponentScan
public class GameConfiguration {
//    private GraphicProperties graphicProperties;
    private GridPoint2 playerCoordinates = new GridPoint2(0, 0);

    public GameConfiguration() {
        setPlayerCoordinates();
//        setGraphicProperties();
    }

    @Bean
    public Map map() {
        ArrayList<GridPoint2> treeCoordinates_ = new ArrayList<>();
        treeCoordinates_.add(new GridPoint2(1, 3));
        treeCoordinates_.add(new GridPoint2(1, 5));
        treeCoordinates_.add(new GridPoint2(5, 1));
        treeCoordinates_.add(new GridPoint2(2, 3));

        ArrayList<GridPoint2> tanksCoordinates = new ArrayList<>();
        tanksCoordinates.add(new GridPoint2(1, 1));

        return new Map(treeCoordinates_, tanksCoordinates, playerCoordinates);
    }

//    @Bean
    private void setPlayerCoordinates() {
        playerCoordinates = new GridPoint2(1, 1);
    }

    @Bean
    public GraphicProperties graphicProperties() {
        Texture tankTexture = new Texture("images/tank_blue.png");
        Texture treeTexture = new Texture("images/greenTree.png");
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");

        return new GraphicProperties(tankTexture, treeTexture, tiledMap);
    }

    public GridPoint2 getPlayerCoordinates() {
        return playerCoordinates;
    }

//    public GraphicProperties getGraphicProperties() {
//        return graphicProperties;
//    }

//    public Map getMap() {
//        return map;
//    }

    @Bean
    public TankMoveModel tankMoveModel() {
        return new TankMoveModel(playerCoordinates, 0f);
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
