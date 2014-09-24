package com.icss.oa.sync.handler;

import java.util.regex.Pattern;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s=" sssssssss";
		Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
		java.util.regex.Matcher matcher = pattern.matcher(s);
		System.out.println(matcher.find());

	}

}
