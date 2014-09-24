<%@ page contentType="text/html; charset=GBK"%>

<HTML>
<HEAD><TITLE>管理制度系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">

<STYLE type=text/css>
BODY {
	MARGIN: 0px
}
#frameswitch {
	BACKGROUND: url(include/frame_switch.gif) no-repeat 0px 50%; CURSOR: pointer
}
</STYLE>

<link href="/Style/css_grey.css" id=homepagestyle rel="stylesheet" type="text/css" />


<SCRIPT src="include/common.js" type=text/javascript></SCRIPT>

<SCRIPT src="include/iframe.js" type=text/javascript></SCRIPT>

<SCRIPT type=text/javascript>
	function framebutton(){
	var obj = document.getElementById('navigation');
	var frameswitch = document.getElementById('frameswitch');
	var switchbar = document.getElementById('switchbar');
	if(obj.style.display == 'none'){
		obj.style.display = '';
		switchbar.style.left = '347px';
		frameswitch.style.backgroundPosition = '0';
	}else{
		obj.style.display = 'none';
		switchbar.style.left = '0px';
		frameswitch.style.backgroundPosition = '-11';
	}
 }

if(top != self) {
	top.location = self.location;
}
</SCRIPT>
</HEAD>

<BODY scroll=no>
		<TABLE  height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TBODY>
				<TR>
					<TD id=navigation vAlign=center align=middle width=350
						name="frametitle">
						<IFRAME onkeydown=refreshrightframe(event)
							style="Z-INDEX: 1; VISIBILITY: inherit; WIDTH: 350px; HEIGHT: 100%"
							name=leftmenu src="leftmenu.jsp" frameBorder=0></IFRAME>
					<TD style="WIDTH: 100%">
						<TABLE id=switchbar
							style="BACKGROUND-POSITION: -343px 50%; LEFT: 347px; BACKGROUND-REPEAT: repeat-y; POSITION: absolute"
							height="100%" cellSpacing=0 cellPadding=0 width=11 border=0>
							<TBODY>
								<TR>
									<TD onclick=framebutton()>
										<IMG id=frameswitch height=49 alt="" src="include/none.gif"
											width=11 border=0>
									</TD>
								</TR>
							</TBODY>
						</TABLE>

						<IFRAME onkeydown=refreshrightframe(event)
							style="Z-INDEX: 1; VISIBILITY: inherit; OVERFLOW: visible; WIDTH: 100%; HEIGHT: 100%"
							name=main src="content.jsp" frameBorder=0
							scrolling=yes></IFRAME>

					</TD>
				</TR>
			</TBODY>
		</TABLE>
		<SCRIPT type=text/javascript>
if(window.location.hash){

	main.location=window.location.hash.replace('#','');
}

</SCRIPT>
	</BODY></HTML>
