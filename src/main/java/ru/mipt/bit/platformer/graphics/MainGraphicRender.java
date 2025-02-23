package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.graphics_objects.GraphicsInterface;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level_properties.GraphicProperties;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

@Component
public class MainGraphicRender {
    private Batch batch;
    private TileMovement tileMovement;
    private TiledMap tiledMap;
    private MapRenderer mapRenderer;

    public MainGraphicRender(GraphicProperties graphicProperties, Level level) {
        this.batch = new SpriteBatch();
        this.tiledMap = graphicProperties.getTiledMap();
        this.tileMovement = new TileMovement(graphicProperties.getTiledMapTileLayer(), Interpolation.smooth);
        this.mapRenderer = createSingleLayerMapRenderer(tiledMap, batch);
        for(Map.Entry<TreeMoveModel, GraphicsInterface> entry : level.getTrees().entrySet()) {
            moveRectangleAtTileCenter(tileMovement.getTileLayer(), entry.getValue().getRectangle(), entry.getKey().getCoordinates());
        }
    }

    public void render(float deltaTime, Level level) {
        mapRenderer.render();
        batchRender(level);

        for (Map.Entry<TankMoveModel, GraphicsInterface> entry : level.getTanks().entrySet()) {
            movementRender(entry.getKey(), entry.getValue().getRectangle());
//            if (entry.getKey().isHealthBarRaise()) {
//                entry.getValue().getHealthBarDecorator().drawHealthBar(batch, entry.getKey().getHealth());
//            }
        }
        for (Map.Entry<BulletMoveModel, GraphicsInterface> entry : level.getBullets().entrySet()) {
            movementRender(entry.getKey(), entry.getValue().getRectangle());
        }
        movementRender(level.getPlayerTank(), level.getPlayerGraphics().getRectangle());
    }

    public void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }
    public void dispose(Level level) {
        level.dispose();
        tiledMap.dispose();
        batch.dispose();
    }

    public void batchRender(Level level) {
        batch.begin();
        level.drow(batch);
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
}
