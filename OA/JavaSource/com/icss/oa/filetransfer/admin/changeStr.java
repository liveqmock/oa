package com.icss.oa.filetransfer.admin;

import java.util.StringTokenizer;

public class changeStr {

    private static int b2i(byte n) {
        int m = n;
        if (n < 0) {
            m = n + 256;
            return m;
        }
        return m;
    }

    private static byte i2b(int n) {
        byte m = (byte) n;
        if (n > 127) {
            m = (byte) (n - 256);
            return m;
        }
        return m;
    }

    private static byte[] getOutputList(byte[] m) { //过滤byte数组中为0的元素
        int len = m.length;
        StringBuffer temp = new StringBuffer(1024);
        for (int i = 0; i < len - 1; i++) {
            if (m[i] != 0) {
                temp.append(m[i]);
                temp.append(",");
            }
        }
        temp.append(m[len - 1]);
        StringTokenizer st = new StringTokenizer(temp.toString(), ",");
        int total = st.countTokens();
        byte[] newList = new byte[total];
        for (int i = 0; i < total; i++) {
            newList[i] = (byte) Integer.parseInt(st.nextToken());
        }
        return newList;

    }
    
    public static String change(String oldStr){
        
        int n = oldStr.getBytes().length;
        byte[] strList = new byte[n + 1];
        for (int j = 0; j < n; j++) {
            strList[j] = oldStr.getBytes()[j];
        }
        byte[] newList = new byte[n];
        int c1, c2;
        for (int i = 0; i < newList.length; i++) {
            c1 = b2i(strList[i]);
            c2 = b2i(strList[i + 1]);

            if (c1 == 163) { //判断是否为全角字符
                newList[i] = i2b(c2 - 128);
                i++;
                continue;
            }

            if (c1 > 163) { //判断是否为汉字
                newList[i] = i2b(c1);
                newList[i + 1] = i2b(c2);
                i++;
                continue;
            }

            if ((c1 == 161) && (c2 == 161))//全角空格是个特例，另加处理
            {
                newList[i] = 32;
                i++;
                continue;
            }
            newList[i] = i2b(c1);

        }

        byte[] outputList = getOutputList(newList);
        
        return new String(outputList);
        
    }
    
    public static void  main (String sgr[]){
    	
    	System.out.println(change("（）"));
    	
    }

}
