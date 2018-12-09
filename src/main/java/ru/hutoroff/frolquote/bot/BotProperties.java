package ru.hutoroff.frolquote.bot;

import org.apache.commons.cli.CommandLine;

class BotProperties {
    static final String OPTION_TOKEN = "token";
    static final String OPTION_TOKEN_C = "t";
    static final String OPTION_USERNAME = "username";
    static final String OPTION_USERNAME_C = "u";

    private final CommandLine commandLine;

    BotProperties(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public String getBotUsername() {
        return commandLine.getOptionValue(OPTION_USERNAME);
    }

    public String getBotToken() {
        return commandLine.getOptionValue(OPTION_TOKEN);
    }
}
