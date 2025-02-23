package ru.mipt.bit.platformer.actions.impl_action;

import ru.mipt.bit.platformer.actions.Action;
import ru.mipt.bit.platformer.level.Level;
import ru.mipt.bit.platformer.logic_objects.LivableModel;
import ru.mipt.bit.platformer.logic_objects.Model;

public class SwitchHealthBarAction implements Action {
    private final LivableModel livableModel;
    private boolean isFinished = false;
    public SwitchHealthBarAction(LivableModel livableModel) {
        this.livableModel = livableModel;
        this.isFinished = false;
    }

    @Override
    public Model getModel() {
        return livableModel;
    }
    @Override
    public boolean isFinished() {
        return isFinished;
    }
    @Override
    public void finished() {
        this.isFinished = true;
    }

    @Override
    public void execute(float deltaTime, Level level) {
        livableModel.switchHealthBar();
        finished();
    }

}
