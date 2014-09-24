import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;

public class moveto implements Runnable {
	int num = 0;
	File des = new File("D:\\mail\\des");
	File cur;
	File[] tobemove;

	public moveto(int num, File cur, File[] move, File des) {
		this.num = num;
		this.cur = cur;
		this.tobemove = move;
		this.des = des;
	}

	public void del(File[] all) {
		for (int k = 0; k < all.length; k++) {
			if (all[k].isDirectory() && (all[k].listFiles()).length != 0) {
				File[] suball = all[k].listFiles();
				del(suball);
				all[k].delete();
			} else {
				all[k].delete();
			}
		}
	}

	public void run() {
		System.out.println("第" + num + "用户:" + cur.getName() + "开始复制。。。。。。");
		long start = System.currentTimeMillis();
		File[] sub = des.listFiles();
		for (int i = 0; i < sub.length; i++) {
			if (sub[i].isDirectory()) {
				File[] user = sub[i].listFiles();
				for (int j = 0; j < user.length; j++) {
					if ((user[j].getName()).equals(cur.getName())) {
						String desp = sub[i].getAbsolutePath();
						String curp = cur.getAbsolutePath();
						// System.out.println(cur.getCanonicalPath());
						try {
							String co = "cp -a --reply=yes " + curp + " "
									+ desp;
							System.out.println(co);
							// Runtime.getRuntime().exec(co);

							Process process = Runtime.getRuntime().exec(co);

							InputStreamReader ir = new InputStreamReader(
									process.getErrorStream());
							BufferedReader input = new BufferedReader(ir);

							String line;
							while ((line = input.readLine()) != null) {
								System.out.println(line);
							}
							input.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// File[] all = user[j].listFiles();
						// del(all);
						// copyfile(user[j],tobemove);
						
					}
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("第" + num + "用户:" + cur.getName() + "复制完成！耗时"
				+ (end - start) + "毫秒");
	}

	private void copyfile(File dir, File[] tocopy) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			for (int i = 0; i < tocopy.length; i++) {
				if (tocopy[i].isDirectory()) {
					File newfolder = new File(dir.getPath() + File.separator
							+ tocopy[i].getName());
					if (!newfolder.exists()) {
						newfolder.mkdir();
					}
					copyfile(newfolder, tocopy[i].listFiles());
				} else {
					File newfile = new File(dir.getPath() + File.separator
							+ tocopy[i].getName());
					if (newfile.exists()) {
						newfile.delete();
					}
					br = new BufferedReader(new InputStreamReader(
							new FileInputStream(tocopy[i])));
					bw = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(dir.getPath() + File.separator
									+ tocopy[i].getName())));
					String line = null;
					while ((line = br.readLine()) != null) {
						bw.write(line + "\r\n");
					}
					bw.flush();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
