package com.icss.oa.sms.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlFile {
	private Document document;
	private String filename;
	private String filepath;
	private String sendAccount;
	private String ip;
	private String port;
	private String user;
	private String pw;
	private String contentpath;
	private String flagpath;
	private String sid;

	public XmlFile() throws Exception {
		// 读取配置文件
		sendAccount = SMSProperties.sendAccount;
		ip = SMSProperties.ip;
		port = SMSProperties.port;
		user = SMSProperties.username;
		pw = SMSProperties.password;
		contentpath = SMSProperties.contentpath;
		flagpath = SMSProperties.flagpath;
		sid = SMSProperties.senderNumber;

		// 如果FTP连接不上，将不进行任何操作
		FtpUtil.isAlive(ip, port, user, pw);

		// 随机产生文件名
		Random random = new Random();
		long n = random.nextLong();
		String s = String.valueOf(n).substring(1, 8);
		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat timeFormat = new SimpleDateFormat("yyMMdd");
		String time = timeFormat.format(calendar.getTime());

		filename = sendAccount + time + "_" + s + ".xml";
		filepath = filename; // xml文件在本机的存放路径

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toWrite(String phone, String mycontent, String id) {
		Element root = document.createElement("ENEWS_SMS");
		document.appendChild(root);

		Element SendTo = document.createElement("SendTo");
		SendTo.appendChild(document.createTextNode(phone));
		root.appendChild(SendTo);

		Element content = document.createElement("Content");
		content.appendChild(document.createTextNode(mycontent));
		root.appendChild(content);

		Element SenderNumber = document.createElement("SenderNumber");
		SenderNumber.appendChild(document.createTextNode(sid + id));
		root.appendChild(SenderNumber);

		Element SenderAccount = document.createElement("SenderAccount");
		SenderAccount.appendChild(document.createTextNode(sendAccount));
		root.appendChild(SenderAccount);

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat timeFormat = new SimpleDateFormat(
				"yyyyMMdd.HHmmss.SSS+0800");
		String time = timeFormat.format(calendar.getTime());
		Element Time = document.createElement("Time");
		Time.appendChild(document.createTextNode(time));
		root.appendChild(Time);
	}

	public void toSave() {
		PrintWriter pw = null;
		FileOutputStream fo = null;
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			fo = new FileOutputStream(filepath);
			pw = new PrintWriter(fo);
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
		} catch (TransformerException mye) {
			mye.printStackTrace();
		} catch (IOException exp) {
			exp.printStackTrace();
		} finally {
			pw.close();
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void toSend() throws Exception {
		String file = filepath;
		boolean binary = true;
		// System.out.println(filename);
		FtpUtil.uploadfile(ip, port, user, pw, file, contentpath, binary);
		FtpUtil.newnullfile(ip, port, user, pw, filename, flagpath);

	}

	public void toDelete() {
		boolean success = (new File(filepath)).delete();
		if (!success) {
			System.out.println(filepath + "删除不成功");
		}

	}
}
