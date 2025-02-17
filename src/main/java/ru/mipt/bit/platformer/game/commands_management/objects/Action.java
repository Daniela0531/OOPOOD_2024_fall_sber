package ru.mipt.bit.platformer.game.commands_management.objects;

public interface Action {
    public ActionType getType();
    public void initFrom(Command command);
//    public
}
