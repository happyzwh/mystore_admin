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
   	    <link rel="stylesheet" href="<%=path %>/js/WdatePicker/date/skin/WdatePicker.css" type="text/css" />
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	    <script type="text/javascript" src="<%=path %>/js/WdatePicker/date/WdatePicker.js"></script>
	    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	    <script  type="text/javascript">
	        var tf = false;
		    $(function(){ 
		         index = $("#tbody tr").length;
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
	                location.href='<%=path%>/adv/advAction!list_advimg.dhtml?pid='+$("#pid").val();
	            });
	            $("#save").click(function(){
	                if($.trim($("#title").val()) == ''){
	                    alert("提示：标题不能为空！");
	                    return false;
	                }
	                
	                if($.trim($("#path_pic").val()) == ''){
	                    alert("提示：图片不能为空！");
	                    return false;
	                }
	                
	                if($.trim($("#url").val()) == ''){
	                    alert("提示：链接不能为空！");
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
							url: '<%=path%>/adv/advAction!saveAdvImg.dhtml',
							type: 'post',
							data: {'pid':$("#pid").val(),'id':$("#id").val(),'title':$.trim($("#title").val()),'path_pic':$.trim($("#path_pic").val()),
								  'sort':$.trim($("#sort").val()),'url':$.trim($("#url").val()),'isPermEffe':$.trim($("#isPermEffe").val()),'startTime':$.trim($("#startTime").val()),
								  'endTime':$("#endTime").val(),'status':$("#status").val()
								  },
							async: false,
							dataType: "text",
							success:function(data){
							   if(data == -2){
				                   alert("提示：参数错误！");
				                }else if(data == 0){
				                   alert("提示：保存成功！");
				                   location.href='<%=path%>/adv/advAction!list_advimg.dhtml?pid='+$("#pid").val();
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
	 <div class="main" style="margin-bottom:20px;">
        <table cellspacing="0" cellpadding="0" class="table1">
           <tbody>
	           <tr>
	               <td colspan="4" class="title">编辑广告位图片</td>
	           </tr>
	          <tr>
	               <td width="20%" style="text-align:right;">标题：</td>
	               <td width="50%" style="text-align:left;">
	                   <input type="text" id="title" name="title" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advImg.title'/>"/> 
	               </td>
	              
	           </tr>
	           <tr>
	               <td style="text-align:right;">预览：</td>
	               <td style="text-align:left;position:relative;">
							                   <input class="file" type="file" id="up_file" name="up_file"/> 
							                   <input type="hidden" class="hideInput" id="path_pic" name="path_pic" value='<s:property value="#request.advImg.path_pic"/>'/>
							                   <p class="upload">选择文件</p>
							                   <p class="up_button" style="margin-left:20px;">点击上传</p>
	                             			   <p class="viewview" style="margin-left:20px;">预览</p>
	                             			   <img class="cue" src="<%=path %>/images/question.png"
	                             title="证件必须是彩色原件电子版，可以是扫描件或者数码照。仅支持.jpg .jpeg .bmp的图片格式，图片大小不超过2M。"/>
				   </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">链接：</td>
	               <td style="text-align:left;">
	                   <input type="text" id="url" name="url" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advImg.url'/>"/> 
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">排序：</td>
	               <td style="text-align:left;">
	                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="50" value="<s:property value='#request.advImg.sort'/>"/> 
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">是否永久有效：</td>
	               <td style="text-align:left;">
	                   <select id="isPermEffe" name="isPermEffe">
	                        <option <s:if test="#request.advImg.isPermEffe == 0">selected = "selected"</s:if> value = "0">否</option>
	                        <option <s:if test="#request.advImg.isPermEffe == 1">selected = "selected"</s:if> value = "1">是</option>
	                   </select>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">开始时间：</td>
	               <td style="text-align:left;">
	                   <input type="text" id="startTime" name="startTime" onClick="showDataTimeDailog('yyyy-MM-dd HH:mm:ss');" value="<s:date name="#request.advImg.startTime" format="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" style="width: 200px"/>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">结束时间：</td>
	               <td style="text-align:left;">
	                  <input type="text" id="endTime" name="endTime" onClick="showDataTimeDailog('yyyy-MM-dd HH:mm:ss');" value="<s:date name="#request.advImg.endTime" format="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" style="width: 200px"/>
	               </td>
	           </tr>
	           <tr>
	               <td style="text-align:right;">状态：</td>
	               <td style="text-align:left;">
	                   <select id="status" name="status">
	                        <option <s:if test="#request.advImg.status == 0">selected = "selected"</s:if> value = "0">不可用</option>
	                        <option <s:if test="#request.advImg.status == 1">selected = "selected"</s:if> value = "1">可用</option>
	                   </select>
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
