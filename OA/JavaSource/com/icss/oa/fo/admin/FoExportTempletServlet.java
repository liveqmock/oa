/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

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

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;



public class FoExportTempletServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;

		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String planid = request.getParameter("planid");
			
			FoHandler handler = new FoHandler(conn);
			
				//取得文章列表信息
			List list=new ArrayList();
			list=handler.getVoteArticlelist(planid);
				//System.out.println("取得文章列表  list.size()"+list.size());
			request.setAttribute("list",list);
			//写excel文件
			String path = this.getServletContext().getRealPath("/fo/temp");
			handler.CreatDir(path);
			String exporttime=String.valueOf(System.currentTimeMillis());
			//System.out.println("临时文件路径exporttime"+exporttime);
			File tempFile =
				new File(path + "/templet" + exporttime+".xls");
			System.out.println("临时文件路径"+tempFile);
			Iterator it = list.iterator();
			FoArticalVO vo = new FoArticalVO();
			//创建导出报表
			WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
			//	   

			WritableSheet sheet = workbook.createSheet("模板", 0);
			
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
			//设置格式
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

			//NumberFormat nf=new NumberFormat("0.00000"); //用于Number的格式
			//WritableCellFormat priceFormat = new WritableCellFormat (detFont,
			// nf);
			//DateFormat df=new DateFormat("yyyy-MM-dd");//用于日期的
			//WritableCellFormat dateFormat = new WritableCellFormat (detFont,
			// df);
			//剩下的事情，就是用上面的内容和格式创建一些单元格，再加到sheet中

				//sheet=handler.exportExcelfirst(vo);
				l = new Label(0, 0, "", headerFormat);
				sheet.addCell(l);
				l = new Label(1, 0, "单位", headerFormat);
				sheet.addCell(l);
				l = new Label(2, 0, "", headerFormat);
				sheet.addCell(l);
				l = new Label(3, 0, "投票人数", headerFormat);
				sheet.addCell(l);
				l = new Label(4, 0, "", headerFormat);
				sheet.addCell(l);
				sheet.setColumnView(0, 0);
				sheet.setColumnView(1, 5);
				sheet.setColumnView(2, 30);
				sheet.setColumnView(3, 20);
				sheet.setColumnView(4, 20);
				//add Title
				int column = 0;
				l = new Label(column++, 2, "", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 2, "顺序", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 2, "文章标题", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 2, "部门", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, 2, "票数", titleFormat);
				sheet.addCell(l);
				
				

				//				add detail

				int i = 3;
				
				int index = 0;
				for (i = 3; it.hasNext(); i++) {
					column = 0;
					vo = (FoArticalVO) it.next();

					++index;
					l = new Label(column++, i, vo.getArtId().toString(), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, String.valueOf(index), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, vo.getArtTitle(), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, vo.getArtWriter(), detFormat);
					sheet.addCell(l);
					l = new Label(column++, i, "", detFormat);
					sheet.addCell(l);
					
					


					//					设置列的宽度
					//sheet.setColumnView(0, 5);
					
				}

				
				column = 0;
				
				l = new Label(column++, i, "", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, i, "注：", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, i, "输入的票数需低于投票人数，总投票数不得高于总投票上界且高于总投票下界,", titleFormat);
				sheet.addCell(l);
				l = new Label(column++, i, "否则此投票无效!", titleFormat);
				sheet.addCell(l);
				l = new Label(column++,i, "", titleFormat);
				sheet.addCell(l);

			

			workbook.write();
			workbook.close();
			

			getServletContext().getRequestDispatcher(
				"/fo/jump.jsp?exporttime="+ exporttime).forward(
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