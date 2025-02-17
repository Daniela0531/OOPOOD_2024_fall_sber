package ru.mipt.bit.platformer.game_management;

import com.badlogic.gdx.Gdx;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_management.commands.Command;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

import static com.badlogic.gdx.Input.Keys.*;

@Component
public class ButtonHandler {
    public void readCommand(CommandQueueHandler receivedCommands, TankMoveModel playerTank) {
//        // System.out.println("ButtonHandler readCommand");
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            // System.out.println("    catch command");
            receivedCommands.add(Command.UP, playerTank);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            // System.out.println("    catch command");
            receivedCommands.add(Command.LEFT, playerTank);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            // System.out.println("    catch command");
            receivedCommands.add(Command.DOWN, playerTank);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            // System.out.println("    catch command");
            receivedCommands.add(Command.RIGHT, playerTank);
        }
//        // System.out.println("dont catch commmand");
    }
}
