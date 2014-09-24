/*
 * �������� 2008-1-11
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.tools;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class StringUtil {
	public StringUtil() {

	}

	public static String replaceAll(
		String source,
		String regex,
		String replacement) {
		//null   �ж�ʡȥ   

		String result = source;
		int pos = result.indexOf(regex);
		int length = regex.length();
		int length1 = replacement.length();
		while (pos != -1) {
			result =
				result.substring(0, pos)
					+ replacement
					+ result.substring(pos + length, result.length());
			pos = result.indexOf(regex, pos + length1);
		}

		return result;
	}

	public static String[] split(String s, String s1) {
		if (s == null || s1 == null)
			return null;
		StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
		Vector vector = new Vector();
		for (;
			stringtokenizer.hasMoreTokens();
			vector.add(stringtokenizer.nextToken()));
		Object aobj[] = vector.toArray();
		String as[] = new String[vector.size()];
		System.arraycopy(((Object) (aobj)), 0, as, 0, vector.size());
		return as;
	}

}
