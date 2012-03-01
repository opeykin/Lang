import ru.spbau.opeykin.lang.Parser;
import ru.spbau.opeykin.lang.SourceReader;
import ru.spbau.opeykin.lang.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, Statement> bindings = new HashMap<String, Statement>();

        if (args.length == 0) {
            System.err.println("Usage: prog imput_file_name");
            System.exit(1);
        }
        String fileName = args[0];
        List<String> tokens = null;
        try {
            tokens = SourceReader.read(fileName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        Parser p = new Parser(tokens, bindings);
        Statement st = p.parse();
        System.out.println(st.evaluate().getString());
    }
}
