/**
 * $RCSfile: StringUtility.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/24 09:40:11 $
 *
 * Copyright (C) 2003 ICSS, Inc. All rights reserved.
 *
 * This software is the proprietary information of ICSS, Inc.
 * Use is subject to license terms.
 */
package com.icss.oa.zbs.duty.handler;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Title: 信息发布系统2.0（CMS2.0）
 * 
 * Description: 字符串操作常用的方法
 * 
 * Copyright: Copyright (c) 2002-12-5
 * 
 * Company: 中软国际
 * 
 * @version 1.0
 * @author
 */
public class StringUtility {

	/**
	 * 如果是null字符串, 则返回""字符串
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String filterNull(String str) {
		if (str == null) {
			return new String();
		} else {
			return str;
		}
	}

	/**
	 * 如果是null对象, 则返回""字符串
	 * 
	 * @param obj
	 *            Object
	 * @return String
	 */
	public static String filterNullObject(Object obj) {
		if (obj == null) {
			return new String();
		} else {
			return obj.toString();
		}
	}

	/**
	 * 将字符串str中出现的oldStr字符串替换为newStr字符串
	 * 
	 * @param str
	 *            要进行替换操作的字符串
	 * @param oldStr
	 *            原字符串
	 * @param newStr
	 *            替换字符串
	 * @return 替换以后的字符串
	 */
	public static String replace(String str, String oldStr, String newStr) {
		String string = str;
		StringBuffer buf = new StringBuffer();
		int index = 0;
		int idx = string.indexOf(oldStr);
		if (idx == -1) {
			buf.append(string);
		}
		while (idx != -1) {
			buf.append(string.substring(index, idx)).append(newStr);
			index = idx + oldStr.length();
			idx = string.indexOf(oldStr, index);
			if (idx == -1) {
				buf.append(string.substring(index, string.length()));
			}
		}

		return buf.toString();
	}
/**
 *处理搜索结果字符串，高亮显示关键字，并截取特定长度
 *
 * @param str
	 *            要进行替换操作的字符串
	 * @param key
	 *            关键词
	 * @param length
	 *            截取长度
	 * @param color 高亮显示的颜色           
	 * @return 替换以后的字符串
 **/
  	public static String searchStringCut(String str,String key,int length,String color)
  	{
  		str = HtmlToText.htmlTotext(str);
  		int strLength = str.length();
  		StringBuffer replaceStr = new StringBuffer();
  		replaceStr.append("<FONT COLOR='")
  		           .append(color)
  		           .append("'>")
  		           .append(key)
  		           .append("</FONT>");
  			int point = str.indexOf(key);
  			if(point==-1)
  			{
  				return getMoreString(str, length, "");
  			}
  			int halfLength = length/2;;
  			int sp = point-halfLength;
  			int ep = point+halfLength;
  		
	  			if(sp>=0&&ep<=strLength)
	  			{
	  			
	  				return str.substring(sp,ep).replaceAll(key,replaceStr.toString());
	  			}
	  			else if(sp<0&&ep<=strLength)
	  			{
	  		
	  				return str.substring(0,length).replaceAll(key,replaceStr.toString());
	  			}
	  			else if(sp>=0&&ep>strLength)
	  			{
	  			
	  				return str.substring(strLength-length,strLength).replaceAll(key,replaceStr.toString());
	  			}
	  			else
	  			{
	  			
	  				return str.replaceAll(key,replaceStr.toString());
	  			}
  	}
	
	/**
	 * 过滤字符串中的非法字符
	 * 
	 * @param in
	 * @return String
	 */
	public static String escapeHTMLTags(String in) {
		if ((in == null) || (in.length() <= 0)) {
			return "";
		}
		// 注意，可以向_temp1末尾添加转义字符，同时也要向_temp2末尾添加替换字符串，并且要以"|"分隔
		// 对于ASCII码大于127的字符，在添加到_temp中时需要以escape方式表示，如：\u00FF表示ASCII码为255的字符
		String _temp1 = "\u0026\u00A9\u00AE\u2122\"\u003C\u003E";
		String _temp2 = "&amp;|&copy;|&reg;|&trade;|&quot;|&lt;|&gt;";

		// Init vars.
		StringBuffer ret = new StringBuffer();
		String _new = "";

		// 先将in中含有的转义后的字符串转换成一般的转义前字符，这样就会正确转换一般字符，
		// 但是会将用户输入的包含在_temp2中的转义后的字符理解成相应的原字符，
		// 比如，用户输入" "，会转换成"&nbsp;"输出，但是用户输入"&nbsp;"就会被认为是" ".
		int i;
		StringTokenizer st1 = new StringTokenizer(_temp2, "|");
		ArrayList escapeArray = new ArrayList();
		while (st1.hasMoreTokens()) {
			String strToken = st1.nextToken();
			escapeArray.add(strToken);
		}
		/*
		 * for (i = 0; st1.hasMoreTokens(); i++) { in = replace(in,
		 * st1.nextToken(), _temp1.substring(i, i + 1)); }
		 */
		// 进行字符转义
		int _length = in.length();
		for (i = 0; i < _length; i++) {
			char t_char = in.charAt(i);
			int _index = _temp1.indexOf(t_char);
			if (_index < 0) {
				// 不存在待转义字符
				ret.append(t_char);
			} else {
				// 存在待转义字符，到_temp2中去查找相应的替换字符串
				_new = (String) escapeArray.get(_index);
				ret.append(_new);
			}
		}
		return ret.toString();
	}

