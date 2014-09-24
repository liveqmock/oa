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
 * <p>Title: OAϵͳ</p>
 * <p>Description: �ַ����������õķ���</p>
 * <p>Copyright: Copyright (c) 2002-12-5</p>
 * <p>Company: ����Զ������</p>
 *
 * @version 1.0
 * @author Andy
 */
public class StringUtility {
	
	public final static int NOT_FILL = 0;
	public final static int NORMAL_FILL = 1;
	public final static int HTML_FILL = 2;	
	
    /**
     * �����null�ַ���, �򷵻�""�ַ���
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
     * �����null����, �򷵻�""�ַ���
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
     * ���ַ���str�г��ֵ�oldStr�ַ����滻ΪnewStr�ַ���
     *
     * @param str Ҫ�����滻�������ַ���
     * @param oldStr ԭ�ַ���
     * @param newStr �滻�ַ���
     * @return �滻�Ժ���ַ���
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
     * �����ַ����еķǷ��ַ�
     *
     * @param in
     * @return String
     */
    public static String escapeHTMLTags(String in) {
        if (in == null || in.length() <= 0) {
            return "";
        }
        // ע�⣬������_temp1ĩβ���ת���ַ���ͬʱҲҪ��_temp2ĩβ����滻�ַ���������Ҫ��"|"�ָ�
        // ����ASCII�����127���ַ�������ӵ�_temp��ʱ��Ҫ��escape��ʽ��ʾ���磺\u00FF��ʾASCII��Ϊ255���ַ�
        String _temp1 = "\u0026\u00A9\u00AE\u2122\"\u003C\u003E";
        String _temp2 = "&amp;|&copy;|&reg;|&trade;|&quot;|&lt;|&gt;";

        // Init vars.
        StringBuffer ret = new StringBuffer();
        String _new = "";

        // �Ƚ�in�к��е�ת�����ַ���ת����һ���ת��ǰ�ַ��������ͻ���ȷת��һ���ַ���
        // ���ǻὫ�û�����İ�����_temp2�е�ת�����ַ�������Ӧ��ԭ�ַ���
        // ���磬�û�����" "����ת����"&nbsp;"����������û�����"&nbsp;"�ͻᱻ��Ϊ��" ".
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
        // �����ַ�ת��
        int _length = in.length();
        for (i = 0; i < _length; i++) {
            char t_char = in.charAt(i);
            int _index = _temp1.indexOf(t_char);
            if (_index < 0) {
                // �����ڴ�ת���ַ�
                ret.append(t_char);
            }
            else {
                // ���ڴ�ת���ַ�����_temp2��ȥ������Ӧ���滻�ַ���
                _new = (String)escapeArray.get(_index);
                ret.append(_new);
            }
        }
        return ret.toString();
    }

