package ru.mipt.bit.platformer.objects;

import com.badlogic.gdx.math.GridPoint2;

public class Obstacle {
//    private Texture texture;
//    private TextureRegion graphics;
    private GridPoint2 coordinates = new GridPoint2();
//    private Rectangle rectangle = new Rectangle();

    public Obstacle(GridPoint2 coordinates) {
//        this.texture = texture;
//        graphics = textureRegion;
        this.coordinates = coordinates;
//        this.rectangle = rectangle;
    }

//    public Rectangle getRectangle() {
//        return rectangle;
//    }

//    public TextureRegion getGraphics() {
//        return graphics;
//    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

//    public Texture getTexture() {
//        return texture;
//    }
}
