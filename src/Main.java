import ru.spbau.opeykin.lang.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
	
	public static void main(String[] args) {

        Map<String, Statement> bindings = new HashMap<String, Statement>();
        /*
        String fileName = "name";
        List<String> tokens = null;
        try {
             tokens = SourceReader.read(fileName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
		*/
		IntegerConstant one = new IntegerConstant(1);

		NameHolder funName = new NameHolder("fact", bindings);
		NameHolder x = new NameHolder("x", bindings);
		
		Statement s = new IF(x, new Mul(x, new Apply(funName, new Minus(x, one))), one);
		
		Fun fun = new Fun(s, x);
		funName.bind(fun);
		//Apply prog = new Apply(one, ten);
		Apply prog = new Apply(fun, new IntegerConstant(3));

		System.out.println(prog.getString());
		System.out.println(prog.evaluate().getString());
	}
}
