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
import com.badlogic.gdx.math.Rectangle;
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
    private TextureRegion playerGraphics;
    private Rectangle playerRectangle;
    private PlayerMovement playerMovement;
    private TreeObstacle treeObstacle;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // load level tiles
        TiledMap level = new TmxMapLoader().load("level.tmx");
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));

        // Texture decodes an image file and loads it into GPU memory, it represents a native resource
        tank = new Tank(new Texture("images/tank_blue.png"));
        // TextureRegion represents Texture portion, there may be many TextureRegion instances of the same Texture
        playerGraphics = new TextureRegion(tank.getTexture());
        playerRectangle = createBoundingRectangle(playerGraphics);

        // set player initial position

        GridPoint2 playerDestinationCoordinates = new GridPoint2(1, 1);
        playerMovement = new PlayerMovement(playerDestinationCoordinates, new GridPoint2(playerDestinationCoordinates), 0f);

        Texture texture = new Texture("images/greenTree.png");
        TextureRegion textureRegion = new TextureRegion(texture);
        treeObstacle = new TreeObstacle(texture, textureRegion, new GridPoint2(1, 3), createBoundingRectangle(textureRegion));
        moveRectangleAtTileCenter(groundLayer, treeObstacle.getTreeObstacleRectangle(), treeObstacle.getTreeObstacleCoordinates());
    }

    private void doStep(GridPoint2 step) {
        if (isEqual(playerMovement.getProgress(), 1f)) {
            // check potential player destination for collision with obstacles
            if (checkNoCollisionWithObstacles(step)) {
                playerMovement.getDestinationCoordinates().y += step.y;
                playerMovement.getDestinationCoordinates().x += step.x;
                playerMovement.setProgress(0f);
            }
            float newPlayerRotation = step.x != 0 ? -90f + step.x * 90f: step.y * 90f;
            playerMovement.setRotation(newPlayerRotation);
        }
    }

    private boolean checkNoCollisionWithObstacles(GridPoint2 step) {
        GridPoint2 newCoordinates = playerMovement.getCoordinates();
        newCoordinates.x += step.x;
        newCoordinates.y += step.y;
        return !treeObstacle.getTreeObstacleCoordinates().equals(newCoordinates);
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
        tiles.getTileMovement().moveRectangleBetweenTileCenters(playerRectangle, playerMovement.getCoordinates(), playerMovement.getDestinationCoordinates(), playerMovement.getProgress());

        playerMovement.setProgress(continueProgress(playerMovement.getProgress(), deltaTime, MOVEMENT_SPEED));
        if (isEqual(playerMovement.getProgress(), 1f)) {
            // record that the player has reached his/her destination
            playerMovement.getCoordinates().set(playerMovement.getDestinationCoordinates());
        }

        // render each tile of the level
        tiles.getLevelRenderer().render();

        // start recording all drawing commands
        batch.begin();

        // render player
        drawTextureRegionUnscaled(batch, playerGraphics, playerRectangle, playerMovement.getRotation());

        // render tree obstacle
        drawTextureRegionUnscaled(batch, treeObstacle.getTreeObstacleGraphics(), treeObstacle.getTreeObstacleRectangle(), 0f);

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
        treeObstacle.getGreenTreeTexture().dispose();
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
