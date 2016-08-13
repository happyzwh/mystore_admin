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
	          $(".selectall").click(function(){
	                if($(this).attr("checked")){
	                    $(".selectone").attr("checked","checked");
	                }else{
	                    $(".selectone").removeAttr("checked");
	                }
	            });
	            $(".selectone").click(function(){
	                if($(".selectone").length == $(".selectone:checked").length){
	                     $(".selectall").attr("checked","checked");
	                }else{
	                     $(".selectall").removeAttr("checked");
	                }
	            });
	          $("#query").click(function(){
	              $("#pageNo").val(1);
	              $("#queryForm").submit();
	          });
	          $("#add").click(function(){
	               location.href="<%=path%>/product/productAction!jbxx.dhtml";
	          });
	          $("#cate_select").click(function(){
				    cate_select();
			  });
		      $("#brand_select").click(function(){
				    brand_select();
			  });
	       });
	       function jumppage(pageNo){
				 $("#pageNo").val(pageNo);
				 $("#queryForm").submit();
		   }
		   function delPro(id){
		      if(confirm("提示：是否确认删除？")){
			       $.ajax({
								url: '<%=path%>/product/productAction!delPro.dhtml',
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
		 function cate_select(){
			 	var url='<%=request.getContextPath() %>/product/productAction!cate_select.dhtml';
			 	var dialogWidth = 600;
			 	var dialogHeight = 600;
			 	var dialogLeft = (screen.width - dialogWidth)/2;
			 	var dialogTop = (screen.width - dialogHeight)/2;
				window.showModalDialog(url,window,"dialogTop:"+dialogTop+"px;dialogLeft:"+dialogLeft+"px;dialogWidth:"+dialogWidth+"px;dialogHeight:"+dialogHeight+"px;scroll:yes;status:no"); 
		 }
		 function setCate(id,name){
		     $("#id_cate").val(id);
		     $("#cateName").val(name);
		     $("#cate_name").text(name);
		 }
		function brand_select(){
			 	var url='<%=request.getContextPath() %>/product/productAction!brand_select.dhtml';
			 	var dialogWidth = 600;
			 	var dialogHeight = 600;
			 	var dialogLeft = (screen.width - dialogWidth)/2;
			 	var dialogTop = (screen.width - dialogHeight)/2;
				window.showModalDialog(url,window,"dialogTop:"+dialogTop+"px;dialogLeft:"+dialogLeft+"px;dialogWidth:"+dialogWidth+"px;dialogHeight:"+dialogHeight+"px;scroll:yes;status:no"); 
		 }
		 function setBrand(id,name){
		     $("#id_brand").val(id);
		     $("#brandName").val(name);
		     $("#brand_name").text(name);
		 }
		 function updateBatchProIsOnSale(){
		      if($(".selectone:checked").length == 0){
	              alert("提示：请选择商品！");
	              return false;
	          }
	          var attrArr = [];
	          $(".selectone:checked").each(function(ind,ent){
	              attrArr.push($(this).val());
	              attrArr.push(",");
	          });
	          $.ajax({
						url: '<%=path%>/product/productAction!updateBatchProIsOnSale.dhtml',
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
							         $("#pageNo").val(1);
	              					 $("#queryForm").submit();
							   }
							      
						},
						error:function(){
								alert("提示：服务异常！");
						}
			   }); 
		 }
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：商品列表</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <!--按条件查询-->
        <form id="queryForm" action="<%=path%>/product/productAction!list.dhtml" method="post">
        <input type="hidden" id="pageNo" name="pageNo" value=''/>
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">查询条件</td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">名称：</td>
	               <td width="35%">
	                   <input type="text" id="name" name="name" value='<s:property value="#request.name"/>'/>
	               </td>
	               <td width="15%" class="center">编号 ：</td>
	               <td width="35%">
	                   <input type="text" id="sn" name="sn" value='<s:property value="#request.sn"/>'/>		  
	               </td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">分类：</td>
	               <td width="35%">
	                    <span id="cate_name" style="width:100px;float:left;height:23px;line-height:23px;"><s:property value="#request.cateName"/></span>
						<p class="upload" id="cate_select" style="margin-left:100px;">分类选择</p> 
						<input type="hidden" id="id_cate"  name="id_cate" value='<s:property value="#request.id_cate"/>'/>
						<input type="hidden" id="cateName"  name="cateName" value='<s:property value="#request.cateName"/>'/>
	               </td>
	               <td width="15%" class="center">品牌 ：</td>
	               <td width="35%">
	                   <span id="brand_name" style="width:100px;float:left;height:23px;line-height:23px;"><s:property value="#request.brandName"/></span>
					   <p class="upload" id="brand_select" style="margin-left:100px;">品牌选择</p> 
					   <input type="hidden" id="id_brand"  name="id_brand" value='<s:property value="#request.id_brand"/>'/>
					   <input type="hidden" id="brandName"  name="brandName" value='<s:property value="#request.brandName"/>'/>		  
	               </td>
	           </tr>
	            <tr>
	               <td width="15%" class="center">是否上下架：</td>
	               <td width="85%" colspan="3">
	                   <select id="isOnSale" name="isOnSale" style="width:100px;">
	                      <option value="">请选择</option>
	                      <option <s:if test='#request.isOnSale != "" and #request.isOnSale == 1'>selected="selected"</s:if> value="1">上架</option>
	                      <option <s:if test='#request.isOnSale != "" and #request.isOnSale == 0'>selected="selected"</s:if> value="0">下架</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td class="buttons" colspan="4">
	                   <input type="button" id="query" value="查询" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;
	                   <power:permission bh="add_product">
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
               <th width="10%"><input type="checkbox" class="selectall"/></th>
               <th width="20%">名称</th> 
               <th width="10%">编号</th> 
               <th width="15%">分类</th>
               <th width="15%">品牌</th>
               <th width="10%">状态</th>
               <th width="10%">上下架时间</th>
               <th width="10%">操作</th>
           </tr>
            <s:iterator value="list" id="pro" status="index">
	           <tr>
	               <td><input type="checkbox" class="selectone" value='<s:property value="#pro.id"/>'/></td>
	               <td><s:property value="#pro.name"/></td> 
	               <td><s:property value="#pro.sn"/></td>
	               <td><s:property value="#pro.cateName"/></td> 
	               <td><s:property value="#pro.brandName"/></td>
	               <td>
	                 <s:if test='#pro.isOnSale == 1'>上架</s:if>
	                 <s:else>下架</s:else>
	               </td> 
	                <td><s:date name='#pro.onOrOffSaleTime' format="yyyy-MM-dd HH:mm:ss" /></td>   
	               <td>
	                   <power:permission bh="update_product">
	                   		<a href='<%=path%>/product/productAction!jbxx.dhtml?id=<s:property value="#pro.id"/>' target="_self">修改</a>
	                   </power:permission>
	                   <power:permission bh="del_product">
	                   		<a href='<%=path%>/product/productAction!delPro.dhtml?id=<s:property value="#pro.id"/>' target="_self">删除</a>
	                   </power:permission>
	               </td>
	           </tr>
           </s:iterator>
        </table>        
   </div>
   <div class="table1" style="border:0px;text-align:right;clear:both;margin:20px auto;">
   <div style="float:left;margin-left:20px;"><power:permission bh="onoffsale"><input type="button" id="query" value="上下架" onclick="javascript:updateBatchProIsOnSale();" class="query" /></power:permission></div>
   ${pageInfo}
   </div>
</body>
</html>
