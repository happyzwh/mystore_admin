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
	            $("#back").click(function(){
	                location.href="<%=path%>/user/userAction!list.dhtml";
	            });
	            $("#save").click(function(){
	                if($.trim($("#account").val()) == ''){
	                    alert("提示：帐号不能为空！");
	                    return false;
	                }
	                if($.trim($("#userName").val()) == ''){
	                    alert("提示：真实姓名不能为空！");
	                    return false;
	                }
	                
	                if($.trim($("#password").val()) != '' && $.trim($("#password").val()).length < 6){
	              		alert("提示：新密码长度应不小于6位字符！");
	                    return false;
	                }
	                
	                if($.trim($("#password_check").val()) != '' && $.trim($("#password_check").val()).length < 6){
	              		alert("提示：确认密码长度应不小于6位字符！");
	                    return false;
	                }
	                
	                if($.trim($("#password").val()) != '' && $.trim($("#password_check").val()) == ''){
	                    alert("提示：确认密码不能为空！");
	                    return false;
	                }
	                
	                if($.trim($("#password").val()) == '' && $.trim($("#password_check").val()) != ''){
	                    alert("提示：新密码不能为空！");
	                    return false;
	                }
	                
	                if($.trim($("#password").val()) != '' && $.trim($("#password_check").val()) != '' && $.trim($("#password_check").val()) != $.trim($("#password").val())){
	                    alert("提示：新密码和确认密码不一致！");
	                    return false;
	                }
	                
	                if($.trim($("#email").val()) != ""){
		                 var patrns=/^\w+((-\w+)|(.\w+))*@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+)*.[A-Za-z0-9]+$/; 
						 if(!patrns.exec($.trim($("#email").val()))){
						    alert("提示：电子邮箱格式错误！");
	                        return false;
						 }
					}
					
					if($.trim($("#sort").val()) != ""){
		                 var patrns=/^[1-9]\d{0,1}$/;  
						 if(!patrns.exec($.trim($("#sort").val()))){
						    alert("提示：序号格式应为1-2位正数！");
	                        return false;
						 }
					}
					
	                $.ajax({
							url: '<%=path%>/user/userAction!save.dhtml',
							type: 'post',
							data: {'id':$.trim($("#id").val()),'userName':$.trim($("#userName").val()),'account':$.trim($("#account").val()),
							       'email':$.trim($("#email").val()),'password':$.trim($("#password").val()),
							       'sort':$.trim($("#sort").val())
							},
							async: false,
							dataType: "text",
							success:function(data){
				                if(data == -3){
							      alert('提示: 该帐号已经存在！');
							    }else if(data == -2){
				                   alert("提示：参数错误！");
				                }else if(data == 0){
				                   alert("提示：保存成功！");
				                   location.href="<%=path%>/user/userAction!list.dhtml";
				                }else if(data == -1){
				                   alert("提示：服务异常！");
				                }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
				 });
	          });
	       });
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="id" value='<s:property value="#request.user.id"/>'/>
	 <div class="location"><span>当前位置：用户管理</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">修改用户</td>
	           </tr>
	           <tr>
	               <td width="15%" style="text-align:right;">帐号：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="account" name="account" style="width:250px;height:28px;" maxlength="25" value="<s:property value='#request.user.account'/>"/> 
	               </td>
	               <td width="15%" style="text-align:right;">真实姓名：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="userName" name="userName" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.user.userName'/>"/> 
	               </td>
	           </tr>
	           <tr>
	               <td width="15%" style="text-align:right;">新密码：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="password" id="password" name="password" style="width:250px;height:28px;" maxlength="50"/>
	                   <input type="hidden" id="passwords" value="<s:property value='#request.user.password'/>"'/>  
	               </td>
	               <td width="15%" style="text-align:right;">确认密码：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="password" id="password_check" name="password_check" style="width:250px;height:28px;" maxlength="50" /> 
	               </td>
	           </tr>
	           <tr>
	               <td width="15%" style="text-align:right;">电子邮箱：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="email" name="email" style="width:250px;height:28px;" maxlength="25" value="<s:property value='#request.user.email'/>"/> 
	               </td>
	               <td width="15%" style="text-align:right;">序号：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="2" value="<s:property value='#request.user.sort'/>"/> 
	               </td>
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>
</body>
</html>
