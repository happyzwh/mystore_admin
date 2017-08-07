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
	         });
	         function jumppage(pageNo){
				 $("#pageNo").val(pageNo);
				 $("#queryForm").submit();
		     }
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：咨询列表</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <!--按条件查询-->
        <form id="queryForm" action="<%=path%>/product/consultAction!list.dhtml" method="post">
        <input type="hidden" id="pageNo" name="pageNo" value=''/>
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">查询条件</td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">用户名称：</td>
	               <td width="35%">
	                   <input type="text" id="userName" name="userName" value='<s:property value="#request.userName"/>'/>
	               </td>
	               <td width="15%" class="center">商品名称 ：</td>
	               <td width="35%">
	                   <input type="text" id="productName" name="productName" value='<s:property value="#request.productName"/>'/>		  
	               </td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">类型：</td>
	               <td width="35%">
	                    <select id="type" name="type" style="width:100px;">
	                        <option value="">全部</option>
	                     	<option <s:if test='#request.type == "0"'>selected="selected"</s:if> value="1">商品</option>
	                     	<option <s:if test='#request.type == "1"'>selected="selected"</s:if> value="2">支付</option>
	                     	<option <s:if test='#request.type == "2"'>selected="selected"</s:if> value="3">售后</option>
	                    </select>
	               </td>
	               <td width="15%" class="center">审核状态：</td>
	               <td width="35%">
	                    <select id="status" name="status" style="width:100px;">
	                        <option value="">全部</option>
	                     	<option <s:if test='#request.status == "0"'>selected="selected"</s:if> value="0">未审核</option>
	                     	<option <s:if test='#request.status == "1"'>selected="selected"</s:if> value="1">审核通过</option>
	                     	<option <s:if test='#request.status == "2"'>selected="selected"</s:if> value="2">审核未通过</option>
	                    </select>
	               </td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">回复状态：</td>
	               <td colspan="3">
	                    <select id="isreply" name="isreply" style="width:100px;">
	                        <option value="">全部</option>
	                     	<option <s:if test='#request.isreply == "0"'>selected="selected"</s:if> value="0">未回复</option>
	                     	<option <s:if test='#request.isreply == "1"'>selected="selected"</s:if> value="1">已回复</option>
	                    </select>
	               </td>
	           </tr>
	           <tr>
	               <td class="buttons" colspan="4">
	                   <input type="button" id="query" value="查询" class="query" style="opacity: 1;"/>
	               </td>
	           </tr>
       	 </tbody>
        </table>
        </form>
         <!--查询结果列表-->
        <table class="table_list" cellpadding="0" cellspacing="0" style="margin-top:0px;">
           <tr class="head">
               <th width="10%">用户名称</th> 
               <th width="20%">商品名称</th> 
               <th width="10%">类型</th> 
               <th width="10%">审核状态</th>
               <th width="10%">回复状态</th>
               <th width="15%">创建时间</th>
               <th width="15%">修改时间</th>
               <th width="10%">操作</th>
           </tr>
            <s:iterator value="list" id="consult" status="index">
	           <tr>
	               <td><s:property value="#consult.userName"/></td> 
	               <td><s:property value="#consult.productName"/></td>
	               <td>
	                  <s:if test='#consult.type == "1"'>商品</s:if>
	                  <s:elseif test='#consult.type == "2"'>支付</s:elseif>
	                  <s:elseif test='#consult.type == "3"'>售后</s:elseif>
	               </td> 
	               <td>
	                  <s:if test='#consult.status == "0"'>未审核</s:if>
	                  <s:elseif test='#consult.status == "1"'>审核通过</s:elseif>
	                  <s:elseif test='#consult.status == "2"'>审核未通过</s:elseif>
	               </td> 
	               <td>
	                  <s:if test='#consult.isreply == "0"'>未回复</s:if>
	                  <s:elseif test='#consult.isreply == "1"'>已回复</s:elseif>
	               </td> 
	               <td><s:date name='#consult.createDate' format="yyyy-MM-dd HH:mm:ss" /></td>   
	               <td><s:date name='#consult.lastDate' format="yyyy-MM-dd HH:mm:ss" /></td>   
	               <td>
	                   <power:permission bh="view_consult">
	                   		<a href='<%=path%>/product/consultAction!detail.dhtml?id=<s:property value="#consult.id"/>' target="_self">详情</a>
	                   </power:permission>
	               </td>
	           </tr>
           </s:iterator>
        </table>        
   </div>
   <div class="table1" style="border:0px;text-align:right;clear:both;margin:20px auto;">
  	 ${pageInfo}
   </div>
</body>
</html>
