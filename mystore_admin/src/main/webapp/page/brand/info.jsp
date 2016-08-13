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
   	    <script src="<%=path%>/js/jquery-1.9.1.js" type="text/javascript"></script>	    
	    <script type="text/javascript" src="<%=path%>/js/artDialog/artDialog.js"></script>
	    <script type="text/javascript" src="<%=path%>/js/ckeditor/ckeditor.js"></script>
	    <link rel="stylesheet" href="<%=path%>/js/artDialog/skins/green.css" />
	    <script  type="text/javascript">
	       $(function(){ 
		        $("#region_select").click(function(){
				    region_select();
				});
				$("#save").click(function(){
				      if($.trim($("#id_region").val()) == ''){
				          alert("提示：请选择省份！");
				          return false;
				      }
				      $.ajax({
							url: '<%=request.getContextPath() %>/brand/brandAction!updateBrandInfo.dhtml',
							type: 'post',
							data: {'id_region':$.trim($("#id_region").val()),'manufacturer':$.trim($("#manufacturer").val()),'info': CKEDITOR.instances.info.getData(),
                                  'culture':CKEDITOR.instances.culture.getData(),'honorsandawards':CKEDITOR.instances.honorsandawards.getData(),
                                  'qualification':CKEDITOR.instances.qualification.getData(),'pid':<s:property value="#request.pid"/>
                                  },
							async: false,
							dataType: "text",
							success:function(data){
				                    if(data == -1){
				                         alert("提示：服务异常！");
				                    }else if(data == -2){
				                         alert("提示：参数出错！");
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
		   function region_select(){
			 	var url='<%=request.getContextPath() %>/brand/brandAction!region_select.dhtml';
			 	var dialogWidth = 600;
			 	var dialogHeight = 600;
			 	var dialogLeft = (screen.width - dialogWidth)/2;
			 	var dialogTop = (screen.width - dialogHeight)/2;
				window.showModalDialog(url,window,"dialogTop:"+dialogTop+"px;dialogLeft:"+dialogLeft+"px;dialogWidth:"+dialogWidth+"px;dialogHeight:"+dialogHeight+"px;scroll:yes;status:no"); 
		   }
		   function setRegion(id,name){
		     $("#id_region").val(id);
		     $("#regionName").text(name);
		   }
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：品牌详情</span></div>
	 <div class="main">
	    <div style="margin:5px;">
		        <div style="width:100%;text-align:center;">
		             <div style="width:100%;text-align:center;margin:0 auto;">
		                  <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
				             <tbody>
						           <tr>
						               <td style="text-align:right;width:10%;">省份：</td>
						               <td style="text-align:left;width:90%;">
						                   <span id="regionName" style="width:100px;float:left;height:23px;line-height:23px;"><s:property value="#request.brandInfo.regName"/></span>
						                   <p class="upload" id="region_select" style="margin-left:100px;">省份选择</p> 
						                   <input type="hidden" id="id_region" value='<s:property value="#request.brandInfo.id_region"/>'  name="id_region"/>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">厂商：</td>
						               <td style="text-align:left;">
						                  <input type="text" id="manufacturer" name="manufacturer" value='<s:property value="#request.brandInfo.manufacturer"/>' style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">品牌概述：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor" id="info" name="info" cols="80"  rows="50"><s:property value="#request.brandInfo.info"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">品牌文化：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="culture" name="culture"  rows="50"><s:property value="#request.brandInfo.culture"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">获奖或荣誉：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="honorsandawards" name="honorsandawards"  rows="50"><s:property value="#request.brandInfo.honorsandawards"/></textarea>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">商品资质：</td>
						               <td style="text-align:left;">
											<textarea class="ckeditor"  cols="80" id="qualification" name="qualification"  rows="50"><s:property value="#request.brandInfo.qualification"/></textarea>
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
