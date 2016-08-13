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
	            $("#jbxx").click(function(){
			     	  location.href='<%=path%>/product/productAction!jbxx.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#info").click(function(){
			     	  location.href='<%=path%>/product/productAction!info.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#img").click(function(){
			     	  location.href='<%=path%>/product/productAction!img.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#price").click(function(){
			     	  location.href='<%=path%>/product/productAction!price.dhtml?id='+$.trim($("#proId").val());
			    });
	        });
	        function save(){
                   var ids = [];
                   $(".inputType").each(function(ind,ent){
                        var cateVttrId = $(this).attr("id");
                        if($(this).find('input[type="text"]').length > 0 && $.trim($(this).find("input:first").val()) != ''){
                           if(ids.length == 0)ids.push(";");
                           ids.push(cateVttrId+"-0-"+$.trim($(this).find("input:first").val()));
                           ids.push(";");
                        }else if($(this).find("select").length > 0 && $.trim($(this).find("select:first").val()) != ''){
                           if(ids.length == 0)ids.push(";");
                           ids.push(cateVttrId+"-1-"+$.trim($(this).find("select:first").val()));
                           ids.push(";");
                        }else if($(this).find('input[type="radio"]').length > 0 && $.trim($(this).find('input[type="radio"]:checked').val()) != ''){
                           if(ids.length == 0)ids.push(";");
                           ids.push(cateVttrId+"-2-"+$.trim($(this).find('input[type="radio"]:checked').val()));
                           ids.push(";");
                        }else if($(this).find('input[type="checkbox"]').length > 0 && $.trim($(this).find('input[type="checkbox"]:checked').val()) != ''){
	                       if(ids.length == 0)ids.push(";");
	                       var idd = [];
	                       $('input[type="checkbox"]:checked').each(function(){
	                           if(idd.length == 0)idd.push(",");
	                           idd.push($(this).val());
	                           idd.push(",");
	                       });
	                       ids.push(cateVttrId+"-3-"+idd.join(''));
	                       ids.push(";");
                        }else{
                           if(ids.length == 0)ids.push(";");
                           ids.push(cateVttrId+"-9");
                           ids.push(";"); 
                        }
                   });
                   if(ids.length == 0){
                         alert("提示：请编辑属性值！");
                         return false;
                   }
			       $.ajax({
								url: '<%=path%>/product/productAction!saveProAttr.dhtml',
								type: 'post',
								data: {'ids':ids.join(''),'id_pro':$.trim($("#proId").val())},
								async: false,
								dataType: "text",
								success:function(data){
					                if(data == 0){
					                    alert("提示：保存成功！");
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
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="proId" value='<s:property value="#request.id"/>'/>
     <div class="tab_menu">
	     <ul>
	         <li class="first" id="jbxx">商品基本信息</li>
	         <li id="info">商品详情</li>
	         <li class="select" id="attr">商品属性</li>
	         <li id="img">商品图片</li>
	         <li class="last" id="price">商品价格</li>
	     </ul>
	 </div>
	 <div class="location"><span>当前位置：商品属性信息</span></div>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">编辑商品属性</td>
	           </tr>
	           <s:iterator value="attrList" id="attr" status="index">
		           <tr>
		               <td width="15%" style="text-align:center;"><s:property value='#attr.basicAttr.name'/></td>
		               <td width="85%" style="text-align:left;" class="inputType" id='<s:property value='#attr.id'/>'>
		                   <s:set name="attrId" value="#attr.id" />
		                   <s:if test='#attr.basicAttr.showType == "0"'>
		                        <s:if test='#request.attrMap != null && #request.attrMap.get(#attrId+"") != null'>
		                        	<input type="text" value='<s:property value='#request.attrMap.get(#attrId+"").attrValue'/>' style="width:500px;height:25px;line-height:25px;" />
		                        </s:if>
		                        <s:else>
		                            <input type="text" value='' style="width:500px;height:25px;line-height:25px;" />
		                        </s:else>
		                   </s:if>
		                   <s:elseif test='#attr.basicAttr.showType == "1"'>
		                        <select  style="width:100px;">
		                            <option value=''>请选择</option>
		                            <s:iterator value="#attr.basicAttr.valueList" id="item" status="ind">
		                               <s:if test='#request.attrMap != null && #request.attrMap.get(#attrId+"") != null && #request.attrMap.get(#attrId+"").id_attr == #item.id'>
		                                   <option selected = "selected" value='<s:property value="#item.id"/>'><s:property value='#item.value'/></option>
		                               </s:if>
		                               <s:else>
		                                   <option value='<s:property value="#item.id"/>'><s:property value='#item.value'/></option>
		                               </s:else>
		                           </s:iterator> 
		                        </select>
		                   </s:elseif>
		                   <s:elseif test='#attr.basicAttr.showType == "2"'>
		                           <s:iterator value="#attr.basicAttr.valueList" id="item" status="ind">
		                               <s:if test='#request.attrMap != null && #request.attrMap.get(#attrId+"") != null && #request.attrMap.get(#attrId+"").id_attr == #item.id'>
		                               		<input type="radio" name='index_<s:property value="#index.index"/>' checked="checked" value='<s:property value="#item.id"/>' />  
		                               </s:if>
		                               <s:else>
		                                  <input type="radio" name='index_<s:property value="#index.index"/>' value='<s:property value="#item.id"/>' /> 
		                               </s:else>
		                               <s:property value='#item.value'/>
		                           </s:iterator> 
		                   </s:elseif>
		                   <s:elseif test='#attr.basicAttr.showType == "3"'>
		                            <s:iterator value="#attr.basicAttr.valueList" id="item" status="ind">
		                               <s:set name="itemId" value="','+#item.id+','" />
		                               <s:if test='#request.attrMap != null && #request.attrMap.get(#attrId+"") != null && #request.attrMap.get(#attrId+"").attrValue.indexOf(#itemId) != -1'>
		                               		<input type="checkbox" checked="checked" value='<s:property value="#item.id"/>' />  
		                               </s:if>
		                               <s:else>
		                                   <input type="checkbox"  value='<s:property value="#item.id"/>' />  
		                               </s:else>
		                               <s:property value='#item.value'/>
		                           </s:iterator> 
		                   </s:elseif>
		               </td>
		           </tr>
	           </s:iterator>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" onclick="javascript:save();" value="保存" class="query" style="opacity: 1;"/></div>
</body>
</html>
