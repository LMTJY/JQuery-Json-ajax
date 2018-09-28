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
  		$.getJSON("JsonServlet",{},function(data){
  			//alert(data);
  			var str = "<option disabled selected hidden>请选择</option>";
  			for(var i=0;i<data.length;i++){
  				str += "<option value="+data[i].tid+">"+data[i].tname+"</option>";
  			}
  			$("#select").html(str);
  		});
  	});
  	function choose(value){
  		$.getJSON("JsonServlet1",{tid:value},function(data){
  			var str = "";
  			for(var i=0;i<data.length;i++){
  				str +="<li>"+data[i].ntitle+"</li>";
  			}
  			$("#content").html(str);
  		});
  	}
  </script>
  <body>
   	<center>
   		<div>
   			<select id="select" onchange="choose(this.value)">
   				
   			</select>
   		</div>
   		<br><br><br><br>
   		<div >
   			<ul id="content">
   				
   			</ul>
   		</div>
   	</center>
  </body>
</html>
