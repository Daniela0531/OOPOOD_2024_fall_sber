package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.GdxGameUtils;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

public class HealthBarDecorator {
    private final int maxHealth;

    public HealthBarDecorator(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void drawHealthBar(Batch batch, TextureRegion livableModelRectangle, int health) {
        TextureRegion healthBarTextureRegion = getHealthBarTexture(health);
        Rectangle rectangle = createRectangle(healthBarTextureRegion);

        GdxGameUtils.drawTextureRegionUnscaled(batch, healthBarTextureRegion, rectangle, 0f);
    }

    private Rectangle createRectangle(TextureRegion livableModelRectangle) {
        Rectangle rectangle = createBoundingRectangle(livableModelRectangle);
        rectangle.y += 90;
        return rectangle;
    }

    private TextureRegion getHealthBarTexture(float health) {
        Pixmap pixmap = new Pixmap(maxHealth, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, maxHealth, 20);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0, 0, (int) (health), 20);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegion(texture);
    }
}