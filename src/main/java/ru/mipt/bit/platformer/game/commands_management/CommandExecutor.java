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
//        System.out.println("CommandExecutor executeAllCommands");
        graphicRender.clear();
        if (!commandQueueHandler.isEmpty()) {
            movementCommandExecuter.executeAllCommands(deltaTime, commandQueueHandler, playerTank);
            System.out.println(playerTank.getCoordinates());
        }
        graphicRender.render(deltaTime, playerTank);
        commandQueueHandler.pop();
    }

    public void dispose() {
        graphicRender.dispose();
    }
}
