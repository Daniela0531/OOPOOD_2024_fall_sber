package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
    private Graphics graphics;
    private GridPoint2 coordinates = new GridPoint2();

    public Obstacle(Texture texture, TextureRegion textureRegion, GridPoint2 coordinates, Rectangle rectangle) {
        this.coordinates = coordinates;
        this.graphics = new Graphics(texture, rectangle, textureRegion);
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void setCoordinates(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }
}
