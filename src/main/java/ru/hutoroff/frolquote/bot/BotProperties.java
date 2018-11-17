package ru.hutoroff.frolquote.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads properties required for bot work form file 'resources/bot.secured.properties"
 */
public class BotProperties {
    private static final String PROPERTIES_FILENAME = "bot.secured.properties";

    private final Properties properties = new Properties();

    public BotProperties() throws IOException {
        /*BotProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);
        URL url = getClass().getClassLoader().getResource(PROPERTIES_FILENAME);
        if (url == null) {
            throw new FileNotFoundException(String.format("Properties file '%s' not found", PROPERTIES_FILENAME));
        }*/
        InputStream is = BotProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILENAME);//new FileInputStream(url.getFile());
        properties.load(is);
    }

    public String getBotUsername() {
        return properties.getProperty("username");
    }

    public String getBotToken() {
        return properties.getProperty("token");
    }
}
