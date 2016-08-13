<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@ taglib uri="/power-tags" prefix="power" %>
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
	       var p = window.dialogArguments; 
	       $(function(){ 
	            $("#ok").click(function(){
	               if(tree.getSelectedItemId() == -1){
	               		alert("提示：请选择省份！");
	               		return false;
	               }else{
	                    p.setRegion(tree.getSelectedItemId(),tree.getItemText(tree.getSelectedItemId()));
	                    window.close();
	               }
	            });
	            $("#close").click(function(){
	                window.close();
	            });
				function tonclick(id){
				
				};
				function tondblclick(id){
				
				};			
				function tondrag(id,id2){
				    return true;
				
				};
				function tonopen(id,mode){
				     return true;
					
				};
				function toncheck(id,state){
				
				};
	
				tree=new dhtmlXTreeObject("tree","100%","100%",0);
	
				tree.setImagePath("<%=path%>/js/dhtmlxtree/imgs/dhxtree_skyblue/");
				tree.enableCheckBoxes(0);
				tree.enableDragAndDrop(0);
	   			tree.attachEvent("onOpenEnd",function(nodeId, event){});
				tree.setOnClickHandler(tonclick);
				tree.setOnCheckHandler(toncheck);
				tree.setOnDblClickHandler(tondblclick);
	
	            tree.setXMLAutoLoading("<%=path%>/region/regionAction!get_note.dhtml");
				tree.loadXML("<%=path%>/region/regionAction!get_note.dhtml", function(){
					//tree.setOnOpenHandler(tonopen);
					//tree.setDragHandler(tondrag);
				});

	       });
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：分类列表</span></div>
	 <div class="main">
	    <div style="margin:20px;">
	        <div id="tree" style="float:left;width:100%;height:400px;background-color:#e3eef9;border:1px solid #c1dbe7;"></div>
        </div>
         <div style="width:100%;text-align:center;border:0px;margin-top:20px;" class="table1">
		   <input type="button" value="确定" class="query" id="ok"/>
		   <input type="button" value="取消" class="query" id="close"/>
		</div>
     </div>
</body>
</html>
