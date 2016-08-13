<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.mystore.business.common.ConfigReader" pageEncoding="UTF-8"%>
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
   	    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	    <script  type="text/javascript">
	        var tf = false;
		    $(function(){ 
		         $("#viewImg").load(function(){
		              tf = true;
		              $("#viewImg").show();
				      $("#loading").hide(); 
		         });
		         $(".viewview").live('click',function(){
		              var thiz = $(this);
		              $("#viewImg").hide();
				      $("#loading").show(); 
		              $("#viewImg").attr("src","<%=ConfigReader.getPath_pic_service()%>/"+$(this).prev().prev().prev().val()+'?'+new Date().getTime()); 
		              $("#review").fadeIn();
		              if($(this).prev().prev().prev().val() != ''){
		                  tf = false;
						  setTimeout(function(){
						       if(!tf){
							         thiz.click();
						        }
						  },1000);
					  }
				 });
			     /*单击关闭*/
				 $("#review-close").live('click',function(){
					  $("#review").fadeOut();
				});
				
				$(".up_button").live('click',function(){
					    tf = false;
				        var uid = $(this).prev().prev().prev().attr("id"); 
					    var fileName = $(this).prev().prev().prev().val();
				        if( fileName == ''){
				           alert("提示：请选择文件！");
				           return false;
				        }
				        var fileType = fileName.substring(fileName.lastIndexOf(".")+1);  
			           	if( fileType !="jpg" && fileType !="jpeg" && fileType !="bmp" ){  
			                alert("提示：请选择正确的图片文件！");  
			                return false;  
			            }else{ 
			                $("#"+uid).next().next().text("上传中");
			                $.ajaxFileUpload({  
			                    url:"<%=path %>/adv/advAction!uploadImg.dhtml", 
			                    fileElementId:uid,        
			                    dataType:"json",    
			                    data:{'id':$("#id").val()},           
			                    success:function(data,status){ 
					                      if(data.returnCode == 1){
					                            $("#"+uid).next().val(data.path);
					                            $("#"+uid).next().next().text("已上传");
					                      }else if(data.returnCode == -2){
					                            alert("提示：参数错误！");
					                            $("#"+uid).next().next().text("选择文件");
					                      }else if(data.returnCode == -3){
					                            alert("提示：文件不存在！");
					                            $("#"+uid).next().next().text("选择文件");
					                      }else if(data.returnCode == -4){
					                            alert("提示：文件太大！");
					                            $("#"+uid).next().next().text("选择文件");
					                      }else if(data.returnCode == -1){
					                            alert("提示：服务异常！");
								     			$("#"+uid).next().next().text("选择文件");
					                      }
			                    },
								error:function(){
								     alert("提示：服务异常！");
								     $("#"+uid).next().next().text("选择文件");
								}
			                });
			            } 
			  });
	          $("#back").click(function(){
	              location.href='<%=path%>/adv/advAction!list_resource.dhtml?pid='+$("#pid").val();
	          });
	          $('.list_pro_search li').live('click',function(){
	              if($(this).attr("id") != ''){
		              $("#proId").val($(this).attr("id"));
		              $("#proName").val($(this).text());
		              $("#cateName").text($(this).attr("name").split('_')[0]);
		              $("#brandName").text($(this).attr("name").split('_')[1]);
	              }else{
	                  $("#proId").val('');
		              $("#proName").val('');
		              $("#cateName").text('');
		              $("#brandName").text('');
	              }
	              $(".list_pro_search").hide();
	          });
	          $("#proName").keyup(function(){
	              if($.trim($(this).val()) == ''){
	                  $(this).val('');
	                  $(".list_pro_search").hide();
	                  $(".list_pro_search ul").empty();
	              }else{
	                 $.ajax({
							url: '<%=path%>/adv/advAction!selectProListByLikeNameOrSn.dhtml',
							type: 'post',
							data: {'name':$.trim($("#proName").val())},
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
	               
	               if($.trim($("#sort").val()) != ""){
		                 var patrns=/^[1-9]\d{0,1}$/; 
						 if(!patrns.exec($.trim($("#sort").val()))){
						    alert("提示：序号格式应为1-2位正数！");
	                        return false;
						 }
					}
					
	                $.ajax({
							url: '<%=path%>/adv/advAction!saveResModule.dhtml',
							type: 'post',
							data: {'pid':$.trim($("#pid").val()),'id':$.trim($("#id").val()),'proId':$.trim($("#proId").val()),'name':$.trim($("#name").val()),'url':$.trim($("#url").val()),'path_pic':$.trim($("#path_pic").val()),
							       'content':$.trim($("#content").val()),'isMarkShow':$.trim($("#isMarkShow").val()),'isShow':$.trim($("#isShow").val()),'sort':$.trim($("#sort").val()),'status':$("#status").val()
							},
							async: false,
							dataType: "text",
							success:function(data){
                                if(data == -2){
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
	                   <input type="text" id="proName" name="proName" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advResourceModule.product.name'/>"/> 
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
	                   <s:property value='#request.advResourceModule.category.name'/>
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">品牌名称：</td>
	               <td width="50%" style="text-align:left;" id="brandName">
	                   <s:property value='#request.advResourceModule.brand.name'/>
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">显示名称：</td>
	               <td width="50%" style="text-align:left;">
	                   <input type="text" id="name" name="name" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advResourceModule.name'/>"/>
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">url：</td>
	               <td width="50%" style="text-align:left;">
	                  <input type="text" id="url" name="url" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advResourceModule.url'/>"/> 
	               </td>
	           </tr>
	            <tr>
	               <td style="text-align:right;">预览：</td>
	               <td style="text-align:left;position:relative;">
							                   <input class="file" type="file" id="up_file" name="up_file"/> 
							                   <input type="hidden" class="hideInput" id="path_pic" name="path_pic" value='<s:property value="#request.advResourceModule.path_pic"/>'/>
							                   <p class="upload">选择文件</p>
							                   <p class="up_button" style="margin-left:20px;">点击上传</p>
	                             			   <p class="viewview" style="margin-left:20px;">预览</p>
	                             			   <img class="cue" src="<%=path %>/images/question.png"
	                             title="证件必须是彩色原件电子版，可以是扫描件或者数码照。仅支持.jpg .jpeg .bmp的图片格式，图片大小不超过2M。"/>
				   </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">显示内容：</td>
	               <td width="50%" style="text-align:left;">
	                  <input type="text" id="content" name="content" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advResourceModule.content'/>"/> 
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">是否显示标签：</td>
	               <td width="50%" style="text-align:left;">
	                  <select id="isMarkShow" name="isMarkShow" style="width:50px;">
	                      <option <s:if test='#request.advResourceModule.isMarkShow== 1'>selected="selected"</s:if> value="1">是</option>
	                      <option <s:if test='#request.advResourceModule.isMarkShow== 0'>selected="selected"</s:if> value = "0">否</option>
	                  </select>  
	               </td>
	           </tr>
	           <tr>
	               <td width="20%" style="text-align:right;">是否显示：</td>
	               <td width="50%" style="text-align:left;">
	                  <select id="isShow" name="isShow" style="width:50px;">
	                      <option <s:if test='#request.advResourceModule.isShow== 1'>selected="selected"</s:if> value="1">是</option>
	                      <option <s:if test='#request.advResourceModule.isShow== 0'>selected="selected"</s:if> value = "0">否</option>
	                  </select> 
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">状态：</td>
	               <td style="text-align:left;">
	                   <select id="status" name="status">
	                        <option <s:if test="#request.advResourceModule.status == 0">selected = "selected"</s:if> value = "0">不可用</option>
	                        <option <s:if test="#request.advResourceModule.status == 1">selected = "selected"</s:if> value = "1">可用</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">排序：</td>
	               <td style="text-align:left;">
	                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advResourceModule.sort'/>"/> 
	               </td>
	           </tr>
       	 </tbody>
        </table>
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="back" value="返回" class="query" style="opacity: 1;"/></div>
   <!--预览按钮的弹出层-->
    <div id="review" style="top:5px;">
        <div style="height:30px;lie-height:30px;width:100%;">
            <img id="review-close" src="<%=path %>/images/close.png"/>
        </div>
        <div style="height:100%;width:100%;text-align: center;">   
            <img style="display:none;" src="" id="viewImg" width="400px" height="400px"/>
            <img  src="<%=path %>/images/loading.gif" id="loading" width="400px" height="400px" style="clear:both;"/>
        </div>
    </div>   
</body>
</html>
