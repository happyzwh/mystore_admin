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
					      $("#code").val('');
					      $("#sort").val(''); 
				          return false;
				       }
                       $.ajax({
							url: '<%=path%>/region/regionAction!getRegionById.dhtml',
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
					                          $("#name").val(data.name);
										      $("#type").val(data.type);
										      $("#code").val(data.code);
										      $("#sort").val(data.sort); 
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
	
	            tree.setXMLAutoLoading("<%=path%>/region/regionAction!get_note.dhtml");
				tree.loadXML("<%=path%>/region/regionAction!get_note.dhtml", function(){
					//tree.setOnOpenHandler(tonopen);
					//tree.setDragHandler(tondrag);
				});

	       });
	       function addNote(){
	       
	           if($.trim($("#name").val()) == ''){
	              alert("提示：名称不能为空！");
	              return false;
	           }
	           
	           if($.trim($("#sort").val()) != ""){
		                 var patrns=/^[1-9]\d{0,1}$/; 
						 if(!patrns.exec($.trim($("#sort").val()))){
						    alert("提示：序号格式应为1-2位正数！");
	                        return false;
						 }
			   }
	           $.ajax({
					url: '<%=path%>/region/regionAction!addNote.dhtml',
					type: 'post',
					data: {'name':$.trim($("#name").val()),'type':$.trim($("#type").val()),'code':$.trim($("#code").val()),
					      'sort':$.trim($("#sort").val()),'pid':tree.getSelectedItemId() == ''?-1:tree.getSelectedItemId()},
					async: false,
					dataType: "text",
					success:function(data){
		                    if(data == -1){
		                         alert("提示：服务异常！");
		                    }else if(data == -2){
		                         alert("提示：参数出错！");
		                    }else if(data == -3){
		                         alert("提示：权限名称重复！");
		                    }else{
		                         tree.insertNewItem(tree.getSelectedItemId() == ''?-1:tree.getSelectedItemId(),data,$.trim($("#name").val())); 
		                          $("#name").val('');
							      $("#type").val('');
							      $("#code").val('');
							      $("#sort").val(''); 
					             alert("提示：保存成功！");
		                    }
					},
					error:function(){
					     alert("提示：服务异常！");
					}
		       });  
	       }
	       function deleteNote() {
	           if(tree.getSelectedItemId() == ''){
	              alert("提示：请选择权限节点！");
	              return false;
	           }
	           if(confirm("提示：确定删除！")){
		           $.ajax({
						url: '<%=path%>/region/regionAction!deleteNote.dhtml',
						type: 'post',
						data: {'id':tree.getSelectedItemId()},
						async: false,
						dataType: "text",
						success:function(data){
			                    if(data == -1){
			                         alert("提示：服务异常！");
			                    }else if(data == -2){
			                         alert("提示：参数出错！");
			                    }else{
			                         tree.deleteItem(tree.getSelectedItemId(),true);   
			                          $("#name").val('');
								      $("#type").val('');
								      $("#code").val('');
								      $("#sort").val(''); 
						             alert("提示：删除成功！");
			                    }
						},
						error:function(){
						     alert("提示：服务异常！");
						}
			       }); 
		       }
	           
	       }
	       function updateNote(){
	          if(tree.getSelectedItemId() == ''){
	              alert("提示：请选择权限节点！");
	              return false;
	          }
	          if($.trim($("#name").val()) == ''){
	              alert("提示：名称不能为空！");
	              return false;
	           }
	           if($.trim($("#sort").val()) != ""){
		                 var patrns=/^[1-9]\d{0,1}$/; 
						 if(!patrns.exec($.trim($("#sort").val()))){
						    alert("提示：序号格式应为1-2位正数！");
	                        return false;
						 }
			   }
	           $.ajax({
					url: '<%=path%>/region/regionAction!updateNote.dhtml',
					type: 'post',
					data: {'name':$.trim($("#name").val()),'type':$.trim($("#type").val()),'code':$.trim($("#code").val()),
					      'sort':$.trim($("#sort").val()),'id':tree.getSelectedItemId(),'pid':tree.getParentId(tree.getSelectedItemId())},
					async: false,
					dataType: "text",
					success:function(data){
		                    if(data == -1){
		                         alert("提示：服务异常！");
		                    }else if(data == -2){
		                         alert("提示：参数出错！");
		                    }else if(data == -3){
		                         alert("提示：权限名称重复！");
		                    }else{
		                         tree.setItemText(tree.getSelectedItemId(),$.trim($("#name").val())); 
		                         alert("提示：保存成功！");
		                    }
					},
					error:function(){
					     alert("提示：服务异常！");
					}
		       });  
	       }
	       function clearNote(){
	             $("#name").val('');
			     $("#type").val('');
			     $("#code").val('');
			     $("#sort").val(''); 
	       }
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：区域管理</span></div>
	 <div class="main">
	    <div style="margin:20px;">
	        <div id="tree" style="float:left;width:30%;height:400px;background-color:#e3eef9;border:1px solid #c1dbe7;"></div>
	        <div style="width:65%;float:left;">
		        <div style="width:100%;text-align:center;">
		             <div style="width:80%;text-align:center;margin:0 auto;">
		                  <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
				             <tbody>
						         	 <tr>
						               <td width="15%" style="text-align:right;">名称：</td>
						               <td width="50%" style="text-align:left;">
						                   <input type="text" id="name" name="name" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td width="15%" style="text-align:right;">类型：</td>
						               <td width="50%" style="text-align:left;">
						                  <select id="type" name="type" style="width:100px;"> 
						                    <option value="0">大区域</option>
						                    <option value="1">省(直辖市)</option>
						                    <option value="2">市</option>
						                    <option value="3">区</option>
						                    <option value="4">县</option>
						                  </select>
						               </td>
						           </tr>
						           <tr>
						               <td width="15%" style="text-align:right;">区号：</td>
						               <td width="50%" style="text-align:left;">
						                   <input type="text" id="code" name="code" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td width="15%" style="text-align:right;">排序：</td>
						               <td width="50%" style="text-align:left;">
						                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="4" /> 
						               </td>
						           </tr>
					           </tbody>
					       </table>    
		             </div>
		             <div id="content" style="width:100%;text-align:center;border:0px;" class="table1">
		             			    <power:permission bh="add_category">
					                <input type="button" value="增加" class="query"  style="opacity: 1;" onclick="javascript:addNote();"/>
					                </power:permission>
					                <power:permission bh="update_category">
							        <input type="button" value="修改" class="query" onclick="javascript:updateNote();"/>
							        </power:permission>
							       	<power:permission bh="del_category">
							        <input type="button" value="删除" class="query" onclick="javascript:deleteNote();"/>
							        </power:permission>
							        <input type="button" value="清空" class="query" onclick="javascript:clearNote();"/>
				    </div>
		        </div>
	        </div>
        </div>
     </div>
</body>
</html>
