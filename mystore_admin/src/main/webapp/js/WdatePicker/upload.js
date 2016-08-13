//#################基本信息图片为产品 的异步提交##########################
//初始加载
	$(document).ready(function(){
		var options = {
	    url:rootPath+'/ancheOffline/ancheOfflineAction!offLine.dhtml',
	    type:'post',
	    beforeSubmit: function(a,f,o) {  
	 	  var sys_name=document.getElementById('fileId').value;
			  if(sys_name==''){
			     alert('提示：删除内容不能为空！');
			     $("#fileId").focus();
			     return false;
			  } 
 		},  
		    success:showResponse  
		   };
		   // 将options传给ajaxForm
		$('#form1').ajaxForm(options);
	   	 return false;
	});
   	    // post-submit callback 
	function showResponse(responseText, statusText, xhr, $form)  { 
		responseText = responseText.replace("</PRE>","").replace("<PRE>","");
		alert(responseText);
		//window.location.href = rootPath+"/jbxx/jbxxAction!list.dhtml";
	} 
     
     