	public static String filterHtml(Object oIn) {
		// 如果是空对象，返回空字符串
		if (oIn == null) {
			return "";
		}
		String strIn = oIn.toString();
		return escapeHTMLTags(strIn);
	}

	/**
	 * 加密 解密
	 * 
	 * @param toBeProcessed
	 *            String
	 * @param mode
	 *            0 - 加密；其它 - 解密
	 * @return String
	 */
	public static String encrypt(String toBeProcessed, int mode) {
		String ret = new String();
		// 如果是空直接返回
		if ((toBeProcessed == null) || (toBeProcessed.length() <= 0)) {
			return ret;
		}
		// 处理
		int len = toBeProcessed.length();
		int i, j, k, l;
		if (mode == 0) { // 加密
			j = (int) (16 * Math.random() - 16);
			for (i = 1; i <= len; i++) {
				k = j + i % 4;
				ret = String.valueOf((char) (toBeProcessed.charAt(i - 1) + k))
						+ ret;
			}
			ret += String.valueOf((char) (j + 70));
		} else { // 解密
			l = len - 1;
			j = toBeProcessed.charAt(len - 1) - 70;
			for (i = 1; i <= l; i++) {
				k = j + i % 4;
				ret += String.valueOf((char) (toBeProcessed.charAt(l - i) - k));
			}
		}
		return ret;
	}

	/**
	 * 处理字符串中的单引号 一个单引号变为两个单引号 Liang Yong, Feb 28, 2003
	 * 
	 * @return java.lang.String
	 * @param oldValue
	 *            java.lang.String
	 */
	public static String processSingleQuote(String oldValue) {
		// Process single quotes
		String newValue = new String();
		if (oldValue != null) {
			char c;
			for (int i = 0; i < oldValue.length(); i++) {
				c = oldValue.charAt(i);
				if (c == '\'') {
					newValue += c;
				}
				newValue += c;
			}
		}
		return newValue;
	}

	// 需要处理的特殊js字符，以免输出到html页面时出现js错误
	// 注意，“\\”必须放在最前端，因为要处理后面的特殊字符时还要用到它
	private final static String[] jsSpecialChars = { "\\", "\'", "\"" };

	/**
	 * 对输出到js中的特殊字符进行转义处理 如，将\'转换成\\\'
	 * 
	 * @param in
	 * @return
	 */
	public static String escapeJSSpecialChar(String in) {
		String ret = new String();
		if ((in == null) || (in.length() <= 0)) {
			return ret;
		}

		// 把in中的js specail char全部加上\
		ret = in;
		for (int i = 0; i < jsSpecialChars.length; i++) {
			ret = replace(ret, jsSpecialChars[i], "\\" + jsSpecialChars[i]);
		}
		return ret;
	}

