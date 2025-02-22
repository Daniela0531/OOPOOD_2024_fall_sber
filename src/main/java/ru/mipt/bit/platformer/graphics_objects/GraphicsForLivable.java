package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.graphics.HealthBarDecorator;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class GraphicsForLivable implements GraphicsInterface, GraphicsForLivableInterface{
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private HealthBarDecorator healthBarDecorator;

    public GraphicsForLivable(Texture texture) {
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
    public HealthBarDecorator getHealthBarDecorator() {
        return healthBarDecorator;
    }
}
