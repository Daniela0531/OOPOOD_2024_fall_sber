package ru.mipt.bit.platformer.game.model;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.game.commands_management.objects.ActionType;
import ru.mipt.bit.platformer.game.model.objects.Direction;
import ru.mipt.bit.platformer.game.model.tank.TankMoveModel;

public class MoveAction {
//    private GridPoint2 vector
    private final ActionType actionType = ActionType.MOVEMENT;
//    private final TankMoveModel tankMoveModel;
    private final Direction direction;
    private final GridPoint2 destinationCoordinates;

    public MoveAction(TankMoveModel tankMoveModel, Direction direction) {
//        this.tankMoveModel = tankMoveModel;
        this.direction = direction;
        this.destinationCoordinates = new GridPoint2(
                tankMoveModel.getCoordinates().x + direction.getVector().x,
                tankMoveModel.getCoordinates().y + direction.getVector().y
        );
    }
    //    @Override
//    public ActionType getType() {
//        return actionType;
//    }
    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public ActionType getActionType() {
        return actionType;
    }

//    public TankMoveModel getTankMoveModel() {
//        return tankMoveModel;
//    }

    public Direction getDirection() {
        return direction;
    }
    //    @Override
//    public void initFrom(Command command) {
//        if (command == Command.UP) {
//            return new GridPoint2(0, 1);
//        }
//        if (command == Command.LEFT) {
//            return new GridPoint2(-1, 0);
//        }
//        if (command == Command.DOWN) {
//            return new GridPoint2(0, -1);
//        }
//        if (command == Command.RIGHT) {
//            return new GridPoint2(1, 0);
//        }
//        return new GridPoint2(0, 0);
//    }
}
