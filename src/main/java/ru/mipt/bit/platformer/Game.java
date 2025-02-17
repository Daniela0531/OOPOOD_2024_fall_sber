package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.level.Map;
import ru.mipt.bit.platformer.game_management.ButtonHandler;
import ru.mipt.bit.platformer.game_management.MainCommandExecutor;
import ru.mipt.bit.platformer.game_management.CommandQueueHandler;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

@Component
public class Game {
    private final ButtonHandler buttonHandler;
    private final MainCommandExecutor commandExecutor;
    private final CommandQueueHandler commandQueueHandler;
    private final TankMoveModel playerTank;

    private final GraphicProperties graphicProperties;

    public Game(Map map, GraphicProperties graphicProperties, TankMoveModel playerTank) {
        this.buttonHandler = new ButtonHandler();
        this.commandQueueHandler = new CommandQueueHandler();
        this.commandExecutor = new MainCommandExecutor(map, graphicProperties);
        this.playerTank = playerTank;
        this.graphicProperties = graphicProperties;
    }

    public void renderCurrentResultByTick() {
        float deltaTime = Gdx.graphics.getDeltaTime();
//        // System.out.println("buttonHandler:");
        buttonHandler.readCommand(commandQueueHandler, playerTank);
//        // System.out.println("executeAllCommands:");
        commandExecutor.executeAllCommands(deltaTime, commandQueueHandler, playerTank);
//        // System.out.println("commandQueueHandler.pop():");
        commandQueueHandler.pop();
    }

    public void stop() {
        commandExecutor.dispose();
    }
}
