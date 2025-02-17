package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.game_management.execution.MovementsExecutor;
import ru.mipt.bit.platformer.level.Map;
import ru.mipt.bit.platformer.game_management.execution.graphics.GraphicRender;
import ru.mipt.bit.platformer.game_objects.movable.tank.TankMoveModel;

@Component
public class MainCommandExecutor {
    private MovementsExecutor movementCommandExecuter;
    private GraphicRender graphicRender;
    public MainCommandExecutor(Map map, GraphicProperties graphicProperties) {
        this.graphicRender = new GraphicRender(map, graphicProperties);
        this.movementCommandExecuter = new MovementsExecutor(map);
    }

    public void executeAllCommands(float deltaTime, CommandQueueHandler commandQueueHandler, TankMoveModel playerTank) {
        graphicRender.clear();
        movementCommandExecuter.executeAllMoveCommands(deltaTime, commandQueueHandler, playerTank);
        graphicRender.render(deltaTime, playerTank);
    }

    public void dispose() {
        graphicRender.dispose();
    }
}
