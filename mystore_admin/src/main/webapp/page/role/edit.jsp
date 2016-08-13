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
	                location.href="<%=path%>/role/roleAction!list.dhtml";
	            });
	            $("#save").click(function(){
	                if($.trim($("#name").val()) == ''){
	                    alert("提示：名称不能为空！");
	                    return false;
	                }
					
					if($.trim($("#descr").val()) != "" && $.trim($("#descr").val()).length > 500 ){
					     alert("提示：描述最多500字符！");
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
							url: '<%=path%>/role/roleAction!save.dhtml',
							type: 'post',
							data: {'id':$.trim($("#id").val()),'name':$.trim($("#name").val()),
							       'descr':$.trim($("#descr").val()),'sort':$.trim($("#sort").val())
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
				                   location.href="<%=path%>/role/roleAction!list.dhtml";
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
     <input type="hidden" id="id" value='<s:property value="#request.role.id"/>'/>
	 <div class="location"><span>当前位置：角色管理</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">修改角色</td>
	           </tr>
	           <tr>
	               <td width="15%" style="text-align:right;">名称 ：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="name" name="name" style="width:250px;height:28px;" maxlength="25" value="<s:property value='#request.role.name'/>"/> 
	               </td>
	               <td width="15%" style="text-align:right;">序号：</td>
	               <td width="20%" style="text-align:left;">
	                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="2" value="<s:property value='#request.role.sort'/>"/> 
	               </td> 
	           </tr>
	           <tr>
	               <td width="15%" style="text-align:right;">描述：</td>
	               <td width="20%" colspan="3" style="text-align:left;"> 
	                   <textarea id="descr" name="descr" style="width:500px;height:80px;"><s:property value='#request.role.descr'/></textarea>
	               </td>
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>
</body>
</html>
