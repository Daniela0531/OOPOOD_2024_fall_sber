package ru.mipt.bit.platformer.game.commands_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.game.level.Map;
import ru.mipt.bit.platformer.game.commands_management.objects.CommandQueueHandler;
import ru.mipt.bit.platformer.game.graphics.GraphicRender;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

@Component
public class CommandExecutor {
    private MovementCommandExecutor movementCommandExecuter;
    private GraphicRender graphicRender;
    private CommandQueueHandler commandQueueHandler;
    public CommandExecutor(Map map) {
        this.graphicRender = new GraphicRender(map);
        this.movementCommandExecuter = new MovementCommandExecutor(map);
    }

    public void executeAllCommands(float deltaTime, CommandQueueHandler commandQueueHandler, TankMoveModel playerTank) {
        graphicRender.clear();
        movementCommandExecuter.executeAllCommands(deltaTime, commandQueueHandler, playerTank);
        graphicRender.render(deltaTime, playerTank);
    }

    public void dispose() {
        graphicRender.dispose();
    }
}
