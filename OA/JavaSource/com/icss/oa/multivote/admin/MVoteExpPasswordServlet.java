/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.multivote.admin;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePersonVO;



public class MVoteExpPasswordServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
    	String planid = request.getParameter("planid");
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			
			
			VoteHandler handler = new VoteHandler(conn);
			
			//ȡ��������Ա��Ϣ
			List list=new ArrayList();
			
			String sql=" plan_planid="+planid;
			list=handler.getUserManagerList(sql);
			
			//дexcel�ļ�
			String path = this.getServletContext().getRealPath("/multivote/temp");
			handler.CreatDir(path);
			String exporttime=String.valueOf(System.currentTimeMillis());
			//System.out.println("��ʱ�ļ�·��exporttime"+exporttime);
			File tempFile =
				new File(path + "/templet" + exporttime+".xls");
			System.out.println("��ʱ�ļ�·��"+tempFile);
			Iterator it = list.iterator();
			VotePersonVO vo = new VotePersonVO();
			//������������
			WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
			//	   

			WritableSheet sheet = workbook.createSheet("ģ��", 0);
			
			Label l = null;

			jxl.write.Number n = null;
			jxl.write.DateTime d = null;
			WritableFont headerFont =
				new WritableFont(
					WritableFont.ARIAL,
					12,
					WritableFont.BOLD,
					false,
					UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLUE);
			WritableCellFormat headerFormat =
				new WritableCellFormat(headerFont);
			WritableFont titleFont =
				new WritableFont(
					WritableFont.ARIAL,
					10,
					WritableFont.NO_BOLD,
					false,
					UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLUE);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			titleFormat.setWrap(true);
			titleFormat.setBorder(jxl.write.Border.ALL, BorderLineStyle.THIN);
			titleFormat.setAlignment(jxl.format.Alignment.CENTRE);
			titleFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			//���ø�ʽ
			WritableFont detFont =
				new WritableFont(
					WritableFont.ARIAL,
					10,
					WritableFont.NO_BOLD,
					false,
					UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat detFormat = new WritableCellFormat(detFont);
			detFormat.setWrap(true);
			detFormat.setBorder(jxl.write.Border.ALL, BorderLineStyle.THIN);
			detFormat.setAlignment(jxl.format.Alignment.LEFT);
			detFormat.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);

			//NumberFormat nf=new NumberFormat("0.00000"); //����Number�ĸ�ʽ
			//WritableCellFormat priceFormat = new WritableCellFormat (detFont,
			// nf);
			//DateFormat df=new DateFormat("yyyy-MM-dd");//�������ڵ�
			//WritableCellFormat dateFormat = new WritableCellFormat (detFont,
			// df);
			//ʣ�µ����飬��������������ݺ͸�ʽ����һЩ��Ԫ���ټӵ�sheet��

				//sheet=handler.exportExcelfirst(vo);
				
				//add Title
				int column = 0;
				l = new Label(column++, 0, "˳��", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 0, "����", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 0, "����", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 0, "����", titleFormat);
				sheet.addCell(l);
				
				
				

				//				add detail

				int i = 1;
				
				int index = 0;
				for (i = 1; it.hasNext(); i++) {
					column = 0;
					vo = (VotePersonVO) it.next();

					++index;
					l = new Label(column++, i, String.valueOf(index), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, vo.getOrgName(), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, vo.getName(), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, vo.getPassword(), detFormat);
					sheet.addCell(l);
					
					
					


					//					�����еĿ��
					
					
				}
				sheet.setColumnView(0, 5);
				sheet.setColumnView(1, 20);
				sheet.setColumnView(2, 20);
				sheet.setColumnView(3, 20);
				
				
			

			

			workbook.write();
			workbook.close();
			

			getServletContext().getRequestDispatcher(
				"/multivote/jump.jsp?exporttime="+ exporttime).forward(
				request,
				response);
				
			
			
			
			
		}catch(Exception e){
			handleError(e) ;
		}finally{
			try {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				handleError(e);
			}
        }
    }
}