/*
 * Created on 2004-6-21
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.statsite.dao;

import java.sql.Connection;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StatSiteDate1DAO extends DAO {

	private Long id;
	private Long h0;
	private Long h1;
	private Long h2;
	private Long h3;
	private Long h4;
	private Long h5;
	private Long h6;
	private Long h7;
	private Long h8;
	private Long h9;
	private Long h10;
	private Long h11;
	private Long h12;
	private Long h13;
	private Long h14;
	private Long h15;
	private Long h16;
	private Long h17;
	private Long h18;
	private Long h19;
	private Long h20;
	private Long h21;
	private Long h22;
	private Long h23;
	private Long visDate;
	public Long getId() {
		return id;
	}
	public void setId(Long _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}
	public Long getH0() {
		return h0;
	}
	public void setH0(Long _h0) {
		firePropertyChange("h0", h0, _h0);
		h0 = _h0;
	}
	public Long getH1() {
		return h1;
	}
	public void setH1(Long _h1) {
		firePropertyChange("h1", h1, _h1);
		h1 = _h1;
	}
	public Long getH2() {
		return h2;
	}
	public void setH2(Long _h2) {
		firePropertyChange("h2", h2, _h2);
		h2 = _h2;
	}
	public Long getH3() {
		return h3;
	}
	public void setH3(Long _h3) {
		firePropertyChange("h3", h3, _h3);
		h3 = _h3;
	}
	public Long getH4() {
		return h4;
	}
	public void setH4(Long _h4) {
		firePropertyChange("h4", h4, _h4);
		h4 = _h4;
	}
	public Long getH5() {
		return h5;
	}
	public void setH5(Long _h5) {
		firePropertyChange("h5", h5, _h5);
		h5 = _h5;
	}
	public Long getH6() {
		return h6;
	}
	public void setH6(Long _h6) {
		firePropertyChange("h6", h6, _h6);
		h6 = _h6;
	}
	public Long getH7() {
		return h7;
	}
	public void setH7(Long _h7) {
		firePropertyChange("h7", h7, _h7);
		h7 = _h7;
	}
	public Long getH8() {
		return h8;
	}
	public void setH8(Long _h8) {
		firePropertyChange("h8", h8, _h8);
		h8 = _h8;
	}
	public Long getH9() {
		return h9;
	}
	public void setH9(Long _h9) {
		firePropertyChange("h9", h9, _h9);
		h9 = _h9;
	}
	public Long getH10() {
		return h10;
	}
	public void setH10(Long _h10) {
		firePropertyChange("h10", h10, _h10);
		h10 = _h10;
	}
	public Long getH11() {
		return h11;
	}
	public void setH11(Long _h11) {
		firePropertyChange("h11", h11, _h11);
		h11 = _h11;
	}
	public Long getH12() {
		return h12;
	}
	public void setH12(Long _h12) {
		firePropertyChange("h12", h12, _h12);
		h12 = _h12;
	}
	public Long getH13() {
		return h13;
	}
	public void setH13(Long _h13) {
		firePropertyChange("h13", h13, _h13);
		h13 = _h13;
	}
	public Long getH14() {
		return h14;
	}
	public void setH14(Long _h14) {
		firePropertyChange("h14", h14, _h14);
		h14 = _h14;
	}
	public Long getH15() {
		return h15;
	}
	public void setH15(Long _h15) {
		firePropertyChange("h15", h15, _h15);
		h15 = _h15;
	}
	public Long getH16() {
		return h16;
	}
	public void setH16(Long _h16) {
		firePropertyChange("h16", h16, _h16);
		h16 = _h16;
	}
	public Long getH17() {
		return h17;
	}
	public void setH17(Long _h17) {
		firePropertyChange("h17", h17, _h17);
		h17 = _h17;
	}
	public Long getH18() {
		return h18;
	}
	public void setH18(Long _h18) {
		firePropertyChange("h18", h18, _h18);
		h18 = _h18;
	}
	public Long getH19() {
		return h19;
	}
	public void setH19(Long _h19) {
		firePropertyChange("h19", h19, _h19);
		h19 = _h19;
	}
	public Long getH20() {
		return h20;
	}
	public void setH20(Long _h20) {
		firePropertyChange("h20", h20, _h20);
		h20 = _h20;
	}
	public Long getH21() {
		return h21;
	}
	public void setH21(Long _h21) {
		firePropertyChange("h21", h21, _h21);
		h21 = _h21;
	}
	public Long getH22() {
		return h22;
	}
	public void setH22(Long _h22) {
		firePropertyChange("h22", h22, _h22);
		h22 = _h22;
	}
	public Long getH23() {
		return h23;
	}
	public void setH23(Long _h23) {
		firePropertyChange("h23", h23, _h23);
		h23 = _h23;
	}
	public Long getVisDate() {
		return visDate;
	}
	public void setVisDate(Long _visDate) {
		firePropertyChange("visDate", visDate, _visDate);
		visDate = _visDate;
	}
	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("h0", "H0");
		addField("h1", "H1");
		addField("h2", "H2");
		addField("h3", "H3");
		addField("h4", "H4");
		addField("h5", "H5");
		addField("h6", "H6");
		addField("h7", "H7");
		addField("h8", "H8");
		addField("h9", "H9");
		addField("h10", "H10");
		addField("h11", "H11");
		addField("h12", "H12");
		addField("h13", "H13");
		addField("h14", "H14");
		addField("h15", "H15");
		addField("h16", "H16");
		addField("h17", "H17");
		addField("h18", "H18");
		addField("h19", "H19");
		addField("h20", "H20");
		addField("h21", "H21");
		addField("h22", "H22");
		addField("h23", "H23");
		addField("visDate", "VIS_DATE");
		setTableName("STAT_SITE_DATE1");
		addKey("ID");
		setAutoIncremented("ID");
	}
	public StatSiteDate1DAO(Connection conn) {
		super(conn);
	}
	public StatSiteDate1DAO() {
		super();
	}
}
