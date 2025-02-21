package ru.mipt.bit.platformer.game_management;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint2;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.commands.Command;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_objects.bullet.BulletMoveModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;
import ru.mipt.bit.platformer.logic_objects.tank.TankMoveModel;

import static com.badlogic.gdx.Input.Keys.*;

@Component
public class ButtonHandler {
    public void readCommand(CommandQueueHandler receivedCommands, Level level) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
//            ExecuteCommand executeCommand = new ExecuteCommand(Command.UP, GenerationType.BUTTON);
            receivedCommands.add(Command.UP, level);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            receivedCommands.add(Command.LEFT, level);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            receivedCommands.add(Command.DOWN, level);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            receivedCommands.add(Command.RIGHT, level);
        }
        if (Gdx.input.isKeyPressed(SPACE)) {
//            receivedCommands.add(Command.SHOOT, level);
            GridPoint2 coord = level.getPlayerTank().getCoordinates().cpy();
            Direction direction = new Direction(
                    level.getPlayerTank().getRotation());

            BulletMoveModel bulletMoveModel = new BulletMoveModel(
                    new GridPoint2(coord.x + direction.getVector().x, coord.y + direction.getVector().y),
                    direction, direction.getRotation());
            level.createBullet(bulletMoveModel);
            ShootAction shootAction = new ShootAction(
                    (TankMoveModel) level.getPlayerTank(), bulletMoveModel);
            receivedCommands.addAction(shootAction);
        }
    }
}
