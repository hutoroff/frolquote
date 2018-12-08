package ru.hutoroff.frolquote.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.hutoroff.frolquote.bot.exceptions.UnknownCommandException;
import ru.hutoroff.frolquote.quote.FrolQuotes;
import ru.hutoroff.frolquote.quote.QuoteProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrolBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(FrolBot.class);
    private static final Pattern hotWordsPattern = HotWords.getPatternFroHotWords();
    private static final String HELP_MSG = "Есть только одна команда, братцы: /quote - по ней вы услышите мой голос";

    private final String botUsername;
    private final String botToken;
    private final CommandParser commandParser;
    private final QuoteProvider quoteProvider = new QuoteProvider(new FrolQuotes());

    public FrolBot(String botUsername, String botToken) {
        super();
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.commandParser = new CommandParser(botUsername);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        final String msgText = update.getMessage().getText();
        if (msgText.startsWith("/")) {
            processCommand(update.getMessage());
        } else if (needAnswer(msgText)) {
            answerWithQuote(update.getMessage());
        }
    }

    private boolean needAnswer(String msgText) {
        Matcher matcher = hotWordsPattern.matcher(msgText);
        return matcher.matches();
    }

    private void processCommand(Message message) {
        Command command = parseCommand(message);
        if (command == null) {
            return;
        }

        switch (command) {
            case QUOTE:
                answerWithQuote(message);
                break;
            case HELP:
            case START:
                answerWithHelp(message);
        }
    }

    private Command parseCommand(Message message) {
        String text = message.getText();
        int i = text.indexOf(' ');
        if (i == -1) {
            i = text.length();
        }
        String commandText = text.substring(0, i);

        try {
            return commandParser.parse(commandText);
        } catch (UnknownCommandException e) {
            LOG.warn(String.format("Failed to parse command from text %s", text), e);
        }
        return null;
    }

    private void answerWithQuote(Message message) {
        String quote = quoteProvider.nextQuote();
        SendMessage sendMessage = new SendMessage(message.getChatId(), quote);
        sendReply(sendMessage);
    }

    private void sendReply(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Error on sending reply", e);
        }
    }

    private void answerWithHelp(Message message) {
        SendMessage sendMessage = new SendMessage(message.getChatId(), HELP_MSG);
        sendReply(sendMessage);
    }
}
