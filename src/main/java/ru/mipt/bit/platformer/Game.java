package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game.level.Map;
import ru.mipt.bit.platformer.game.commands_management.ButtonHandler;
import ru.mipt.bit.platformer.game.commands_management.CommandExecutor;
import ru.mipt.bit.platformer.game.commands_management.objects.CommandQueueHandler;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

@Component
public class Game {
    private final ButtonHandler buttonHandler;
    private final CommandExecutor commandExecutor;
    private final CommandQueueHandler commandQueue;
    private final TankMoveModel playerTank;

    public Game(ButtonHandler buttonHandler, Map map, TankMoveModel playerTank) {
        this.buttonHandler = buttonHandler;
        this.playerTank = playerTank;
        this.commandQueue = new CommandQueueHandler();
        this.commandExecutor = new CommandExecutor(map);
    }

    public void renderCurrentResultByTick() {
//        System.out.println("Game renderCurrentResultByTick");
        float deltaTime = Gdx.graphics.getDeltaTime();
        buttonHandler.readCommand(commandQueue, playerTank);
//        if (!commandQueue.isEmpty()) {
//            System.out.println("hey");
//        }
        commandExecutor.executeAllCommands(deltaTime, commandQueue, playerTank);
    }

    public void stop() {
//        System.out.println("Game stop");
        commandExecutor.dispose();
    }
}
