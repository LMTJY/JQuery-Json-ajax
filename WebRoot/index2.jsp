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
  	$(document).ready(function(){
  		$("#guonei").load("NewServlet?tid=1");
  		$.get("NewServlet?tid=2",function(data){
  			$("#guowai").html(data);
  		})
  	})
  </script>
  <body>
   	<center>
   		<h1>下面是正文部分</h1>
   			<h2>国内新闻</h2>
   			<div id="guonei">
   				<img  src="img/ajax-loader.gif">正在加载中请稍后。。。。
   			</div>
   			<br><br><br><br>
   			<hr>
   			<div id="guonei">
   				<img  src="img/ajax-loader.gif">正在加载中请稍后。。。。
   			</div>
   	</center>
  </body>
</html>
