package ru.mipt.bit.platformer.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.level.Level;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

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

    public void render(Batch batch, Level tiles, TankMoveModel playerTank) {
//        System.out.println("befor for");
        Graphics tankGraphics = tanksGraphics.get(0);
//        for(Graphics tankGraphics : tanksGraphics) {
//            System.out.println("drawTextureRegionUnscaled");
//            if (System.out.println("draw");

//        System.out.println("playerTank: " + playerTank.getCoordinates());
        drawTextureRegionUnscaled(batch, tankGraphics.getTextureRegion(), tankGraphics.getRectangle(), playerTank.getDirection().getRotation());
//            System.out.println("getTileMovement");
        if (playerTank.getProgress() < 1f) {
            System.out.println("TanksGraphicRender render: getCoordinates()" + playerTank.getCoordinates() + " getDestination() " + playerTank.getDestination());
            tiles.getTileMovement().moveRectangleBetweenTileCenters(
                    tankGraphics.getRectangle(),
                    playerTank.getCoordinates(),
                    playerTank.getDestination(),
                    playerTank.getProgress()
            );
        }
    }

//    public void render(Batch batch, Level tiles, Direction direction, GridPoint2 playerCoordinates) {
//        for(Graphics tankGraphics : tanksGraphics) {
//            drawTextureRegionUnscaled(batch, tankGraphics.getTextureRegion(), tankGraphics.getRectangle(), direction.getRotation());
//            tiles.getTileMovement().moveRectangleBetweenTileCenters(
//                    tankGraphics.getRectangle(),
//                    playerCoordinates,
//                    movement.getDestinationCoordinates(),
//                    movement.getProgress()
//            );
//        }
//    }

    public void dispose() {
        for(Graphics tankGraphics : tanksGraphics) {
            tankGraphics.getTexture().dispose();
        }
    }
}