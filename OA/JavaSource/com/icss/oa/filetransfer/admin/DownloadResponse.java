/*
 * �������� 2005-9-16
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.filetransfer.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: ���ô�����</p>
 * <p>Description: �������ز�����Ӧ���� </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: ����Զ������</p>
 * @author Andy
 * @version 1.0
 */
public class DownloadResponse {

	protected final static int M_BLOCKSIZE = 65535;

	protected HttpServletResponse m_response = null;
	protected String mime = null;
	private final String DEFAULTMIME = "application/octet-stream";
	
	public static final int DOWNLOADTYPE_INLINE = 2;
	public static final int DOWNLOADTYPE_ATTACHMENT = 1;
	
	private int downloadType = 1;
	private String desFileNameEncoding = "GBK";

	public DownloadResponse(HttpServletResponse response) {
		//mime = "application/x-msdownload";
		mime = DEFAULTMIME;
		m_response = response;
	}

	public DownloadResponse (HttpServletResponse response, int downloadType) {
	    this(response);
	    this.downloadType = downloadType;
	}
	
	public DownloadResponse(HttpServletResponse response, String mime) {
		m_response = response;
		if (mime == null || mime.trim().equals("")) {
			mime = DEFAULTMIME;
		}
		this.mime = mime;
	}

	public DownloadResponse(HttpServletResponse response, String mime, int downloadType) {
		m_response = response;
		if (mime == null || mime.trim().equals("")) {
			mime = DEFAULTMIME;
		}
		this.mime = mime;
	    this.downloadType = downloadType;
	}

	public void download(File srcFile) throws IOException, ServletException {
		download(srcFile, null);
	}

	public void download(File srcFile, String desFile)
		throws IOException, ServletException {
		if (!srcFile.exists()) {
			throw new IOException(
				"File " + srcFile.getAbsolutePath() + " not found.");
		}
		if (!srcFile.canRead())
			throw new IOException(
				"File " + srcFile.getAbsolutePath() + " can not be read.");
		if (desFile == null)
			desFile = srcFile.getName();
		download(
			new FileInputStream(srcFile),
			desFile,
			(long) srcFile.length());
	}

	/**
	 * added by chenjie 2004-10-12
	 * @param srcFile
	 * @param desFile
	 * @param desFileNameEncoding Ŀ���ļ�����Ҫ����ɸò���ָ�����ַ�������websphere5����Ҫ�����8859-1�������������������⡣
	 * @throws IOException
	 * @throws ServletException
	 */
	public void download(File srcFile, String desFile, String desFileNameEncoding)
	throws IOException, ServletException {
	    this.desFileNameEncoding = desFileNameEncoding;
	    download(srcFile, desFile);
	}
	
	public void download(
		InputStream ins,
		String desfileName,
		long contentLength)
		throws ServletException, IOException {
		if (m_response == null)
			throw new ServletException("ServletResponse can not be null.");
		if (ins == null)
			throw new ServletException("InputStream can not be null.");
		if (desfileName == null || desfileName.equals("")) {
			throw new ServletException("Destination file name can not be null.");
		} else {
			ServletOutputStream out = m_response.getOutputStream();
			try {
				m_response.reset();
				m_response.setContentType(mime);
				m_response.setContentLength((int) contentLength);
				//m_response.setHeader("Content-Disposition", "filename=" + desfileName);
				if (downloadType == DOWNLOADTYPE_INLINE) {
					m_response.setHeader(
							"Content-Disposition",
							String.valueOf(
								new StringBuffer("inline;").append(
									" filename=").append(new String(desfileName.getBytes(), desFileNameEncoding))));
				} else {
					m_response.setHeader(
						"Content-Disposition",
						String.valueOf(
							new StringBuffer("attachment;").append(
								" filename=").append(new String(desfileName.getBytes(), desFileNameEncoding))));
				}
				int readBytes = 0;
				int totalRead = 0;
				byte buf[] = new byte[0x20000];
				int i;
				while ((i = ins.read(buf)) != -1)
					out.write(buf, 0, i);
				out.flush();
			} finally {
				try {
					ins.close();
				} catch (Exception ignored) {
				}
				try {
					out.close();
				} catch (Exception ignored) {
				}
			}
		}
	}

	public void download(String srcFile, String destFile)
		throws ServletException, IOException {
		download(new File(srcFile), destFile);
	}
}
