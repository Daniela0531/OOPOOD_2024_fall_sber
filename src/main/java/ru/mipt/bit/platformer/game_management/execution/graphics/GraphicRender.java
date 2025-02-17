package ru.mipt.bit.platformer.game_management.execution.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
import ru.mipt.bit.platformer.level.Map;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GraphicRender {
    private Batch batch;
    private LevelGraphicRender levelGraphicRender;
    private MovementGraphicRender movementGraphicRender;

    public GraphicRender(Map map, GraphicProperties graphicProperties) {
        batch = new SpriteBatch();
        levelGraphicRender = new LevelGraphicRender(batch, map, graphicProperties);
        movementGraphicRender = new MovementGraphicRender();
    }
    public void render(float deltaTime, TankMoveModel playerTank) {
        levelGraphicRender.getTiles().getMapRenderer().render();
        batchRender(playerTank);
        movementGraphicRender.render(levelGraphicRender.getTiles(), playerTank, levelGraphicRender.getTanksGraphics());
    }
    public void batchRender(TankMoveModel playerTank) {
        batch.begin();
        levelGraphicRender.render(batch, playerTank);
        batch.end();
    }

    public void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void dispose() {
        levelGraphicRender.dispose();
        batch.dispose();
    }
}
