/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.addressbook.admin;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookContentVO;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class writeexcel {
	private Connection conn;

	public String filepath = null;
	List chabfList = null;
	List chabcList = null;
	AddressbookFolderVO chabfvo = null;
	AddressbookContentVO chabcvo = null;
	Label l = null;
	WritableFont detFont;
	WritableCellFormat detFormat;
	WritableSheet sheet;
	WritableCellFormat titleFormat;
	int returncolumn;
	int returnrow;
	int changecolumn;
	int changerow;

	public writeexcel(Connection conn) {
		this.conn = conn;
	}
	public void setl(Label _l) {
		l = _l;

	}
	public void setdetFormat(WritableCellFormat _detFormat) {
		detFormat = _detFormat;

	}
	public void setsheet(WritableSheet _sheet) {
		sheet = _sheet;

	}
	public void settitleFormat(WritableCellFormat _titleFormat) {
		titleFormat = _titleFormat;

	}
	public Integer writeexcel(int column, int i, Integer haschildabf) throws RowsExceededException, WriteException {

		AddressbookHandler handler = new AddressbookHandler(conn);
		chabfList = handler.getchildList(haschildabf);
		Iterator chabfit = chabfList.iterator();
		int j;
		//int i=row;
		for (j = i + 1; chabfit.hasNext(); j++) {
			chabfvo = (AddressbookFolderVO) chabfit.next();
			if (("1").equals(chabfvo.getAbfFlag())) {
				//打印出所有分组信息
				//System.out.println("+++++++has child abf+++++++");
				if (j > 50) {
					j = 3;
					column = column + 2;
					writetitle(sheet, l, detFormat, titleFormat, column);
				}

				l = new Label(column, j, chabfvo.getAbfName(), detFormat);
				sheet.addCell(l);
				haschildabf = chabfvo.getAbfId();
				//System.out.println("+++++++this is in the while abfolder column_j+++++++"+haschildabf+column+j);
				if (handler.hasChildinfo(haschildabf)) {
					System.out.println("+++++++there is still has childabf haschildabf+++++++" + haschildabf + column + j);
					writeexcel childwriteexcel = new writeexcel(conn);
					childwriteexcel.setsheet(sheet);
					childwriteexcel.setl(l);
					childwriteexcel.setdetFormat(detFormat);
					childwriteexcel.settitleFormat(titleFormat);
					haschildabf = childwriteexcel.writeexcel(column, j, haschildabf);
					column = childwriteexcel.getcolumn();
					j = childwriteexcel.getrow() - 1;
					System.out.println("++++++out out out " + column + j + haschildabf);
				}
			} else {
				chabcList = handler.getdetailFileList(chabfvo.getAbfId());
				System.out.println("+++++++there is childabc+++++++");
				System.out.println("+++++++column_j+++++++" + column + j);
				Iterator chabcit = chabcList.iterator();
				if (chabcit.hasNext()) {
					chabcvo = (AddressbookContentVO) chabcit.next();
					System.out.println("+++++++this is in the while abcontent column_j+++++++" + chabcvo.getAddAbfcId() + column + j);
					if (j > 50) {
						j = 3;
						column = column + 2;
						writetitle(sheet, l, detFormat, titleFormat, column);
					}
					l = new Label(column, j, chabcvo.getAbcName(), detFormat);
					sheet.addCell(l);
					if (j > 50) {
						j = 3;
						column = column + 2;
						writetitle(sheet, l, detFormat, titleFormat, column);
					}
					l = new Label(column + 1, j, "(c)" + chabcvo.getAbcCellphone(), detFormat);
					sheet.addCell(l);
					if (j > 50) {
						j = 3;
						column = column + 2;
						writetitle(sheet, l, detFormat, titleFormat, column);
					}
					l = new Label(column + 1, j + 1, "(o)" + chabcvo.getAbcCompanytel(), detFormat);
					sheet.addCell(l);
					if (j > 50) {
						j = 3;
						column = column + 2;
						writetitle(sheet, l, detFormat, titleFormat, column);
					}
					l = new Label(column + 1, j + 2, "(f)" + chabcvo.getAbcFamilytel(), detFormat);
					sheet.addCell(l);
					if (j > 50) {
						j = 3;
						column = column + 2;
						writetitle(sheet, l, detFormat, titleFormat, column);
					}
					l = new Label(column + 1, j + 3, "(e)" + chabcvo.getAbcEmail(), detFormat);
					sheet.addCell(l);
					j = j + 3;
					//haschildabf=childabcvo.getAddAbfcId();
					//System.out.println("+++++++there is childabc haschildabf+++++++"+haschildabf);
				}

			}

		}
		i = j;
		returncolumn = column;
		returnrow = i;
		System.out.println("+++++++there is outing while+++++++" + haschildabf + returncolumn + returnrow);

		return haschildabf;

	}
	public int getcolumn() {
		return returncolumn;
	}
	public int getrow() {

		return returnrow;
	}
	/*public int changcolumn(int _changecolumn) {
		changecolumn=_changecolumn+2;
		return changecolumn;
				}
	public int changrow(int _changerow) {
			changerow=_changerow;
			if(changerow>20){
				changerow=3;
				
			}
			return returnrow;
					}
					*/
	public void writetitle(WritableSheet sheet, Label l, WritableCellFormat detFormat, WritableCellFormat titleFormat, int column) throws RowsExceededException, WriteException {

		l = new Label(column, 2, "姓名", titleFormat);

		sheet.addCell(l);
		l = new Label(column + 1, 2, "电话", titleFormat);
		sheet.addCell(l);
	}

}
