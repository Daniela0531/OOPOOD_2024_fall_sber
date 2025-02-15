package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.model.Movement;

import java.util.ArrayList;

import static ru.mipt.bit.platformer.game.util.GdxGameUtils.drawTextureRegionUnscaled;

//@Component
public class TanksGraphicRender {
    private ArrayList<Graphics> tanksGraphics;

    public TanksGraphicRender(ArrayList<GridPoint2> tanksCoordinates) {
        Texture tankTexture = new Texture("images/tank_blue.png");
        TextureRegion playerGraphics = new TextureRegion(tankTexture);
        this.tanksGraphics = new ArrayList<>();
        for(GridPoint2 tankCoordinates : tanksCoordinates) {
            tanksGraphics.add(new Graphics(tankTexture, playerGraphics));
        }
    }

    public ArrayList<Graphics> getTanksGraphics() {
        return tanksGraphics;
    }

    public void render(Batch batch, Level tiles, Movement movement) {
        for(Graphics tankGraphics : tanksGraphics) {
            drawTextureRegionUnscaled(batch, tankGraphics.getTextureRegion(), tankGraphics.getRectangle(), movement.getRotation());
            tiles.getTileMovement().moveRectangleBetweenTileCenters(
                    tankGraphics.getRectangle(),
                    movement.getCoordinates(),
                    movement.getDestinationCoordinates(),
                    movement.getProgress()
            );
        }
    }

    public void dispose() {
        for(Graphics tankGraphics : tanksGraphics) {
            tankGraphics.getTexture().dispose();
        }
    }
}