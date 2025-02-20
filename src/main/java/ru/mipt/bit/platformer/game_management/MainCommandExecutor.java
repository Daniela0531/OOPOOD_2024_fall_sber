package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.GraphicProperties;
import ru.mipt.bit.platformer.game_management.execution.MovementsExecutor;
import ru.mipt.bit.platformer.game_management.execution.MainGraphicRender;
import ru.mipt.bit.platformer.level.Level;

@Component
public class MainCommandExecutor {
    private MovementsExecutor movementCommandExecuter;
    private MainGraphicRender graphicRender;
    public MainCommandExecutor(GraphicProperties graphicProperties, Level level) {
        this.graphicRender = new MainGraphicRender(graphicProperties, level);
        this.movementCommandExecuter = new MovementsExecutor();
    }

    public void executeAllCommands(
            float deltaTime,
            CommandQueueHandler commandQueueHandler,
            Level level
    ) {
        graphicRender.clear();
        movementCommandExecuter.executeAllMoveCommands(deltaTime, commandQueueHandler, level);
        graphicRender.render(deltaTime, level);

//        for (TankMoveModel tankMoveModel : tanks) {
//            System.out.println(tankMoveModel.getCoordinates());
//            graphicRender.render(deltaTime, tankMoveModel);
//        }
    }

    public void dispose(Level level) {
        graphicRender.dispose(level);
    }
}
