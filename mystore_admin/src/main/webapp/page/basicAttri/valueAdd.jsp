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
	                location.href='<%=path%>/basicAttri/basicAttriAction!valueList.dhtml?pid=<s:property value="#request.pid"/>';
	            });
	            $("#save").click(function(){
	                if($.trim($("#value").val()) == ''){
	                    alert("提示：值不能为空！");
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
							url: '<%=path%>/basicAttri/basicAttriAction!valueSave.dhtml',
							type: 'post',
							data: {'pid':'<s:property value="#request.pid"/>','value':$.trim($("#value").val()),'sort':$.trim($("#sort").val())},
							async: false,
							dataType: "text",
							success:function(data){
							    if(data == -3){
							      alert('提示: 该值已经存在！');
							    }else if(data == -2){
				                   alert("提示：参数错误！");
				                }else if(data == 0){
				                   alert("提示：保存成功！");
				                   location.href='<%=path%>/basicAttri/basicAttriAction!valueList.dhtml?pid=<s:property value="#request.pid"/>';
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
	 <div class="location"><span>当前位置：属性值管理</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">添加属性值</td>
	           </tr>
	           <tr>
	               <td width="15%" style="text-align:right;">值：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="value" name="value" style="width:250px;height:28px;" maxlength="25" /> 
	               </td>
	               <td width="15%" style="text-align:right;">序号：</td>
	               <td width="20%"  style="text-align:left;">
	                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="2" /> 
	               </td>
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>
</body>
</html>
