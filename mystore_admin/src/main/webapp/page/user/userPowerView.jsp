<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
   	    <script src="<%=path%>/js/jquery.min.js" type="text/javascript"></script>
   	    <link rel="stylesheet"  href="<%=path%>/js/dhtmlxtree/dhtmlxtree.css" type="text/css"/>
	    <script src="<%=path%>/js/dhtmlxtree/dhtmlxtree.js" type="text/javascript"></script>
	    <script  type="text/javascript">
	       $(function(){ 
	            $("#back").click(function(){
	                location.href="<%=path%>/user/userAction!list.dhtml";
	            });
			    function toncheck(id,state){
				
				};
				
				tree=new dhtmlXTreeObject("tree","100%","100%",0);
	
				tree.setImagePath("<%=path%>/js/dhtmlxtree/imgs/dhxtree_skyblue/");
				tree.enableCheckBoxes(1);
				tree.enableDragAndDrop(0);
				tree.setOnCheckHandler(toncheck);
				tree.loadXML('<%=path%>/user/userAction!getAllNoteView.dhtml?id=<s:property value="#request.id"/>', function(){
				
				});

	       });
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：用户权限</span></div>
	 <div class="main" style="margin:0 auto;">
	    <div style="width:100%;">
	        <div id="tree" style="width:auto;height:auto;background-color:#e3eef9;"></div>
        </div>
     </div>
     <div style="width:100%;text-align:center;border:0px;" class="table1">
		   <input type="button" id="back" value="返回" class="query" style="opacity: 1;"/>
	 </div>
</body>
</html>
