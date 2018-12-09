package ru.hutoroff.frolquote.bot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.hutoroff.frolquote.bot.command.CommandType;
import ru.hutoroff.frolquote.bot.command.CommandTypeParser;
import ru.hutoroff.frolquote.bot.exceptions.UnknownCommandException;

public class CommandTypeParserTest {
    private static final String BOT_USERNAME = "frolquote";
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private final CommandTypeParser parser = new CommandTypeParser(BOT_USERNAME);

    @Test
    public void parse_happyPassSimple() throws Exception {
        CommandType commandType = parser.parse("/" + CommandType.QUOTE.toString().toLowerCase());
        Assert.assertEquals(CommandType.QUOTE, commandType);
    }

    @Test
    public void parse_happyPassMention() throws Exception {
        CommandType commandType = parser.parse("/" + CommandType.START.toString().toLowerCase() + "@" + BOT_USERNAME);
        Assert.assertEquals(CommandType.START, commandType);
    }

    @Test
    public void parse_notCommand() throws Exception {
        CommandType commandType = parser.parse(CommandType.QUOTE.toString().toLowerCase());
        Assert.assertNull("Not command: starts not with '/'", commandType);
    }

    @Test
    public void parse_notForThisBot() throws Exception {
        CommandType commandType = parser.parse("/" + CommandType.HELP.toString().toLowerCase() + "@anotherBot");
        Assert.assertNull("Not command: another bot mentioned", commandType);
    }

    @Test
    public void parse_unknownCommand() throws UnknownCommandException {
        expectedException.expect(UnknownCommandException.class);
        expectedException.expectMessage("Command 'wrongCommand' is unknown");

        parser.parse("/wrongCommand@" + BOT_USERNAME);
    }
}