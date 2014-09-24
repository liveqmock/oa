package com.fredck.FCKeditor;

import java.io.*;

public class IOObject implements Serializable {
    private static final long serialVersionUID = 1L;
    public String[] strs;

    public IOObject() {
        strs = new String[3];
        strs[0] = "XXXX";
        strs[1] = "Huang";
        strs[2] = "Jorneyr";
    }

    public String toString() {
        String str;
        str = strs[0] + ", " + strs[1] + ", " + strs[2];

        return str;
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("Object.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        IOObject tout = new IOObject();
        tout.strs[0] = "YYYYY XXXXX";
        oos.writeObject(tout);

        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("Object.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);

        IOObject tin;
        tin = (IOObject) ois.readObject();
        System.out.println(tin);

        ois.close();
        fis.close();
    }
}

