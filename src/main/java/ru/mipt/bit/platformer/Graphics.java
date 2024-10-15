package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Graphics {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    public Graphics(Texture texture, Rectangle rectangle, TextureRegion textureRegion) {
        this.texture = texture;
        this.rectangle = rectangle;
        this.textureRegion = textureRegion;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
