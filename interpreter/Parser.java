package interpreter;

import java.util.*;
import command.AssignmentCommand;
import command.BindCommand;
import command.Command;
import command.ConnectCommand;
import command.DisconnectCommand;
import command.OpenServerCommand;
import command.ReturnCommand;
import command.VarCommand;
import command.WhileCommand;

public class Parser {

	private static Parser parserInstance = null;
	private static HashMap<String, Command> MYMAP = new HashMap<String, Command>();
	private static boolean whileMode = false;
	private WhileCommand command = new WhileCommand();

	private Parser() {
	}

	public static Parser getInstance() {
		if (parserInstance == null) {
			parserInstance = new Parser();
			initializeMYMAP();
		}
		return parserInstance;
	}
	 // ממוש מאף לבדוק מילים שמורות בקוד שקיבלנו
	private static void initializeMYMAP() {
		MYMAP.put("=", new AssignmentCommand());
		MYMAP.put("return", new ReturnCommand());
		MYMAP.put("disconnect", new DisconnectCommand());
		MYMAP.put("bind", new BindCommand());
		MYMAP.put("while", new WhileCommand());
		MYMAP.put("openDataServer", new OpenServerCommand());
		MYMAP.put("connect", new ConnectCommand());
		MYMAP.put("var", new VarCommand());

	}

	public void parser(String[] line) {
		String str = "";
		List<String> lineList = new ArrayList<>(Arrays.asList(line));

		ListIterator<String> it = lineList.listIterator();

		while (it.hasNext()) {

			str = it.next();

			if (MYMAP.containsKey(str)) {

				if (whileMode) {
					it.remove();
					command.addCommand(MYMAP.get(str));
					command.addParams(lineList);
				}
				else if (str.equals("=") && it.hasNext() && lineList.get(it.nextIndex()).equals("bind")) {
					it.remove(); // remove the Symbol "="
					it.next(); // get the Symbol "bind"
					it.remove(); // remove the Symbol "bind"
					MYMAP.get("bind").doCommand(lineList);
				}
				else if (str.equals("while")) {
					it.remove(); // remove the Symbol "while"
					whileMode = true;
					command.setCondition(it.next());
				} 
				else {
					it.remove();
					MYMAP.get(str).doCommand(lineList);
				}
			} 
			else if (str.equals("}")) {
				whileMode = false;
				command.doCommand(null);
			}
		}
	}
}