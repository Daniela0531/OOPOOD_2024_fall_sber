package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private static final float MOVEMENT_SPEED = 0.4f;
    private Batch batch;
    private Level tiles;
    private Tank tank;
    private Movement tankMovement;
    private TreeObstacle treeObstacle;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));

        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        Texture tankTexture = new Texture("images/tank_blue.png");
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        TextureRegion playerGraphics =  new TextureRegion(tankTexture);
        // set player initial position
        GridPoint2 tankCoordinates = new GridPoint2(1, 1);
        tank = new Tank(tankTexture, playerGraphics, createBoundingRectangle(playerGraphics), tankCoordinates);

        tankMovement = new Movement(new GridPoint2(tankCoordinates), 0f);

        Texture texture = new Texture("images/greenTree.png");
        TextureRegion textureRegion = new TextureRegion(texture);
        treeObstacle = new TreeObstacle(texture, textureRegion, new GridPoint2(1, 3), createBoundingRectangle(textureRegion));
        moveRectangleAtTileCenter(groundLayer, treeObstacle.getRectangle(), treeObstacle.getCoordinates());
    }

    private void doStep(GridPoint2 step) {
        if (isEqual(tankMovement.getProgress(), 1f)) {
            // check potential player destination for collision with obstacles
            if (checkNoCollisionWithObstacles(step)) {
                tankMovement.getDestinationCoordinates().y += step.y;
                tankMovement.getDestinationCoordinates().x += step.x;
                tankMovement.setProgress(0f);
            }
            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
            tankMovement.setRotation(newPlayerRotation);
        }
    }

    private boolean checkNoCollisionWithObstacles(GridPoint2 step) {
        GridPoint2 newCoordinates = tank.getCoordinates();
        newCoordinates.x += step.x;
        newCoordinates.y += step.y;
        return !treeObstacle.getCoordinates().equals(newCoordinates);
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            doStep(new GridPoint2(0, 1));
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            doStep(new GridPoint2(-1, 0));
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            doStep(new GridPoint2(0, -1));
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            doStep(new GridPoint2(1, 0));
        }

        // calculate interpolated player screen coordinates
        tiles.getTileMovement().moveRectangleBetweenTileCenters(tank.getRectangle(), tank.getCoordinates(), tankMovement.getDestinationCoordinates(), tankMovement.getProgress());

        tankMovement.setProgress(continueProgress(tankMovement.getProgress(), deltaTime, MOVEMENT_SPEED));
        if (isEqual(tankMovement.getProgress(), 1f)) {
            // record that the player has reached his/her destination
            tank.getCoordinates().set(tankMovement.getDestinationCoordinates());
        }

        // render each tile of the level
        tiles.getLevelRenderer().render();

        // start recording all drawing commands
        batch.begin();

        // render player
        drawTextureRegionUnscaled(batch, tank.getGraphics(), tank.getRectangle(), tankMovement.getRotation());

        // render tree obstacle
        drawTextureRegionUnscaled(batch, treeObstacle.getGraphics(), treeObstacle.getRectangle(), 0f);

        // submit all drawing requests
        batch.end();
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
        treeObstacle.getTexture().dispose();
        tank.getTexture().dispose();
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
