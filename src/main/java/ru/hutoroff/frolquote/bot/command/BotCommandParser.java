package ru.hutoroff.frolquote.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.hutoroff.frolquote.bot.exceptions.UnknownCommandException;

public class BotCommandParser {
    private static final Logger LOG = LoggerFactory.getLogger(BotCommandParser.class);

    private final String botUsername;
    private final CommandTypeParser commandTypeParser;

    public BotCommandParser(String botUsername) {
        this.botUsername = botUsername;
        this.commandTypeParser = new CommandTypeParser(botUsername);
    }

    public BotCommand parseCommand(Message message) {
        boolean isProcessable = getPossibility(message);
        if (!isProcessable) {
            return new BotCommand(null, false);
        }
        String text = message.getText();
        int i = text.indexOf(' ');
        if (i == -1) {
            i = text.length();
        }
        String commandText = text.substring(0, i);

        CommandType commandType = parseCommandType(commandText);
        if (commandType == null) {
            isProcessable = false;
        }

        return new BotCommand(commandType, isProcessable);
    }

    private boolean getPossibility(Message message) {
        return message.isUserMessage() || message.getText().contains("@" + botUsername);
    }

    private CommandType parseCommandType(String commandText) {
        try {
            return commandTypeParser.parse(commandText);
        } catch (UnknownCommandException e) {
            LOG.warn(String.format("Failed to parse command from text %s", commandText), e);
        }
        return null;
    }
}
