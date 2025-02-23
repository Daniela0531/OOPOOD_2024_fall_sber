package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class HealthBarDecorator {
    private final int maxHealth;
    private final int height;
    private TextureRegion healthBarTextureRegion;
    private Texture texture;
    private Color existingHealth;
    private Color wastedHealth;
//    private int shiftFromModel = 0;

    public HealthBarDecorator(int maxHealth, int height, Color existingHealth, Color wastedHealth) {
        this.maxHealth = maxHealth;
        this.height = height;
        this.existingHealth = existingHealth;
        this.wastedHealth = wastedHealth;
    }

    public void drawHealthBar(Batch batch, TextureRegion livableModelRectangle, int health) {
        this.healthBarTextureRegion = getHealthBarTexture(health);
        Rectangle rectangle = createBoundingRectangle(livableModelRectangle);

        drawTextureRegionUnscaled(batch, healthBarTextureRegion, rectangle, 0f);
    }

//    private Rectangle createRectangle(TextureRegion livableModelRectangle) {
//        Rectangle rectangle = createBoundingRectangle(livableModelRectangle);
////        rectangle.y += 90;
//        return rectangle;
//    }

    private TextureRegion getHealthBarTexture(int health) {
        Pixmap pixmap = new Pixmap(maxHealth, height, Pixmap.Format.RGBA8888);
//        pixmap.setColor(Color.RED);
//        pixmap.fillRectangle(0, 0, maxHealth, height);
//        pixmap.setColor(Color.GREEN);
//        pixmap.fillRectangle(0, 0, health, height);
        pixmap.setColor(wastedHealth);
        pixmap.fillRectangle(0, 0, maxHealth, height);
        pixmap.setColor(existingHealth);
        pixmap.fillRectangle(0, 0, health, height);

        this.texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegion(texture);
    }

    public void dispose() {
        texture.dispose();
    }
}