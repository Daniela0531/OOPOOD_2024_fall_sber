package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logic_objects.LivableModel;
import ru.mipt.bit.platformer.logic_objects.Model;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class GraphicsForLivableDecorator implements GraphicsInterface {
    private Graphics graphics;
    private HealthBarDrower healthBarDecorator;

    public GraphicsForLivableDecorator(Texture texture, HealthBarDrower healthBarDecorator) {
        this.graphics = new Graphics(texture);
        this.healthBarDecorator = healthBarDecorator;
    }
    @Override
    public Rectangle getRectangle() {
        return graphics.getRectangle();
    }

    @Override
    public void draw(Batch batch, Model model) {
        drawTextureRegionUnscaled(batch, graphics.getTextureRegion(), graphics.getRectangle(), model.getRotation());
        if (((LivableModel)model).isHealthBarRaise()) {
            drowHealthBar(batch, (LivableModel)model);
        }
    }

    private void drowHealthBar(Batch batch, LivableModel model) {
        healthBarDecorator.drawHealthBar(batch, graphics.getTextureRegion(), model.getMaxHealth(), model.getCurrentHealth());
    }
    private void disposeHealthBar() {
        healthBarDecorator.dispose();
    }

    private Rectangle getHealthBarRectangle() {
        return healthBarDecorator.getRectangle();
    }

    @Override
    public void dispose() {
        graphics.getTexture().dispose();
    }
}
