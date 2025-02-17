package ru.mipt.bit.platformer;

//@Configuration
//@ComponentScan
//@PropertySource("classpath:application.properties")

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.level.Map;
import ru.mipt.bit.platformer.game.commands_management.ButtonHandler;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

import java.util.ArrayList;

// здесь должны быть входные параметры игры?
public class Configuration {
    private Map map;

    public Game getGameConfiguration() {
//        System.out.println("Configuration getGameConfiguration");
        ButtonHandler buttonHandler = new ButtonHandler();

        // create models
        GridPoint2 tankCoordinates = new GridPoint2(1, 1);
//        tank = new Tank(tankCoordinates);
        GridPoint2 treeCoordinates = new GridPoint2(1, 3);
//        treeObstacle = new Obstacle(treeCoordinates);

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

//        GraphicRender graphicRender = new GraphicRender(map.getObstaclesCoordinates(), tanksCoordinates);
//        Movement movement = new Movement(playerCoordinates, 0f, playerCoordinates, map.getObstacles());
        TankMoveModel playerTank = new TankMoveModel(playerCoordinates, 0f);

        return new Game(buttonHandler, map, playerTank);
    }

    public Map getMap() {
        return map;
    }
}
