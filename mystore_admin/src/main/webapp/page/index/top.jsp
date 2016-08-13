<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="<%=path%>/css/neiye.css" type="text/css"/>
</head>
<body>
	<div class="header">
	       <img class="header_left" src="<%=path%>/images/header-left.jpg"/>
	       <img class="header_right" src="<%=path%>/images/header-right.jpg" />
	       <div class="infoBar">
	           <span style="margin-left:30px;">今天是:</span>
	           <span>${today}</span>
	           <span class="welcome">您好!${userName}</span>
	       </div>
	       <!--返回首页-->
	       <div class="home">
	           <img src="<%=path%>/images/home.jpg" onclick=""/>
	           <a onclick="#">首页</a>
	       </div>
	       <!--退出-->
	       <div class="quit">
	         <img src="<%=path%>/images/quit.jpg" onclick='javascript:location.href="<%=path%>/user/userAction!logout.dhtml"'/>
	         <a href='javascript:location.href="<%=path%>/user/userAction!logout.dhtml"'>退出</a>
	       </div>
	</div>
</body>
</html>