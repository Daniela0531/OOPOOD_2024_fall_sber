package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Graphics implements GraphicsInterface {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    public Graphics(Texture texture) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
    }
    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void draw(Batch batch, float rotation) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, rotation);
    }

}
