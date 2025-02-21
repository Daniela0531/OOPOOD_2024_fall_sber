package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.graphics_properties.GraphicProperties;
import ru.mipt.bit.platformer.logic_objects.MoveModel;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;
import ru.mipt.bit.platformer.logic_objects.tree.TreeMoveModel;
import ru.mipt.bit.platformer.graphics_objects.Graphics;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

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
        for(Map.Entry<TreeMoveModel, Graphics> entry : level.getTrees().entrySet()) {
            moveRectangleAtTileCenter(tileMovement.getTileLayer(), entry.getValue().getRectangle(), entry.getKey().getCoordinates());
        }
    }

    public void render(float deltaTime, Level level) {
        mapRenderer.render();
        batchRender(level);

        for (Map.Entry<TankMoveModel, Graphics> entry : level.getTanks().entrySet()) {
            movementRender(entry.getKey(), entry.getValue().getRectangle());
        }
        for (Map.Entry<BulletMoveModel, Graphics> entry : level.getBullets().entrySet()) {
            movementRender(entry.getKey(), entry.getValue().getRectangle());
        }
//        movementRender(level.getPlayerTank(), level.getPlayerTank().getGraphics().getRectangle());
    }

    public void batchRender(Level level) {
        batch.begin();
        for (Map.Entry<TreeMoveModel, Graphics> entry : level.getTrees().entrySet()) {
            drawTextureRegionUnscaled(batch, entry.getValue().getTextureRegion(), entry.getValue().getRectangle(), entry.getKey().getRotation());
        }
        for (Map.Entry<TankMoveModel, Graphics> entry : level.getTanks().entrySet()) {
            drawTextureRegionUnscaled(batch, entry.getValue().getTextureRegion(), entry.getValue().getRectangle(), entry.getKey().getRotation());
        }
        for (Map.Entry<BulletMoveModel, Graphics> entry : level.getBullets().entrySet()) {
            drawTextureRegionUnscaled(batch, entry.getValue().getTextureRegion(), entry.getValue().getRectangle(), entry.getKey().getRotation());
        }
//        drawTextureRegionUnscaled(batch, level.getPlayerTank().getGraphics().getTextureRegion(), level.getPlayerTank().getGraphics().getRectangle(), level.getPlayerTank().getMoveModel().getRotation());
        batch.end();
    }

    public void movementRender(MoveModel node, Rectangle rectangle) {
        if (node instanceof BulletMoveModel) {
            System.out.println("bullet graphics render\n" +
                    "    coord: " + node.getCoordinates() + "\n" +
                    "    dest: " + node.getDestination() + "\n" +
                    "    progres: " + node.getProgress());
        }
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
        for(Graphics graphics : level.getTrees().values()) {
            graphics.getTexture().dispose();
        }
        for(Graphics graphics : level.getTanks().values()) {
            graphics.getTexture().dispose();
        }
        for(Graphics graphics : level.getBullets().values()) {
            graphics.getTexture().dispose();
        }
//        level.getPlayerTank().getGraphics().getTexture().dispose();
        tiledMap.dispose();
        batch.dispose();
    }
}
