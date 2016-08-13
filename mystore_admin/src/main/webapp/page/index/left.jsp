<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="<%=path%>/css/neiye.css" type="text/css"/>
	<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script src="<%=path%>/js/style-neiye.js" type="text/javascript"></script>
	</head>
<body class="main">
       <input type="hidden" id="path" value='<%=path%>'/>
	   <div class="left">
	   <s:iterator value="powerList" id="power" status="index">
	         <h3><s:property value="#power.name"/><img src="<%=path%>/images/arrow-right.jpg"/></h3>
	         <ul>
	         <s:iterator value="#power.powerList" id="pow" status="index">
	             <li><a href="<%=path %><s:property value="#pow.url"/>" target="mainFrame" style="outline:none;"> >> <s:property value="#pow.name"/></a></li>
	         </s:iterator>
	         </ul>
	   </s:iterator>
   </div>
</body>
</html>
