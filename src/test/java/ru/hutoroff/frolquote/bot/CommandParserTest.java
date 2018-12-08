package ru.hutoroff.frolquote.bot;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.hutoroff.frolquote.bot.exceptions.UnknownCommandException;

public class CommandParserTest {
    private static final String BOT_USERNAME = "frolquote";
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
    private final CommandParser parser = new CommandParser(BOT_USERNAME);

    @Test
    public void parse_happyPassSimple() throws Exception {
        Command command = parser.parse("/" + Command.QUOTE.toString().toLowerCase());
        Assert.assertEquals(Command.QUOTE, command);
    }

    @Test
    public void parse_happyPassMention() throws Exception {
        Command command = parser.parse("/" + Command.START.toString().toLowerCase() + "@" + BOT_USERNAME);
        Assert.assertEquals(Command.START, command);
    }

    @Test
    public void parse_notCommand() throws Exception {
        Command command = parser.parse(Command.QUOTE.toString().toLowerCase());
        Assert.assertNull("Not command: starts not with '/'", command);
    }

    @Test
    public void parse_notForThisBot() throws Exception {
        Command command = parser.parse("/" + Command.HELP.toString().toLowerCase() + "@anotherBot");
        Assert.assertNull("Not command: another bot mentioned", command);
    }

    @Test
    public void parse_unknownCommand() throws UnknownCommandException {
        expectedException.expect(UnknownCommandException.class);
        expectedException.expectMessage("Command 'wrongCommand' is unknown");

        parser.parse("/wrongCommand@" + BOT_USERNAME);
    }
}