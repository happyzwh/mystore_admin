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
	            $("#save").click(function(){
			        if($.trim($("#title").val()).replace(/[^\x00-\xff]/g,"**").length > 100){
			           alert("提示：供应商最多为100位字符！");
			           return false;
			        }
			        $.ajax({
							url: '<%=request.getContextPath() %>/info/infoAction!save.dhtml',
							type: 'post',
							data: {'id_cate':$.trim($("#id_cate").val()),'id':$.trim($("#id").val()),'title':$.trim($("#title").val()),
                                  'content':CKEDITOR.instances.content.getData(),'status':$.trim($("#status").val())
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
     <input type="hidden" id="id" value='<s:property value="#request.id"/>'/>
     <input type="hidden" id="id_cate" name="id_cate" value='<s:property value="#request.id_cate"/>'/>
	 <div class="location"><span>当前位置：信息详情</span></div>
	 <div class="main">
	    <div style="margin:5px;">
		        <div style="width:100%;text-align:center;">
		             <div style="width:100%;text-align:center;margin:0 auto;">
		                  <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
				             <tbody>
				                     <tr>
						               <td colspan="2" class="title">编辑信息详情</td>
						           </tr>
						           <tr>
						               <td style="text-align:right;width:10%;">标题：</td>
						               <td style="text-align:left;width:90%;">
						                   <input type="text" id="title" name="title" value='<s:property value="#request.info.title"/>' style="width:400px;height:28px;" maxlength="100" />
						               </td>
						           </tr>
						           <tr>
						          	   <td style="text-align:right;width:10%;">状态：</td>
						               <td style="text-align:left;width:90%;">
						                   <select id="status" name="status" style="width:100px;">
						                       <option <s:if test='#request.info.status == 0'>selected="selected"</s:if> value="0">禁用</option>
						                       <option <s:if test='#request.info.status == 1'>selected="selected"</s:if> value="1">启用</option>
						                   </select>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">信息内容：</td>
						               <td style="text-align:left;">
						                   <textarea class="ckeditor" id="content" name="content" cols="80"  rows="50"><s:property value="#request.info.content"/></textarea>
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
