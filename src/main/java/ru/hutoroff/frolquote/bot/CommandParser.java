package ru.hutoroff.frolquote.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hutoroff.frolquote.bot.exceptions.UnknownCommandException;

public class CommandParser {
    private static final Logger LOG = LoggerFactory.getLogger(CommandParser.class);

    private final String botUserName;

    public CommandParser(String botUserName) {
        this.botUserName = botUserName;
    }

    public Command parse(String command) throws UnknownCommandException {
        LOG.trace("Command to parse: '{}'", command);
        if (!command.startsWith("/")) {
            return null;
        }
        String clearCommand = getClearCommandName(command);
        return clearCommand == null ? null : getCommand(clearCommand);
    }

    private String getClearCommandName(String command) {
        int mentionIdx = command.indexOf('@');
        if (mentionIdx > 0) {
            String mentionedBot = command.substring(mentionIdx + 1);
            if (!botUserName.equals(mentionedBot)) {
                return null;
            }
            return command.substring(1, mentionIdx);
        }
        return command.substring(1);
    }

    private Command getCommand(String clearCommand) throws UnknownCommandException {
        try {
            return Command.valueOf(clearCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException(clearCommand);
        }
    }
}
