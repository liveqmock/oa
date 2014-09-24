/*
 * 创建日期 2006-10-17
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.multivote.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class DEPARTMENT_CODE {
	public static final int DEP_NUM = 10;
	public static final String DEP_1 = "内设机构";
	public static final String DEP_2 = "直属事业";
	public static final String DEP_3 = "直属企业";
	public static final String DEP_4 = "华北地区";
	public static final String DEP_5 = "东北地区";
	public static final String DEP_6 = "华东地区";
	public static final String DEP_7 = "中南地区";
	public static final String DEP_8 = "西南地区";
	public static final String DEP_9 = "西北地区";
	public static final String DEP_10 = "驻外分社";
	public static final String GET_DEP_NAME(int i){
		switch(i){		
			case(0):
			return DEP_1;
			
			case(1):
			return DEP_2;
			
			case(2):
			return DEP_3;
			
			case(3):
			return DEP_4;
			
			case(4):
			return DEP_5;
			
			case(5):
			return DEP_6;
			
			case(6):
			return DEP_7;
			
			case(7):
			return DEP_8;
			
			case(8):
			return DEP_9;
			
			case(9):
				return DEP_10;
			
			default:
			return "ERROR!";
		}
	}
}
