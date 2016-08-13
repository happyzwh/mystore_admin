<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
  String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城管理后台</title>
<link rel="stylesheet" href="<%=path%>/css/login.css" type="text/css"/>
<script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/encryption/security.js"></script>
<script type="text/javascript">	
     if (top.location != self.location){    
         top.location=self.location;     
     }
     function fnKeydown(e){
		if(((e && e.which) ? e.which : event.keyCode)==13){login()};
	 }
	 function login(){  
	    var account = $.trim($("#account").val());
	    var password = $.trim($("#password").val());
	    if(account == ''){
	       $("#account").focus();
	       alert("提示：请输入用户名！");
	       return false;
	    }
	    if(password == ''){
	       $("#password").focus();
	       alert("提示：请输入密码！");
	       return false;
	    }
	    var modulus = $("#modulus").val();
		var exponent = $("#exponent").val();
		var publicKey = RSAUtils.getKeyPair(exponent, '',modulus);
		password = RSAUtils.encryptedString(publicKey, password);
	    $.ajax({
					url: '<%=path%>/user/userAction!login.dhtml',
					type: 'post',
					data: {'account':account,'password':password},
					async: false,
					dataType: "text",
					success:function(data){
						if(data == 1){
	                        location.href = "<%=path%>/user/userAction!index.dhtml";
	                    }else{
	                        $("#password").val("");
		                    if(data == 0){
		                        alert("提示：用户名或密码错误！");
		                    }else if(data == -1){
		                         alert("提示：服务异常！");
		                    }else if(data == -2){
		                         alert("提示：参数出错！");
		                    }
	                    }
					},
					error:function(){
					     alert("提示：服务异常！");
					}
		});
	 }
	 function reset(){
	    $("#loginForm").reset();
	 }
</script>
</head>
<body onload="document.getElementById('account').focus();">
 <form id="loginForm">
   <div class="login">
       <!--input-->
       <input class="user" id="account" type="text" onkeypress="javascript:fnKeydown(event);"/>
       <input class="pw" id="password" type="password" onkeypress="javascript:fnKeydown(event);"/>
       <!--buttons-->
       <input class="button-login" type="button" onclick="javascript:login();" value="登录"/>
       <input class="button-reset" type="button" onclick="javascript:reset();"  value="重置"/>
       <input type="hidden" id = "exponent" value="${model.exponent}"/>
	   <input type="hidden" id="modulus" value="${model.modulus}"/>
   </div>
</form>   
</body>
</html>
