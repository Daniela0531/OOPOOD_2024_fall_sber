package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic_objects.LivableModel;
import ru.mipt.bit.platformer.logic_objects.Model;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class GraphicsForLivable implements GraphicsInterface {
    private Texture texture;
    private TextureRegion textureRegion;
    private Rectangle rectangle;
    private HealthBarDecorator healthBarDecorator;
//    private boolean isHealthBarRase = false;

    public GraphicsForLivable(Texture texture, HealthBarDecorator healthBarDecorator) {
        this.texture = texture;
        this.textureRegion = new TextureRegion(texture);
        this.rectangle = createBoundingRectangle(textureRegion);
        this.healthBarDecorator = healthBarDecorator;
    }
    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }
//    @Override
//    public TextureRegion getTextureRegion() {
//        return textureRegion;
//    }
//    @Override
//    public Texture getTexture() {
//        return texture;
//    }

    @Override
    public void draw(Batch batch, Model model) {
        drawTextureRegionUnscaled(batch, textureRegion, rectangle, model.getRotation());
        if (((LivableModel)model).isHealthBarRaise()) {
            drowHealthBar(batch, (LivableModel)model);
        }
    }

    private void drowHealthBar(Batch batch, LivableModel model) {
        healthBarDecorator.drawHealthBar(batch, textureRegion, model.getMaxHealth(), model.getHealth());
    }
    private void disposeHealthBar() {
        healthBarDecorator.dispose();
    }

    private Rectangle getHealthBarRectangle() {
        return healthBarDecorator.getRectangle();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
