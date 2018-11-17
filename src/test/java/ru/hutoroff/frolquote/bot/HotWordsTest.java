package ru.hutoroff.frolquote.bot;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotWordsTest {
    private final Pattern patternFroHotWords = HotWords.getPatternFroHotWords();

    @Test
    public void getPatternFroHotWords() {
        Assert.assertTrue(isStringMatching("фрол"));
        Assert.assertTrue(isStringMatching("мат.анализ"));
        Assert.assertTrue(isStringMatching("братцы"));
        Assert.assertTrue(isStringMatching("Братец"));
    }

    private boolean isStringMatching(String str) {
        Matcher matcher = patternFroHotWords.matcher(str);
        return matcher.matches();
    }
}