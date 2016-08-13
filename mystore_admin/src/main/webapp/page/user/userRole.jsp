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
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	    <script  type="text/javascript">
	      $(function(){
	          if($(".select").length == $(".select:checked").length){
	                 $(".selectAll").attr("checked",true);
	          }
	          $("#back").click(function(){
	               location.href="<%=path%>/user/userAction!list.dhtml";
	          });
	          $("#save").click(function(){
	               if($(".select:checked").length == 0){
	                   alert("提示：请选择角色！");
	                   return false;    
	               }
	               var roleIds = [];
	         	   $(".select:checked").each(function(){
	         	       roleIds.push($(this).val());
	         	   });
	         	   $.ajax({
							url: '<%=path%>/user/userAction!addUserRole.dhtml',
							type: 'post',
							data: {'id':$.trim($("#id").val()),'roleIds':roleIds.join(',')},
							async: false,
							dataType: "text",
							success:function(data){
							    if(data == -2){
				                   alert("提示：参数错误！");
				                }else if(data == 0){
				                   alert("提示：保存成功！");
				                }else if(data == -1){
				                   alert("提示：服务异常！");
				                }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
					 });
	         });
	         $(".selectAll").click(function(){
	             if($(this).attr("checked") == true){
	                $(".select").attr("checked",true);
	             }else{
	                $(".select").removeAttr("checked");
	             }
	         });
	         $(".select").click(function(){
	             if($(".select").length == $(".select:checked").length){
	                 $(".selectAll").attr("checked",true);
	             }else{
	                 $(".selectAll").removeAttr("checked");
	             }
	         });
	      });
	          
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="id" value='<s:property value="#request.id"/>'/>
	 <div class="location"><span>当前位置：用户角色</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table class="table_list" cellpadding="0" cellspacing="0" style="margin-top:0px;">
           <tr class="head">
               <th width="10%"><input type="checkbox" class="selectAll" /></th>
               <th width="20%">角色名</th> 
           </tr>
            <s:iterator value="roleList" id="role" status="index">
	           <tr>
	               <td><input type="checkbox" <s:if test='#request.userRoleMap.get(#role.id+"-") != null'>checked="checked"</s:if> class="select" value='<s:property value="#role.id"/>'/></td>
	               <td><s:property value="#role.name"/></td> 
	           </tr>
           </s:iterator>
        </table> 
        <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>       
   </div>
</body>
</html>
