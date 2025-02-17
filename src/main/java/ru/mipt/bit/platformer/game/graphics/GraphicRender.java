package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.mipt.bit.platformer.game.level.Level;
import ru.mipt.bit.platformer.game.level.Map;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GraphicRender {
    private Batch batch;
//    private Map map;
    private Level level;
    private LevelAndTreesGraphicRender levelAndTreesGraphicRender;
    private TanksGraphicRender tanksGraphicRender;

    public GraphicRender(Map map) {
        batch = new SpriteBatch();
        levelAndTreesGraphicRender = new LevelAndTreesGraphicRender(batch, map);
        tanksGraphicRender = new TanksGraphicRender(map.getTanksCoordinates());
    }

//    public Batch getBatch() {
//        return batch;
//    }

    public void batchRender(TankMoveModel playerTank) {
//        System.out.println("levelAndTreesGraphicRender");
        levelAndTreesGraphicRender.render(batch);
//        System.out.println("tanksGraphicRender");
        tanksGraphicRender.render(batch, levelAndTreesGraphicRender.getTiles(), playerTank);
    }

    public void render(float deltaTime, TankMoveModel playerTank) {

//        clear();
//        System.out.println("GraphicRender render");

        levelAndTreesGraphicRender.getTiles().getLevelRenderer().render();
//        System.out.println("batch.begin()");

        batch.begin();
//        System.out.println("batchRender");
        batchRender(playerTank);
        batch.end();
//        System.out.println("batch.end()");
    }

    public void clear() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    public void dispose() {
        levelAndTreesGraphicRender.dispose();
        tanksGraphicRender.dispose();
        batch.dispose();
    }
}
