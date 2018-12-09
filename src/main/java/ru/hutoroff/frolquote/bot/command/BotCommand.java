package ru.hutoroff.frolquote.bot.command;

public class BotCommand {
    private final CommandType type;
    private final boolean isProcessable;

    public BotCommand(CommandType type, boolean isProcessable) {
        this.type = type;
        this.isProcessable = isProcessable;
    }

    public CommandType getType() {
        return type;
    }

    public boolean isProcessable() {
        return isProcessable;
    }
}
