<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript">
  function login(){
	$("#loading").html("<img src='img/ajax-loader.gif'/>正在加载中请稍后。。。。");
	var name = document.getElementById("uname").value;
	var pwd = document.getElementById("upwd").value;
  	$.post("LoginServlet",{uname:name,upwd:pwd},function(data){
  		if(data!="0"){
  			$("#mydiv").html("欢迎您"+data);
  		}else{
  			$("#loading").html("用户名或者密码错误");
  		}
  	});
  }
  </script>
  <body>
   	<div id="mydiv">
    	用户名：<input type="text" id="uname"/>
    	密码：<input type="password" id="upwd"/>
    	<input type="button" value="登录" onclick="login()"/>
    	<span id="loading"></span>
    </div>
  </body>
</html>
