package com.icss.oa.workmeeting.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.RightException;
import com.icss.resourceone.sdk.right.Role;

/**
 * 得到组织的uuid,将本组织下所有人的信息都放入session中
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ImpBatchRoleWorkingMeetingPerson extends ServletBase {

	protected void performTask(
		HttpServletRequest arg0,
		HttpServletResponse arg1)
		throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Role role = new Role();
		role.setRoleid(new Integer(189)); //189为社工作会议的id
		try {
			role = role.getInstanceByKey();
		} catch (RightException e1) {
			e1.printStackTrace();
		}

		List personlist = new ArrayList();
		String personuuid = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			String url = "jdbc:oracle:thin:@10.102.1.200:1521:orcl";
			//String url="jdbc:oracle:thin:@192.9.100.25:1521:orcl";
			//								  orcl为你的数据库的SID 
			String user = "roeee";
			String password = "roeee";
			conn = DriverManager.getConnection(url, user, password);
			ConnLog.open("ImpBatchRoleWorkingMeetingPerson");
			//String  sql= " select personuuid from ro_person where cnname not like'%分社%' and cnname not like'%子库%' and cnname not like'%用户%' ";
			String sql =
				" select personuuid from ro_person where cnname in('方义农','贺晓林','刘德玉','多吉占堆','施勇峰','郑国新','陈建玉','姚立','刘金寿','于苏生','王启恩','费新春','龙松林','石加铃','王丁','山生峰','陆骏','孙晓临','骆国骏','王文杰','夏林','吴锦才','崔俐莎','孙连生','卢仲云','戚德良','孔祥迎','解国记','姜占国','赵磊','肖思维','何大新','康长兴','刘子富','李贵森','顾筑胜','杜跃进','刘绍芝','宁晨新','姚光','党周','王宏伟','马建中','陈良杰','罗辉','国建军','邓柄根','王言彬','康松','欧阳晓晴','王春福','刘欣欣','何洪锡','李凤双','葛相文','陈国齐','景旭蜂','袁小平','梁相斌','胥寿宏','李洪峰','雷中原','姬斌','林晨','王魁','朱玉泉','刘庆禄','邢桂华','宁世群','杜文峰','万武义','于振海','蒲立业','吴国清','孙杰','马义','赵力','陈鹤高','牟维旭','凌德权','端木来娣','姜军','要力石','邹云','张浩','何大龙','赖官宝','任明','华桂勤','周锡生','张民华','张铁柱','关文','张百新','李志勇','陈雅妮','任卫东','方政军','苏玲','蒋志敏','王运才','张铁钢','陈云珍','王坚','岳春茂','范世如','樊英利','杨树勋','朱国贤','郭献文','王正忠','赵志祥','沙黾农','于磊焰','赵竹修','陆小华','魏紫川','方小翔','徐祖根','祖因春','徐步','杨廷建','刘东山','林毅慧','刘洁','方立新','陈凯星','关心国','徐金鹏','李锦','王进业','高欣','周树春','李春雷','高殿民','慎海雄','王国华','孙明华','王希孟','王仲明','杨健','戴玉松','杨维成','崔景明','高连涛','刘敏','冯小舢','陈乃进','李承祖','黄玉书','臧公禹','曹士忠','周勇','孙志平','张锦','徐军峰','赵士敏','董瑞生','崔济哲','孙燕','徐长银','虞家复','费强','杨继刚','吴劲秋','楮言义','田舒斌','庞伟华','于绍良','陆维新','刘建军','夏俊生','李红旗','李善远','王海征','韩战辉','谢国华','杜晓明','华卫列','翁学仁','王耕','高淑华','许基仁','郑景胜','闫振国','连志','王朝文','陈磊','冯诚','曹学会','杜新','刘宪生','段国选','苏民生','张持坚','陈新洲','张伟弟','闫德清','邓久翔','张彬','赵永法','张健','张正宪','卓培荣','张晔','王树成','冯瑛冰','俱孟军','景如月','刘江','彭树杰','刘伟','张宿堂','姜在忠','刘思扬','谭俊林','胡俊凯','曾虎','高庆林','栗金孚','焦然','王启星','陈桂林','周宗敏','高力','王志文','熊小立','陈明祥','田晔','王存理','冉文祥','熊迪强','周晓农','杨真','侯严峰','龚剑','房方','王春生','崔燮钧','史春生','刘士华','王增海','段芝璞','罗燕军','胡惠忠','卢殿洛','金小明','唐卫彬','冯全新','黄远传','林楠','汤华','陈必将','郑青吉','谢胜和','杨春南','袁海兴','郭力群','鹿继林','鲁炜','阮大华','储学军','陆骏','调研室','报值班简报稿','董瑞生','赵志祥','欧阳晓晴','刘欣欣','杨继刚','陈雅妮','陈国齐','任卫东','陈建玉','康松','付绪山','姚立','刘金寿','张佑山','于苏生','王启恩','费新春','龙松林','张首第','张正宪','李仁虎','吴永恒','徐丰','马晓霖','陈俊','石加铃','高长富','王丁','山生峰','陆骏','孙晓临','赵永法','詹得雄','李烽','刘仙鹏','陆士新','段吉勇','黄国文','吕金铃','张小军','王文杰','夏林','吴锦才','崔俐莎','孙连生','卢仲云','戚德良','孔祥迎','解国记','姜占国','赵磊','肖思维','何大新','康长兴','刘子富','顾筑胜','杜跃进','刘绍芝','宁晨新','姚光','党周','王宏伟','陈良杰','国建军','邓柄根','王言彬','邓全施','方义农','杨玉良','王春福','贾永','何洪锡','邓久翔','丁玫','张彬','葛相文','袁小平','梁相斌','胥寿宏','李洪峰','雷中原','姬斌','林晨','王魁','朱玉泉','刘庆禄','宁世群','杜文峰','万武义','于振海','蒲立业','吴国清','孙杰','马义','赵力','陈鹤高','牟维旭','端木来娣','姜军','要力石','邹云','张浩','何大龙','岳春茂','赖官宝','任明','华桂勤','周锡生','张民华','范世如','张铁柱','关文','张百新','李志勇','方政军','苏玲','蒋志敏','陈云珍','王坚','樊英利','成建','方新','陈玩娟','何懋绩','蒋鹏','郎杰','梁贵和','申启芳','林晓君','刘洁','刘顺','李志高','马小林','邵军','申尊敬','苏会志','汤一鸣','王春荣','王发恩','王敬诚','王晓光','魏作清','王礼贶','王林','王树柏','吴锡俊','吴毅宏','肖庆民','高萍','杨树勋','沙黾农','朱国贤','杨溟','郭献文','鱼世昌','于磊焰','臧公禹','张非非','张锦','王正忠','张晓华','张行端','张晔','张永平','赵竹修','周联成','周顺敖','朱云龙','卓培荣','宗焕平','陆小华','方小翔','徐祖根','罗海岩','祖因春','徐步','杨廷建','刘东山','林毅慧','刘洁','方立新','陈凯星','关心国','徐江善','徐金鹏','李锦','王进业','高欣','周树春','孙本尧','高殿民','慎海雄','王国华','曹国强','王希孟','王仲明','戴玉松','杨健','杨维成','崔景明','高连涛','刘敏','黄国柱','杨民青','王景和','冯小舢','陈乃进','李承祖','黄玉书','周勇','孙志平','徐军峰','马忠志','王建国','崔济哲','孙燕','徐长银','费强','虞家复','吴劲秋','田舒斌','楮言义','于绍良','陆维新','冮冶','许群','李永长','张松青','罗祖权','曹绍平','沈祖润','黄慧珠','刘建军','夏俊生','李红旗','李善远','王海征','韩战辉','谢国华','杜晓明','华卫列','翁学仁','王耕','许基仁','郑景胜','闫振国','陈磊','冯诚','曹学会','杜新','刘宪生','段国选','苏民生','陈新洲','闫德清','张健','张金海','王树成','冯瑛冰','俱孟军','景如月','刘江','彭树杰','刘伟','张宿堂','姜在忠','刘思扬','谭俊林','曾虎','高庆林','栗金孚','焦然','陈桂林','周宗敏','高力','熊小立','陈明祥','冉文祥','熊迪强','周晓农','杨真','侯严峰','王春生','刘士华','王增海','段芝璞','罗燕军','胡惠忠','卢殿洛','金小明','林楠','冯全新','汤华','陈必将','郑青吉','谢胜和','杨春南','景旭蜂','袁海兴','郭力群','鹿继林','郑国新','刘德玉','多吉占堆')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				personuuid = rs.getString(1);
				Person p = new Person();
				p.setUuid(personuuid);
				personlist.add(p);
			}

			this.forward(
				arg0,
				arg1,
				"/include/errorString.jsp?errorS=分配社工作会议权限成功！");
		} catch (Exception e) {
			e.printStackTrace();
			
			
		} finally {
			
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
			
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
			
			if(conn!=null){
				try {
					conn.close();
					ConnLog.close("ImpBatchRoleWorkingMeetingPerson");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		try {
			role.addPersons(personlist);
		} catch (RightException e2) {
			e2.printStackTrace();
		}

		System.out.println("分配社工作会议权限成功");
		
		
	}
	
	
	
}





