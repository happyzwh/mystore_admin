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
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
   	    <link rel="stylesheet"  href="<%=path%>/js/dhtmlxtree/dhtmlxtree.css" type="text/css"/>
	    <script src="<%=path%>/js/dhtmlxtree/dhtmlxtree.js" type="text/javascript"></script>
	    <script  type="text/javascript">
	       $(function(){ 
	            $(".selectall").live('click',function(){
	                if($(this).attr("checked")){
	                    $(".selectone").attr("checked","checked");
	                }else{
	                    $(".selectone").removeAttr("checked");
	                }
	            });
	            $(".selectone").live('click',function(){
	                if($(".selectone").length == $(".selectone:checked").length){
	                     $(".selectall").attr("checked","checked");
	                }else{
	                     $(".selectall").removeAttr("checked");
	                }
	            });
	             $(".up").live('click',function(){
	                 if($(this).parent().parent().prev().length > 0){
	                    $(this).parent().parent().insertBefore($(this).parent().parent().prev());  
	                 }
	            });
	            $(".down").live('click',function(){
	                  if($(this).parent().parent().next().length > 0){
	                     $(this).parent().parent().insertAfter($(this).parent().parent().next());  
	                 }
	            });
				function tonclick(id){
				    if(id != -1){
						    $.ajax({
									url: '<%=path%>/category/categoryAction!getAttriByCateId.dhtml',
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
							                         $("#tbody").empty();
							                         var content = [];
		                                             if(data.list && data.list.length > 0){
		                                                  $.each(data.list,function(inde,item){
		                                                      content.push('<tr><td><input type="checkbox"');
		                                                      if(item.isOwn){
		                                                        content.push('checked = "checked"'); 
		                                                      }
		                                                      content.push('class="selectone" value="'+item.id_attri+'"></td>'); 
		                                                      content.push('<td><input type="text" value="'+item.name_attri+'"></td>');
		                                                      content.push('<td><img src="<%=path%>/images/up.jpg" alt="上移" class="arrow up" style="cursor: pointer;"/>    <img src="<%=path%>/images/down.jpg" alt="下移" class="arrow down" style="cursor: pointer;"/></td></tr>');  
		                                                  });
		                                                  $("#tbody").append(content.join(''));
		                                                  setSelect();
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
				       }
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
	
	            tree.setXMLAutoLoading("<%=path%>/category/categoryAction!get_note.dhtml");
				tree.loadXML("<%=path%>/category/categoryAction!get_note.dhtml", function(){
					//tree.setOnOpenHandler(tonopen);
					//tree.setDragHandler(tondrag);
				});

	       });
	       function save(){
	          if(  $(".selectone:checked").length == 0){
	              alert("提示：请选择属性！");
	              return false;
	          }
	          var attrArr = [];
	          $(".selectone:checked").each(function(ind,ent){
	              if(attrArr.length == 0)attrArr.push(",");
	              attrArr.push($(this).val());
	              attrArr.push(",");
	          });
	          $.ajax({
						url: '<%=path%>/category/categoryAction!saveAttr.dhtml',
						type: 'post',
						data: {'id':tree.getSelectedItemId(),'ids':attrArr.join('')},
						async: false,
						dataType: "json",
						success:function(data){
							   if(data == -1){
							         alert("提示：服务异常！");
							   }else if(data == -2){
							         alert("提示：参数出错！");
							   }else{
							         alert("提示：保存成功！");
							   }
							      
						},
						error:function(){
								alert("提示：服务异常！");
						}
			   }); 
	       }
	       function setSelect(){
	           if($(".selectone").length == $(".selectone:checked").length){
	                 $(".selectall").attr("checked","checked");
	           }else{
	                 $(".selectall").removeAttr("checked");
	           }
	       }
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：分类属性管理</span></div>
	 <div class="main">
	    <div style="margin:20px;">
	        <div id="tree" style="float:left;width:30%;height:400px;background-color:#e3eef9;border:1px solid #c1dbe7;"></div>
	        <div style="width:65%;float:left;">
		        <div style="width:100%;text-align:center;">
		             <div style="width:80%;text-align:center;margin:0 auto;">
		                  <!--查询结果列表-->
					        <table class="table_list" cellpadding="0" cellspacing="0" style="margin-top:0px;">
					           <thead>
						           <tr class="head">
						               <th width="10%"><input type="checkbox" class="selectall"/></th>
						               <th width="70%">名称</th>
						               <th width="20%">顺序</th>
						           </tr>
					           </thead>
					           <tbody id="tbody">
						       </tbody>
					        </table>   
				             <div id="content" style="width:100%;text-align:center;border:0px;" class="table1">
				             		  <power:permission bh="save_cate_attr">
									      <input type="button" value="保存" class="query" onclick="javascript:save();"/>
									  </power:permission>
						    </div>
				    </div>
		        </div>
	        </div>
        </div>
     </div>
</body>
</html>
