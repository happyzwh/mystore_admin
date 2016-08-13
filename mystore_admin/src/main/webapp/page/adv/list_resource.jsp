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
	    <script  type="text/javascript">
	     $(function(){
	           $(".up").click(function(){
	                 if($(this).parent().parent().prev().length > 0){
	                    $(this).parent().parent().insertBefore($(this).parent().parent().prev());  
	                    $("#tbody tr").each(function(ind,ent){
	                        $(this).find("td:first").text(ind+1);
	                    });
	                 }
	            });
	            $(".down").click(function(){
	                  if($(this).parent().parent().next().length > 0){
	                     $(this).parent().parent().insertAfter($(this).parent().parent().next());  
	                     $("#tbody tr").each(function(ind,ent){
	                        $(this).find("td:first").text(ind+1);
	                     });
	                 }
	            });
	     });
	     function addPro(){
			 location.href='<%=path%>/adv/advAction!edit_resource.dhtml?pid='+$("#pid").val();   
		 }
		 function updatePro(id){
			 location.href='<%=path%>/adv/advAction!edit_resource.dhtml?id='+id;   
		 }
		 function delPro(id){
		      if(confirm("提示：是否确认删除？")){
			       $.ajax({
								url: '<%=path%>/adv/advAction!delResModule.dhtml',
								type: 'post',
								data: {'id':id},
								async: false,
								dataType: "text",
								success:function(data){
					                if(data == 0){
					                    alert("提示：删除成功！");
					                    location.href='<%=path%>/adv/advAction!list_resource.dhtml?pid='+$("#pid").val(); 
					                }else if(data == -1){
					                    alert("提示：服务异常！");
					                }else if(data == -2){
					                    alert("提示：参数错误！");
					                }
								},
								error:function(){
								     alert("提示：服务异常！");
								}
			     });
		     }
		 }
		 function save(){
			          var attrArr = [];
			          $("tbody tr").each(function(ind,ent){
			              if($.trim($(this).attr("id")) != ''){
				              if(attrArr.length == 0)attrArr.push(",");
				              attrArr.push($.trim($(this).attr("id")));
				              attrArr.push(",");
			              }
			          });
			          if(attrArr.length == 0){
			              alert("提示：无可排序项！");
			          }else{
				          $.ajax({
									url: '<%=path%>/adv/advAction!saveResModuleSort.dhtml',
									type: 'post',
									data: {'ids':attrArr.join('')},
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
			 }
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="pid" value='<s:property value="#request.pid"/>' />
	 <div class="location"><span>当前位置：广告商品模块列表</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table class="table_list" cellpadding="0" cellspacing="0" style="margin-top:0px;">
           <tr class="head">
               <th width="10%">序号</th>
               <th width="10%">显示名称</th> 
               <th width="10%">商品名称</th> 
               <th width="10%">商品编号</th> 
               <th width="15%">商品分类</th>
               <th width="15%">商品品牌</th>
               <th width="10%">状态</th>
               <th width="10%">排序</th>
               <th width="10%">操作</th>
           </tr>
            <tbody id="tbody">
            <s:iterator value="resMoudleList" id="pro" status="index">
	           <tr id='<s:property value="#pro.id"/>'>
	               <td><s:property value="#index.index+1"/></td>
	               <td><s:property value="#pro.name"/></td> 
	               <td><s:property value="#pro.product.name"/></td> 
	               <td><s:property value="#pro.product.sn"/></td>
	               <td><s:property value="#pro.category.name"/></td> 
	               <td><s:property value="#pro.brand.name"/></td>
	               <td>
	                 <s:if test='#pro.product.isOnSale == 1'>上架</s:if>
	                 <s:elseif test='#pro.product.isOnSale == 0'>下架</s:elseif>
	               </td> 
	               <td>
	                  <img src="<%=path%>/images/up.jpg" alt="上移" class="arrow up" style="cursor: pointer;"/>    
					  <img src="<%=path%>/images/down.jpg" alt="下移" class="arrow down" style="cursor: pointer;"/>
	               </td>   
	               <td>
	                   <a href='javascript:updatePro(<s:property value="#pro.id"/>);' target="_self">修改</a>
	                   <a href='javascript:delPro(<s:property value="#pro.id"/>);' target="_self">删除</a>
	               </td>
	           </tr>
           </s:iterator>
           </tbody>
        </table>        
   </div>
   <div class="table1" style="border:0px;text-align:right;clear:both;margin:20px auto;">
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" onclick="javascript:save();" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="add" value="增加" onclick="javascript:addPro();" class="query" style="opacity: 1;"/></div>
   </div>
</body>
</html>
