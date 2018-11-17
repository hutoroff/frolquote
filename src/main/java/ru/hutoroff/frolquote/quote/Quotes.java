package ru.hutoroff.frolquote.quote;

import java.util.Iterator;

public interface Quotes {
    int size();

    Iterator<String> iterator();

    String[] toArray();

    String get(int index);
}
