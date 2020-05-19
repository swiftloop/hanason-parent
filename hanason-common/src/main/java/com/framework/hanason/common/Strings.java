package com.framework.hanason.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sorata  2018/11/1 11:39
 */
public class Strings {


	private static final Pattern PATTERN_EMOJI = Pattern.compile("([\\x{10000}-\\x{10ffff}\ud800-\udfff])");

	/**
	 * 匹配emoji表情的工具  将表情替换成""
	 *
	 * @param str 需要转化的字符串
	 */
	public static String emojiConvert1(String str) {
		Matcher matcher = PATTERN_EMOJI.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "");
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
