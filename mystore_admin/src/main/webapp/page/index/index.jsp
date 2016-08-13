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
<title>商城管理后台</title>
</head>
	<frameset rows="128,*,28" cols="*" framespacing="0" frameborder="no" border="0" name="allFrame">
	  <frame src="<%=path%>/user/userAction!top.dhtml" name="topFrame" scrolling="NO" noresize marginwidth="0" marginheight="0" >
	  <frameset rows="*" cols = "192,9,*" framespacing="0" frameborder="NO" border="0" name="main" id="main">
	    <frame src="<%=path%>/user/userAction!left.dhtml" name="leftFrame" id="leftFrame" scrolling="NO" noresize>
	    <frame src="<%=path%>/page/index/control.html" name="midFrame" id="midFrame" scrolling="NO" frameborder="NO">
	    <frame src="<%=path%>/page/index/right.jsp" name="mainFrame">
	  </frameset>
	  <frame src="<%=path%>/page/index/foot.jsp" name="topFrame" scrolling="NO" noresize marginwidth="0" marginheight="0" >
	</frameset>
	<noframes></noframes>
</html>