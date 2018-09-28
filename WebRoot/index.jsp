<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <script type="text/javascript">
	var httpRequest = null;
	function checkname(oval) {
		if (oval == "") {
			return;
		}
		try {
			
			httpRequest = new ActiveXObject("Msxml2.XMLHTTP");

		} catch (e) {
			try {
				
				httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				try {
					
					httpRequest = new XMLHttpRequest();
					if (httpRequest.overrideMimeType) {
						httpRequest.overrideMimeType("text/xml");
					}
				} catch (e) {

				}
			}

		}

		if (httpRequest == null) {
			alert("浏览器不支持XMLHttpRequest");
			return;
		}
	
		httpRequest.open("post", "CheckNameServlet", true);
		
		httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		
		httpRequest.onreadystatechange = nameCallBack;

		httpRequest.send("uname=" + oval);
}
	function nameCallBack() {
		if (httpRequest.readyState==4&& httpRequest.status==200) {
			//alert(httpRequest.responseText);
			var str = httpRequest.responseText;
			if(str=="1"){
				document.getElementById("msg").innerHTML="用户名不可用"
			}else{
				document.getElementById("msg").innerHTML="恭喜你用户名可以"
			}
		}
	}
  </script>
  <body>
     用户名：   <input type="text" onblur="checkname(this.value)" />
     	<span id="msg">用户名不能为空</span>
  </body>
</html>
