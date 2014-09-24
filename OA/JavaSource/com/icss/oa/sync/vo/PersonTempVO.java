package com.icss.oa.sync.vo;

import com.icss.j2ee.vo.ValueObject;

public class PersonTempVO extends ValueObject{
		Integer id;
	    String operateid;
	    String hrid;
		String personname;
	    String orgid;
		String deptid;
		String userid;
		String operatetype;
		Integer isuniq;
		Integer isright;
		Integer approved;
		Integer tohr;
		Integer totq;
		Integer tomail;
		String origin;
		String purpose;
		String describe;
		String alterfield;
		String mobilephone;
		String serialindex;
		String time;

		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer _id) {
			id = _id;
		}
	 
		public String getSerialindex() {
			return serialindex;
		}
		public void setSerialindex(String serialindex) {
			this.serialindex = serialindex;
		}
		public String getOperateid(){
			return operateid;
		}
		public void setOperateid(String _operateid){
			operateid =_operateid;
		}
		 
		public String getHrid() {
			return hrid;
		}
		public void setHrid(String _hrid) {
			hrid = _hrid;
		}
		public String getPersonname() {
			return personname;
		}
		public void setPersonname(String _personname) {
			personname = _personname;
		}
	 
		public String getOrgid() {
			return  orgid;
		}
		public void setOrgid(String _orgid) {
			orgid = _orgid;
		}
	 
		 
		 
		public String getDeptid() {
			return deptid;
		}
		public void setDeptid(String _deptid) {
			deptid = _deptid;
		}
		
		 
		public String getUserid() {
			return userid;
		}
		public void setUserid(String _userid) {
			userid = _userid;
		}
		
		
		public String getOperatetype() {
			return operatetype;
		}
		public void setOperatetype(String _operatetype) {
			operatetype = _operatetype;
		}
		
		public Integer getIsuniq() {
			return isuniq;
		}
		public void setIsuniq(Integer _isuniq) {
			isuniq = _isuniq;
		}
		
		public Integer getIsright() {
			return isright;
		}
		public void setIsright(Integer _isright) {
			isright = _isright;
		}
		
		public Integer getApproved() {
			return approved;
		}
		public void setApproved(Integer _approved) {
			approved = _approved;
		}
		
		public Integer getTohr() {
			return tohr;
		}
		public void setTohr(Integer _tohr) {
			tohr = _tohr;
		}
		
		public Integer getTotq() {
			return totq;
		}
		public void setTotq(Integer _totq) {
			totq = _totq;
		}
		
		public Integer getTomail() {
			return tomail;
		}
		public void setTomail(Integer _tomail) {
			tomail = _tomail;
		}
		
		public String getOrigin(){
			return origin;
		}
		
		public void setOrigin(String _origin){
			origin = _origin;
		}
		
		
		public String getPurpose(){
			return purpose;
		}
		public void setPurpose(String _purpose){
			purpose = _purpose;
			
		}
		public String getDescribe(){
			return describe;
		}
		public void setDescribe(String _describe){
			describe = _describe;
			
		}
		public String getAlterfield(){
			return alterfield;
		}
		public void setAlterfield(String _alterfield){
			alterfield = _alterfield;
			
		}
		
		public String getMobilephone(){
			return mobilephone;
		}
		public void setMobilephone(String _mobilephone){
			mobilephone = _mobilephone;
			
		}
	 
 }