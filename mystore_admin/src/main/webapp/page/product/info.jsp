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
   	    <script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript"></script>	    
	    <script type="text/javascript" src="<%=path%>/js/artDialog/artDialog.js"></script>
	    <script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
	    <link rel="stylesheet" href="<%=path%>/js/artDialog/skins/green.css" />
	    <script  type="text/javascript">
	        $(function(){
	            $("#jbxx").click(function(){
			     	  location.href='<%=path%>/product/productAction!jbxx.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#attr").click(function(){
			     	  location.href='<%=path%>/product/productAction!attr.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#img").click(function(){
			     	  location.href='<%=path%>/product/productAction!img.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#price").click(function(){
			     	  location.href='<%=path%>/product/productAction!price.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#save").click(function(){
			        if($.trim($("#provider").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：供应商最多为100位字符！");
			           return false;
			        }
			        $.ajax({
							url: '<%=request.getContextPath() %>/product/productAction!saveInfo.dhtml',
							type: 'post',
							data: {'id_pro':$.trim($("#proId").val()),'provider':$.trim($("#provider").val()),
                                  'descr':CKEDITOR.instances.descr.getData(),'feature':CKEDITOR.instances.feature.getData(),
                                  'proShow':CKEDITOR.instances.proShow.getData(),'areaIntroduce':CKEDITOR.instances.areaIntroduce.getData(),
                                  'qualification':CKEDITOR.instances.qualification.getData(),'useMethod':CKEDITOR.instances.useMethod.getData(),
                                  'brandCulture':CKEDITOR.instances.brandCulture.getData(),'honor':CKEDITOR.instances.honor.getData(),
                                  'providerIntroduce':CKEDITOR.instances.providerIntroduce.getData()
                                  },
							async: false,
							dataType: "text",
							success:function(data){
				                    if(data == -1){
				                         alert("提示：服务异常！");
				                    }else{
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
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="basePath" value='<%=path%>'/>
     <input type="hidden" id="proId" value='<s:property value="#request.id"/>'/>
     <div class="tab_menu">
	     <ul>
	         <li class="first" id="jbxx">商品基本信息</li>
	         <li class="select" id="info">商品详情</li>
	         <li id="attr">商品属性</li>
	         <li id="img">商品图片</li>
	         <li class="last" id="price">商品价格</li>
	     </ul>
	 </div>
	 <div class="location"><span>当前位置：商品详情</span></div>
	 <div class="main">
	    <div style="margin:5px;">
		        <div style="width:100%;text-align:center;">
		             <div style="width:100%;text-align:center;margin:0 auto;">
		                  <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
				             <tbody>
				                     <tr>
						               <td colspan="2" class="title">编辑商品详情</td>
						           </tr>
						           <tr>
						               <td style="text-align:right;width:10%;">供应商：</td>
						               <td style="text-align:left;width:90%;">
						                   <input type="text" id="provider" name="provider" value='<s:property value="#request.proInfo.provider"/>' style="width:400px;height:28px;" maxlength="100" />
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">商品描述：</td>
						               <td style="text-align:left;">
						                   <textarea class="ckeditor" id="descr" name="descr" cols="80"  rows="50"><s:property value="#request.proInfo.descr"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">商品特点：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor" id="feature" name="feature" cols="80"  rows="50"><s:property value="#request.proInfo.feature"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">商品展示：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="proShow" name="proShow"  rows="50"><s:property value="#request.proInfo.proShow"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">产地介绍：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="areaIntroduce" name="areaIntroduce"  rows="50"><s:property value="#request.proInfo.areaIntroduce"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">商品资质：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="qualification" name="qualification"  rows="50"><s:property value="#request.proInfo.qualification"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">使用方法：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="useMethod" name="useMethod"  rows="50"><s:property value="#request.proInfo.useMethod"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">品牌文化：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="brandCulture" name="brandCulture"  rows="50"><s:property value="#request.proInfo.brandCulture"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">获奖荣誉：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="honor" name="honor"  rows="50"><s:property value="#request.proInfo.honor"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">厂家介绍：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="providerIntroduce" name="providerIntroduce"  rows="50"><s:property value="#request.proInfo.providerIntroduce"/></textarea>
						               </td>
						           </tr>
					           </tbody>
					       </table>   
		             </div>
		             <div id="content" style="width:100%;text-align:center;border:0px;" class="table1">
						  <input type="button" value="保存" class="query" id="save"/>
				    </div>
		        </div>
	        </div>
     </div>
</body>
</html>
