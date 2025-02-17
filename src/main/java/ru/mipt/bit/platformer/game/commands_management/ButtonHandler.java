package ru.mipt.bit.platformer.game.commands_management;

import com.badlogic.gdx.Gdx;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game.commands_management.objects.Command;
import ru.mipt.bit.platformer.game.commands_management.objects.CommandQueueHandler;

import static com.badlogic.gdx.Input.Keys.*;

@Component
public class ButtonHandler {
//    private final CommandQueue commandQueue;
//
//    public ButtonHandler(CommandQueue commandQueue) {
//        this.commandQueue = commandQueue;
//    }

//    @Autowired
//    public ButtonHandler(CommandQueue commandQueue) {
//        this.commandQueue = commandQueue;
//    }
//    private final BaseLevel level;
//    private final CommandFactory commandFactory;
//    private final Map<InputInstruction, CommandType> accordingTypesMap;


    public void readCommand(CommandQueueHandler receivedCommands) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            receivedCommands.add(Command.UP);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            receivedCommands.add(Command.LEFT);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            receivedCommands.add(Command.DOWN);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            receivedCommands.add(Command.RIGHT);
        }
    }
}
