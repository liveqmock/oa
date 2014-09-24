/*
 * Created on 2004-9-18
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgSendSearchVO extends ValueObject {
	private String cnname;
	private String ocnname;
	private Integer msMid;
	private Integer msId;
	private String msPersonuuid;
	private String msOrguuid;
	private Long msDate;
	private String msContent;
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String _cnname) {
		cnname = _cnname;
	}
	public String getOcnname() {
		return ocnname;
	}
	public void setOcnname(String _ocnname) {
		ocnname = _ocnname;
	}
	public Integer getMsMid() {
		return msMid;
	}
	public void setMsMid(Integer _msMid) {
		msMid = _msMid;
	}
	public Integer getMsId() {
		return msId;
	}
	public void setMsId(Integer _msId) {
		msId = _msId;
	}
	public String getMsPersonuuid() {
		return msPersonuuid;
	}
	public void setMsPersonuuid(String _msPersonuuid) {
		msPersonuuid = _msPersonuuid;
	}
	public String getMsOrguuid() {
		return msOrguuid;
	}
	public void setMsOrguuid(String _msOrguuid) {
		msOrguuid = _msOrguuid;
	}
	public Long getMsDate() {
		return msDate;
	}
	public void setMsDate(Long _msDate) {
		msDate = _msDate;
	}
	public String getMsContent() {
		return msContent;
	}
	public void setMsContent(String _msContent) {
		msContent = _msContent;
	}
}