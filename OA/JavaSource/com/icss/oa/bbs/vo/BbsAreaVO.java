package com.icss.oa.bbs.vo;

import com.icss.j2ee.vo.ValueObject;

public class BbsAreaVO extends ValueObject {
	private Integer areaid;
	private String areades;
	private String areaname;
	private String arearight;
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer _areaid) {
		areaid = _areaid;
	}
	public String getAreades() {
		return areades;
	}
	public void setAreades(String _areades) {
		areades = _areades;
	}
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String _areaname) {
		areaname = _areaname;
	}
	public java.lang.String getArearight(){
		return arearight;
	}
	public void setArearight(String arearight){
		this.arearight = arearight;
	}
}