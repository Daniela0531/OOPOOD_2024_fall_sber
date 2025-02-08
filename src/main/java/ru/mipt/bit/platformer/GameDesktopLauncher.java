package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.objects.*;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;
    private Batch batch;
    private Level tiles;
    private Tank tank;
    private Obstacle treeObstacle;

    private Movement movement;
//    private Graphics treeGraphics;
//    private Graphics tankGraphics;

    private ButtonHandler buttonHandler;

    private GraphicRender graphicRender;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));

        graphicRender = new GraphicRender();
        // set player initial position
        GridPoint2 tankCoordinates = new GridPoint2(1, 1);
        tank = new Tank(tankCoordinates, new GridPoint2(tankCoordinates), 0f);

        treeObstacle = new Obstacle(new GridPoint2(1, 3));

        moveRectangleAtTileCenter(groundLayer, graphicRender.getTreeGraphics().getRectangle(), treeObstacle.getCoordinates());

        movement = new Movement(new GridPoint2(tankCoordinates), 0f, tankCoordinates, treeObstacle);

        buttonHandler = new ButtonHandler();
    }

    @Override
    public void render() {

        float deltaTime = Gdx.graphics.getDeltaTime();

        Command command = buttonHandler.getCommand();
        if (command != null) {
            movement.doStep(buttonHandler.action(command));
        }

        graphicRender.renderMovement(tiles, batch, movement);

        movement.setProgress(continueProgress(movement.getProgress(), deltaTime, MOVEMENT_SPEED));
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
        tiles.getLevel().dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
