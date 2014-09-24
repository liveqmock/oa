/*
 * Created on 2004-3-31
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.journal.vo;




import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeJournalVO extends ValueObject {
	Integer rjId;
	Long rjDate;
	String rjHeadline;
	String rjContent;
	String rjWeather;
	String rjOwner;
	public void setRjId(Integer _rjId) {
		rjId = _rjId;
	}
	public Integer getRjId() {
		return rjId;
	}
	public void setRjDate(Long _rjDate) {
		rjDate = _rjDate;
	}
	public Long getRjDate() {
		return rjDate;
	}
	public void setRjHeadline(String _rjHeadline) {
		rjHeadline = _rjHeadline;
	}
	public String getRjHeadline() {
		return rjHeadline;
	}
	public void setRjContent(String _rjContent) {
		rjContent = _rjContent;
	}
	public String getRjContent() {
		return rjContent;
	}
	public void setRjWeather(String _rjWeather) {
		rjWeather = _rjWeather;
	}
	public String getRjWeather() {
		return rjWeather;
	}
	public void setRjOwner(String _rjOwner) {
		rjOwner = _rjOwner;
	}
	public String getRjOwner() {
		return rjOwner;
	}
}