	// 单位字符，
	public static final char[] ascchars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', '`', '~', '!', '@', '#', '$', '%', '^', '&', '*',
			'(', ')', '-', '_', '=', '+', '|', '\\', '[', ']', '{', '}', '\'',
			'\"', ';', ':', ',', '.', '<', '>', '/', '?', ' ' };

	/**
	 * 获得截取到指定长度后的字符串，多出部分用指定字符串代替。
	 * 
	 * @param str
	 *            需要限定长度的字符串
	 * @param length
	 *            截取位数,汉字算两位
	 * @param more
	 *            有更多情况的替换字符串
	 * @return 截取后的字符串
	 */
	public static String getMoreString(String str, int length, String more) {
		// length = 100;
		if ((str == null) || str.equals("")) {
			return "";
		} else if(str.length()< length){
			return str;
			}else{
			int len = str.length();
			int curLength = 0;
			StringBuffer buf = new StringBuffer();
			boolean isSingleChar;
			boolean hasMore = false;
			if (more == null) {
				more = "...";
			}
			for (int i = 0; i < len; i++) {
				isSingleChar = false;
				if(length - more.length() <= 0)
				{
					more = "";
				}
				if (curLength < length - more.length()) {
					for (int j = 0; j < ascchars.length; j++) {
						if (str.charAt(i) == ascchars[j]) {
							buf.append(str.charAt(i));
							curLength++;
							isSingleChar = true;
							break;
						}
					}
					if (!isSingleChar) {
						buf.append(str.charAt(i));
						curLength += 2;
					}
				} else {
					hasMore = true;
				}
			}
			if (hasMore) {
				return buf.append(more).toString();
			} else {
				return buf.toString();
			}
		}
	}

	public static String getNextLevel(String maxLevel, int level) {
		String ParentLevel = maxLevel.substring(0, maxLevel.length() - 3);
		String curr = maxLevel.substring(maxLevel.length() - 3, maxLevel
				.length());
		int iNext = Integer.parseInt(curr) + 1;
		return ParentLevel + leftPad(iNext, level, '0');
	}

	/**
	 * 将数字转化为字符串，并格式化为指定的长度，不够位数的前面补指定字符 Input: 1, 5, '0' Output: "00001"
	 * 
	 * @return java.lang.String
	 * @param value -
	 *            要转换的数字
	 * @param number -
	 *            要补齐的位数
	 * @param c
	 *            char - 补充的字符
	 */
	public static String leftPad(int value, int number, char c) {
		String s = "";
		String temp = (new Integer(value)).toString();
		if (temp.length() <= number) {
			for (int i = 0; i < number; i++) {
				s += c;
			}
			s = s.substring(0, number - temp.length()) + temp;
		} else {
//			System.out.println("Error: " + number + "'s length is "
//					+ temp.length() + ", it's bigger than " + number + ".");
		}
		return s;
	}

	/**
	 * 随机生成一个指定长度的整数字符串
	 * 
	 * @return java.lang.String
	 * @param int
	 *            num - 要生成的字符串长度
	 */
	public static String random(int num) {
		String ret = "";
		for (int i = 0; i < num; i++) {
			int randomInt = (int) (java.lang.Math.random() * 10);
			ret = ret.concat(Integer.toString(randomInt));
		}
		return ret;
	}

	/**
	 * 从字符串中得到文件名
	 * 
	 * @return java.lang.String
	 * @param src
	 *            java.lang.String
	 */
	public static String getFileName(String str) {
		if (str == null) {
			return "";
		}
		int idx = str.lastIndexOf("/") + 1;
		if (idx == 0) {
			return "";
		}
		return str.substring(idx);
	}

	/**
	 * 从字符串中得到文件路径
	 * 
	 * @return java.lang.String
	 * @param src
	 *            java.lang.String
	 */
	public static String getFileDir(String str) {
		if (str == null) {
			return "";
		}
		int idx = str.lastIndexOf("/");
		if (idx == 0) {
			return "";
		}
		return str.substring(0, idx);
	}

	/**
	 * 把分割的字符串合成数组
	 * 
	 * @param src
	 * @param delim
	 * @return
	 */
	public static String[] split(String src, String delim) {
		if ((src == null) || (delim == null)) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(src, delim);
		Vector vct = new Vector();
		for (; st.hasMoreTokens(); vct.add(st.nextToken())) {
			;
		}
		Object tks[] = vct.toArray();
		String rt[] = new String[vct.size()];
		System.arraycopy(tks, 0, rt, 0, vct.size());
		return rt;
	}

	public static void main(String args[]) {
		String str = "1．登录社办公信息化系统。 2．在“功能导航区à文件传递”点击“收件箱” 3．进入收件箱邮件列表页面： 　　点击邮件主题，查看该邮件信息。　　4．添加到网络文件夹：如果您查看的邮件有附件，则可以把附件保存到您的网络文件夹中，点击附件对应的“添加到网络文件夹”链接。 　　系统打开网络文件夹选择页面，在该页面中选择要存放的位置。 　　选择存放的文件夹，点击“确定”按钮即可";
		String key = "办公";
		System.out.println(searchStringCut(str,key,20,"red"));
	}

	/**
	 * 过滤字符串中的非法字符,将字符串中的html用字符过滤掉
	 * 
	 * @param in
	 * @return String
	 */
	public static String escapeHTML(String in) {
		if ((in == null) || in.equals("")) {
			return in;
		}
		int length = in.length();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < length; i++) {
			switch (in.charAt(i)) {
			case ' ': // ' '
				tmp.append("&nbsp;");
				break;
			case '<': // '<'
				tmp.append("&lt;");
				break;

			case '>': // '>'
				tmp.append("&gt;");
				break;

			case '"': // '"'
				tmp.append("&quot;");
				break;

			case '&': // '&'
				tmp.append("&amp;");
				break;

			case '\'':
				tmp.append("&quots;");
				break;

			default:
				tmp.append(in.charAt(i));
				break;
			}
		}

		return tmp.toString();
	}

	/**
	 * 过滤字符串中的非法字符,转换为html格式字符
	 * 
	 * @param in
	 * @return String
	 */
	public static final String unescapeHTML(String in) {

		in = replace(in, "&nbsp;", " ");
		in = replace(in, "&lt;", "<");
		in = replace(in, "&gt;", ">");
		in = replace(in, "&quot;", "\"");
		in = replace(in, "&quots;", "\'");
		return replace(in, "&amp;", "&");
	}
	
	public static boolean isEmpty(String str)
	{
		return ((str==null)||((str!=null)&&str.trim().equalsIgnoreCase("")));
	}

}