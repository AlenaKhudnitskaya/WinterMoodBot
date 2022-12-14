package com.github.AlenaKhudnitskaya.WinterMoodBot.command;

public enum CommandName {

    START("/start"),
    HELP("/help"),
    NO("no command"),
    RA("/random"),
    STOP("/stop");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
