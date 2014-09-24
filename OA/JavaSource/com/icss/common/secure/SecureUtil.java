package com.icss.common.secure;

import com.icss.common.secure.SecureTool;

public class SecureUtil 
{	
	private static final String KEY = "xhsoapassword";
	
	public static void main(String[] args)
	{	
		String s1 = "eff25a241e71c83239efc6dcc7404d40893b5576b15dc41ae3d40f4655c26ad8";
		String s2 = "2db6249b6e2840783ff07a5000a34fed7421d6c00b8a98f52b4889b3ff4bb263";
		String s3 = "08ee31a1eeb4e0dcf174afeb870606649102bc3b4172e382904ff9180002a193";
		String s4 = "7991f289e75b791ccf9aaa74d9d8dc364d206c7ca6a67a25d0657b60a7775150";
		String s5 = "786879360885404c549f8334cd1a6be310b0d6e7e9965ab477b0f9f5689d3d81";
		
		System.out.println(new SecureTool(KEY).decode(s1));
		System.out.println(new SecureTool(KEY).decode(s2));
		System.out.println(new SecureTool(KEY).decode(s3));
		System.out.println(new SecureTool(KEY).decode(s4));
		System.out.println(new SecureTool(KEY).decode(s5));
	}
}
