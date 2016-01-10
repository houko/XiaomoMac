package com.xiaomo.TimeMachine.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证工具类
 * 
 * @author Code Life
 * @date 2013年7月13日
 * 
 */
public final class RegexUtil {

	/**
	 * 通过正则检测字符串是否匹配 注：不区别大小写
	 * 
	 * @param source
	 * @param regex
	 * @return
	 */
	public static Boolean preg_test(String source, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(source);
		return matcher.matches();
	}

	/**
	 * 通过正则替换字符串 注：不区别大小写
	 * 
	 * @param source
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String preg_replace(String source, String regex, String replacement) {
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(source);
		return matcher.find() ? source.replace(matcher.group(), replacement) : source;
	}

	/**
	 * 通过正则返回匹配的唯一结果 注：不区别大小写
	 * 
	 * @param source
	 * @param regex
	 * @return
	 */
	public static String preg_match(String source, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(source);
		return matcher.find() ? matcher.group() : "";
	}

	/**
	 * 通过正则返回匹配的所有结果 注：不区别大小写
	 * 
	 * @param source
	 * @param regex
	 * @return
	 */
	public static List<String> preg_match_all(String source, String regex) {
		List<String> result = new ArrayList<String>();
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(source);
		while (matcher.find()) {
			result.add(matcher.group());
		}
		return result;
	}

}