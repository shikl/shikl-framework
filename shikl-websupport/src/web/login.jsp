<%--
  Created by IntelliJ IDEA.
  User: zhanghai
  Date: 2014/10/31
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>RPlus快速开发运行平台-登录</title>
 <link href="${contextPath}/resources/public/stylesheets/login.css" rel="stylesheet" type="text/css"/>
</head>

<body class="bigbox">
	
    	<div class="smallbox">
        	<div class="left"> 
           		 <img src="${contextPath}/resources/public/images/logo.png"/>
            </div>
            <div class="right"> 
           		 <div class="inpu">
                 	<h2>用户登录<span>User Login...</span></h2>
                    <form>
                    	<div class="userbox">
                            <p>用户名：</p>
                            <input type="text"/>
                        </div>
                        <div class="passbox">
                            <p>密  码：</p>
                            <input type="password"/>
                        </div>
                        <div class="chebox">
                       		 <input type="checkbox"/>记住密码
                        </div>
                        <div class="dlbut">
                        	<input type="submit" value="登 录"/>
                        </div>
                    </form>
                 </div>
            </div>
        </div>
        
    
</body>
</html>