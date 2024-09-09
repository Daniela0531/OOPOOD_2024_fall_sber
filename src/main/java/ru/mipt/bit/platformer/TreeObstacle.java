package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class TreeObstacle {
    private Texture greenTreeTexture;
    private TextureRegion treeObstacleGraphics;
    private GridPoint2 treeObstacleCoordinates = new GridPoint2();
    private Rectangle treeObstacleRectangle = new Rectangle();

    public TreeObstacle(Texture texture, TextureRegion textureRegion, GridPoint2 coordinates, Rectangle rectangle) {
        greenTreeTexture = texture;
        treeObstacleGraphics = textureRegion;
        treeObstacleCoordinates = coordinates;
        treeObstacleRectangle = rectangle;
    }

    public Rectangle getTreeObstacleRectangle() {
        return treeObstacleRectangle;
    }

    public TextureRegion getTreeObstacleGraphics() {
        return treeObstacleGraphics;
    }

    public GridPoint2 getTreeObstacleCoordinates() {
        return treeObstacleCoordinates;
    }

    public Texture getGreenTreeTexture() {
        return greenTreeTexture;
    }
}
