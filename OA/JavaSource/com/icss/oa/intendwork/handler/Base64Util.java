package com.icss.oa.intendwork.handler;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Base64编解码的工具类. <BR> 
 * [08.12.12]增加byte[] decodeBytes(String dest)支持将字符串直接解码成字节码
 * 
 * @author TRS信息技术有限公司
 */
public class Base64Util {

	private static final String ENCODING = "ISO8859-1";
 
	/**
	 * 如果做了base64加密，则将该加密后的串开头添加该指定字符串由此标识该字符串是否是base64加密
	 */
	public static final String BASE64_HEADER = "(BASE64)";

	/**
	 * base64解码过程.
	 * 
	 * @param dest
	 *            待解码的字符串
	 * @return 解码得到的原始字符串
	 */
	public static String decode(String dest) {
		if (dest == null) {
			return "";
		}

		return new String(Base64.decodeBase64(dest.getBytes()));
	}

	/**
	 * Base64解码
	 * 
	 * @param dest
	 *            待解码的字符串
	 * @return 字节码
	 */
	public static byte[] decodeBytes(String dest) {
		if (dest == null) {
			return null;
		}
		return Base64.decodeBase64(dest.getBytes());
	}

	public static String decode(byte[] encoded) {
		return new String(Base64.decodeBase64(encoded));
	}

	/**
	 * base64编码过程.
	 * 
	 * @param origin
	 *            待编码的字符串
	 * @return 解码后的字符串. 如果origin参数为null, 则返回"".
	 */
	public static String encode(String origin) {
		if (origin == null) {
			return "";
		}
		return new String(Base64.encodeBase64(origin.getBytes())); // [liushen] the base64 not chunked
	}

	public static String encode(String origin, String charsetName) {
		if (origin == null) {
			return "";
		}
		String base64Str = "";
		try {
			base64Str = new String(Base64.encodeBase64(origin.getBytes(charsetName)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64Str;
	}

	public static String decode(String base64Str, String charsetName) {
		if (base64Str == null || base64Str.trim().length() == 0) {
			return "";
		}

		if (charsetName == null || charsetName.trim().length() == 0) {
			return decode(base64Str);
		}
		String originStr = "";
		try {
			originStr = new String(Base64.decodeBase64(base64Str.getBytes(ENCODING)), charsetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return originStr;
	}

	static InputStream encode(InputStream inputStream) {
		// [liushen@2005-02-06] 未测试.
		try {
			int bytesToRead = inputStream.available(); // 对文件流可靠, 对网络流不可靠.
			byte[] input = new byte[bytesToRead];
			int bytesRead = 0;
			while (bytesRead < bytesToRead) {
				int actualRead = inputStream.read(input, bytesRead, bytesToRead - bytesRead);
				if (actualRead == -1)
					break;
				bytesRead += actualRead;
			}
			String encodeBytes = encode(input);
			return new ByteArrayInputStream(encodeBytes.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream("".getBytes()); // 返回empty的流
	}

	public static String encode(byte[] data) {
		if (null == data)
			return "";
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 将二进制字节数组编码成为base64数组.
	 * 
	 * @since ls@08.0106
	 */
	public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
		return Base64.encodeBase64(binaryData, isChunked);
	}

	/**
	 * 将base64数组解码成为二进制字节数组.
	 * 
	 * @since ls@08.0106
	 */
	public static byte[] decodeBase64(byte[] base64Data) {
		return Base64.decodeBase64(base64Data);
	}

	/**
	 * 判断是否是Base64编码
	 * 
	 * Base64编码规则: 1)字串只由A～Z a~z 0~9 + / = 构成。 2)字串长度是4的倍数。 3) = 只出现在字串尾端，最多2个。
	 * 
	 * @param strEncoded
	 * @return
	 * @since v3.5
	 * @creator shixin @ 2010-4-5
	 */
	public static boolean isBase64Encoded(String strEncoded, String charsetName) {
		// 长度不是4的倍数，返回false
		if (strEncoded.length() % 4 != 0) {
			return false;
		}
		try {
			byte[] base64Data = strEncoded.getBytes(charsetName);
			if (Base64.isArrayByteBase64(base64Data)) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断是否是Base64编码
	 * 
	 * Base64编码规则: 1)字串只由A～Z a~z 0~9 + / = 构成。 2)字串长度是4的倍数。 3) = 只出现在字串尾端，最多2个。
	 * 
	 * @return
	 * @since v3.5
	 * @creator shixin @ 2010-4-5
	 */
	public static boolean isBase64Encoded(String strEncoded) {
		// 长度不是4的倍数，返回false
		if (strEncoded.length() % 4 != 0) {
			return false;
		}
		byte[] base64Data = strEncoded.getBytes();
		if (Base64.isArrayByteBase64(base64Data)) {
			return true;
		}
		return false;
	}
}
