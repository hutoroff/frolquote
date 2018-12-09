package ru.hutoroff.frolquote.bot.command;

import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.hutoroff.test.UnitTest;

public class BotCommandParserTest extends UnitTest {
    private static final String BOT_NAME = "frolquote_bot";

    private final BotCommandParser parser = new BotCommandParser(BOT_NAME);

    @Test
    public void parseCommand_happyPass() throws Exception {
        Update update = loadUpdate("update_happyPass_quote.json");

        BotCommand botCommand = parser.parseCommand(update.getMessage());
        Assert.assertTrue(botCommand.isProcessable());
        Assert.assertEquals(CommandType.QUOTE, botCommand.getType());
    }

    @Test
    public void parseCommand_notProcessable() throws Exception {
        Update update = loadUpdate("update_happyPass_notProcessable.json");

        BotCommand botCommand = parser.parseCommand(update.getMessage());
        Assert.assertFalse(botCommand.isProcessable());
        Assert.assertNull(botCommand.getType());
    }
}