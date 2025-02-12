package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.graphics.GraphicRender;
import ru.mipt.bit.platformer.model.Movement;
import ru.mipt.bit.platformer.model.objects.Obstacle;
import ru.mipt.bit.platformer.model.objects.Tank;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;
    private Tank tank;
    private Obstacle treeObstacle;
    private Movement movement;
    private ButtonHandler buttonHandler;
    private GraphicRender graphicRender;

    private Map map;
    @Override
    public void create() {
        buttonHandler = new ButtonHandler();

        // create models
        GridPoint2 tankCoordinates = new GridPoint2(1, 1);
        tank = new Tank(tankCoordinates);
        GridPoint2 treeCoordinates = new GridPoint2(1, 3);
        treeObstacle = new Obstacle(treeCoordinates);

        // create map
        ArrayList<GridPoint2> treeCoordinates_ = new ArrayList<>();
        treeCoordinates_.add(treeCoordinates);
        treeCoordinates_.add(new GridPoint2(1, 5));
        map = new Map(treeCoordinates_);

        // create graphic
        graphicRender = new GraphicRender(map.getMap());
        // create movement
        GridPoint2 tankDestinationCoordinates = new GridPoint2(1, 1);
        movement = new Movement(tank.getCoordinates(), 0f, tankDestinationCoordinates, map.getMap());
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float progress = continueProgress(movement.getProgress(), deltaTime, MOVEMENT_SPEED);
        movement.setProgress(progress);

        Command command = buttonHandler.getCommand();
        if (command != null) {
            movement.doStep(buttonHandler.action(command));
        }

        graphicRender.renderMovement(graphicRender.getTiles(), movement);

        if (isEqual(movement.getProgress(), 1f)) {
            // record that the player has reached his/her destination
            movement.getCoordinates().set(movement.getDestinationCoordinates());
        }
    }


    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        graphicRender.getTreeGraphics().getTexture().dispose();
        graphicRender.getTankGraphics().getTexture().dispose();
        graphicRender.getTiles().getLevel().dispose();
        graphicRender.getBatch().dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
