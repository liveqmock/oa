/*
 * Created on 2004-11-11
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test;


import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExcelHandler {

	/**
	 * 
	 */
	public ExcelHandler() {
	}
	
	public void setCellCenterAlign(WritableCellFormat cellformat){
		try {
			cellformat.setAlignment(jxl.format.Alignment.CENTRE);
			cellformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		} catch (WriteException e) {
			System.out.println("…Ë÷√æ”÷–≥ˆ¥Ì£°");
		}
	}

}
