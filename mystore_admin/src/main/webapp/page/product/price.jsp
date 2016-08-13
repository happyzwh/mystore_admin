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
	        var regExp = /^(([1-9]\d*)|([0-9]\d*\.\d{1,2}))$/;  
	        $(function(){
	            $("#jbxx").click(function(){
			     	  location.href='<%=path%>/product/productAction!jbxx.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#info").click(function(){
			     	  location.href='<%=path%>/product/productAction!info.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#attr").click(function(){
			     	  location.href='<%=path%>/product/productAction!attr.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#img").click(function(){
			     	  location.href='<%=path%>/product/productAction!img.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#save").click(function(){
			         if($.trim($("#markPrice").val()) == ''){
			              alert("提示：请输入市场价！");
			              return false;
			         }
			         if(!regExp.test($.trim($("#markPrice").val()))){
			              alert("提示：市场价只能为大于0且仅有两位小数的数字！");
			              return false;
			         }
			         if($.trim($("#shopPrice").val()) == ''){
			              alert("提示：请输入商城价！");
			              return false;
			         }
			         if(!regExp.test($.trim($("#shopPrice").val()))){
			              alert("提示：商城价只能为大于0且仅有两位小数的数字！");
			              return false;
			         }

			         $.ajax({
							url: '<%=request.getContextPath() %>/product/productAction!savePrice.dhtml',
							type: 'post',
							data: {'id_pro':$.trim($("#proId").val()),'markPrice':$.trim($("#markPrice").val()),
                                   'shopPrice':$.trim($("#shopPrice").val()),'activePrice':$.trim($("#activePrice").val())
                                  },
							async: false,
							dataType: "text",
							success:function(data){
				                    if(data == -1){
				                         alert("提示：服务异常！");
				                    }else if(data == -2){
				                         alert("提示：参数错误！");
				                    }else{
				                         alert("提示：保存成功！");
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
     <input type="hidden" id="proId" value='<s:property value="#request.id"/>'/>
     <div class="tab_menu">
	     <ul>
	         <li class="first" id="jbxx">商品基本信息</li>
	         <li id="info">商品详情</li>
	         <li id="attr">商品属性</li>
	         <li id="img">商品图片</li>
	         <li class="last  select" id="price">商品价格</li>
	     </ul>
	 </div>
	 <div class="location"><span>当前位置：商品价格信息</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
            <tbody>
	           <tr>
	               <td colspan="4" class="title">编辑商品价格</td>
	           </tr>
	           <tr>
	               <td class="center">市场价：</td>
	               <td >
	                   <input type="text" id="markPrice" name="markPrice" style="width:250px;height:28px;" maxlength="10" value='<s:property value="#request.price.markPrice"/>'/> 
	               </td>
	               <td class="center">商城价：</td>
	               <td >
	                   <input type="text" id="shopPrice" name="shopPrice" style="width:250px;height:28px;" maxlength="10" value='<s:property value="#request.price.shopPrice"/>'/>
	               </td>
	           </tr>
	           <tr>
	               <td class="center">活动价：</td>
	               <td colspan="3">
	                   <input type="text" id="activePrice" name="activePrice" style="width:250px;height:28px;" maxlength="10" value='<s:property value="#request.price.activePrice"/>'/>
	               </td>
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/></div>
</body>
</html>
