//#################������ϢͼƬΪ��Ʒ ���첽�ύ##########################
//��ʼ����
	$(document).ready(function(){
		var options = {
	    url:rootPath+'/ancheOffline/ancheOfflineAction!offLine.dhtml',
	    type:'post',
	    beforeSubmit: function(a,f,o) {  
	 	  var sys_name=document.getElementById('fileId').value;
			  if(sys_name==''){
			     alert('��ʾ��ɾ�����ݲ���Ϊ�գ�');
			     $("#fileId").focus();
			     return false;
			  } 
 		},  
		    success:showResponse  
		   };
		   // ��options����ajaxForm
		$('#form1').ajaxForm(options);
	   	 return false;
	});
   	    // post-submit callback 
	function showResponse(responseText, statusText, xhr, $form)  { 
		responseText = responseText.replace("</PRE>","").replace("<PRE>","");
		alert(responseText);
		//window.location.href = rootPath+"/jbxx/jbxxAction!list.dhtml";
	} 
     
     