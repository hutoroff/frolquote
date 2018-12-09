package ru.hutoroff.frolquote.bot;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HotWordsTest {
    private final Pattern patternFroHotWords = HotWords.getPatternForHotWords();

    @Test
    public void getPatternFroHotWords_singleWords() {
        Assert.assertTrue(isStringMatching("фрол"));
        Assert.assertTrue(isStringMatching("мат.анализ"));
        Assert.assertTrue(isStringMatching("братцы"));
        Assert.assertTrue(isStringMatching("Братец"));
        Assert.assertTrue(isStringMatching("фроловичева"));
    }

    @Test
    public void getPatternFroHotWords_sentences() {
        Assert.assertTrue(isStringMatching("Братцы, привет!"));
        Assert.assertTrue(isStringMatching("...яблочный пирог..."));
    }

    @Test
    public void getPatternFroHotWords_negative() {
        Assert.assertFalse(isStringMatching("Здесь нет триггеров"));
    }

    private boolean isStringMatching(String str) {
        Matcher matcher = patternFroHotWords.matcher(str);
        return matcher.matches();
    }
}