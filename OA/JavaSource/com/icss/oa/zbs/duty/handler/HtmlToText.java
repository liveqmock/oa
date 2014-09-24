package com.icss.oa.zbs.duty.handler;

public class HtmlToText {
	private static String tag = "GO", title = "";

	public static String title() {
		return title;
	}

	public static String htmlTotext(String s) {
		StringBuffer sb = new StringBuffer();
		int pos;
		while (s != null) {
			if (tag.equals("GO")) {
				if ((pos = s.toUpperCase().indexOf("<TITLE>")) != -1) {
					tag = "TITLE";
					sb.append(s.substring(0, pos));
					if (s.length() > pos + 7) {
						s = s.substring(pos + 7);
					} else {
						s = null;
					}
				} else if ((pos = s.toUpperCase().indexOf("<SCRIPT")) != -1) {
					tag = "SCRIPT";
					sb.append(s.substring(0, pos));
					if (s.length() > pos + 7) {
						s = s.substring(pos + 7);
					} else {
						s = null;
					}
				} else if ((pos = s.toUpperCase().indexOf("<STYLE")) != -1) {
					tag = "STYLE";
					sb.append(s.substring(0, pos));
					if (s.length() > pos + 6) {
						s = s.substring(pos + 6);
					} else {
						s = null;
					}
				} else if ((pos = s.toUpperCase().indexOf("<!--")) != -1) {
					tag = "!--";
					sb.append(s.substring(0, pos));
					if (s.length() > pos + 4) {
						s = s.substring(pos + 4);
					} else {
						s = null;
					}
				} else if ((pos = s.toUpperCase().indexOf("<")) != -1) {
					tag = "<";
					sb.append(s.substring(0, pos));
					if (s.length() > pos + 1) {
						s = s.substring(pos + 1);
					} else {
						s = null;
					}
				} else {
					sb.append(s);
					s = null;
				}
			} else if (tag.equals("TITLE")) {
				if ((pos = s.toUpperCase().indexOf("</TITLE>")) != -1) {
					title = s.substring(0, pos);
					if (s.length() > pos + 8) {
						s = s.substring(pos + 8);
					} else {
						s = null;
					}
					tag = "GO";
				} else {
					s = null;
				}
			} else if (tag.equals("SCRIPT")) {
				if ((pos = s.toUpperCase().indexOf("</SCRIPT>")) != -1) {
					if (s.length() > pos + 9) {
						s = s.substring(pos + 9);
					} else {
						s = null;
					}
					tag = "GO";
				} else {
					s = null;
				}
			} else if (tag.equals("STYLE")) {
				if ((pos = s.toUpperCase().indexOf("</STYLE>")) != -1) {
					if (s.length() > pos + 8) {
						s = s.substring(pos + 8);
					} else {
						s = null;
					}
					tag = "GO";
				} else {
					s = null;
				}
			} else if (tag.equals("!--")) {
				if ((pos = s.toUpperCase().indexOf("-->")) != -1) {
					if (s.length() > pos + 3) {
						s = s.substring(pos + 3);
					} else {
						s = null;
					}
					tag = "GO";
				} else {
					s = null;
				}
			} else if (tag.equals("<")) {
				if ((pos = s.toUpperCase().indexOf(">")) != -1) {
					if (s.length() > pos + 1) {
						s = s.substring(pos + 1);
					} else {
						s = null;
					}
					tag = "GO";
				} else {
					s = null;
				}
			}
		}
		return sb.toString();
	}
}
