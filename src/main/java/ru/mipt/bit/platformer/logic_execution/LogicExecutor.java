package ru.mipt.bit.platformer.logic_execution;

import org.springframework.stereotype.Component;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.actions.impl_action.ShootAction;
import ru.mipt.bit.platformer.game_management.ExecutingActionsQueue;
import ru.mipt.bit.platformer.level.Level;


@Component
public class LogicExecutor {

    public void executeActions(float deltaTime, ExecutingActionsQueue executingActionsQueue, Level level) {
        for (Action action : executingActionsQueue.getActions()) {
            action.execute(deltaTime, level);
        }
    }
    private void printBullet(ShootAction action) {
        System.out.println("bullet\n" +
                "    status is move: " + action.getBullet().isMoving() + "\n" +
                "    shoot from: " + action.getBullet().getCoordinates() + "\n" +
                "    in direction: " + action.getBullet().getDirection().getVector() + "\n" +
                "    progres: " + action.getBullet().getProgress() + "\n" +
                "    destination: " + action.getBullet().getDestination());
    }

}
