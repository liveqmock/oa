/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.useraddress.admin;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.useraddress.handler.UserAddressHandler;
import com.icss.oa.useraddress.vo.OaaddListVO;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class UseraddressPrintServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		List UseraddressList = null;

		int j;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
ConnLog.open("UseraddressPrintServlet");			
			UserAddressHandler handler = new UserAddressHandler(conn);

			String path = this.getServletContext().getRealPath("/addressbooktmp");
			handler.CreatDir(path);

			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List addressList = handler.getUserInfoList();
			Iterator it = addressList.iterator();
			OaaddListVO addressvo = null;

			//Date tmpdate=new Date();
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			//String formatTime = formatter.format(tmpdate);
			//System.out.println("+++++++++++create is ok++++++++"+formatTime);
			File tempFile = new File(path + "/useraddress.xls");

			//if(!tempFile.dexists()){

			//System.out.println("+++++++++++create is ok++++++++");
			WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
			//	   }

			WritableSheet sheet = workbook.createSheet("�û����ϱ�", 0);
			Label l = null;

			jxl.write.Number n = null;
			jxl.write.DateTime d = null;
			WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
			WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
			WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat detFormat = new WritableCellFormat(detFont);
			//NumberFormat nf=new NumberFormat("0.00000"); //����Number�ĸ�ʽ
			//WritableCellFormat priceFormat = new WritableCellFormat (detFont,
			// nf);
			//DateFormat df=new DateFormat("yyyy-MM-dd");//�������ڵ�
			//WritableCellFormat dateFormat = new WritableCellFormat (detFont,
			// df);
			//ʣ�µ����飬��������������ݺ͸�ʽ����һЩ��Ԫ���ټӵ�sheet��
			l = new Label(0, 0, "�û����ϱ�", headerFormat);
			sheet.addCell(l);
			//add Title
			int column = 0;
			l = new Label(column++, 2, "����", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "���ڵ���", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "���ڲ���", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "���ڰ칫��", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "��ϵ�绰", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "��������", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "ְ��", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "����ϵͳ", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "IP", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "����ϵͳ", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "MAC��ַ", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "����ڵ�", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "�豸���", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "�������", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "�Ƿ���ͨ", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "������������", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "����", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "��ͨʱ��", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "�û���ʶ", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "��ע", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "�ۺϲ���", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "����绰", titleFormat);
			sheet.addCell(l);

			l = new Label(column++, 2, "����", titleFormat);
			sheet.addCell(l);
			l = new Label(column++, 2, "�۸�", titleFormat);
			sheet.addCell(l);
			//add detail
			int i = 3;
			column = 0;
			if (it.hasNext()) {
				//System.out.println("+++++++++++here is ok2++++++++");
				//

			} else {
				//System.out.println("+++++++++++no++++++++");
			}
			for (i = 3; it.hasNext(); i++) {
				addressvo = (OaaddListVO) it.next();

				/*if (addressvo.getOdUsrname()==null) {
					tmp1="";
					l=new Label(column, i,tmp1, detFormat);
					sheet.addCell(l);
				}else{*/
				l = new Label(column, i, addressvo.getOdUsrname(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 1, i, addressvo.getOdRegion(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 2, i, addressvo.getOdDept(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 3, i, addressvo.getOdRoomnum(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 4, i, addressvo.getOdTel(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 5, i, addressvo.getOdWord(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 6, i, addressvo.getOdBuss(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 7, i, addressvo.getOdSys(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 8, i, addressvo.getOdIp(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 9, i, addressvo.getOdOpesys(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 10, i, addressvo.getOdMacnum(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 11, i, addressvo.getOdRoomnod(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 12, i, addressvo.getOdMachid(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 13, i, addressvo.getOdMachcircs(), detFormat);
				sheet.addCell(l);
				//	l=new Label(column+14, i,addressvo.getOdOnflag(), detFormat);
				//sheet.addCell(l);
				//l=new Label(column+15, i,addressvo.getOdVirus(), detFormat);
				//sheet.addCell(l);
				//l=new Label(column+16, i,addressvo.getOdOffice(), detFormat);
				//sheet.addCell(l);
				//l=new Label(column+17, i,addressvo.getOdOpentime(), detFormat);
				//sheet.addCell(l);
				l = new Label(column + 18, i, addressvo.getOdMachname(), detFormat);
				sheet.addCell(l);

				l = new Label(column + 19, i, addressvo.getOdMemo(), detFormat);
				sheet.addCell(l);

				//l=new Label(column+, i,addressvo.getOdNetcircs(), detFormat);
				//sheet.addCell(l);
				//l=new Label(column+, i,addressvo.getOdNettel(), detFormat);
				//sheet.addCell(l);

				//System.out.println("+++++++++++here is ok2++++++++");
			}

			workbook.write();
			workbook.close();

			//System.out.println("++++++++++++++++++"+path+"\\useraddress.xls");
			this.forward(request, response, "/useraddress/jump.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("UseraddressPrintServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
