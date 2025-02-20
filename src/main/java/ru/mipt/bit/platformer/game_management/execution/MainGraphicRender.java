package ru.mipt.bit.platformer.game_management.execution;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.game_objects.LevelNodeImpl;
import ru.mipt.bit.platformer.game_objects.MoveModel;
import ru.mipt.bit.platformer.game_objects.movable.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

@Component
public class MainGraphicRender {
    private Batch batch;
//    private GraphicProperties graphicProperties;
    private TileMovement tileMovement;
    private TiledMap tiledMap;
    private MapRenderer mapRenderer;
//    private LevelGraphics levelGraphics;
//    private TiledMapTileLayer tileLayer;
//    private Level level;
//    private EnvironmentGraphicRender environmentGraphicRender;
//    private Graphics playerGraphics;
//    private ArrayList<Graphics> tanksGraphics;


    public MainGraphicRender(GraphicProperties graphicProperties, Level level) {
//        this.graphicProperties = graphicProperties;
        this.batch = new SpriteBatch();
        this.tiledMap = graphicProperties.getTiledMap();
        this.tileMovement = new TileMovement(graphicProperties.getTiledMapTileLayer(), Interpolation.smooth);
        this.mapRenderer = createSingleLayerMapRenderer(tiledMap, batch);
//        this.levelGraphics = new LevelGraphics(graphicProperties, map);
//        this.level = level;
//        this.levelGraphicRender = new EnvironmentGraphicRender(batch, level.getMap(), graphicProperties);

//        TiledMap tiledMap = graphicProperties.getTiledMap();
//        groundLayer = getSingleLayer(tiledMap);
//
//        mapRenderer = createSingleLayerMapRenderer(tiledMap, batch);
//        tiles = new Level(tiledMap, new TileMovement(groundLayer, Interpolation.smooth));
//
//        Texture treeTexture = graphicProperties.getTreeTexture();
//        TextureRegion textureRegion = new TextureRegion(treeTexture);
//        this.treeGraphics = new ArrayList<>();
//
        for(LevelNodeImpl levelNode : level.getEnvirenmentNodes()) {
//            Graphics treeGraphic = new Graphics(treeTexture, textureRegion, 0f);
            moveRectangleAtTileCenter(tileMovement.getTileLayer(), levelNode.getGraphics().getRectangle(), levelNode.getGraphics().getCoordinates());
//            this.treeGraphics.add(treeGraphic);
        }

//        TiledMap tiledMap = graphicProperties.getTiledMap();
//
//        Texture tankTexture = graphicProperties.getTankTexture();
//        TextureRegion textureRegion = new TextureRegion(tankTexture);
//        this.tanksGraphics = new ArrayList<>();
//        for(int i = 0; i < map.getTanksCoordinates().size(); ++i) {
//            Graphics tankGraphics = new Graphics(tankTexture, textureRegion, 0f);
//            moveRectangleAtTileCenter(getSingleLayer(tiledMap), tankGraphics.getRectangle(), map.getTanksCoordinates().get(i));
//            this.tanksGraphics.add(tankGraphics);
//        }
//
//        this.playerGraphics = new Graphics(tankTexture, textureRegion, 0f);
    }

//    public void update(Level level) {
//        for
//    }
    public void render(float deltaTime, Level level) {
        mapRenderer.render();
        batchRender(level);

        for (LevelNodeImpl levelNode : level.getMoveNodes()) {
            movementRender(levelNode.getMoveModel(), levelNode.getGraphics().getRectangle());
        }
        for (Map.Entry<BulletMoveModel, Graphics> entry : level.getBullets().entrySet()) {
            movementRender(entry.getKey(), entry.getValue().getRectangle());
        }
        movementRender(level.getPlayerTank().getMoveModel(), level.getPlayerTank().getGraphics().getRectangle());
    }

    public void batchRender(Level level) {
        batch.begin();
        for (LevelNodeImpl levelNode : level.getEnvirenmentNodes()) {
//            System.out.println("arestrdtyfgkhlj;,'" + graphics.getCoordinates());
            drawTextureRegionUnscaled(batch, levelNode.getGraphics().getTextureRegion(), levelNode.getGraphics().getRectangle(), levelNode.getGraphics().getRotation());
        }
        for (LevelNodeImpl levelNode : level.getMoveNodes()) {
            drawTextureRegionUnscaled(batch, levelNode.getGraphics().getTextureRegion(), levelNode.getGraphics().getRectangle(), levelNode.getMoveModel().getRotation());
        }
        for (Map.Entry<BulletMoveModel, Graphics> entry : level.getBullets().entrySet()) {
            drawTextureRegionUnscaled(batch, entry.getValue().getTextureRegion(), entry.getValue().getRectangle(), entry.getValue().getRotation());
        }
        drawTextureRegionUnscaled(batch, level.getPlayerTank().getGraphics().getTextureRegion(), level.getPlayerTank().getGraphics().getRectangle(), level.getPlayerTank().getMoveModel().getRotation());
        batch.end();
    }

    public void movementRender(MoveModel node, Rectangle rectangle) {
        tileMovement.moveRectangleBetweenTileCenters(
                rectangle,
                node.getCoordinates(),
                node.getDestination(),
                node.getProgress()
        );
    }

    public void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void dispose(Level level) {
//        environmentGraphicRender.dispose();
        for(LevelNodeImpl levelNode : level.getEnvirenmentNodes()) {
            levelNode.getGraphics().getTexture().dispose();
        }
        for(LevelNodeImpl levelNode : level.getMoveNodes()) {
            levelNode.getGraphics().getTexture().dispose();
        }

        level.getPlayerTank().getGraphics().getTexture().dispose();
        tiledMap.dispose();
//        playerGraphics.getTexture().dispose();
//        tanksGraphics.get(0).getTexture().dispose();
        batch.dispose();
    }
}
