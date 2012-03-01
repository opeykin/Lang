package ru.spbau.opeykin.lang;

import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 3/1/12
 */
public class TokenIterator {
    private List<String> tokens;
    private int position = 0;

    public TokenIterator(List<String> tokens) {
        this.tokens = tokens;
    }


    public boolean hasNext() {
        return position < tokens.size();
    }


    String next() {
        return tokens.get(position++);
    }


    String current() {
        return tokens.get(position);
    }


    boolean isEmpty() {
        return tokens.isEmpty();
    }
}
