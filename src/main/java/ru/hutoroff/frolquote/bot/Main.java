package ru.hutoroff.frolquote.bot;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Bot is starting...");
        BotProperties botProperties = parseArgs(args);
        LOG.trace("Bot properties were read successfully");
        ApiContextInitializer.init();
        LOG.trace("Context was initiated");

        TelegramBotsApi botsApi = new TelegramBotsApi();
        LOG.trace("TelegramBotApi created");

        boolean registrationResult = registerBot(botsApi, botProperties);
        if (!registrationResult) {
            return;
        }
        LOG.info("Bot has started successfully!");
    }

    private static BotProperties parseArgs(String[] args) {
        Options options = new Options();

        Option tokenOpt = new Option(BotProperties.OPTION_TOKEN_C, BotProperties.OPTION_TOKEN, true, "bot token");
        tokenOpt.setRequired(true);
        options.addOption(tokenOpt);

        Option usernameOpt = new Option(BotProperties.OPTION_USERNAME_C, BotProperties.OPTION_USERNAME, true, "bot user name");
        tokenOpt.setRequired(true);
        options.addOption(usernameOpt);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine commandLine = parser.parse(options, args);
            return new BotProperties(commandLine);
        } catch (ParseException e) {
            throw new AssertionError("Can not start bot. Arguments were parsed with error", e);
        }
    }

    private static boolean registerBot(TelegramBotsApi botsApi, BotProperties botProperties) {
        try {
            botsApi.registerBot(new FrolBot(botProperties.getBotUsername(), botProperties.getBotToken()));
            LOG.info("Bot '{}' registered successfully", botProperties.getBotUsername());
            return true;
        } catch (TelegramApiRequestException e) {
            LOG.error("Error on bot registration", e);
        }
        return false;
    }
}
