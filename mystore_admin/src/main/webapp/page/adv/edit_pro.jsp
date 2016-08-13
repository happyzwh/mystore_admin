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
	              location.href='<%=path%>/adv/advAction!list_pro.dhtml?pid='+$("#pid").val();
	          });
	          $('.list_pro_search li').live('click',function(){
	              if($(this).attr("id") != ''){
		              $("#proId").val($(this).attr("id"));
		              $("#name").val($(this).text());
		              $("#cateName").text($(this).attr("name").split('_')[0]);
		              $("#brandName").text($(this).attr("name").split('_')[1]);
	              }else{
	                  $("#proId").val('');
		              $("#name").val('');
		              $("#cateName").text('');
		              $("#brandName").text('');
	              }
	              $(".list_pro_search").hide();
	          });
	          $("#name").keyup(function(){
	              if($.trim($(this).val()) == ''){
	                  $(this).val('');
	                  $(".list_pro_search").hide();
	                  $(".list_pro_search ul").empty();
	              }else{
	                 $.ajax({
							url: '<%=path%>/adv/advAction!selectProListByLikeNameOrSn.dhtml',
							type: 'post',
							data: {'name':$.trim($("#name").val())},
							async: true,
							dataType: "json",
							success:function(data){
							     $(".list_pro_search ul").empty();
							     if(data && data.returnCode == 0 && data.list && data.list.length > 0){
							          var content = [];
							          $.each(data.list,function(ind,ent){
							             if(ind == data.list.length-1){
							           	 	content.push('<li  class="li_last" id="'+ent.id+'" name="'+ent.cateName+"_"+ent.brandName+'">');
							           	 }else{
							           	    content.push('<li id="'+ent.id+'" name="'+ent.cateName+"_"+ent.brandName+'">'); 
							           	 }
							           	 content.push(ent.name);
							             content.push('</li>');
							          });
							          if(content.lenght == 0)content.push('<li class="li_last">暂无相关商品</li>');
							          $(".list_pro_search ul").append(content.join(''));
							         
							     }else{
							     	$(".list_pro_search ul").append('<li class="li_last">暂无相关商品</li>');
							     }
							     $(".list_pro_search").show();
							},
							error:function(){
							     alert("提示：服务异常！");
							}
				 	});
	              }
	          });
	          $("#save").click(function(){
	               if($.trim($("#name").val()) == '' || $.trim($("#proId").val()) == ''){
	                  alert("提示：商品名称或编号不能为空！");
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
							url: '<%=path%>/adv/advAction!saveProModule.dhtml',
							type: 'post',
							data: {'pid':$.trim($("#pid").val()),'id':$.trim($("#id").val()),'proId':$.trim($("#proId").val()),'name':$.trim($("#name").val()),
								  'sort':$.trim($("#sort").val()),'startTime':$.trim($("#startTime").val()),'endTime':$("#endTime").val(),'status':$("#status").val()
							},
							async: false,
							dataType: "text",
							success:function(data){
							    if(data == -3){
				                   alert("提示：此商品已加入！");
				                }else if(data == -2){
				                   alert("提示：参数错误！");
				                }else if(data == 0){
				                   alert("提示：保存成功！");
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
     <input type="hidden" id="pid" value='<s:property value="#request.pid"/>'/>
	 <input type="hidden" id="id" value='<s:property value="#request.id"/>'/>
	 <input type="hidden" id="proId" value='<s:property value='#request.advProModule.id_pro'/>'/>
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">编辑广告商品模块</td>
	           </tr>
	          <tr>
	               <td width="20%" style="text-align:right;">商品名称或编号：</td>
	               <td width="50%" style="text-align:left;position:relative;">
	                   <input type="text" id="name" name="name" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advProModule.name'/>"/> 
	                   <div class="list_pro_search">
	                      <ul>
	                         <li>1111</li>
	                         <li>2222</li>
	                         <li class="li_last">3333</li>
	                      </ul>
	                   </div>
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">分类名称：</td>
	               <td width="50%" style="text-align:left;" id="cateName">
	                   <s:property value='#request.advProModule.category.name'/>
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">品牌名称：</td>
	               <td width="50%" style="text-align:left;" id="brandName">
	                   <s:property value='#request.advProModule.brand.name'/>
	               </td>
	           </tr>
	            <tr>
	               <td style="text-align:right;">开始时间：</td>
	               <td style="text-align:left;">
	                   <input type="text" id="startTime" name="startTime" onClick="showDataTimeDailog('yyyy-MM-dd HH:mm:ss');" value="<s:date name="#request.advProModule.startTime" format="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" style="width: 200px"/>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">结束时间：</td>
	               <td style="text-align:left;">
	                  <input type="text" id="endTime" name="endTime" onClick="showDataTimeDailog('yyyy-MM-dd HH:mm:ss');" value="<s:date name="#request.advProModule.endTime" format="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" style="width: 200px"/>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">状态：</td>
	               <td style="text-align:left;">
	                   <select id="status" name="status">
	                        <option <s:if test="#request.advProModule.status == 0">selected = "selected"</s:if> value = "0">不可用</option>
	                        <option <s:if test="#request.advProModule.status == 1">selected = "selected"</s:if> value = "1">可用</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">排序：</td>
	               <td style="text-align:left;">
	                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advProModule.sort'/>"/> 
	               </td>
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>
</body>
</html>
