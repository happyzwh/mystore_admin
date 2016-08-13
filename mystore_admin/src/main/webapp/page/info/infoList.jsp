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
	          $("#query").click(function(){
	              $("#pageNo").val(1);
	              $("#queryForm").submit();
	          });
	          $("#add").click(function(){
	               location.href="<%=path%>/info/infoAction!edit.dhtml?id_cate="+$("#id_cate").val();
	          });
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
	       function jumppage(pageNo){
				 $("#pageNo").val(pageNo);
				 $("#queryForm").submit();
		   }
		   function del(id){
		      if(confirm("提示：是否确认删除？")){
			       $.ajax({
								url: '<%=path%>/info/infoAction!delete.dhtml',
								type: 'post',
								data: {'id':id},
								async: false,
								dataType: "text",
								success:function(data){
					                if(data == 0){
					                    alert("提示：删除成功！");
					                    $("#queryForm").submit();
					                }else if(data == -1){
					                    alert("提示：服务异常！");
					                }
								},
								error:function(){
								     alert("提示：服务异常！");
								}
			     });
		     }
		  }
		  function sort(){
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
									url: '<%=path%>/info/infoAction!sort.dhtml',
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
	 <div class="location"><span>当前位置：属性管理</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <!--按条件查询-->
        <form id="queryForm" action="<%=path%>/info/infoAction!infoList.dhtml" method="post">
        <input type="hidden" id="id_cate" name="id_cate" value='<s:property value="#request.id_cate"/>'/>
        <input type="hidden" id="pageNo" name="pageNo" value=''/>
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">查询条件</td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">标题：</td>
	               <td width="35%">
	                   <input type="text" id="title" name="title" value='<s:property value="#request.title"/>'/>
	               </td>
	               <td width="15%" class="center">状态：</td>
	               <td width="35%">
	                   <select id="status" name="status">
	                        <option value="">所有</option>
	                       <option <s:if test='status == 1'>selected="selected"</s:if> value="1">启用</option>
	                       <option <s:if test='status == 0'>selected="selected"</s:if> value="0">禁用</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td class="buttons" colspan="4">
	                   <input type="button" id="query" value="查询" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                    <power:permission bh="add_attr">
	                  	 <input type="button" id="add" value="添加" class="query" style="opacity: 1;"/>   
	                   </power:permission>
	               </td>
	           </tr>
       	 </tbody>
        </table>
        </form>
         <!--查询结果列表-->
        <table class="table_list" cellpadding="0" cellspacing="0" style="margin-top:0px;">
           <tr class="head">
               <th width="10%">序号</th>
               <th width="30%">标题</th> 
               <th width="20%">状态</th> 
               <th width="20%">排序</th> 
               <th width="20%">操作</th>
           </tr>
            <s:iterator value="infoList" id="info" status="index">
	           <tr id='<s:property value="#info.id"/>'>
	               <td><s:property value="#index.index+1"/></td>
	               <td>
	               	   <a href="<%=path%>/info/infoAction!edit.dhtml?id_cate=<s:property value="#request.id_cate"/>&id=<s:property value="#info.id"/>"><s:property value="#info.title"/></a>
	               </td> 
	               <td>
	                  <s:if test="#info.status == 0">禁用</s:if>
	                  <s:elseif test="#info.status == 1">启用</s:elseif>
	               </td> 
	               <td style="text-align:center;">
					    <img src="<%=path%>/images/up.jpg" alt="上移" class="arrow up" style="cursor: pointer;"/>    
					    <img src="<%=path%>/images/down.jpg" alt="下移" class="arrow down" style="cursor: pointer;"/>
				   </td>
	               <td>
	                  <power:permission bh="update_attr">
	                  	<a href='<%=path%>/info/infoAction!edit.dhtml?id=<s:property value="#info.id"/>' target="_self">修改</a>
	                  </power:permission>
	                  <power:permission bh="del_attr">
	                  	<a href='javascript:void(0);' onclick='javascript:del(<s:property value="#info.id"/>);'>删除</a>
	                  </power:permission>
	               </td>
	           </tr>
           </s:iterator>
        </table>        
   </div>
    <div class="table1" style="width:45%;border:0px;text-align:center;clear:both;margin:20px auto;float:left;"><input type="button" id="save" onclick="javascript:sort();" value="保存" class="query" style="opacity: 1;"/></div><div class="table1" style="width:45%;float:right;border:0px;text-align:right;margin:20px auto;">${pageInfo}</div>
</body>
</html>
