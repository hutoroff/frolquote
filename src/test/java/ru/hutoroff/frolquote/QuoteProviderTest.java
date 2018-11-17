package ru.hutoroff.frolquote;

import org.junit.Assert;
import org.junit.Test;
import ru.hutoroff.frolquote.quote.FrolQuotes;
import ru.hutoroff.frolquote.quote.QuoteProvider;
import ru.hutoroff.frolquote.quote.Quotes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuoteProviderTest {
    private final int RUNS = 5;
    private final Quotes quotes = new FrolQuotes();
    private final QuoteProvider quoteProvider = new QuoteProvider(quotes);
    private final ExecutorService executor = Executors.newFixedThreadPool(RUNS);

    @Test
    public void nextQuoteFullness() {
        Set<String> receivedQuotes = new HashSet<>();
        final int size = quotes.size();

        for (int i = 0; i < 1000 || size < receivedQuotes.size(); i++) {
            receivedQuotes.add(quoteProvider.nextQuote());
        }

        Assert.assertEquals("All quotes must be provided", size, receivedQuotes.size());
    }

    @Test
    public void nextQuoteAsync() throws Exception {
        Set<String> randomQuotes = new HashSet<>(RUNS);
        List<Future<String>> futures = new ArrayList<>(RUNS);

        for (int i = 0; i < RUNS; i++) {
            futures.add(executor.submit(new RandomQuoteAsyncProvider()));
        }

        for (Future<String> future : futures) {
            randomQuotes.add(future.get());
        }

        Assert.assertTrue("Quotes must be different", randomQuotes.size() > 1);
    }

    private class RandomQuoteAsyncProvider implements Callable<String> {
        @Override
        public String call() {
            return quoteProvider.nextQuote();
        }
    }
}