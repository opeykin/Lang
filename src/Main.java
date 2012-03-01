import ru.spbau.opeykin.lang.Parser;
import ru.spbau.opeykin.lang.ParsingException;
import ru.spbau.opeykin.lang.SourceReader;
import ru.spbau.opeykin.lang.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {

        Map<String, Statement> bindings = new HashMap<String, Statement>();
        String fileName = "name.txt";
        List<String> tokens = null;
        try {
             tokens = SourceReader.read(fileName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        Parser p = new Parser(tokens, bindings);
        Statement s = null;
        try {
            s = p.parse();
        } catch (ParsingException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        System.out.println(s.getString());
        System.out.println(s.evaluate().getString());
	}
}
