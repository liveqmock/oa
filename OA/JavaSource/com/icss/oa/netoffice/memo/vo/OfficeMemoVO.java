/*
 * Created on 2004-5-11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class OfficeMemoVO extends ValueObject {
	Integer memoId;
	String memoContent;
	Long memoTime;
	String memoHeadline;
	String memoOwnerid;
	public void setMemoId(Integer _memoId) {
		memoId = _memoId;
	}
	public Integer getMemoId() {
		return memoId;
	}
	public void setMemoContent(String _memoContent) {
		memoContent = _memoContent;
	}
	public String getMemoContent() {
		return memoContent;
	}
	public void setMemoTime(Long _memoTime) {
		memoTime = _memoTime;
	}
	public Long getMemoTime() {
		return memoTime;
	}
	public void setMemoHeadline(String _memoHeadline) {
		memoHeadline = _memoHeadline;
	}
	public String getMemoHeadline() {
		return memoHeadline;
	}
	public void setMemoOwnerid(String _memoOwnerid) {
		memoOwnerid = _memoOwnerid;
	}
	public String getMemoOwnerid() {
		return memoOwnerid;
	}
}
