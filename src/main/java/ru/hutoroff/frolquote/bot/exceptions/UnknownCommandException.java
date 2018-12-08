package ru.hutoroff.frolquote.bot.exceptions;

public class UnknownCommandException extends Exception {
    private final static String MSG_F = "Command '%s' is unknown";

    public UnknownCommandException(String command) {
        super(String.format(MSG_F, command));
    }
}
