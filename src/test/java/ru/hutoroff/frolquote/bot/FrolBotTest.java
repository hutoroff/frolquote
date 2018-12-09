package ru.hutoroff.frolquote.bot;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.hutoroff.test.UnitTest;

public class FrolBotTest extends UnitTest {
    private static final String BOT_NAME = "frolquote_bot";
    private static final String TOKEN = "token";

    @Test
    public void onUpdateReceived_happyPassCommandQuote() throws Exception { // probably not the best way to unit test FrolBot class
        FrolBot frolBot = buildFrolBotForTest();
        Update update = loadUpdate("update_happyPass_quote.json");
        frolBot.onUpdateReceived(update);
        Assert.assertTrue("Succeed", true);
    }

    private FrolBot buildFrolBotForTest() throws Exception {
        FrolBot spy = Mockito.spy(new FrolBot(BOT_NAME, TOKEN));
        Mockito.doReturn(null).when((AbsSender) spy).execute(Mockito.any(SendMessage.class));
        return spy;
    }
}