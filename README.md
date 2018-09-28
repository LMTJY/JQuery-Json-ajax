# JQuery-Json-ajax
异步传输数据
#### Ajax用户名验证

1、用户输入框

```
  <body>
     用户名：   <input type="text" onblur="checkname(this.value)" />
     	<span id="msg">用户名不能为空</span>
  </body>
```

2、Ajax代码

```
		httpRequest.open("post", "CheckNameServlet", true);
		
		httpRequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		
		httpRequest.onreadystatechange = nameCallBack;//调用回调函数

		httpRequest.send("uname=" + oval);
}
	function nameCallBack() {
		if (httpRequest.readyState==4&& httpRequest.status==200) {//判断后端返回的数据
			var str = httpRequest.responseText;
			if(str=="1"){
				document.getElementById("msg").innerHTML="用户名不可用"
			}else{
				document.getElementById("msg").innerHTML="恭喜你用户名可以"
			}
		}
	}
```

3、Servlet服务器处理代码 

```
public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("uname");
		String pwd=request.getParameter("upwd");
		if (name.equals("LMT") && pwd.equals("123")) {
			out.print(name);//返回前端数据
		}else{
			out.print("0");
		}
		out.flush();
		out.close();
	}
```

#### jQuery实现登录

前端代码

```
<script type="text/javascript" src="js/jquery.js"></script>//加入JQuery外链
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
```
![](https://github.com/LMTJY/JQuery-Json-ajax/blob/master/11.png)
