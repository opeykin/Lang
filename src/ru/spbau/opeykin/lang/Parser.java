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

    public Parser(List<String> tokens, Map<String, Statement> bindings) {
        tokenIterator = new TokenIterator(tokens);
        this.bindings = bindings;
        String [] termsArray = {"a", "f", "!", "@", "?", "x", ";"};
        terms = Arrays.asList(termsArray);
    }


   public Statement parse() {
        if (tokenIterator.isEmpty()) {
            return null;
        }
        return parseNext();
    }


    private Statement parseNext() {
        while (tokenIterator.hasNext()) {
            String token = tokenIterator.next();
            if (token.equals("a")) {
                return parseApply();
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
                inContext = false;
                return parseNext();
            } else {
                // If we meet identifier like "fact"
                if (inContext) {
                    bindings.put(token, parseNext());
                } else {
                    return bindings.get(token);
                }
            }

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


    private Apply parseApply() {
        return new Apply(parseNext(), parseNext());
    }


    private BinaryOperation parseBinaryOperation() {
        String sign = tokenIterator.next();

        if (sign.equals("+")) {
            return new Plus(parseNext(), parseNext());

        } else if (sign.equals("-")) {
            return new Minus(parseNext(), parseNext());

        } else if (sign.equals("*")) {
            return new Mul(parseNext(), parseNext());

        } else if (sign.equals("/")) {
            return new Div(parseNext(), parseNext());

        } else if (sign.equals("%")) {
            return new Mod(parseNext(), parseNext());

        } else if (sign.equals("<")) {
            return new Less(parseNext(), parseNext());

        } else if (sign.equals("<=")) {
            return new LessEq(parseNext(), parseNext());

        } else if (sign.equals(">")) {
            return new Greater(parseNext(), parseNext());

        } else if (sign.equals(">=")) {
            return new GreaterEq(parseNext(), parseNext());

        } else if (sign.equals("==")) {
            return new Eq(parseNext(), parseNext());

        } else if (sign.equals("!=")) {
            return new NotEq(parseNext(), parseNext());

        }

        //TODO return null
        return null;
    }
}
