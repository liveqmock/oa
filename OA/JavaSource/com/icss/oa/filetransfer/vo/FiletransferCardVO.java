/*
 * Created on 2004-12-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.filetransfer.vo;

import java.io.InputStream;
import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FiletransferCardVO extends ValueObject {
	Integer cardid;
	String cardname;
	InputStream cardstream;
	String filename;
	String sortid;
	Long creattime;
	String deltag;
	String uploadperson;
	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer _cardid) {
		cardid = _cardid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String _cardname) {
		cardname = _cardname;
	}
	public InputStream getCardstream() {
		return cardstream;
	}
	public void setCardstream(InputStream _cardstream) {
		cardstream = _cardstream;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String _filename) {
		filename = _filename;
	}
	public String getSortid() {
		return sortid;
	}
	public void setSortid(String _sortid) {
		sortid = _sortid;
	}
	public Long getCreattime() {
		return creattime;
	}
	public void setCreattime(Long _creattime) {
		creattime = _creattime;
	}
	public String getDeltag() {
		return deltag;
	}
	public void setDeltag(String _deltag) {
		deltag = _deltag;
	}
	public String getUploadperson() {
		return uploadperson;
	}
	public void setUploadperson(String _uploadperson) {
		uploadperson = _uploadperson;
	}
}