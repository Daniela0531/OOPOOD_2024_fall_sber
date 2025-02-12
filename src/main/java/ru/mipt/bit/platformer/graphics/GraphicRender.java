package ru.mipt.bit.platformer.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.model.Movement;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GraphicRender {
    private Batch batch;
    private LevelAndTreesGraphicRender levelAndTreesGraphicRender;
    private TanksGraphicRender tanksGraphicRender;

    public GraphicRender(ArrayList<GridPoint2> obstacleCoordinates, ArrayList<GridPoint2> tanksCoordinates) {
        batch = new SpriteBatch();
        levelAndTreesGraphicRender = new LevelAndTreesGraphicRender(obstacleCoordinates, batch);
        tanksGraphicRender = new TanksGraphicRender(tanksCoordinates);
    }

    public Batch getBatch() {
        return batch;
    }

    public void render(Movement movement) {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        levelAndTreesGraphicRender.getTiles().getLevelRenderer().render();

        batch.begin();
        levelAndTreesGraphicRender.render(batch);

        tanksGraphicRender.render(batch, levelAndTreesGraphicRender.getTiles(), movement);
        batch.end();
    }

    public void dispose() {
        levelAndTreesGraphicRender.dispose();
        tanksGraphicRender.dispose();
        batch.dispose();
    }
}
