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
	               location.href="<%=path%>/user/userAction!add.dhtml";
	          });
	       });
	       function jumppage(pageNo){
				 $("#pageNo").val(pageNo);
				 $("#queryForm").submit();
		   }
		   function del(id){
		      if(confirm("提示：是否确认删除？")){
			       $.ajax({
								url: '<%=path%>/user/userAction!delete.dhtml',
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
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：用户管理</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <!--按条件查询-->
        <form id="queryForm" action="<%=path%>/user/userAction!list.dhtml" method="post">
        <input type="hidden" id="pageNo" name="pageNo" value=''/>
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">查询条件</td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">用户名：</td>
	               <td width="35%">
	                   <input type="text" id="account" name="account" value='<s:property value="#request.account"/>'/>
	               </td>
	               <td width="15%" class="center">真实姓名 ：</td>
	               <td width="35%">
	                   <input type="text" id="userName" name="userName" value='<s:property value="#request.userName"/>'/>		  
	               </td>
	           </tr>
	           <tr>
	               <td class="buttons" colspan="4">
	                   <input type="button" id="query" value="查询" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                   <power:permission bh="add_user">
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
               <th width="20%">用户名</th> 
               <th width="20%">姓名</th> 
               <th width="20%">电子邮箱</th>
               <th width="10%">排序</th>
               <th width="20%">操作</th>
           </tr>
            <s:iterator value="list" id="user" status="index">
	           <tr>
	               <td><s:property value="#index.index+1"/></td>
	               <td><s:property value="#user.account"/></td> 
	               <td><s:property value="#user.userName"/></td>
	               <td><s:property value="#user.email"/></td> 
	               <td><s:property value="#user.sort"/></td> 
	               <td>
	                   <power:permission bh="update_user">
					   <a href='<%=path%>/user/userAction!edit.dhtml?id=<s:property value="#user.id"/>' target="_self">修改</a>
					   </power:permission>
					   <power:permission bh="del_user">
					   <a href='javascript:void(0);' onclick='javascript:del(<s:property value="#user.id"/>);'>删除</a>
					   </power:permission>
					   <power:permission bh="setup_role">
					   <a href='<%=path%>/user/userAction!userRole.dhtml?id=<s:property value="#user.id"/>' target="_self">角色</a>
					   </power:permission>
					   <power:permission bh="setup_power">
					   <a href='<%=path%>/user/userAction!userPower.dhtml?id=<s:property value="#user.id"/>' target="_self">权限</a>
					   </power:permission>
					   <power:permission bh="view_power">
					   <a href='<%=path%>/user/userAction!userPowerView.dhtml?id=<s:property value="#user.id"/>' target="_self">权限查看</a>
					   </power:permission>
	               </td>
	           </tr>
           </s:iterator>
        </table>        
   </div>
   <div class="table1" style="border:0px;text-align:right;clear:both;margin:20px auto;">${pageInfo}</div>
</body>
</html>
