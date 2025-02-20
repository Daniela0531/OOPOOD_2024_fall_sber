//package ru.mipt.bit.platformer.level;
//
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
//import com.badlogic.gdx.math.Interpolation;
//import org.springframework.stereotype.Component;
//import ru.mipt.bit.platformer.game_objects.LevelNode;
//import ru.mipt.bit.platformer.util.TileMovement;
//
//import java.util.ArrayList;
//
//@Component
//public class Environment {
////    private final Level tiles;
////    private ArrayList<Graphics> treeGraphics;
////    private TiledMap tilesMap; // расположение плиток
////    private TileMovement tileMovement;
////    private TiledMapTileLayer groundLayer;
//    private ArrayList<LevelNode> obstacles;
////    private MapRenderer mapRenderer;
//
//    public Environment(TiledMapTileLayer groundLayer, ArrayList<LevelNode> obstacles) {
//        this.groundLayer = groundLayer;
//        this.obstacles = obstacles;
//
//        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
////        mapRenderer = createSingleLayerMapRenderer(tiledMap, batch);
////        tiles = new Level(tiledMap, new TileMovement(groundLayer, Interpolation.smooth));
//
////        Texture treeTexture = graphicProperties.getTreeTexture();
////        TextureRegion textureRegion = new TextureRegion(treeTexture);
////        this.treeGraphics = new ArrayList<>();
////
////        for(int i = 0; i < map.getObstaclesCoordinatesTrees().size(); ++i) {
////            Graphics treeGraphic = new Graphics(treeTexture, textureRegion, 0f);
////            moveRectangleAtTileCenter(groundLayer, treeGraphic.getRectangle(), map.getObstaclesCoordinatesTrees().get(i));
////            this.treeGraphics.add(treeGraphic);
////        }
//    }
//
//    public ArrayList<LevelNode> getObstacles() {
//        return obstacles;
//    }
//
//    public TiledMapTileLayer getGroundLayer() {
//        return groundLayer;
//    }
//
//    public TileMovement getTileMovement() {
//        return tileMovement;
//    }
//
//    public TiledMap getTilesMap() {
//        return tilesMap;
//    }
//}
