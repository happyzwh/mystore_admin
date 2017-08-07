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
			    $("#save").click(function(){
			        if($.trim($("#content").val()) != '' && $.trim($("#content").val()).replace(/[^\x00-\xff]/g,"**").length > 1000){
			           alert("提示：回复最多为1000位字符！");
			           return false;
			        }
			        $.ajax({
							url: '<%=request.getContextPath() %>/product/consultAction!audit.dhtml',
							type: 'post',
							data: {'id':$("#id").val(),'status':$("#status").val(),'content':$.trim($("#content").val())},
							async: false,
							dataType: "text",
							success:function(data){
				                    if(data == -1){
				                         alert("提示：服务异常！");
				                    }else if(data == -2){
				                         alert("提示：参数出错！");
				                    }else if(data > 0){
				                         alert("提示：保存成功！");
				                         $("#id").val(data);
				                    }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
				       }); 
			    });
			    $("#back").click(function(){
			      	  location.href="<%=path%>/product/consultAction!list.dhtml";
			    });
	        });
		    </script>
	</head>
<body class="main">
     <input type="hidden" id="id" value='<s:property value="#request.consult.id"/>'/>
	 <div class="location"><span>当前位置：咨询详情</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">咨询详情</td>
	           </tr>
	           <tr>
	               <td class="center" width="15%">用户名称：</td>
	               <td width="35%">
	                  <s:property value="consult.userName"/>
	               </td>
	               <td class="center" width="15%">商品名称：</td>
	               <td width="35%">
	                   <s:property value="consult.productName"/>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">咨询类型：</td>
	               <td >
	                   <s:if test='consult.type == "1"'>商品</s:if>
	                   <s:elseif test='consult.type == "2"'>支付</s:elseif>
	                   <s:elseif test='consult.type == "3"'>售后</s:elseif>
	               </td>
	              <td class="center">回复状态：</td>
	               <td >
	                   <s:if test='consult.isreply == "0"'>未回复</s:if>
	                   <s:elseif test='consult.isreply == "1"'>已回复</s:elseif>
	               </td>
	           </tr>
	           <tr>
	              <td class="center">创建时间：</td>
	               <td colspan="3">
	                  <s:date name='consult.createDate' format="yyyy-MM-dd HH:mm:ss" />
	               </td>
	           </tr>
	           <tr>
	               <td class="center">咨询内容：</td>
	               <td colspan="3">
	                  <s:property value="consult.content"/>
	               </td>    
	           </tr>
	           <tr>
	               <td class="center">审核状态：</td>
	               <td colspan="3">
	                    <select id="status" name="status" style="width:100px;">
	                     	<option <s:if test='consult.status == "1"'>selected="selected"</s:if> value="1">审核通过</option>
	                     	<option <s:if test='consult.status == "2"'>selected="selected"</s:if> value="2">审核不通过</option>
	                    </select>
	               </td> 
	           </tr>
	           <tr>
	               <td class="center">历史回复内容：</td>
	               <td colspan="3">
	                 <s:iterator value="consult.replys" id="reply" status="index">
	                 	<p><s:property value="#reply.content"/></p>
	                 </s:iterator>
	               </td>    
	           </tr>
	           <tr>
	               <td class="center">回复内容：</td>
	               <td colspan="3">
	                   <textarea  cols="100" id="content" name="content"  rows="10"></textarea>
	               </td>    
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;">
    	<power:permission bh="check_consult">
	   		<input type="button" id="save" value="提交" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;
	   </power:permission>
   		<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/>
   </div>
</body>
</html>
