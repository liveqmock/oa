/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.addressbook.admin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookAllSearchVO;
import com.icss.oa.addressbook.vo.AddressbookContentVO;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.addressbook.vo.AddressbookManagerVO;
import com.icss.oa.bbs.handler.UserMsgHandler;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AddressbookPrintServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;

		List foldersList = null;
		List addressbooksList = null;
		List haschildabfList = null;
		List childabcList = null;
		int j;
		InputStream in = null;
		InputStream is = null;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("AddressbookPrintServlet");
			AddressbookHandler handler = new AddressbookHandler(conn);

			String path = this.getServletContext().getRealPath("/addressbooktmp");
			handler.CreatDir(path);

			UserMsgHandler userHandler = new UserMsgHandler(conn);
			String userId = userHandler.getUserId();
			List abmList = handler.getUserList(userId);
			Iterator it = abmList.iterator();
			AddressbookManagerVO abmvo = null;
			AddressbookContentVO abcvo = null;
			AddressbookFolderVO childabfvo = null;
			AddressbookContentVO childabcvo = null;

			if (it.hasNext()) {
				abmvo = (AddressbookManagerVO) it.next();
			}

			//System.out.println("+++++++++++abmvo is ok++++++++");
			if (abmvo != null) {

				//System.out.println("+++++++++++there is abmvo++++++++");
				File tempFile = new File(path + "/" + abmvo.getAbmId() + "addressbook.xls");
				//if(!tempFile.dexists()){

				//System.out.println("+++++++++++create is ok++++++++");
				WritableWorkbook workbook = Workbook.createWorkbook(tempFile);
				//	   }

				WritableSheet sheet = workbook.createSheet("TestCreateExcel", 0);
				Label l = null;

				foldersList = handler.getFoldersList(abmvo.getAbmId(), new Integer("1"));
				Iterator foldersit = foldersList.iterator();
				AddressbookAllSearchVO abfvo = null;

				jxl.write.Number n = null;
				jxl.write.DateTime d = null;
				WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
				WritableCellFormat headerFormat = new WritableCellFormat(headerFont);
				WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);
				WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
				WritableFont detFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
				WritableCellFormat detFormat = new WritableCellFormat(detFont);
				//NumberFormat nf=new NumberFormat("0.00000");  //用于Number的格式
				//WritableCellFormat priceFormat = new WritableCellFormat (detFont, nf); 
				//DateFormat df=new DateFormat("yyyy-MM-dd");//用于日期的
				//WritableCellFormat dateFormat = new WritableCellFormat (detFont, df); 
				//剩下的事情，就是用上面的内容和格式创建一些单元格，再加到sheet中
				l = new Label(0, 0, "我的通讯录", headerFormat);
				sheet.addCell(l);
				//add Title
				int column = 0;
				l = new Label(column++, 2, "姓名", titleFormat);
				//System.out.println("+++++++++++姓名++++++++");
				sheet.addCell(l);
				l = new Label(column++, 2, "电话", titleFormat);
				sheet.addCell(l);
				//l=new Label(column++, 2, "货币", titleFormat);
				//sheet.addCell(l);
				//l=new Label(column++, 2, "价格", titleFormat);
				//sheet.addCell(l); 
				//add detail
				int i = 3;
				column = 0;
				for (i = 3; foldersit.hasNext(); i++) {
					abfvo = (AddressbookAllSearchVO) foldersit.next();
					if (abfvo != null) {

						// System.out.println("+++++++column+++++++"+column+i);
						//System.out.println("+++++++getAbfFlag+++++++"+abfvo.getAbfFlag());
						if (("1").equals(abfvo.getAbfFlag())) {
							//System.out.println("+++++++this is  first layer +++++++"+abfvo.getAbfId()+column+i);
							if (i > 50) {
								i = 3;
								column = column + 2;
								l = new Label(column, 2, "姓名", titleFormat);

								sheet.addCell(l);
								l = new Label(column + 1, 2, "电话", titleFormat);
								sheet.addCell(l);

							}
							l = new Label(column, i, abfvo.getAbfName(), detFormat);
							sheet.addCell(l);
							Integer haschildabf = abfvo.getAbfId();
							/*若该分组有子分组进入子分组写入表格*/
							if (handler.hasChildinfo(haschildabf)) {

								//System.out.println("+++++++getAbfId()+++++++"+haschildabf);
								writeexcel writechild = new writeexcel(conn);
								writechild.setsheet(sheet);
								writechild.setl(l);
								writechild.setdetFormat(detFormat);
								writechild.settitleFormat(titleFormat);
								System.out.println("+++++++this entering the while haschildabf+++++++" + haschildabf + column + i);
								haschildabf = writechild.writeexcel(column, i, haschildabf);
								column = writechild.getcolumn();
								i = writechild.getrow();
							}
							/*while(handler.hasChild(haschildabf)){
								System.out.println("+++++++new is enterint the while ++++++");
								
								haschildabfList=handler.getchildList(haschildabf);
								Iterator childabfit = haschildabfList.iterator();
								for( j=i+1;childabfit.hasNext();j++){
									childabfvo = (AddressbookFolderVO) childabfit.next();
									if(("1").equals(childabfvo.getAbfFlag())){
										System.out.println("+++++++has child abf+++++++");
										System.out.println("+++++++column_j+++++++"+column+j);
										l=new Label(column, j,childabfvo.getAbfName() , detFormat);
										sheet.addCell(l);
										haschildabf=childabfvo.getAbfId();
										System.out.println("+++++++there is childabf haschildabf+++++++"+haschildabf);
										}
									else{
										childabcList=handler.getdetailFileList(childabfvo.getAbfId());
										System.out.println("+++++++there is childabc+++++++");
										System.out.println("+++++++column_j+++++++"+column+j);
										Iterator childabcit = childabcList.iterator();
										if(childabcit.hasNext()){
											childabcvo = (AddressbookContentVO) childabcit.next();
											l=new Label(column, j,childabcvo.getAbcName() , detFormat);
											sheet.addCell(l);
											l=new Label(column+1, j,"(c)"+childabcvo.getAbcCellphone(), detFormat);
											sheet.addCell(l);
											l=new Label(column+1, j+1,"(o)"+childabcvo.getAbcCompanytel(), detFormat);
											sheet.addCell(l);
											l=new Label(column+1, j+2,"(f)"+childabcvo.getAbcFamilytel(), detFormat);
											sheet.addCell(l);
											i=i+2;
											//haschildabf=childabcvo.getAddAbfcId();
											System.out.println("+++++++there is childabc haschildabf+++++++"+haschildabf);
										}
							
									}
									
								}
								i=j+1;
								System.out.println("+++++++new is outing the while ++++++");
													
								//haschildabf=writeChild( column, i, haschildabf, sheet, l, detFormat);
							    }
								*/

						} else {
							addressbooksList = handler.getdetailFileList(abfvo.getAbfId());
							Iterator addressbooksit = addressbooksList.iterator();
							if (addressbooksit.hasNext()) {
								abcvo = (AddressbookContentVO) addressbooksit.next();
								if (i > 50) {
									i = 3;
									column = column + 2;
									l = new Label(column, 2, "姓名", titleFormat);

									sheet.addCell(l);
									l = new Label(column + 1, 2, "电话", titleFormat);
									sheet.addCell(l);

								}
								l = new Label(column, i, abcvo.getAbcName(), detFormat);
								sheet.addCell(l);
								if (i > 50) {
									i = 3;
									column = column + 2;
									l = new Label(column, 2, "姓名", titleFormat);

									sheet.addCell(l);
									l = new Label(column + 1, 2, "电话", titleFormat);
									sheet.addCell(l);

								}
								l = new Label(column + 1, i, "(c)" + abcvo.getAbcCellphone(), detFormat);
								sheet.addCell(l);
								if (i > 50) {
									i = 3;
									column = column + 2;
									l = new Label(column, 2, "姓名", titleFormat);

									sheet.addCell(l);
									l = new Label(column + 1, 2, "电话", titleFormat);
									sheet.addCell(l);

								}
								l = new Label(column + 1, i + 1, "(o)" + abcvo.getAbcCompanytel(), detFormat);
								sheet.addCell(l);
								if (i > 50) {
									i = 3;
									column = column + 2;
									l = new Label(column, 2, "姓名", titleFormat);

									sheet.addCell(l);
									l = new Label(column + 1, 2, "电话", titleFormat);
									sheet.addCell(l);

								}
								l = new Label(column + 1, i + 2, "(f)" + abcvo.getAbcFamilytel(), detFormat);
								sheet.addCell(l);
								if (i > 50) {
									i = 3;
									column = column + 2;
									l = new Label(column, 2, "姓名", titleFormat);

									sheet.addCell(l);
									l = new Label(column + 1, 2, "电话", titleFormat);
									sheet.addCell(l);

								}
								l = new Label(column + 1, i + 3, "(e)" + abcvo.getAbcEmail(), detFormat);
								sheet.addCell(l);
								i = i + 3;
							}

						}
					}

				}

				/*/d=new DateTime(column++, i+3, new java.util.Date(), dateFormat);
				sheet.addCell(d);
				l=new Label(column++, i+3, "CNY", detFormat);
				sheet.addCell(l);
				n=new jxl.write.Number(column++, i+3, 5.678, priceFormat);
				sheet.addCell(n); 
				i++;
				column=0;
				l=new Label(column++, i+3, "标题"+i, detFormat);
				sheet.addCell(l);
				d=new DateTime(column++, i+3, new java.util.Date(), dateFormat);
				sheet.addCell(d);
				l=new Label(column++, i+3, "SGD", detFormat);
				sheet.addCell(l);
				n=new jxl.write.Number(column++, i+3, 98832, priceFormat);
				sheet.addCell(n);
				*/
				//设置列的宽度
				//column=0;
				//sheet.setColumnView(column++, 20);
				//sheet.setColumnView(column++, 20);
				//sheet.setColumnView(column++, 10);
				//sheet.setColumnView(column++, 20); 
				workbook.write();
				workbook.close();

				in = new FileInputStream(path + "/" + abmvo.getAbmId() + "addressbook.xls");
				AddressbookManagerVO inabmvo = new AddressbookManagerVO();
				inabmvo.setAbmAccessory(in);
				//inabmvo.setAbmId(abmvo.getAbmId());
				AddressbookManagerVO getabmvo = new AddressbookManagerVO();
				handler.inaccessory(inabmvo, abmvo.getAbmId());
				List getabmList = handler.getUserList(userId);
				Iterator getabmit = getabmList.iterator();
				if (getabmit.hasNext()) {
					getabmvo = (AddressbookManagerVO) getabmit.next();
				}

				is = getabmvo.getAbmAccessory();
				String fileName = getabmvo.getAbmId() + "addressbook" + ".xls";
				DownloadResponse ds = new DownloadResponse(response);
				//					ds.downloadInputStream(is, fileName);
				ds.downloadInputStreamByTempfile(is, fileName);
				tempFile.delete();

			}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("AddressbookPrintServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				if (is != null)
					is.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				if (in != null)
					in.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
