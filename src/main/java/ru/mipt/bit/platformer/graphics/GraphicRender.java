package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.model.Movement;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GraphicRender {
    private static final float MOVEMENT_SPEED = 0.4f;
    private Batch batch;
    private Level tiles;
    private Graphics treeGraphics;
    private Graphics tankGraphics;
    private TiledMapTileLayer groundLayer;

    public GraphicRender() {
        batch = new SpriteBatch();

        TiledMap level = new TmxMapLoader().load("level.tmx");
        groundLayer = getSingleLayer(level);

        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));


        Texture tankTexture = new Texture("images/tank_blue.png");
        TextureRegion playerGraphics = new TextureRegion(tankTexture);
        this.tankGraphics = new Graphics(tankTexture, playerGraphics);

//        System.out.println("Hello world!");
        Texture texture = new Texture("images/greenTree.png");
        TextureRegion textureRegion = new TextureRegion(texture);
        this.treeGraphics = new Graphics(texture, textureRegion);

//        moveRectangleAtTileCenter(groundLayer, treeGraphics.getRectangle(), treeObstacle.getCoordinates());
    }

    public Graphics getTreeGraphics() {
        return treeGraphics;
    }

    public Graphics getTankGraphics() {
        return tankGraphics;
    }

    public Batch getBatch() {
        return batch;
    }

    public Level getTiles() {
        return tiles;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public void renderMovement(Level tiles, Movement movement) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        tiles.getTileMovement().moveRectangleBetweenTileCenters(
                this.tankGraphics.getRectangle(),
                movement.getCoordinates(),
                movement.getDestinationCoordinates(),
                movement.getProgress()
        );

        tiles.getLevelRenderer().render();

        batch.begin();

        drawTextureRegionUnscaled(batch, this.tankGraphics.getTextureRegion(), this.tankGraphics.getRectangle(), movement.getRotation());

//        System.out.println("treeee Hello world!");
        drawTextureRegionUnscaled(batch, this.treeGraphics.getTextureRegion(), this.treeGraphics.getRectangle(), 0f);

        batch.end();
    }

}
