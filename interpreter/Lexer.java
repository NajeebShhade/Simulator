package interpreter;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Lexer {

	private static Lexer lexerInstance = null;

	private Lexer() {
	}
	
	public static Lexer getInstance() {
		if (lexerInstance == null)
			lexerInstance = new Lexer();
		return lexerInstance;
	}

	public String[] lexer(String line) {
		Scanner MyScanner;
		String Index1, Index2;
		StringBuilder builder = new StringBuilder();
		String[] tokens = line.split("(?<=([={}])|(bind))|(?=([={}]))");

		for (String token : tokens) {
			MyScanner = new Scanner(token);
			Index1 = MyScanner.next();
			builder.append(Index1);

			while (MyScanner.hasNext()) {
				Index2 = MyScanner.next();
				if (ExpressionCheck(Index1, Index2))
					builder.append(",");
				builder.append(Index2);
				Index1 = Index2;
			}
			builder.append(",");
		}
		return builder.toString().split(","); //הפרדה בין מלים ב ,
	}

	private boolean ExpressionCheck(String Index1, String Index2) {
		Pattern start = Pattern.compile("[\\w(\"].*"); //בדיקת מילה שמורה
		Pattern end = Pattern.compile(".*[\\w)\"]"); // בדיקת מילה שמורה
		return (end.matcher(Index1).matches() && start.matcher(Index2).matches());
	}
}