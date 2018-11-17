package ru.hutoroff.frolquote.bot;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

class HotWords {
    private static final List<String> HOT_WORDS_PATTERNS = Arrays.asList(
            "[Фф]рол(овичев)?",
            "[Мм]ат(.)?( )?ан(ализ)?",
            "[Мм]атематический анализ",
            "[Яя]блок[ои]",
            "[Мм]андарин(ы)?",
            "[Зз]ач[её]т(ы)?",
            "[Ээ]кзамен(ы)?",
            "[Бб]рат(цы|ец)",
            "[Лл]екци[яи]"
    );

    static Pattern getPatternFroHotWords() {
        return Pattern.compile(String.join("|", HOT_WORDS_PATTERNS));
    }
}
