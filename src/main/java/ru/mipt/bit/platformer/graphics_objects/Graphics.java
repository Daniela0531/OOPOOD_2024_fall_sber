package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

//@Component
public class Graphics {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private GridPoint2 coordinates;
    private float rotation;
//    private float rotation;

    public Graphics(Texture texture, GridPoint2 coordinates, float rotation) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
        this.coordinates = coordinates;
        this.rotation = rotation;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Texture getTexture() {
        return texture;
    }
    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    //    public float getRotation() {
//        return rotation;
//    }

//    public void setRotation(float rotation) {
//        this.rotation = rotation;
//    }
}
