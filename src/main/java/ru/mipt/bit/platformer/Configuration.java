package ru.mipt.bit.platformer;

//@Configuration
//@ComponentScan
//@PropertySource("classpath:application.properties")

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.level.Map;

import java.util.ArrayList;

// здесь должны быть входные параметры игры
public class Configuration {
    private Map map;

    public Game getGameConfiguration() {
//        ButtonHandler buttonHandler = new ButtonHandler();

        // create models
        GridPoint2 tankCoordinates = new GridPoint2(1, 1);
        GridPoint2 treeCoordinates = new GridPoint2(1, 3);

        // create map
        ArrayList<GridPoint2> treeCoordinates_ = new ArrayList<>();
        treeCoordinates_.add(treeCoordinates);
        treeCoordinates_.add(new GridPoint2(1, 5));
        treeCoordinates_.add(new GridPoint2(5, 1));
        treeCoordinates_.add(new GridPoint2(2, 3));

        ArrayList<GridPoint2> tanksCoordinates = new ArrayList<>();
        tanksCoordinates.add(tankCoordinates);

        GridPoint2 playerCoordinates = new GridPoint2(1, 1);

        this.map = new Map(treeCoordinates_, tanksCoordinates, playerCoordinates);

        TankMoveModel playerTank = new TankMoveModel(playerCoordinates, 0f);

        Texture tankTexture = new Texture("images/tank_blue.png");
        Texture treeTexture = new Texture("images/greenTree.png");
        TiledMap tiledMap = new TmxMapLoader().load("level.tmx");

        GraphicProperties graphicProperties = new GraphicProperties(tankTexture, treeTexture, tiledMap);

        return new Game(map, graphicProperties, playerTank);
    }

    public Map getMap() {
        return map;
    }
}
