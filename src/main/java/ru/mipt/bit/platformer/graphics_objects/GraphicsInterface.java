package ru.mipt.bit.platformer.graphics_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

//@Component
public interface GraphicsInterface {
     Rectangle getRectangle();
     TextureRegion getTextureRegion();
     Texture getTexture();
//     GridPoint2 getCoordinates();
//     float getRotation();
}
