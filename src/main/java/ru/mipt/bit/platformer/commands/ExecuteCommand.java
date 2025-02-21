package ru.mipt.bit.platformer.commands;

public class ExecuteCommand {
    public Command command;
    public GenerationType generationType;

    public ExecuteCommand(Command command, GenerationType generationType) {
        this.command = command;
        this.generationType = generationType;
    }

//    public GenerationType getGenerationType() {
//        return generationType;
//    }
}
