package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;

public class Tank {
    private Texture texture;

    public Tank(Texture tankTexture) {
        this.texture = tankTexture;
    }

    public Texture getTexture() {
        return texture;
    }
}
