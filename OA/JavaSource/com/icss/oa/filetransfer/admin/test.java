package com.icss.oa.filetransfer.admin;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;



public class test{
	
	public static void main(String arg[]){
		String a ="Subject: =?gb2312?B?1tDOxCwxMjQwNjQ2Mjg1MjEy?=";
		
		try {
			System.out.println(MimeUtility.decodeText(a));
			System.out.println(new String(a.getBytes("gb2312")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}