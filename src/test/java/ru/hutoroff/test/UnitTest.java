package ru.hutoroff.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URL;

public abstract class UnitTest {
    private static final String UPDATES_DIRECTORY = "jsons/updates/";

    protected Update loadUpdate(String fileName) throws IOException {
        URL resource = this.getClass().getClassLoader().getResource(UPDATES_DIRECTORY + fileName);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(resource, Update.class);
    }
}
