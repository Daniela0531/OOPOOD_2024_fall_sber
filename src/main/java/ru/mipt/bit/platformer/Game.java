package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game_management.ButtonHandler;
import ru.mipt.bit.platformer.game_management.CommandQueueHandler;
import ru.mipt.bit.platformer.game_management.GeneratorActions;
import ru.mipt.bit.platformer.game_management.MainCommandExecutor;
import ru.mipt.bit.platformer.level.Level;

@Component
public class Game {
    private final ButtonHandler buttonHandler;
    private final MainCommandExecutor commandExecutor;
    private final CommandQueueHandler commandQueueHandler;
    private final GeneratorActions generatorActions;
    private final Level level;


    public Game(ButtonHandler buttonHandler,
                MainCommandExecutor commandExecutor,
                CommandQueueHandler commandQueueHandler, GeneratorActions generatorActions, Level level) {
        this.buttonHandler = buttonHandler;
        this.commandQueueHandler = commandQueueHandler;
        this.commandExecutor = commandExecutor;
        this.generatorActions = generatorActions;
        this.level = level;
    }

    public void renderCurrentResultByTick() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        buttonHandler.readCommand(commandQueueHandler, level);
        generatorActions.getCommand(commandQueueHandler, level);
        commandExecutor.executeAllCommands(deltaTime, commandQueueHandler, level);
    }

    public void stop() {
        commandExecutor.dispose(level);
    }
}
