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
	            $("#cate_select").click(function(){
				    cate_select();
			    });
		        $("#brand_select").click(function(){
				    brand_select();
			    });
			    $("#back").click(function(){
			        location.href="<%=path%>/product/productAction!list.dhtml";
			    });
			    $("#info").click(function(){
			          if($.trim($("#id").val()) == ''){
			            	 alert("提示：请选保存基本信息！");
			          }else{
			     	  		location.href='<%=path%>/product/productAction!info.dhtml?id='+$.trim($("#id").val());
			     	  }
			    });
			    $("#attr").click(function(){
			    	  if($.trim($("#id").val()) == ''){
			            	 alert("提示：请选保存基本信息！");
			          }else{
			     	  		location.href='<%=path%>/product/productAction!attr.dhtml?id='+$.trim($("#id").val());
			     	  }
			    });
			    $("#img").click(function(){
			          if($.trim($("#id").val()) == ''){
			            	 alert("提示：请选保存基本信息！");
			          }else{
			     	  		location.href='<%=path%>/product/productAction!img.dhtml?id='+$.trim($("#id").val());
			     	  }
			    });
			    $("#price").click(function(){
			    	  if($.trim($("#id").val()) == ''){
			            	 alert("提示：请选保存基本信息！");
			          }else{
			     	 		 location.href='<%=path%>/product/productAction!price.dhtml?id='+$.trim($("#id").val());
			     	  }
			    });
			    $("#save").click(function(){
			        if($.trim($("#id_cate").val()) == ''){
			           alert("提示：请选择分类！");
			           return false;
			        }
			        if($.trim($("#id_brand").val()) == ''){
			           alert("提示：请选择品牌！");
			           return false;
			        }
			        if($.trim($("#name").val()) == ''){
			           alert("提示：请输入名称！");
			           return false;
			        }
			        if($.trim($("#name").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：名称最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#sn").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：编号最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#shortTitle").val()) != '' && $.trim($("#shortTitle").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：短标题最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#subTitle").val()) != '' && $.trim($("#subTitle").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：副标题最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#enTitle").val()) != '' && $.trim($("#enTitle").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：英文标题最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#rome").val()) != '' && $.trim($("#rome").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：拼音最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#jianPin").val()) != '' && $.trim($("#jianPin").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：简拼最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#keyWords").val()) != '' && $.trim($("#keyWords").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：关键字最多为100位字符！");
			           return false;
			        }
			        if($.trim($("#descr").val()) != '' && $.trim($("#descr").val()).replace(/[^\x00-\xff]/g,"**").length > 1000){
			           alert("提示：描述最多为1000位字符！");
			           return false;
			        }
			        $.ajax({
							url: '<%=request.getContextPath() %>/product/productAction!saveJbxx.dhtml',
							type: 'post',
							data: {'id':$.trim($("#id").val()),'id_cate':$.trim($("#id_cate").val()),'id_brand': $.trim($("#id_brand").val()),
                                  'name':$.trim($("#name").val()),'sn':$.trim($("#sn").val()),
                                  'shortTitle':$.trim($("#shortTitle").val()),'subTitle':$.trim($("#subTitle").val()),
                                  'enTitle':$.trim($("#enTitle").val()),'rome':$.trim($("#rome").val()),
                                  'jianPin':$.trim($("#jianPin").val()),'keyWords':$.trim($("#keyWords").val()),
                                  'isShow':$.trim($("#isShow").val()),'isShipping':$.trim($("#isShipping").val()),'isOnSale':$.trim($("#isOnSale").val()),
                                  'descr':$.trim($("#descr").val())
                                  },
							async: false,
							dataType: "text",
							success:function(data){
				                    if(data == -1){
				                         alert("提示：服务异常！");
				                    }else if(data == -2){
				                         alert("提示：参数出错！");
				                    }else if(data == -3){
				                         alert("提示：名称重复！");
				                    }else if(data == -4){
				                         alert("提示：编号重复！");
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
	        });
	        
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
			     $("#cateName").text(name);
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
			     $("#brandName").text(name);
			 }
		    </script>
	</head>
<body class="main">
     <input type="hidden" id="id" value='<s:property value="#request.product.id"/>'/>
     <div class="tab_menu">
	     <ul>
	         <li class="first select" id="jbxx" >商品基本信息</li>
	         <li id="info">商品详情</li>
	         <li id="attr">商品属性</li>
	         <li id="img">商品图片</li>
	         <li class="last" id="price">商品价格</li>
	     </ul>
	 </div>
	 <div class="location"><span>当前位置：商品基本信息</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">编辑商品基本信息</td>
	           </tr>
	           <tr>
	               <td width="15%" class="center">分类：</td>
	               <td width="35%">
	                    <span id="cateName" style="width:100px;float:left;height:23px;line-height:23px;"><s:property value="#request.product.cateName"/></span>
						<p class="upload" id="cate_select" style="margin-left:100px;">分类选择</p> 
						<input type="hidden" id="id_cate"  name="id_cate" value='<s:property value="#request.product.id_cate"/>'/>
	               </td>
	               <td width="15%" class="center">品牌 ：</td>
	               <td width="35%">
	                   <span id="brandName" style="width:100px;float:left;height:23px;line-height:23px;"><s:property value="#request.product.brandName"/></span>
					   <p class="upload" id="brand_select" style="margin-left:100px;">品牌选择</p> 
					   <input type="hidden" id="id_brand"  name="id_brand" value='<s:property value="#request.product.id_brand"/>'/>	  
	               </td>
	           </tr>
	           <tr>
	               <td class="center">名称：</td>
	               <td >
	                   <input type="text" id="name" name="name" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.name"/>'/> 
	               </td>
	               <td class="center">编号：</td>
	               <td >
	                   <input type="text" id="sn" name="sn" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.sn"/>'/>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">短标题：</td>
	               <td >
	                   <input type="text" id="shortTitle" name="shortTitle" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.shortTitle"/>'/>
	               </td>
	               <td class="center">副标题：</td>
	               <td >
	                   <input type="text" id="subTitle" name="subTitle" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.subTitle"/>'/>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">英文标题：</td>
	               <td >
	                   <input type="text" id="enTitle" name="enTitle" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.enTitle"/>'/>
	               </td>
	               <td class="center">拼音：</td>
	               <td >
	                   <input type="text" id="rome" name="rome" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.rome"/>'/>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">简拼：</td>
	               <td >
	                   <input type="text" id="jianPin" name="jianPin" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.jianPin"/>'/>
	               </td>
	               <td class="center">关键字：</td>
	               <td >
	                   <input type="text" id="keyWords" name="keyWords" style="width:250px;height:28px;" maxlength="100" value='<s:property value="#request.product.keyWords"/>'/>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">是否显示：</td>
	               <td >
	                   <select id="isShow" style="width:50px;">
	                       <option <s:if test="#request.product.isShow == 0">selected = 'selected'</s:if> value="0">否</option>
	                       <option <s:if test="#request.product.isShow == 1">selected = 'selected'</s:if> value="1">是</option>
	                   </select>
	               </td>
	               <td class="center">是否包邮：</td>
	               <td >
	                  <select id="isShipping" style="width:50px;">
	                       <option <s:if test="#request.product.isShipping == 0">selected = 'selected'</s:if> value="0">否</option>
	                       <option <s:if test="#request.product.isShipping == 1">selected = 'selected'</s:if> value="1">是</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">是否上下架：</td>
	               <td colspan="3">
	                   <select id="isOnSale" style="width:50px;">
	                       <option <s:if test="#request.product.isOnSale == 0">selected = 'selected'</s:if> value="0">否</option>
	                       <option <s:if test="#request.product.isOnSale == 1">selected = 'selected'</s:if> value="1">是</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">描述：</td>
	               <td colspan="3">
	                   <textarea  cols="100" id="descr" name="descr"  rows="10"><s:property value="#request.product.descr"/></textarea>
	               </td>    
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>
</body>
</html>
