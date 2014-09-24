/*
 * Created on 2003-12-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.util;   

import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.Date;

import com.icss.oa.config.Config;
import gnu.regexp.RE;
import gnu.regexp.REException;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommUtil {
	/**
	* �������ڵ��ַ���
	* @param time
	* @return
	*/
	public static String getTime(long time) {

		SimpleDateFormat formatter =
			new SimpleDateFormat("yyyy-MM-dd ' 'HH:mm  ");

		return formatter.format(new java.util.Date(time));

	}

	/**
	 * ���������ַ���
	 * @param time
	 * @param type
	 * @return
	 */
	public static String getTime(long time, int type) {
		String formatTime = "";
		switch (type) {
			case 0 :
				formatTime = getTime(time);
				break;
			case 1 :
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				formatTime = formatter.format(new java.util.Date(time));
				break;

			default :
				break;
		}
		return formatTime;
	}

	/**
		 * ���ַ���ת��Ϊlong
		 * @param time
		 * @return long
		 */
	public static long getLongByTime(String time) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = formatter.parse(time);
		if (date == null)
			throw new Exception("the date/time string can not parse");
		return date.getTime();
	}
	/**
	* ���ַ���ת��Ϊlong
	* @param time
	* @return long
	*/
	public static long getLongByTime(String time, int type) throws Exception {
		long formatTime = 0;
		switch (type) {
			case Config.DATE_THREAD :
				formatTime = getLongByTime(time);
				break;
			case Config.DATE_DOT :
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
				ParsePosition pos = new ParsePosition(0);
				java.util.Date date = formatter.parse(time, pos);
				if (date == null)
					throw new Exception("the date/time string can not parse");
				formatTime = date.getTime();
				break;

			default :
				break;
		}
		return formatTime;
	}
	/**
		 * ��ʽ���û�����������ַ�Ϊhtml��ʽ,�ο�unformathtm,���߹��������෴
		 * @param input
		 * @return
		 * @throws Exception
		 */
	public static String formathtm(String input) throws REException{
		if (input == null)
			return "";
		String[] toReplace = { "&", "\"", "<", "\r\n", " ", "�o", "\'" };
		String[] replaceWith =
			{
				"&amp;",
				"&quot;",
				"&lt;",
				"<br>",
				"&nbsp;",
				"&nbsp;&nbsp;",
				"&#146;" };
		int i;
		for (i = 0; i < toReplace.length; i++) {
			RE re = new RE(toReplace[i]);
			input = re.substituteAll(input, replaceWith[i]);

		}
		return input;
	}
	/**
		 * ���Ѹ�ʽ�������ַ���String��ԭ,�ο�formathtm,���߹��������෴
		 * @param input
		 * @return
		 * @throws Exception
		 */

	public static String unformathtm(String input) throws Exception {
		if (input == null)
			return "";
		String[] toReplace =
			{ "<br>", "&nbsp;", "&quot;", "&amp;", "&lt;", "&#146;" };
		String[] replaceWith = { "\r\n", " ", "\"", "&", "<", "\'" };
		int i;
		for (i = 0; i < toReplace.length; i++) {
			RE re = new RE(toReplace[i]);
			input = re.substituteAll(input, replaceWith[i]);
		}
		return input;
	}
	/**
		 * �ַ�ת��
		 * @param str
		 * @return
		 * @throws java.io.UnsupportedEncodingException
		 */
	public static String iso2utf(String str)
		throws java.io.UnsupportedEncodingException {
		if (str == null)
			return null;
		else
			return new String(str.getBytes("ISO8859_1"), "GB2312");
	}

	/**
			 * �ַ�ת��
			 * @param str
			 * @return
			 * @throws java.io.UnsupportedEncodingException
			 */
	public static String gb2iso(String str)
		throws java.io.UnsupportedEncodingException {
		if (str == null)
			return null;
		else
			return new String(str.getBytes("GB2312"), "ISO8859_1");
	}
	/**
	 * �������㣬ʮ����
	 * @param dividend ������
	 * @param divisor ���� 
	 * @param digit ��ౣ����λС��
	 * @return ��
	 */
	public static double getDivision(long dividend, long divisor, int digit) {
		double result = 0;
		if (divisor == 0) {
			return 0;
		}
		result = (double) dividend / divisor;

		result = result * Math.pow(10, digit);
		result = Math.round(result);
		result = result / Math.pow(10, digit);
		return result;
	}
	
	/**
	 * ��������������������num1-num2���,�������С�����һλ
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String getLeftSpace(float num1,float num2){
		String result = String.valueOf((num1 - num2));
		int i = result.indexOf(".");
		result = result.substring(0,i+2);
		return result;
	}
	
//	��΢�루Long����ʱ��ת��Ϊ�ַ�������ʽ
	public static String getTimeByLong(Long time){
		try{
			  Date date=new Date(time.longValue());
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			  String formatTime = formatter.format(date);
			  return formatTime;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static void main(String[] args){
		String s1 = "20";
		String s2 = "19.9";
		System.out.println(CommUtil.getLeftSpace(Float.parseFloat(s1), Float.parseFloat(s2)));
		
	}
}
