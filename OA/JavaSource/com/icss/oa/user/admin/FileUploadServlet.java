package com.icss.oa.user.admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.tq.handler.TQHandler;
import com.icss.resourceone.sdk.framework.Context;

public class FileUploadServlet extends ServletBase {

	private static final long serialVersionUID = 1L;
	private static String filePath = "";

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = getConnection(Globals.DATASOURCEJNDI);
			Context ctx = Context.getInstance();
			String uuid = ctx.getCurrentPersonUuid();

			TQHandler handler = new TQHandler(conn);
			Integer ti = handler.getTqid(uuid);

			res.setContentType("text/html; charset=UTF-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(4096);
			// the location for saving data that is larger than
			// getSizeThreshold()
			factory.setRepository(new File(filePath));
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum size before a FileUploadException will be thrown
			upload.setSizeMax(200 * 1024);

			List fileItems = upload.parseRequest(req);
			Iterator iter = fileItems.iterator();
			// Get the file name
			String regExp = ".+\\\\(.+\\.?())$";
			Pattern fileNamePattern = Pattern.compile(regExp);
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!item.isFormField()) {
					String name = item.getName();
					// int dotIndex = name.indexOf(".");
					// String fileType = name.substring(dotIndex);

					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0)
						continue;
					Matcher m = fileNamePattern.matcher(name);
					boolean result = m.find();
					if (result) {

						// String type =
						// m.group(1).substring(m.group(1).lastIndexOf('.')+1);
						InputStream stream = item.getInputStream();
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						byte[] b = new byte[1000];
						while (stream.read(b) > 0) {
							baos.write(b);
						}
						byte[] imageByte = baos.toByteArray();
						String type = ImageUtil.getImageType(imageByte);
						if (type.equals(ImageUtil.TYPE_NOT_AVAILABLE))
							throw new Exception("file is not a image");
						//BufferedImage myImage = ImageUtil.readImage(imageByte);
						// display the image
						//ImageUtil.printImage(myImage, type, res.getOutputStream());
						// save the image
						// if you want to save the file into database, do it
						// here
						// when you want to display the image, use the
						// method printImage in ImageUtil
						item.write(new File(filePath + "/" + ti + ".jpg"));

						stream.close();
						baos.close();

						req.setAttribute("msg", "上传成功!");

					} else {
						throw new IOException("fail to upload");
					}
				}
			}
		} catch (FileUploadException e) {
			req.setAttribute("msg", "图片超过200K,请重新选择!");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("msg", "上传失败!");
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		this.forward(req, res, "/user/uploadPic.jsp");

	}

	public void init() throws ServletException {
		// Change the file path here
		// filePath = getServletContext().getRealPath("/");
		filePath = HRGroupWebservice.readValue("TQPICPATH");
		//System.out.println(filePath);
	}

	@Override
	protected void performTask(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(arg0, arg1);

	}
}
