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
 * @author ���ڲ�
 * �ļ��Ĳ���
 */
public class FileWork {
	private String path;
	private String something;

	/**
	 *WriteOver�����������ڳ�ʼ������
	 */
	public FileWork() {
		path = null;
		something = "";
	}

	//�����ļ�·��
	/**
	 * �����ļ�·��
	 * @param apath �ļ�·��
	 */
	public void setPath(String apath) {
		path = apath;
	}

	//��ȡ·������
	/**
	 * ��ȡ·������
	 * @return �ļ�·��
	 */
	public String getPath() {
		return path;
	}

	//��ȡsomething����ֵ
	/**
	 * �����ļ�������
	 * @param asomething �ļ�������
	 */
	public void setSomething(String asomething) {
		something = asomething;
	}

	//����something����
	/**
	 * �õ��ļ�������
	 * @return �ļ�������
	 */
	public String getSomething() {
		return something;
	}

	//��something������ֵд��pahtָ�����ļ���
	/**
	 * ��something������ֵд��pahtָ�����ļ���
	 * @return �����ɹ�����true��ʧ�ܷ���false
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
	 * �����ļ��������е�XML�ļ�
	 * @param from �ļ��ӵ�·����String����
	 * @return ����XML�ļ�������
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
				System.err.print("Ŀ¼��������");
				e.printStackTrace();
				System.err.println("��鿴read(String from)��from������");
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
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return null;

	}
	public String getString(InputStream is, String encoding) throws IOException {

		//���ص��ַ������
		String result = "";

		//�ֽ���������������ڴ�Ŵ��������ж�ȡ���ֽ���Ϣ
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		//���������������ڴ��������ж�ȡ�ֽ�
		BufferedInputStream bis = new BufferedInputStream(is);
		//ÿ�ζ�ȡ���ֽڵĻ�������С
		byte[] buf = new byte[1024];
		//ÿ�ζ�ȡ���ֽڳ��ȼ�¼
		int len = 0;

		try {
			while ((len = bis.read(buf)) != -1) {
				//�ѵ�ǰ��ζ�ȡ���ֽ�д�뵽�ֽ������������
				bos.write(buf, 0, len);
			}
			bos.flush();
			//��ָ�����ַ������ֽ�������б��룬����String����
			result = new String(bos.toByteArray(), encoding);

		} catch (IOException e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (bos != null) {
				try {
					//�ֽ����������
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					//�رջ���������
					bis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		return result;

	}

	/**
	 * ����XML
	 * @param file xml�ļ���·��
	 * @return Hashtable��ʽ��Vector
	 */
	public Vector getXml(String file) {
		//SetXmlNode setXml = new SetXmlNode();
		//return setXml.setXml(file);

		return null;
	}
	/**
	 * ɾ��ָ�����ļ�
	 * @param file �ļ���·��
	 * @return ɾ���ɹ�����trueʧ�ܷ���false
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
	 * ɾ��ָ���������ļ�
	 * @param files �ļ�·���б�Vector��ʽ
	 * @return ����ɾ�����ļ�����
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
	 * �ļ�copy
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
				/* ԭ�ļ���Ŀ¼ ����Ŀ¼*/
				File[] files = src.listFiles();
				Stack stack = new Stack();
				for (int i = 0; i < files.length; i++) {
					dest.mkdirs();
					System.out.println(files[i].getName());
					copy2(src + File.separator + files[i].getName(), to + File.separator + files[i].getName(), overwrite);
				}
			} else {
				/* ԭ�ļ������ڻ������쳣���, �׳��쳣*/
				throw fnfe;
			}
		} catch (IOException e) {
			/* δ֪��I/O�쳣 */
			throw e;
		}
	}
	/**
	 * ���� BASE64 ����
	 * @param s
	 * @return
	 */
	public String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}
	/**
	 * �� BASE64 ������ַ��� s ���н���
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
					//����ȡ����������
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
		//��������
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
			//if ("[�ر�����]".equals(thisKey)) {
				String t1 = (String) reMap.get(baseKey + "[�ر�����]");
				String t2 = (String) reMap.get(baseKey + "[������]");
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
					buf.append("[").append(baseKey).append("]�����������⣺��[").append(open).append("]���ر�[").append(close).append("]����[").append(open - close).append("]��û�йرգ�\n");
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
			file.out("*******************************" + set[i] + "[��ʼ]*******************************");
			file.show(vec, "1".equals(detail));
			file.out("*******************************" + set[i] + "[����]*******************************");
		}
		if ("1".equals(all)) {
			Vector allVec = new Vector();
			for (int i = 0; i < set.length; i++) {
				Vector vec = (Vector) map.get(set[i]);
				allVec.addAll(vec);
			}
			file.out("----------------------------ȫ��[��ʼ]------------------------------");
			file.show(allVec, "1".equals(detail));
			file.out("----------------------------ȫ��[����]------------------------------");
		}
	}
}
