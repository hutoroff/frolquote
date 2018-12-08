package ru.hutoroff.frolquote.bot;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class HotWords {
    private static final List<String> HOT_WORDS_PATTERNS = Arrays.asList(
            "([Фф]рол[овиче]*)",
            "([Мм]ат(ематическ)?.*\\.? ?анализ.*)",
            "([Яя]бло.*)",
            "([Мм]андарин.*)",
            "([Зз]ач[её]т.*)",
            "([Ээ]кзамен.*)",
            "([Бб]рат.+)",
            "([Лл]екци.+)"
    );

    static Pattern getPatternForHotWords() {
        return Pattern.compile("^.*(" + String.join("|", HOT_WORDS_PATTERNS) + "){1,}.*$");
    }
}
