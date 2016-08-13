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
		              $("#viewImg").attr("src","<%=ConfigReader.getPath_pic_service()%>/"+$(this).prev().val()+'?'+new Date().getTime()); 
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
			    $(".up").click(function(){
	                 if($(this).parent().parent().prev().length > 0){
	                    $(this).parent().parent().insertBefore($(this).parent().parent().prev());  
	                    $("#tbody tr").each(function(ind,ent){
	                        $(this).find("td:first").text(ind+1);
	                    });
	                 }
	            });
	            $(".down").click(function(){
	                  if($(this).parent().parent().next().length > 0){
	                     $(this).parent().parent().insertAfter($(this).parent().parent().next());  
	                     $("#tbody tr").each(function(ind,ent){
	                        $(this).find("td:first").text(ind+1);
	                     });
	                 }
	            });
			    $("#add").click(function(){
			           location.href="<%=path%>/adv/advAction!edit_advimg.dhtml?pid="+$("#pid").val();
			    });
	         });
	         function delImg(id){
	             if(confirm("确定要删除?")){
			            $.ajax({
								url: '<%=path%>/adv/advAction!delAdvImg.dhtml',
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
									         alert("提示：删除成功！");
									         $("#"+id).remove();
									   }    
								},
								error:function(){
										alert("提示：服务异常！");
								}
					   }); 
			     } 
			 }
	         function save(){
			          var attrArr = [];
			          $("tbody tr").each(function(ind,ent){
			              if($.trim($(this).attr("id")) != ''){
				              if(attrArr.length == 0)attrArr.push(",");
				              attrArr.push($.trim($(this).attr("id")));
				              attrArr.push(",");
			              }
			          });
			          if(attrArr.length == 0){
			              alert("提示：无可排序项！");
			          }else{
				          $.ajax({
									url: '<%=path%>/adv/advAction!saveAdvImgSort.dhtml',
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
										   }   
									},
									error:function(){
											alert("提示：服务异常！");
									}
						   }); 
					   }
			 }
	    </script>
	</head>
<body class="main">
     <input type="hidden" id="pid" value='<s:property value="#request.pid"/>'/>
	 <div class="main" style="margin-bottom:20px;">
                 <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
                            <tr>
					               <td colspan="5" class="title">广告位图片列表</td>
					        </tr>
					        <tr class="head">
					               <th width="10%">序号</th>
					               <th width="40%">标题</th> 
					               <th width="30%">预览</th>
					               <th width="10%">排序</th>
					               <th width="10%">操作</th>
					         </tr>
				             <tbody id="tbody">
				                 <s:iterator value="advImgList" id="img" status="index">
							           <tr id='<s:property value="#img.id"/>'>
							               <td style="text-align:center;"><s:property value="#index.index+1"/></td>
							               <td style="text-align:center;"><s:property value="#img.title"/></td>
							               <td style="text-align:left;position:relative;padding-left:5px;">
							                   <input type="hidden" class="hideInput" id="path_pic" name='path_pic' value='<s:property value="#img.path_pic"/>'/>
	                             			   <p class="viewview" style="margin-left:5px;">预览</p>
							               </td>
							               <td style="text-align:center;">
								               <img src="<%=path%>/images/up.jpg" alt="上移" class="arrow up" style="cursor: pointer;"/>    
								               <img src="<%=path%>/images/down.jpg" alt="下移" class="arrow down" style="cursor: pointer;"/>
							               </td>
							               <td style="text-align:center;">
							                     <a href='<%=path%>/adv/advAction!edit_advimg.dhtml?id=<s:property value="#img.id"/>' >修改</a>
								                <a href='javascript:void(0);' class="delImg" onclick="javascript:delImg(<s:property value="#img.id"/>);">删除</a>
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
