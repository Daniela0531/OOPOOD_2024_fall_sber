package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

//@Component
public class Graphics {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    public Graphics(Texture texture, TextureRegion textureRegion) {
        this.texture = texture;
        this.textureRegion = textureRegion;
        this.rectangle = createBoundingRectangle(textureRegion);
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
}
