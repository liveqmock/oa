/**
 * $RCSfile: StringUtility.java,v $
 * $Revision: 1.1 $
 * $Date: 2008/07/24 09:40:02 $
 *
 * Copyright (C) 2003 ICSS, Inc. All rights reserved.
 *
 * This software is the proprietary information of ICSS, Inc.
 * Use is subject to license terms.
 */
package com.icss.oa.util;  

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * <p>Title: OA系统</p>
 * <p>Description: 字符串操作常用的方法</p>
 * <p>Copyright: Copyright (c) 2002-12-5</p>
 * <p>Company: 中软远东国际</p>
 *
 * @version 1.0
 * @author Andy
 */
public class StringUtility {
	
	public final static int NOT_FILL = 0;
	public final static int NORMAL_FILL = 1;
	public final static int HTML_FILL = 2;	
	
    /**
     * 如果是null字符串, 则返回""字符串
     *
     * @param str String
     * @return String
     */
    public static String filterNull(String str) {
        if (str == null) {
            return new String();
        }
        else {
            return str;
        }
    }

    /**
     * 如果是null对象, 则返回""字符串
     *
     * @param obj Object
     * @return String
     */
    public static String filterNullObject(Object obj) {
        if (obj == null) {
            return new String();
        }
        else {
            return obj.toString();
        }
    }

    /**
     * 将字符串str中出现的oldStr字符串替换为newStr字符串
     *
     * @param str 要进行替换操作的字符串
     * @param oldStr 原字符串
     * @param newStr 替换字符串
     * @return 替换以后的字符串
     */
    public static String replace(String str, String oldStr, String newStr) {
        String string = str;
        StringBuffer buf = new StringBuffer();
        int index = 0;
        int idx = string.indexOf(oldStr);
        if (idx == -1)
            buf.append(string);
        while (idx != -1) {
            buf.append(string.substring(index, idx)).append(newStr);
            index = idx + oldStr.length();
            idx = string.indexOf(oldStr, index);
            if (idx == -1)
                buf.append(string.substring(index, string.length()));
        }

        return buf.toString();
    }

