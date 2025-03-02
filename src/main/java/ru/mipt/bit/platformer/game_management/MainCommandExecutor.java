package ru.mipt.bit.platformer.game_management;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.graphics_management.MainGraphicRender;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.level_properties.GraphicProperties;
import ru.mipt.bit.platformer.logic_execution.LogicExecutor;

@Component
public class MainCommandExecutor {
    private LogicExecutor movementCommandExecuter;
    private MainGraphicRender graphicRender;
//    private ExecutingActionsQueue executingActions;
    public MainCommandExecutor(GraphicProperties graphicProperties, Level level) {
        this.graphicRender = new MainGraphicRender(graphicProperties, level);
        this.movementCommandExecuter = new LogicExecutor();
//        this.executingActions = new ExecutingActionsQueue();
    }

    public void executeAllCommands(
            float deltaTime,
            ExecutingActionsQueue executingActions,
            Level level
    ) {
        movementCommandExecuter.executeActions(executingActions, level);
        level.update(deltaTime);

        graphicRender.render(level);

//        level.update(deltaTime);
    }

    public void dispose(Level level) {
        graphicRender.dispose(level);
    }

}
