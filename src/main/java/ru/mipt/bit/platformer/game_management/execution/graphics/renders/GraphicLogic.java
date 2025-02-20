//package ru.mipt.bit.platformer.game_management.execution.graphics;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import ru.mipt.bit.platformer.GraphicProperties;
//import ru.mipt.bit.platformer.Map;
//import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;
//import ru.mipt.bit.platformer.graphics_objects.Graphics;
//import ru.mipt.bit.platformer.level.Level;
//
//import java.util.ArrayList;
//
//import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
//import static ru.mipt.bit.platformer.util.GdxGameUtils.*;
//import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
//
//public class GraphicLogic {
//    private Batch batch;
//    private LevelGraphicRender levelGraphicRender;
//    private Graphics playerGraphics;
//    private ArrayList<Graphics> tanksGraphics;
//    public GraphicLogic(Map map, GraphicProperties graphicProperties) {
//        batch = new SpriteBatch();
//        levelGraphicRender = new LevelGraphicRender(batch, map, graphicProperties);
//
//        TiledMap tiledMap = graphicProperties.getTiledMap();
//
//        Texture tankTexture = graphicProperties.getTankTexture();
//        TextureRegion textureRegion = new TextureRegion(tankTexture);
//        this.tanksGraphics = new ArrayList<>();
//        for(int i = 0; i < map.getTanksCoordinates().size(); ++i) {
//            Graphics tankGraphics = new Graphics(tankTexture, textureRegion);
//            moveRectangleAtTileCenter(getSingleLayer(tiledMap), tankGraphics.getRectangle(), map.getTanksCoordinates().get(i));
//            this.tanksGraphics.add(tankGraphics);
//        }
//
//        this.playerGraphics = new Graphics(tankTexture, textureRegion);
//    }
//    public void render(float deltaTime, TankMoveModel playerTank, ArrayList<TankMoveModel> tankMoveModels) {
//        levelGraphicRender.renderTiles();
//
//        ArrayList<Float> rotations = new ArrayList<>();
//        rotations.add(tankMoveModels.get(0).getRotation());
//        batchRender(playerTank.getRotation(), rotations);
//
//        movementRender(levelGraphicRender.getTiles(), playerTank, playerGraphics);
//    }
//
//    public void batchRender(float playerRotation, ArrayList<Float> rotations) {
//        batch.begin();
//        levelGraphicRender.render(batch);
//        drawTextureRegionUnscaled(batch, playerGraphics.getTextureRegion(), playerGraphics.getRectangle(), playerRotation);
//        drawTextureRegionUnscaled(batch, tanksGraphics.get(0).getTextureRegion(), tanksGraphics.get(0).getRectangle(), rotations.get(0));
//        batch.end();
//    }
//
//    public void movementRender(Level tiles, TankMoveModel node, Graphics graphics) {
//        tiles.getTileMovement().moveRectangleBetweenTileCenters(
//                graphics.getRectangle(),
//                node.getCoordinates(),
//                node.getDestination(),
//                node.getProgress()
//        );
//    }
//
//    public void clear() {
//        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
//        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
//    }
//
//    public void dispose() {
//        levelGraphicRender.dispose();
//        playerGraphics.getTexture().dispose();
//        tanksGraphics.get(0).getTexture().dispose();
//        batch.dispose();
//    }
//}
//
//}
