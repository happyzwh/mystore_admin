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
	        var index = 0;
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
			                    url:"<%=path %>/product/productAction!uploadImg.dhtml", 
			                    fileElementId:uid,        
			                    dataType:"json",    
			                    data:{'id_pro':<s:property value="#request.id"/>,'id':$("#"+uid).next().attr("name")},           
			                    success:function(data,status){ 
					                      if(data.returnCode == 1){
					                            $("#"+uid).next().val(data.path);
					                            $("#"+uid).parent().prev().text(data.path);
					                            $("#"+uid).next().attr("name",data.id);
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
			    $(".up").live('click',function(){
	                 if($(this).parent().parent().prev().length > 0){
	                    $(this).parent().parent().insertBefore($(this).parent().parent().prev());  
	                    $("#tbody tr").each(function(ind,ent){
	                        $(this).find("td:first").text(ind+1);
	                    });
	                 }
	            });
	            $(".down").live('click',function(){
	                  if($(this).parent().parent().next().length > 0){
	                     $(this).parent().parent().insertAfter($(this).parent().parent().next());  
	                     $("#tbody tr").each(function(ind,ent){
	                        $(this).find("td:first").text(ind+1);
	                     });
	                 }
	            });
	            $("#jbxx").click(function(){
			     	  location.href='<%=path%>/product/productAction!jbxx.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#info").click(function(){
			     	  location.href='<%=path%>/product/productAction!info.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#attr").click(function(){
			     	  location.href='<%=path%>/product/productAction!attr.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#price").click(function(){
			     	  location.href='<%=path%>/product/productAction!price.dhtml?id='+$.trim($("#proId").val());
			    });
			    $("#add").click(function(){
			           var content = [];
			           content.push("<tr>");
			                   content.push('<td style="text-align:center;">'+(++index)+'</td>');
			                   content.push('<td style="text-align:center;"></td>');
			                   content.push('<td style="text-align:left;position:relative;">');
			                  		 content.push('<input class="file" type="file" id="upload_path_img_'+index+'" name="upload_path_img_'+index+'"/>');
							         content.push('<input type="hidden" class="hideInput" id="path_img_'+index+'"/>');
							         content.push('<p class="upload">选择文件</p>');
							         content.push('<p class="up_button" style="	margin-left:20px;">点击上传</p>');
	                             	 content.push('<p class="viewview" style="	margin-left:20px;">预览</p>');
	                             	 content.push('<img class="cue" src="<%=path %>/images/question.png" title="证件必须是彩色原件电子版，可以是扫描件或者数码照。仅支持.jpg .jpeg .bmp的图片格式，图片大小不超过2M。"/>');
							  content.push('</td>');
							  content.push('<td style="text-align:center;"><img src="<%=path%>/images/up.jpg" alt="上移" class="arrow up" style="cursor: pointer;"/>    <img src="<%=path%>/images/down.jpg" alt="下移" class="arrow down" style="cursor: pointer;"/></td>');
							  content.push('<td style="text-align:center;"><a href="javascript:void(0);" class="delImg">删除</a></td>');  
			           content.push("</tr>");
			           $("#tbody").append(content.join(''));
			           $("#tbody tr").each(function(ind,ent){
	                         $(this).find("td:first").text(ind+1);
	                   });
			    });
			    $(".delImg").live('click',function(){
			        var id = $.trim($(this).parent().prev().prev().find(".hideInput").attr("name"));
			        var thiz = $(this);
			        if(id == ''){
			              $(this).parent().parent().remove(); 
			              $("#tbody tr").each(function(ind,ent){
	                         $(this).find("td:first").text(ind+1);
	                      });
			        }else{
			            $.ajax({
								url: '<%=path%>/product/productAction!delImg.dhtml',
								type: 'post',
								data: {'id':id},
								async: false,
								dataType: "json",
								success:function(data){
									   if(data == -1){
									         alert("提示：服务异常！");
									   }else if(data == -2){
									         alert("提示：参数出错！");
									   }else{
									          thiz.parent().parent().remove(); 
								              $("#tbody tr").each(function(ind,ent){
						                         $(this).find("td:first").text(ind+1);
						                      });
									         alert("提示：删除成功！");
									   }    
								},
								error:function(){
										alert("提示：服务异常！");
								}
					   });  
			        }
			    });
			});  
			function save(){
			          var attrArr = [];
			          $(".hideInput").each(function(ind,ent){
			              if($.trim($(this).attr("name")) != ''){
				              if(attrArr.length == 0)attrArr.push(",");
				              attrArr.push($.trim($(this).attr("name")));
				              attrArr.push(",");
			              }
			          });
			          $.ajax({
								url: '<%=path%>/product/productAction!saveImgSort.dhtml',
								type: 'post',
								data: {'ids':attrArr.join(''),'id_pro':<s:property value="#request.id"/>},
								async: false,
								dataType: "json",
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
			 }
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="proId" value='<s:property value="#request.id"/>'/>
     <div class="tab_menu">
	     <ul>
	         <li class="first" id="jbxx">商品基本信息</li>
	         <li id="info">商品详情</li>
	         <li id="attr">商品属性</li>
	         <li class="select" id="img">商品图片</li>
	         <li class="last" id="price">商品价格</li>
	     </ul>
	 </div>
	 <div class="location"><span>当前位置：商品图片信息</span></div>
	 <div class="main" style="margin-bottom:20px;">
                 <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
                            <tr>
					               <td colspan="5" class="title">编辑商品图片</td>
					        </tr>
					        <tr class="head">
					               <th width="10%">序号</th>
					               <th width="40%">路径</th> 
					               <th width="30%">上传</th>
					               <th width="10%">排序</th>
					               <th width="10%">操作</th>
					         </tr>
				             <tbody id="tbody">
				                 <s:iterator value="imgList" id="img" status="index">
							           <tr>
							               <td style="text-align:center;"><s:property value="#index.index+1"/></td>
							               <td style="text-align:center;"><s:property value="#img.path_img"/></td>
							               <td style="text-align:left;position:relative;">
							                   <input class="file" type="file" id='upload_path_img_<s:property value="#index.index+1"/>' name='upload_path_img_<s:property value="#index.index+1"/>'/> 
							                   <input type="hidden" class="hideInput" id="path_img_<s:property value="#index.index+1"/>" name='<s:property value="#img.id"/>' value='<s:property value="#img.path_img"/>'/>
							                   <p class="upload">选择文件</p>
							                   <p class="up_button" style="margin-left:20px;">点击上传</p>
	                             			   <p class="viewview" style="margin-left:20px;">预览</p>
	                             			   <img class="cue" src="<%=path %>/images/question.png"
	                             title="证件必须是彩色原件电子版，可以是扫描件或者数码照。仅支持.jpg .jpeg .bmp的图片格式，图片大小不超过2M。"/>
							               </td>
							               <td style="text-align:center;">
								               <img src="<%=path%>/images/up.jpg" alt="上移" class="arrow up" style="cursor: pointer;"/>    
								               <img src="<%=path%>/images/down.jpg" alt="下移" class="arrow down" style="cursor: pointer;"/>
							               </td>
							               <td style="text-align:center;">
								                <a href='javascript:void(0);' class="delImg">删除</a>
							               </td>
							           </tr>
						           </s:iterator>
					           </tbody>
					       </table>   
   </div>
   <div class="table1" style="border:0px;text-align:center;clear:both;margin:20px auto;"><input type="button" id="save" onclick="javascript:save();" value="保存" class="query" style="opacity: 1;"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="add" value="增加" class="query" style="opacity: 1;"/></div>
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
