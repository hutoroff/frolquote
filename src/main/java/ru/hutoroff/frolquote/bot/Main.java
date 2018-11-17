package ru.hutoroff.frolquote.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOG.info("Bot is starting...");
        ApiContextInitializer.init();
        LOG.trace("Context was initiated");

        TelegramBotsApi botsApi = new TelegramBotsApi();
        LOG.trace("TelegramBotApi created");

        BotProperties botProperties = loadProperties();
        if (botProperties == null) {
            return;
        }
        LOG.trace("Bot properties were read successfully");

        boolean registrationResult = registerBot(botsApi, botProperties);
        if (!registrationResult) {
            return;
        }
        LOG.info("Bot has started successfully!");
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

    private static BotProperties loadProperties() {
        try {
            return new BotProperties();
        } catch (IOException e) {
            LOG.error("Error on loading bot properties. Program will be stopped", e);
        }
        return null;
    }
}
