package ua.goit.hw6.command;

public interface Command {
    boolean canExecute(String input);
    void execute(String input);
}
