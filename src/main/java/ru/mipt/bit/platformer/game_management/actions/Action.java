package ru.mipt.bit.platformer.game_management.actions;

import ru.mipt.bit.platformer.game_management.commands.Command;

public interface Action {
    public ActionType getType();
    public void initFrom(Command command);
//    void execute();
//    public
}
