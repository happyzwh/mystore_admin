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
	       $(function(){ 
				function tonclick(id){
				       if(id == -1){
				          $("#name").val('');
					      $("#type").val('');
					      $("#bh").val('');
					      $("#sort").val('');
					      $("#descr").val('');
				          return false;
				       }
                       $.ajax({
							url: '<%=path%>/adv/advAction!getCateById.dhtml',
							type: 'post',
							data: {'id':id},
							async: false,
							dataType: "json",
							success:function(data){
							        if(data){
					                    if(data.returnCode == -1){
					                         alert("提示：服务异常！");
					                    }else if(data.returnCode == -2){
					                         alert("提示：参数出错！");
					                    }else{
					                        if(data.type == '2'){
					                           $("#id_iframe").attr("src","<%=path%>/adv/advAction!list_advimg.dhtml?pid="+id);
					                        }else if(data.type == '3'){
					                           $("#id_iframe").attr("src","<%=path%>/adv/advAction!list_pro.dhtml?pid="+id);
					                        }else if(data.type == '4'){
					                           $("#id_iframe").attr("src","<%=path%>/adv/advAction!list_resource.dhtml?pid="+id);
					                        }
					                    }
				                    }else{
				                        alert("提示：服务异常！");
				                    }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
				       });  
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
	
	            tree.setXMLAutoLoading("<%=path%>/adv/advAction!get_note.dhtml");
				tree.loadXML("<%=path%>/adv/advAction!get_note.dhtml", function(){
					//tree.setOnOpenHandler(tonopen);
					//tree.setDragHandler(tondrag);
				});

	       });
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：广告内容管理</span></div>
	 <div class="main">
	    <div style="margin:20px;">
	        <div id="tree" style="float:left;width:20%;height:400px;background-color:#e3eef9;border:1px solid #c1dbe7;"></div>
	        <div style="width:70%;float:left;">
	            <div style="width:100%;text-align:center;margin-left:10px;margin-bottom:10px;">
		        	<iframe id="id_iframe" src="" width="100%" height="500px;" style="border:1px solid #c1dbe7;"/>
		        </div>
	        </div>
        </div>
     </div>
</body>
</html>
