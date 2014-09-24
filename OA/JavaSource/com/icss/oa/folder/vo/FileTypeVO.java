/*
 * Created on 2004-3-4
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.folder.vo;
import com.icss.j2ee.vo.ValueObject;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FileTypeVO extends ValueObject {
	
	private String fileType;
	private String filePic;
	
	public String getFileType() {
		return fileType;
	}
	public String getFilePic() {
		return filePic;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setFilePic(String filePic) {
		this.filePic = filePic;
	}

}