    public static String filterHtml(Object oIn) {
        //����ǿն��󣬷��ؿ��ַ���
        if (oIn == null) {
            return "";
        }
        String strIn = oIn.toString();
        return escapeHTMLTags(strIn);
    }
    /**
     * ���� ����
     *
     * @param toBeProcessed String
     * @param mode 0 - ���ܣ����� - ����
     * @return String
     */
    public static String encrypt(String toBeProcessed, int mode) {
        String ret = new String();
        // ����ǿ�ֱ�ӷ���
        if (toBeProcessed == null || toBeProcessed.length() <= 0) {
            return ret;
        }
        // ����
        int len = toBeProcessed.length();
        int i, j, k, l;
        if (mode == 0) { // ����
            j = (int) (16 * Math.random() - 16);
            for (i = 1; i <= len; i++) {
                k = j + i % 4;
                ret = String.valueOf((char) (toBeProcessed.charAt(i - 1) + k)) + ret;
            }
            ret += String.valueOf((char) (j + 70));
        }
        else { // ����
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
     * �����ַ����еĵ�����
     * һ�������ű�Ϊ����������
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

    // ��Ҫ���������js�ַ������������htmlҳ��ʱ����js����
    // ע�⣬��\\�����������ǰ�ˣ���ΪҪ�������������ַ�ʱ��Ҫ�õ���
    private final static String[] jsSpecialChars = { "\\", "\'", "\"" };

    /**
     * �������js�е������ַ�����ת�崦��
     * �磬��\'ת����\\\'
     *
     * @param in
     * @return
     */
    public static String escapeJSSpecialChar(String in) {
        String ret = new String();
        if (in == null || in.length() <= 0) {
            return ret;
        }

        // ��in�е�js specail charȫ������\
        ret = in;
        for (int i = 0; i < jsSpecialChars.length; i++) {
            ret = replace(ret, jsSpecialChars[i], "\\" + jsSpecialChars[i]);
        }
        return ret;
    }

    // ��λ�ַ���
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
	 * ��ý�ȡ��ָ�����Ⱥ���ַ��������������ָ���ַ������档
	 *
	 * @param str ��Ҫ�޶����ȵ��ַ���
	 * @param length ��ȡλ��,��������λ
	 * @param more �и���������滻�ַ���
	 * @param fillchar �ַ�����ʱ����䷽ʽ
	 * @return ��ȡ����ַ���
	 */
	public static String getMoreString(String str, int length, String more) {
		return getMoreString(str, length, more, NOT_FILL);
	}
            
    /**
     * ��ý�ȡ��ָ�����Ⱥ���ַ��������������ָ���ַ������档
     *
     * @param str ��Ҫ�޶����ȵ��ַ���
     * @param length ��ȡλ��,��������λ
     * @param more �и���������滻�ַ���
     * @param fillchar �ַ�����ʱ����䷽ʽ
     * @return ��ȡ����ַ���
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
     * ������ת��Ϊ�ַ���������ʽ��Ϊָ���ĳ��ȣ�����λ����ǰ�油ָ���ַ�
     * Input: 1, 5, '0'
     * Output: "00001"
     *
     * @return java.lang.String
     * @param value - Ҫת��������
     * @param number - Ҫ�����λ��
     * @param c char - ������ַ�
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
     * �������һ��ָ�����ȵ������ַ���
     *
     * @return java.lang.String
     * @param int num - Ҫ���ɵ��ַ�������
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
     * ���ַ����еõ��ļ���
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
     * ���ַ����еõ��ļ�·��
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
     * �ѷָ���ַ����ϳ�����
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
     * �жϸ����ַ����Ƿ�Ϊ��(add in 2003.11.20@author windy)
     *
     * @param str ��Ҫ�����ַ���
     * @return ���Ϊnull��""���򷵻�true,����Ϊfalse
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
     * �鿴Դ�������Ƿ�������������(add in 2003.11.21@author windy)
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
     * ���������ַ���ת��Ϊ����GBK������ַ���;��ΪNULL��ת���ɿ� ("")(add in 2003.11.21@author windy)
     *
     * @param str �����ַ���
     * @return ��GBK�������ַ�����������쳣���򷵻�ԭ�����ַ���
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
     * ������������GBK�ַ���ת��ΪISO������ַ���;��ΪNULL��ת���ɿ� ("")(add in 2003.11.21@author windy)
     *
     * @param str �����ַ���
     * @return ��GBK�������ַ�����������쳣���򷵻�ԭ�����ַ���
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
     * �����ַ����еĵ�����
     * һ�������ű�Ϊ����������
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
//        String str = "����abc��hehe";
//        System.out.println("getMoreString( \"" + str + "\", 7, \"...\" ) = " + getMoreString(str, 7, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 8, \"...\" ) = " + getMoreString(str, 8, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 9, \"...\" ) = " + getMoreString(str, 9, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 10, \"...\" ) = " + getMoreString(str, 10, "..."));
//        System.out.println("getMoreString( \"" + str + "\", 50, \"...\" ) = " + getMoreString(str, 50, "..."));
//
//        //Test random()
//        System.out.println("the method random(10)=" + StringUtility.random(10));
//    }
    //�ж�һ���ַ����ĳ���,�����������ֽ�
    
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
		 * ��ȫ���ַ���Ϊȫ���ַ�
		 *
		 * @return boolean [yes or no]
		 * @param str java.lang.String ��������ַ���
		 */
	
		public static String CtoB(String str){
		
			String control []
			={  "��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��",
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				",", 
				"��", 
				"��", 
				"��", 
				"��", 
				"��", 
				",", 
				"�D",
				"��",
				"#",
				"����",
				"��"};        
				
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
				"��",
				" ",
				"(",
				")",
				"{",
				"}",
				"[",
				"]",
				"��",
				"��", 
				"��",
				"��",
				"`",
				"~",
				"!",
				"@",
				"",
				"%",
				"^",
				"&",
				"��",
				"",
				"",
				"+",
				"=",
				"��",
				"\\",
				"-",
				"'",
				"��",
				"��",
				"",
				"��",
				",",
				"��",
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