    /**
     * 过滤字符串中的非法字符
     *
     * @param in
     * @return String
     */
    public static String escapeHTMLTags(String in) {
        if (in == null || in.length() <= 0) {
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
        for (i = 0; st1.hasMoreTokens(); i++) {
          in = replace(in, st1.nextToken(), _temp1.substring(i, i + 1));
        }
        */
        // 进行字符转义
        int _length = in.length();
        for (i = 0; i < _length; i++) {
            char t_char = in.charAt(i);
            int _index = _temp1.indexOf(t_char);
            if (_index < 0) {
                // 不存在待转义字符
                ret.append(t_char);
            }
            else {
                // 存在待转义字符，到_temp2中去查找相应的替换字符串
                _new = (String)escapeArray.get(_index);
                ret.append(_new);
            }
        }
        return ret.toString();
    }

    public static String filterHtml(Object oIn) {
        //如果是空对象，返回空字符串
        if (oIn == null) {
            return "";
        }
        String strIn = oIn.toString();
        return escapeHTMLTags(strIn);
    }
    /**
     * 加密 解密
     *
     * @param toBeProcessed String
     * @param mode 0 - 加密；其它 - 解密
     * @return String
     */
    public static String encrypt(String toBeProcessed, int mode) {
        String ret = new String();
        // 如果是空直接返回
        if (toBeProcessed == null || toBeProcessed.length() <= 0) {
            return ret;
        }
        // 处理
        int len = toBeProcessed.length();
        int i, j, k, l;
        if (mode == 0) { // 加密
            j = (int) (16 * Math.random() - 16);
            for (i = 1; i <= len; i++) {
                k = j + i % 4;
                ret = String.valueOf((char) (toBeProcessed.charAt(i - 1) + k)) + ret;
            }
            ret += String.valueOf((char) (j + 70));
        }
        else { // 解密
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
     * 处理字符串中的单引号
     * 一个单引号变为两个单引号
     * Liang Yong, Feb 28, 2003
     *
     * @return java.lang.String
     * @param oldValue java.lang.String
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
     * 对输出到js中的特殊字符进行转义处理
     * 如，将\'转换成\\\'
     *
     * @param in
     * @return
     */
    public static String escapeJSSpecialChar(String in) {
        String ret = new String();
        if (in == null || in.length() <= 0) {
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
    public static final char[] ascchars =
        {
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z',
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            '0',
            '`',
            '~',
            '!',
            '@',
            '#',
            '$',
            '%',
            '^',
            '&',
            '*',
            '(',
            ')',
            '-',
            '_',
            '=',
            '+',
            '|',
            '\\',
            '[',
            ']',
            '{',
            '}',
            '\'',
            '\"',
            ';',
            ':',
            ',',
            '.',
            '<',
            '>',
            '/',
            '?' };

	/**
	 * 获得截取到指定长度后的字符串，多出部分用指定字符串代替。
	 *
	 * @param str 需要限定长度的字符串
	 * @param length 截取位数,汉字算两位
	 * @param more 有更多情况的替换字符串
	 * @param fillchar 字符不足时的填充方式
	 * @return 截取后的字符串
	 */
	public static String getMoreString(String str, int length, String more) {
		return getMoreString(str, length, more, NOT_FILL);
	}
            
    /**
     * 获得截取到指定长度后的字符串，多出部分用指定字符串代替。
     *
     * @param str 需要限定长度的字符串
     * @param length 截取位数,汉字算两位
     * @param more 有更多情况的替换字符串
     * @param fillchar 字符不足时的填充方式
     * @return 截取后的字符串
     */
    public static String getMoreString(String str, int length, String more, int  filltype) {
        if (str == null || str.equals(""))
            return "";
        else {
            int len = str.length();
            int curLength = 0;
            StringBuffer buf = new StringBuffer();
            boolean isSingleChar;
            boolean hasMore = false;
            for (int i = 0; i < len; i++) {
                isSingleChar = false;
                if (curLength < length) {
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
                }
                else
                    hasMore = true;
            }
            if (more == null) {
                more = "...";
            }
            if (hasMore){
            	if(getLengthOfString(buf.toString())-length==1){
            		return buf.append(".").toString();
            	}
            	else{
            		return buf.append(more).toString();
            	}
				
				
            }
            else {
            	int bufLen=getLengthOfString(buf.toString());
            	           	
            	switch(filltype){
            		case NORMAL_FILL:
						for(int i=0;i<2+length - bufLen; i++){
							buf.append(" ");
						}
					case HTML_FILL:
						for(int j=0;j<2+length - bufLen; j++){
							buf.append("&nbsp;");
						}
            		default : 
						//do nothing;
            	}
				return buf.toString();
            }
            
        }
    }


    public static String getNextLevel(String maxLevel, int level) {
        String ParentLevel = maxLevel.substring(0, maxLevel.length() - 3);
        String curr = maxLevel.substring(maxLevel.length() - 3, maxLevel.length());
        int iNext = Integer.parseInt(curr) + 1;
        return ParentLevel + leftPad(iNext, level, '0');
    }

    /**
     * 将数字转化为字符串，并格式化为指定的长度，不够位数的前面补指定字符
     * Input: 1, 5, '0'
     * Output: "00001"
     *
     * @return java.lang.String
     * @param value - 要转换的数字
     * @param number - 要补齐的位数
     * @param c char - 补充的字符
     */
    public static String leftPad(int value, int number, char c) {
        String s = "";
        String temp = (new Integer(value)).toString();
        if (temp.length() <= number) {
            for (int i = 0; i < number; i++) {
                s += c;
            }
            s = s.substring(0, number - temp.length()) + temp;
        }
        else {
            System.out.println("Error: " + number + "'s length is " + temp.length() + ", it's bigger than " + number + ".");
        }
        return s;
    }

    /**
     * 随机生成一个指定长度的整数字符串
     *
     * @return java.lang.String
     * @param int num - 要生成的字符串长度
     */
    public static String random(int num) {
        String ret = "";
        for (int i = 0; i < num; i++) {
            int randomInt = (int) ((java.lang.Math.random()) * 10);
            ret = ret.concat(Integer.toString(randomInt));
        }
        return ret;
    }

    /**
     * 从字符串中得到文件名
     *
     * @return java.lang.String
     * @param src java.lang.String
     */
    public static String getFileName(String str) {
        if (str == null)
            return "";
        int idx = str.lastIndexOf("/") + 1;
        if (idx == 0)
            return "";
        return str.substring(idx);
    }

    /**
     * 从字符串中得到文件路径
     *
     * @return java.lang.String
     * @param src java.lang.String
     */
    public static String getFileDir(String str) {
        if (str == null)
            return "";
        int idx = str.lastIndexOf("/");
        if (idx == 0)
            return "";
        return str.substring(0, idx);
    }
    /**
     * 把分割的字符串合成数组
     * @param src
     * @param delim
     * @return
     */
    public static String[] split(String src, String delim) {
        if (src == null || delim == null)
            return null;
        StringTokenizer st = new StringTokenizer(src, delim);
        Vector vct = new Vector();
        for (; st.hasMoreTokens(); vct.add(st.nextToken()));
        Object tks[] = vct.toArray();
        String rt[] = new String[vct.size()];
        System.arraycopy(((Object) (tks)), 0, rt, 0, vct.size());
        return rt;
    }
    
    /**
     * 判断给定字符串是否为空(add in 2003.11.20@author windy)
     *
     * @param str 需要检查的字符串
     * @return 如果为null或""，则返回true,否则为false
     */
    public static boolean isNullorBlank(final String str){
        boolean isNorB = false;
        if(null == str || 0 >= str.length()){
            isNorB = true;
        }
        return isNorB;
    }//end isNullorBlank()
    /**
     * convert string to int(add in 2003.11.20@author windy)
     * @param String StringNum
     * @return Int;if it can be converted retrun intNum,or return -1
     */
    public static int toInt(String StringNum){
        try
        {
            return Integer.parseInt(StringNum);
        }
        catch(Exception e)
        {return -1;}
    }//end toInt(String)

    /**
     * 查看源数据中是否包含后面的数据(add in 2003.11.21@author windy)
     * @param String source
     * @param String pattern
     * @return boolean;if contains retrun true,or return false
     */    
    public static boolean contains(String source, String pattern)
    {
        if(source == null || pattern == null)
            return false;
        source = "," + source.trim() + ",";
        pattern = "," + pattern.trim() + ",";
        return source.indexOf(pattern) != -1;
    }//eof contains(String,String)
    
    /**
     * 将给定的字符串转换为中文GBK编码的字符串;若为NULL，转换成空 ("")(add in 2003.11.21@author windy)
     *
     * @param str 输入字符串
     * @return 经GBK编码后的字符串，如果有异常，则返回原编码字符串
     */
    public static String toChinese(final String str){
        if(isNullorBlank(str)){
            return "";
        }
        String rn = str;
        try{
            rn = new String(str.getBytes("ISO8859_1"), "GBK");
        } catch(Exception e){
            //log...
        }
        return rn.trim();
    }//end toGb2312(String)

    /**
     * 将给定的中文GBK字符串转换为ISO编码的字符串;若为NULL，转换成空 ("")(add in 2003.11.21@author windy)
     *
     * @param str 输入字符串
     * @return 经GBK编码后的字符串，如果有异常，则返回原编码字符串
     */
    public static String toIso(final String str){
        if(isNullorBlank(str)){
            return "";
        }
        String rn = str;
        try{
            rn = new String(str.getBytes("GBK"), "ISO8859_1");
        } catch(Exception e){
            //log...
        }
        return rn.trim();
    }//end toIso(String)
    
    /**
     * 处理字符串中的单引号
     * 一个单引号变为两个单引号
     *
     * @return java.lang.String
     * @param oldValue java.lang.String
     */
    public static String processSingleQuotes(String oldValue) {
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
 

//    public static void main(String args[]) {
//        String str = "我是abc啊hehe";
//        System.out.println("getMoreString( \"" + str + "\", 7, \"...\" ) = " + getMoreString(str, 7, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 8, \"...\" ) = " + getMoreString(str, 8, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 9, \"...\" ) = " + getMoreString(str, 9, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 10, \"...\" ) = " + getMoreString(str, 10, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 50, \"...\" ) = " + getMoreString(str, 50, "..."));
//
//        //Test random()
//        System.out.println("the method random(10)=" + StringUtility.random(10));
//    }
    //判断一个字符串的长度,汉字算两个字节
    
    public static int getLengthOfString(String str) {
        if (str == null || str.equals(""))
            return 0;
        else {
            int len = str.length();
            int curLength = 0;
            StringBuffer buf = new StringBuffer();
            boolean isSingleChar;
            boolean hasMore = false;
            for (int i = 0; i < len; i++) {
                isSingleChar = false;
                
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
               
            }
            
            return curLength;
        }
    }
    
	     /**
		 * 将全角字符变为全角字符
		 *
		 * @return boolean [yes or no]
		 * @param str java.lang.String 被检验的字符串
		 */
	
		public static String CtoB(String str){
		
			String control []
			={  "０", 
				"１", 
				"２", 
				"３", 
				"４", 
				"５", 
				"６", 
				"７", 
				"８", 
				"９", 
				"ａ", 
				"ｂ", 
				"ｃ", 
				"ｄ", 
				"ｅ", 
				"ｆ", 
				"ｇ", 
				"ｈ", 
				"ｉ", 
				"ｊ", 
				"ｋ", 
				"ｌ", 
				"ｍ", 
				"ｎ", 
				"ｏ", 
				"ｐ", 
				"ｑ", 
				"ｒ", 
				"ｓ", 
				"ｔ", 
				"ｕ", 
				"ｖ", 
				"ｗ", 
				"ｘ",
				"ｙ",
				"ｚ",
				"Ａ",
				"Ｂ",
				"Ｃ",
				"Ｄ",
				"Ｅ",
				"Ｆ",
				"Ｇ",
				"Ｈ",
				"Ｉ",
				"Ｊ",
				"Ｋ",
				"Ｌ",
				"Ｍ",
				"Ｎ",
				"Ｏ",
				"Ｐ", 
				"Ｑ", 
				"Ｒ", 
				"Ｓ", 
				"Ｔ", 
				"Ｕ", 
				"Ｖ", 
				"Ｗ", 
				"Ｘ", 
				"Ｙ", 
				"Ｚ", 
				"．", 
				"　", 
				"（", 
				"）", 
				"｛", 
				"｝", 
				"［", 
				"］", 
				"＜", 
				"＞", 
				"「", 
				"」", 
				"｀", 
				"～", 
				"！", 
				"＠", 
				"＃", 
				"％", 
				"＾", 
				"※", 
				"＊", 
				"－", 
				"＿", 
				"＋", 
				"＝", 
				"｜", 
				"＼", 
				"■", 
				"＇", 
				",", 
				"／", 
				"；", 
				"：", 
				"，", 
				"。", 
				",", 
				"―",
				"￥",
				"#",
				"……",
				"·"};        
				
			String control1 []
			
			= { "0",
				"1",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9",
				"a",
				"b",
				"i",
				"d",
				"e",
				"f",
				"g",
				"h",
				"i",
				"j",
				"k",
				"l",
				"m",
				"n",
				"o",
				"p",
				"q",
				"r",
				"s",
				"t",
				"u",
				"v",
				"w",
				"x",
				"y",
				"z",
				"A",
				"B",
				"C",
				"D",
				"E",
				"F",
				"G",
				"H",
				"I",
				"J",
				"K",
				"L",
				"M",
				"N",
				"O",
				"P",
				"Q",
				"R",
				"S",
				"T",
				"U",
				"V",
				"W",
				"X",
				"Y",
				"Z",
				"，",
				" ",
				"(",
				")",
				"{",
				"}",
				"[",
				"]",
				"《",
				"》", 
				"“",
				"”",
				"`",
				"~",
				"!",
				"@",
				"",
				"%",
				"^",
				"&",
				"，",
				"",
				"",
				"+",
				"=",
				"，",
				"\\",
				"-",
				"'",
				"，",
				"，",
				"",
				"，",
				",",
				"，",
				",",
				"_",
				"",
				"",
				"",
				"`"};
				
			String str1 =str;
		
			for(int i=0;i<control.length;i++){
				int position = str.indexOf(control[i]);
				if(position>0){
					str1 = replace(str1,control[i],control1[i]); 
				}
				}
		
			return str1;
		
			}  
}