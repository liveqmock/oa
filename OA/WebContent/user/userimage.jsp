<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.io.*"%>
<%@ page import="com.icss.oa.user.vo.UserInfoSearchVO"%>
<%
	UserInfoSearchVO vo = (UserInfoSearchVO) request
			.getAttribute("userInfoSearchVO");
	if (vo != null) {
		OutputStream outs = null;
		InputStream in = null;
		try {
			response.setContentType("image/jpeg");
			outs = response.getOutputStream();
			in = vo.getImage();
			if (in != null) {
				byte[] blobbytes = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = in.read(blobbytes)) != -1) {
					outs.write(blobbytes, 0, bytesRead);
				}
			}
			outs.flush();
			out.clear();
			out = pageContext.pushBody();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (outs != null) {
				outs.close();
			}
		}
	}
%>