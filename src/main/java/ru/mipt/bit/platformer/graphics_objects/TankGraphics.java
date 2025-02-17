package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TankGraphics {
        private Graphics tankGraphics;

    public TankGraphics() {
        Texture tankTexture = new Texture("images/tank_blue.png");
        TextureRegion tankGraphics = new TextureRegion(tankTexture);
        this.tankGraphics = new Graphics(tankTexture, tankGraphics);
    }
}
