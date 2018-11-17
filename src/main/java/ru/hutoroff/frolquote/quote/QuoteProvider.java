package ru.hutoroff.frolquote.quote;

import java.util.concurrent.ThreadLocalRandom;

public class QuoteProvider {
    private final Quotes quotes;

    public QuoteProvider(Quotes quotes) {
        this.quotes = quotes;
    }

    public String nextQuote() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}
