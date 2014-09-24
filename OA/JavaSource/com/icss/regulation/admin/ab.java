package com.icss.regulation.admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ab {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a = "/oabase/UserFiles/File/DSCN1533(1).JPG";
		System.out.println("/UserFiles".length());
		System.out.println(a.substring(a.indexOf("/UserFiles")+10));
		
		
		String str = "(?s)url= <a href='http://www.csdn.net' target='_blank'>¹þ¹þ¹þ </a><a href=\"ab.com\" >"; 
        Matcher matcher = Pattern.compile("href=['\"]([^'\"]*)['\"]").matcher(str);
        while(matcher.find()) {
            System.out.println(matcher.group(1));
        }

	}

}
