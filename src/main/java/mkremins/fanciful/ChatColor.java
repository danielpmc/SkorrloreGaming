package mkremins.fanciful;

import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.collect.ImmutableMap;

public enum ChatColor {
	BLACK('0'), DARK_BLUE('1'), DARK_GREEN('2'), DARK_AQUA('3'), DARK_RED('4'), DARK_PURPLE('5'), GOLD('6'), GRAY('7'), DARK_GRAY('8'), BLUE('9'), GREEN('a'), AQUA('b'), RED('c'), LIGHT_PURPLE('d'), YELLOW('e'), WHITE('f'), MAGIC('k', true), BOLD('l', true), STRIKETHROUGH('m', true), UNDERLINE('n', true), ITALIC('o', true), RESET('r');
	private final char code;
	private final boolean isFormat;
	private final String toString;

	ChatColor(char code) {
		this(code, false);
	}

	ChatColor(char code, boolean isFormat) {
		this.code = code;
		this.isFormat = isFormat;
		this.toString = new String(new char[] { COLOR_CHAR, code });
	}

	public char getChar() {
		return code;
	}

	@Override
	public String toString() {
		return toString;
	}

	public boolean isFormat() {
		return isFormat;
	}

	public boolean isColor() {
		return !isFormat && this != RESET;
	}

	public static final char COLOR_CHAR = '\u00A7';
	private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf(COLOR_CHAR) + "[0-9A-FK-OR]");
	private final static Map<Character, ChatColor> BY_CHAR;
	static {
		ImmutableMap.Builder<Character, ChatColor> byChar = ImmutableMap.builder();
		for (ChatColor color : values()) {
			byChar.put(color.code, color);
		}
		BY_CHAR = byChar.build();
	}

	public static ChatColor getByChar(char code) {
		return BY_CHAR.get(code);
	}

	public static ChatColor getByChar(String code) {
		if (code == null)
			throw new NullPointerException("code");
		if (code.length() <= 0)
			throw new IllegalArgumentException("code must have 1 char");
		return BY_CHAR.get(code.charAt(0));
	}

	public static String stripColor(final String input) {
		if (input == null)
			return null;
		return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
	}
}