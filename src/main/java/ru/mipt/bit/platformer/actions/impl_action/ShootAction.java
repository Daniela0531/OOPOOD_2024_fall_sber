package ru.mipt.bit.platformer.actions.impl_action;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.logic_objects.DamageDealerModel;
import ru.mipt.bit.platformer.logic_objects.Model;
import ru.mipt.bit.platformer.logic_objects.ShootableModel;
import ru.mipt.bit.platformer.logic_objects.properties.Direction;

public class ShootAction implements Action {
    private final Direction direction;
    private final ShootableModel shootableModel;
    private final DamageDealerModel damageDealerModel;
    private boolean isFinished = false;

    public ShootAction(ShootableModel shootableModel,
                       DamageDealerModel damageDealerModel, Direction direction) {
        this.direction = direction;
        this.shootableModel = shootableModel;
        this.damageDealerModel = damageDealerModel;
    }
    public GridPoint2 getDestinationCoordinates() {
        return new GridPoint2(
                damageDealerModel.getCoordinates().x + direction.getVector().x,
                damageDealerModel.getCoordinates().y + direction.getVector().y);
    }
    @Override
    public Model getModel() {
        return (Model) shootableModel;
    }
    public void execute() {
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void finished() {
        this.isFinished = true;
    }

    public DamageDealerModel getBullet() {
        return damageDealerModel;
    }

}
