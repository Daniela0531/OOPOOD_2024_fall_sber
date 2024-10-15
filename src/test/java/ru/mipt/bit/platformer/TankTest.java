package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mipt.bit.platformer.util.GdxGameUtils.createBoundingRectangle;

class TankTest {
    @Test
    public void testCreatingTank() {
        Texture tankTexture = new Texture("images/tank_blue.png");
        System.out.println("+");
        TextureRegion playerGraphics = new TextureRegion(tankTexture);
        GridPoint2 tankCoordinates = new GridPoint2(1, 1);
        Model tankFirst = new Model(tankTexture, playerGraphics, createBoundingRectangle(playerGraphics), tankCoordinates, new GridPoint2(tankCoordinates), 0f);
        assertEquals(tankTexture, tankFirst.getGraphics().getTexture());
//        assert (tankFirst.getGraphics().getTextureRegion() == playerGraphics);
//        assert (tankFirst.getGraphics().getRectangle() == createBoundingRectangle(playerGraphics));
//        assert (tankFirst.getCoordinates() == tankCoordinates);
//        assert (tankFirst.getDestinationCoordinates() == tankCoordinates);
//        assert (tankFirst.getDirection().getRotation() == 0);
        //        Tank tankSecond = new Tank(null, null, null, tankCoordinates, tankCoordinates, 0f)
    }
}