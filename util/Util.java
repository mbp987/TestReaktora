package util;

public class Util {

	public static final char ESCAPE_CHAR = '\\';

	/**
	 * Metoda podmienia w Stringu str "%" na "\%", "_" na "\_", "*" na "%", "?"
	 * na "_" w celu umozliwienia poslugiwania sie symbolami wieloznacznymi "*"
	 * i "?" w wyszukiwaniu.
	 * 
	 * @param str
	 * @return
	 */
	public static final String convert(String str) {
		if (str == null) {
			return null;
		}

		char[] c = str.toCharArray();
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '%' || c[i] == '_') {
				out.append(ESCAPE_CHAR);
				out.append(c[i]);
			} else if (c[i] == ESCAPE_CHAR) {
				out.append(ESCAPE_CHAR);
				out.append(c[i]);
			} else if (c[i] == '*') {
				out.append('%');
			} else if (c[i] == '?') {
				out.append('_');
			} else {
				out.append(c[i]);
			}
		}
		return out.toString();
	}

}
