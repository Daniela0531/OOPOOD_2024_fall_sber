package ru.mipt.bit.platformer.logic_execution;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.game_management.ExecutingActionsQueue;
import ru.mipt.bit.platformer.level.Level;


@Component
public class LogicExecutor {
    public void executeActions(ExecutingActionsQueue executingActionsQueue, Level level) {
        for (Action action : executingActionsQueue.getActions()) {
            action.execute(level);
        }
    }

}
