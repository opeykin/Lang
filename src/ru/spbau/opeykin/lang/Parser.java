package ru.spbau.opeykin.lang;

import java.util.ArrayList;
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


    private class UnexpectedTokenException extends ParsingException {
        public UnexpectedTokenException(String message) {
            super(message);
        }
    }


    private class UnexpectedEndOfTokens extends ParsingException {
        public UnexpectedEndOfTokens() {
            super("UnexpectedEndOfTokens");
        }
    }


    public Parser(List<String> tokens, Map<String, Statement> bindings) {
        tokenIterator = new TokenIterator(tokens);
        this.bindings = bindings;
        String [] termsArray = {"a", "f", "!", "@", "?", "x", ";"};
        terms = Arrays.asList(termsArray);
    }




/*
    boolean hasNext() {
        
    }
*/

    public Statement parse() throws ParsingException {
        if (tokenIterator.isEmpty()) {
            return null;
        }

        if (terms.contains(tokenIterator.current())) {
            inContext = false;
        } else {
            inContext = true;
        }

        List<Statement> context = new ArrayList<Statement>();
        while(inContext) {
            context.add(parseNext());
            if (tokenIterator.current().equals(";")) {
                inContext = false;
                tokenIterator.next(); // skip ";"
            }
        }
        return parseNext();
    }


    private Statement parseNext () throws UnexpectedTokenException {

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
        } else if (inContext) {
            return parseContext(token);
        } else {
            throw new UnexpectedTokenException(token);
        }
    }


    private NameHolder parseContext(String name) throws UnexpectedTokenException {
        NameHolder holder = new NameHolder(name, bindings);
        holder.bind(parseNext());
        return holder;
    }


    private NameHolder parseNameHolder() {
        return new NameHolder(tokenIterator.next(), bindings);
    }


    private IntegerConstant parseIntegerConstant() {
        return new IntegerConstant(Integer.parseInt(tokenIterator.next()));
    }


    private Fun parseFunction() throws UnexpectedTokenException {
        NameHolder argument = parseNameHolder();
        Statement nextStatement = parseNext();
        return new Fun(nextStatement, argument);
    }


    private IF parseIF() throws UnexpectedTokenException {
        Statement condition = parseNext();
        Statement trueStatement = parseNext();
        Statement falseStatement = parseNext();
        return new IF(condition, trueStatement, falseStatement);
    }


    private Apply parseApply() throws UnexpectedTokenException {
        return new Apply(parseNext(), parseNext());
    }


    private BinaryOperation parseBinaryOperation() throws UnexpectedTokenException {
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
        } else {
            throw new UnexpectedTokenException("Unknown binary operator sign \"" + sign + "\"");
        }
    }
}
