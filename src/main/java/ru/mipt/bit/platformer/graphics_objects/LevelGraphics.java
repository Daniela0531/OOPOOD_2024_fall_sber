package ru.mipt.bit.platformer.graphics_objects;

//@Component
//public class LevelGraphics {
//    private ArrayList<Graphics> treeGraphics;
//    private TiledMapTileLayer groundLayer;
//    private Level tiles;
//    private Graphics tanksGraphics;
//
//
//    public LevelGraphics(Batch batch, Map map) {
//
//        TiledMap level = new TmxMapLoader().load("level.tmx");
//        groundLayer = getSingleLayer(level);
//
//        tiles = new Level(level, createSingleLayerMapRenderer(level, batch), new TileMovement(groundLayer, Interpolation.smooth));
//
//        Texture texture = new Texture("images/greenTree.png");
//        TextureRegion textureRegion = new TextureRegion(texture);
//        this.treeGraphics = new ArrayList<>();
//
//        for(int i = 0; i < map.getObstaclesCoordinates().size(); ++i) {
//            Graphics treeGraphic = new Graphics(texture, textureRegion);
//            moveRectangleAtTileCenter(groundLayer, treeGraphic.getRectangle(), map.getObstaclesCoordinates().get(i));
//            this.treeGraphics.add(treeGraphic);
//        }
//
//        Texture tankTexture = new Texture("images/tank_blue.png");
//        TextureRegion tankGraphics = new TextureRegion(tankTexture);
//        this.tanksGraphics = new Graphics(tankTexture, tankGraphics);
//    }
//
//    public Graphics getTanksGraphics() {
//        return tanksGraphics;
//    }
//
//    public Level getTiles() {
//        return tiles;
//    }
//
//
//    public void dispose() {
//        for(Graphics treeGraphic : treeGraphics) {
//            treeGraphic.getTexture().dispose();
//        }
//        tiles.getLevel().dispose();
//    }
//}
