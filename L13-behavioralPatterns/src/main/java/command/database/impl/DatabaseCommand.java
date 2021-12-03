package command.database.impl;

@FunctionalInterface
public interface DatabaseCommand {
    void execute();
}
