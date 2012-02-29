package ru.spbau.opeykin.lang;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Alexander Opeykin
 * Date: 3/1/12
 */
public class SourceReader {
    public static List<String> read(String fileName) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> tokens = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s != null) {
                tokens.add(s);
            } else {
                break;
            }
        }
        return tokens;
    }
}
