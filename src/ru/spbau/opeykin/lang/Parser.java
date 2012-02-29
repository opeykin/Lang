package ru.spbau.opeykin.lang;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * User: Alexander Opeykin
 * Date: 3/1/12
 */
public class Parser {
    private TokenIterator tokenIterator;
    private boolean inContext = true;
    Map<String, Statement> bindings;

    List<String> terms;

    Parser(List<String> tokens, Map<String, Statement> bindings) {
        tokenIterator = new TokenIterator(tokens);
        this.bindings = bindings;
        String [] termsArray = {"a", "f", "!", "@", "?", "x", ";"};
        terms = Arrays.asList(termsArray);
    }


/*
    boolean hasNext() {
        
    }
*/

    Statement parse() {
        if (tokenIterator.isEmpty()) {
            return null;
        }
        String firstToken = tokenIterator.next();
        inContext = terms.contains(firstToken);
        return parseNext();
    }


    private Statement parseNext() {
        //String [] termsArray = {"a", "f", "!", "@", "?", "x", ";"};
        while (tokenIterator.hasNext()) {
            String token = tokenIterator.next();
            if (token.equals("a")) {

            } else if (token.equals("f")) {
                return parseFunction();
            } else if (token.equals("!")) {
                return parseIntegerConstant();
            } else if (token.equals("@")) {
                return parseBinaryOperation();
            } else if (token.equals("?")) {
                return parseIF();
            } else if (token.equals("x")) {
                return parseNameHolder();
            } else if (token.equals(";")) {

            } else {
                //TODO ????
            }

                /*
            if (inContext) {
                
            }
            */
            
        }
        return null;
    }


    private NameHolder parseNameHolder() {
        return new NameHolder(tokenIterator.next(), bindings);
    }


    private IntegerConstant parseIntegerConstant() {
        return new IntegerConstant(Integer.parseInt(tokenIterator.next()));
    }


    private Fun parseFunction() {
        NameHolder argument = parseNameHolder();
        Statement nextStatement = parseNext();
        return new Fun(nextStatement, argument);
    }
    
    
    private IF parseIF() {
        Statement condition = parseNext();
        Statement trueStatement = parseNext();
        Statement falseStatement = parseNext();
        return new IF(condition, trueStatement, falseStatement);
    }



    private Statement parseBinaryOperation() {
        String sign = tokenIterator.next();

        if (sign.equals("+")) {

        } else if (sign.equals("-")) {

        } else if (sign.equals("*")) {

        } else if (sign.equals("/")) {

        } else if (sign.equals("%")) {

        } else if (sign.equals("<")) {

        } else if (sign.equals("<=")) {

        } else if (sign.equals(">")) {

        } else if (sign.equals(">=")) {

        } else if (sign.equals("==")) {

        } else if (sign.equals("!=")) {

        }

        //TODO return null
        return null;
    }
}
