<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	function login() {
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
		var name = document.getElementById("uname").value;
		var pwd = document.getElementById("upwd").value;
		httpRequest.open("post", "LoginServlet", true);
		
		httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		
		httpRequest.onreadystatechange = callBack;

		httpRequest.send("uname=" + name +"&upwd="+pwd);
}
	function callBack() {
		if (httpRequest.readyState==4&& httpRequest.status==200) {
			//alert(httpRequest.responseText);
			var str = httpRequest.responseText;
			if(str!="0"){
				document.getElementById("mydiv").innerHTML="欢迎您"+str;
			}
		}
	}
  </script>
  <body>
    <div id="mydiv">
    	用户名：<input type="text" id="uname"/>
    	密码：<input type="password" id="upwd"/>
    	<input type="button" value="登录" onclick="login()"/>
    </div>
    
    <hr></hr>
    <center>
    	<h1>下面是正文部分</h1>
    </center>
  </body>
</html>
