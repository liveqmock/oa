package test.fileopen;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

import sun.misc.BASE64Decoder;
/**
 * 
 * @author 李宗波
 * 文件的操作
 */
public class FileWork {
	private String path;
	private String something;

	/**
	 *WriteOver构造器，用于初始化参数
	 */
	public FileWork() {
		path = null;
		something = "";
	}

	//设置文件路径
	/**
	 * 设置文件路径
	 * @param apath 文件路径
	 */
	public void setPath(String apath) {
		path = apath;
	}

	//获取路径参数
	/**
	 * 获取路径参数
	 * @return 文件路径
	 */
	public String getPath() {
		return path;
	}

	//获取something参数值
	/**
	 * 设置文件的内容
	 * @param asomething 文件的内容
	 */
	public void setSomething(String asomething) {
		something = asomething;
	}

	//设置something参数
	/**
	 * 得到文件的内容
	 * @return 文件的内容
	 */
	public String getSomething() {
		return something;
	}

	//将something参数的值写入paht指定的文件中
	/**
	 * 将something参数的值写入paht指定的文件中
	 * @return 操作成功返回true，失败返回false
	 */
	public boolean writeSomething() {
		try {
			File f = new File(path);

			PrintWriter out = new PrintWriter(new FileWriter(f));
			out.print(this.getSomething() + "\n");
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 遍历文件夹中所有的XML文件
	 * @param from 文件加的路径，String类型
	 * @return 所有XML文件的名字
	 */
	public Vector read(String from) {
		File src = new File(from);
		File[] files = src.listFiles();
		Vector filesVector = new Vector();
		if (files == null) {
			return filesVector;
		}
		for (int i = 0; i < files.length; i++) {
			try {
				String tempStr = files[i].toString();
				if (tempStr.substring(tempStr.length() - 4).toUpperCase().equals(".LOG")) {
					filesVector.add(tempStr);
				}
			} catch (Exception e) {
				System.err.print("目录解析错误：");
				e.printStackTrace();
				System.err.println("请查看read(String from)中from参数。");
				filesVector = null;
			}
		}

		return filesVector;
	}
	public String readFile(String from) {
		File src = new File(from);
		try {
			FileInputStream fis = new FileInputStream(src);
			return getString(fis, "GBK");
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return null;

	}
	public String getString(InputStream is, String encoding) throws IOException {

		//返回的字符串结果
		String result = "";

		//字节数组输出流，用于存放从输入流中读取的字节信息
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		//缓冲输入流，用于从输入流中读取字节
		BufferedInputStream bis = new BufferedInputStream(is);
		//每次读取的字节的缓冲区大小
		byte[] buf = new byte[1024];
		//每次读取的字节长度记录
		int len = 0;

		try {
			while ((len = bis.read(buf)) != -1) {
				//把当前这次读取的字节写入到字节数组输出流中
				bos.write(buf, 0, len);
			}
			bos.flush();
			//以指定的字符集对字节数组进行编码，构造String对象
			result = new String(bos.toByteArray(), encoding);

		} catch (IOException e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (bos != null) {
				try {
					//字节数组输出流
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					//关闭缓冲输入流
					bis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		return result;

	}

	/**
	 * 解析XML
	 * @param file xml文件的路径
	 * @return Hashtable格式的Vector
	 */
	public Vector getXml(String file) {
		//SetXmlNode setXml = new SetXmlNode();
		//return setXml.setXml(file);

		return null;
	}
	/**
	 * 删除指定的文件
	 * @param file 文件的路径
	 * @return 删除成功返回true失败返回false
	 */
	public boolean delFile(String file) {
		if (!file.equals("")) {
			File delFile = new File(file);
			delFile.delete();
			return true;
		}
		return false;
	}
	/**
	 * 删除指定的所有文件
	 * @param files 文件路径列表，Vector格式
	 * @return 返回删除的文件数量
	 */
	public int delFiles(Vector files) {
		File delFile = null;
		int delNum = 0;
		for (int i = 0; i < files.size(); i++) {
			delFile = new File(files.get(i).toString());
			delFile.delete();
			delNum++;
		}
		return delNum;
	}
	/**
	 * 文件copy
	 * @param from
	 * @param to
	 * @param overwrite
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void copy2(String from, String to, boolean overwrite) throws FileNotFoundException, IOException {
		File src = new File(from);
		File dest = new File(to);
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(to, !overwrite);

			byte[] buf = new byte[1024];
			int size = fis.read(buf);
			while (size != -1) {
				fos.write(buf, 0, size);
				size = fis.read(buf);
			}
			fis.close();
			fos.close();
		} catch (FileNotFoundException fnfe) {
			if (src.isDirectory()) {
				/* 原文件是目录 复制目录*/
				File[] files = src.listFiles();
				Stack stack = new Stack();
				for (int i = 0; i < files.length; i++) {
					dest.mkdirs();
					System.out.println(files[i].getName());
					copy2(src + File.separator + files[i].getName(), to + File.separator + files[i].getName(), overwrite);
				}
			} else {
				/* 原文件不存在或其它异常情况, 抛出异常*/
				throw fnfe;
			}
		} catch (IOException e) {
			/* 未知的I/O异常 */
			throw e;
		}
	}
	/**
	 * 进行 BASE64 编码
	 * @param s
	 * @return
	 */
	public String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}
	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * @param s
	 * @return
	 */
	public String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
	public Map lookLog(String src) {
		Map reMap = new HashMap();
		Vector vec = read(src);
		if (vec != null) {
			for (int i = 0; i < vec.size(); i++) {
				String fileName = (String) vec.get(i);
				String value = readFile(fileName);
				String name = fileName.substring(fileName.indexOf("FileConn_") + 9, fileName.length() - 4);
				int mm = value.indexOf("[");
				Vector connVec = new Vector();
				while (mm >= 0) {
					//这里取得连接内容
					int kk = value.indexOf(">");
					String oneStr = value.substring(mm - 1, kk + 1);
					Map map = new HashMap();
					map.put("className", oneStr.substring(oneStr.indexOf("<") + 1, oneStr.indexOf(">")));
					map.put("state", oneStr.substring(oneStr.indexOf("["), oneStr.indexOf("]") + 1));
					connVec.add(map);
					value = value.substring(kk + 1, value.length());
					mm = value.indexOf("[");
				}
				reMap.put(name, connVec);
			}
		}
		return reMap;
	}
	public void show(Vector vec, boolean detail) {
		TreeMap reMap = new TreeMap();
		//解析对象
		for (int i = 0; i < vec.size(); i++) {
			Map map = (Map) vec.get(i);
			String state = (String) map.get("state");
			String className = (String) map.get("className");
			String key = className + state;
			if (reMap.get(key) == null) {
				reMap.put(key, "1");
			} else {
				int size = Integer.parseInt((String) reMap.get(key));
				size = size + 1;
				reMap.put(key, String.valueOf(size));
			}
		}
		Object[] set = reMap.keySet().toArray();
		Map map = new HashMap();

		for (int i = 0; i < set.length; i++) {
			String key = (String) set[i];
			String baseKey = key.substring(0, key.indexOf("["));
			String thisKey = key.substring(key.indexOf("["), key.length());
			//if ("[关闭连接]".equals(thisKey)) {
				String t1 = (String) reMap.get(baseKey + "[关闭连接]");
				String t2 = (String) reMap.get(baseKey + "[打开连接]");
				if (t1 == null || "".equals(t1)) {
					t1 = "0";
				}
				if (t2 == null || "".equals(t2)) {
					t2 = "0";
				}
				long close = Long.parseLong(t1);
				long open = Long.parseLong(t2);
				StringBuffer buf = new StringBuffer();
				if (close != open) {
					buf.append("[").append(baseKey).append("]的连接有问题：打开[").append(open).append("]，关闭[").append(close).append("]；有[").append(open - close).append("]个没有关闭！\n");
					map.put(baseKey, buf.toString());
				}
		//	}
			if (detail) {
				out(set[i] + "=" + reMap.get(set[i]));
			}
		}
		if (map.size() > 0) {
			out("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			set = map.keySet().toArray();
			for (int i = 0; i < set.length; i++) {
				out((String) map.get(set[i]));
			}
			out("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		}
	}
	public void out(String str) {
		System.out.println(str);
	}
	public static void main(String[] args) {
		String path = "e:/logs/logs";
		String all = "1";
		String detail = "1";
		if (args != null && args.length == 3) {
			if (args[0] != null) {
				path = args[0];
			}
			if (args[1] != null) {
				detail = args[1];
			}
			if (args[2] != null) {
				all = args[2];
			}
		}

		FileWork file = new FileWork();
		Map map = file.lookLog(path);
		Object[] set = map.keySet().toArray();
		for (int i = 0; i < set.length; i++) {
			Vector vec = (Vector) map.get(set[i]);
			file.out("*******************************" + set[i] + "[开始]*******************************");
			file.show(vec, "1".equals(detail));
			file.out("*******************************" + set[i] + "[结束]*******************************");
		}
		if ("1".equals(all)) {
			Vector allVec = new Vector();
			for (int i = 0; i < set.length; i++) {
				Vector vec = (Vector) map.get(set[i]);
				allVec.addAll(vec);
			}
			file.out("----------------------------全部[开始]------------------------------");
			file.show(allVec, "1".equals(detail));
			file.out("----------------------------全部[结束]------------------------------");
		}
	}
}
