package command.database.impl;

import command.database.Database;

public class UpdateCommand implements DatabaseCommand {
    private final Database database;

    public UpdateCommand(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        database.update();
    }
}
