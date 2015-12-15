package com.example.bajian.leaguesharplogindemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author hgx
 * 313066164@qq.com
 * 2015-6-1
 *
 */
public class StringUtil {
	
	/**
	 * 取文本中间
	 * @param str 原文本
	 * @param left 左边文本
	 * @param right 右边文本
	 * @param mode =1 返回匹配文本包含左右边，其他只返回匹配文本
	 * @return string 返回匹配文本
	 */
	public static String getStringMiddle(String str,String left,String right,int mode) {
		Matcher matcher=Pattern.compile(left+"([\\d\\D]*?)"+right).matcher(str);
		if (matcher.find()) {
			if (mode==1) {
				return matcher.group(0);
			}
			return matcher.group(1);
		}
		return "";
	}

	/**
	 * 取文本中间
	 * @param str 原文本
	 * @param left 左边文本
	 * @param right 右边文本
	 * @return string 返回匹配文本不包含左右边
	 */
	public static String getStringMiddle(String str,String left,String right) {
		return getStringMiddle(str,left,right,0);
	}
}
