<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.mystore.business.common.ConfigReader" pageEncoding="UTF-8"%>
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
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
   	    <link rel="stylesheet"  href="<%=path%>/js/dhtmlxtree/dhtmlxtree.css" type="text/css"/>
	    <script src="<%=path%>/js/dhtmlxtree/dhtmlxtree.js" type="text/javascript"></script>
	    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	    
	    <script  type="text/javascript">
	        var tf = false;
		    $(function(){ 
		         $("#viewImg").load(function(){
		              tf = true;
		              $("#viewImg").show();
				      $("#loading").hide(); 
		         });
		         $(".viewview").click(function(){
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
				$("#cate_select").click(function(){
				    cate_select();
				});
				
				$(".up_button").click(function(){
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
			                    url:"<%=path %>/brand/brandAction!uploadImg.dhtml", 
			                    fileElementId:uid,        
			                    dataType:"json",    
			                    data:{'type':uid},           
			                    success:function(data,status){ 
					                      if(data.returnCode == 1){
					                            $("#"+uid).next().val(data.path);
					                            $("#"+uid).next().next().text("已上传");
					                      }else if(data.returnCode == -2){
					                            alert("提示：文件不存在！");
					                            $("#"+uid).next().next().text("选择文件");
					                      }else if(data.returnCode == -3){
					                            alert("提示：文件太大！");
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
				
				function tonclick(id){
				       if(id == -1){
				          $("#name").val('');
				          $("#id_cate").val('');
				          $("#cateName").text('');
					      $("#rome").val('');
					      $("#jianPin").val('');
					      $("#enName").val('');
					      $("#keyWords").val('');
					      $("#path_logoimg_fir").val('');
					      $("#path_logoimg_sec").val('');
					      $("#url").val('');
					      $("#sort").val(''); 
					      $("#descr").val('');
				          return false;
				       }
                       $.ajax({
							url: '<%=path%>/brand/brandAction!getBrandById.dhtml',
							type: 'post',
							data: {'id':id},
							async: false,
							dataType: "json",
							success:function(data){
							        if(data){
					                    if(data.returnCode == -1){
					                         alert("提示：服务异常！");
					                    }else if(data.returnCode == -2){
					                         alert("提示：参数出错！");
					                    }else{
										      $("#name").val(data.name);
									          $("#id_cate").val(data.id_cate);
									          $("#cateName").text(data.cateName);
										      $("#rome").val(data.rome);
										      $("#jianPin").val(data.jianPin);
										      $("#enName").val(data.enName);
										      $("#keyWords").val(data.keyWords);
										      $("#path_logoimg_fir").val(data.path_logoimg_fir);
										      $("#path_logoimg_sec").val(data.path_logoimg_sec);
										      $("#url").val(data.url);
										      $("#sort").val(data.sort); 
										      $("#descr").val(data.descr);
					                    }
				                    }else{
				                        alert("提示：服务异常！");
				                    }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
				       });  
				};
				function tondblclick(id){
				
				};			
				function tondrag(id,id2){
				    return true;
				
				};
				function tonopen(id,mode){
				     return true;
					
				};
				function toncheck(id,state){
				
				};
	
				tree=new dhtmlXTreeObject("tree","100%","100%",0);
	
				tree.setImagePath("<%=path%>/js/dhtmlxtree/imgs/dhxtree_skyblue/");
				tree.enableCheckBoxes(0);
				tree.enableDragAndDrop(0);
	   			tree.attachEvent("onOpenEnd",function(nodeId, event){});
				tree.setOnClickHandler(tonclick);
				tree.setOnCheckHandler(toncheck);
				tree.setOnDblClickHandler(tondblclick);
	
	            tree.setXMLAutoLoading("<%=path%>/brand/brandAction!get_note.dhtml");
				tree.loadXML("<%=path%>/brand/brandAction!get_note.dhtml", function(){
					//tree.setOnOpenHandler(tonopen);
					//tree.setDragHandler(tondrag);
				});

	       });
	       function addNote(){
	       
	           if($.trim($("#name").val()) == ''){
	              alert("提示：名称不能为空！");
	              return false;
	           }
	           if($.trim($("#id_cate").val()) == ''){
	           	  alert("提示：请选择分类！");
	              return false;
	           }
	           var reg=/^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;      
	           if($.trim($("#url").val()) != '' && !reg.test($.trim($("#url").val()))){   
	                alert("提示：网址格式不正确！");  
	                return false;  
	           }  
	           if($.trim($("#descr").val()) != "" && $.trim($("#descr").val()).length > 500 ){
					     alert("提示：描述最多500字符！");
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
					url: '<%=path%>/brand/brandAction!addNote.dhtml',
					type: 'post',
					data: {'name':$.trim($("#name").val()),'id_cate':$.trim($("#id_cate").val()),'rome':$.trim($("#rome").val()),'jianPin':$.trim($("#jianPin").val()),
					      'enName':$.trim($("#enName").val()),'keyWords':$.trim($("#keyWords").val()),'sort':$.trim($("#sort").val()),'descr':$.trim($("#descr").val()),
					      'path_logoimg_fir':$.trim($("#path_logoimg_fir").val()),'path_logoimg_sec':$.trim($("#path_logoimg_sec").val()),'url':$.trim($("#url").val()),
					      'pid':tree.getSelectedItemId() == ''?-1:tree.getSelectedItemId()},
					async: false,
					dataType: "text",
					success:function(data){
		                    if(data == -1){
		                         alert("提示：服务异常！");
		                    }else if(data == -2){
		                         alert("提示：参数出错！");
		                    }else if(data == -3){
		                         alert("提示：权限名称重复！");
		                    }else{
		                         tree.insertNewItem(tree.getSelectedItemId() == ''?-1:tree.getSelectedItemId(),data,$.trim($("#name").val())); 
		                         $("#name").val('');
						         $("#id_cate").val('');
						         $("#cateName").text('');
							     $("#rome").val('');
							     $("#jianPin").val('');
							     $("#enName").val('');
							     $("#keyWords").val('');
							     $("#path_logoimg_fir").val('');
							     $("#path_logoimg_sec").val('');
							     $("#url").val('');
							     $("#sort").val(''); 
							     $("#descr").val('');
					             alert("提示：保存成功！");
		                    }
					},
					error:function(){
					     alert("提示：服务异常！");
					}
		       });  
	       }
	       function deleteNote() {
	           if(tree.getSelectedItemId() == ''){
	              alert("提示：请选择权限节点！");
	              return false;
	           }
	           if(confirm("提示：确定删除！")){
		           $.ajax({
						url: '<%=path%>/brand/brandAction!deleteNote.dhtml',
						type: 'post',
						data: {'id':tree.getSelectedItemId()},
						async: false,
						dataType: "text",
						success:function(data){
			                    if(data == -1){
			                         alert("提示：服务异常！");
			                    }else if(data == -2){
			                         alert("提示：参数出错！");
			                    }else{
			                         tree.deleteItem(tree.getSelectedItemId(),true);   
						             $("#name").val('');
							         $("#id_cate").val('');
							         $("#cateName").text('');
								     $("#rome").val('');
								     $("#jianPin").val('');
								     $("#enName").val('');
								     $("#keyWords").val('');
								     $("#path_logoimg_fir").val('');
								     $("#path_logoimg_sec").val('');
								     $("#url").val('');
								     $("#sort").val(''); 
								     $("#descr").val('');
						             alert("提示：删除成功！");
			                    }
						},
						error:function(){
						     alert("提示：服务异常！");
						}
			       }); 
		       }
	           
	       }
	       function updateNote(){
	          if(tree.getSelectedItemId() == ''){
	              alert("提示：请选择权限节点！");
	              return false;
	          }
	          if($.trim($("#name").val()) == ''){
	              alert("提示：名称不能为空！");
	              return false;
	           }
	           if($.trim($("#descr").val()) != "" && $.trim($("#descr").val()).length > 500 ){
					     alert("提示：描述最多500字符！");
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
					url: '<%=path%>/brand/brandAction!updateNote.dhtml',
					type: 'post',
					data: {'name':$.trim($("#name").val()),'id_cate':$.trim($("#id_cate").val()),'rome':$.trim($("#rome").val()),'jianPin':$.trim($("#jianPin").val()),
					      'enName':$.trim($("#enName").val()),'keyWords':$.trim($("#keyWords").val()),'sort':$.trim($("#sort").val()),'descr':$.trim($("#descr").val()),
					      'path_logoimg_fir':$.trim($("#path_logoimg_fir").val()),'path_logoimg_sec':$.trim($("#path_logoimg_sec").val()),'url':$.trim($("#url").val()),
					      'id':tree.getSelectedItemId(),'pid':tree.getParentId(tree.getSelectedItemId())},
					async: false,
					dataType: "text",
					success:function(data){
		                    if(data == -1){
		                         alert("提示：服务异常！");
		                    }else if(data == -2){
		                         alert("提示：参数出错！");
		                    }else if(data == -3){
		                         alert("提示：权限名称重复！");
		                    }else{
		                         tree.setItemText(tree.getSelectedItemId(),$.trim($("#name").val())); 
		                         alert("提示：保存成功！");
		                    }
					},
					error:function(){
					     alert("提示：服务异常！");
					}
		       });  
	       }
	       function clearNote(){
	            $("#name").val('');
				$("#id_cate").val('');
				$("#cateName").text('');
				$("#rome").val('');
				$("#jianPin").val('');
				$("#enName").val('');
				$("#keyWords").val('');
				$("#path_logoimg_fir").val('');
				$("#path_logoimg_sec").val('');
				$("#url").val('');
				$("#sort").val(''); 
				$("#descr").val(''); 
	       }
	       function cate_select(){
			 	var url='<%=request.getContextPath() %>/brand/brandAction!cate_select.dhtml';
			 	var dialogWidth = 600;
			 	var dialogHeight = 600;
			 	var dialogLeft = (screen.width - dialogWidth)/2;
			 	var dialogTop = (screen.width - dialogHeight)/2;
				window.showModalDialog(url,window,"dialogTop:"+dialogTop+"px;dialogLeft:"+dialogLeft+"px;dialogWidth:"+dialogWidth+"px;dialogHeight:"+dialogHeight+"px;scroll:yes;status:no"); 
		   }
		   function setCate(id,name){
		     $("#id_cate").val(id);
		     $("#cateName").text(name);
		   }
		   function brandInfo(){
		       if(tree.getSelectedItemId() == '' || tree.getSelectedItemId() == -1){
		           alert("提示：请选择品牌！");
		           return false;
		       }
		       window.open('<%=request.getContextPath() %>/brand/brandAction!brandInfo.dhtml?pid='+tree.getSelectedItemId(),'newwindow','height=500,width=1000,top=0,left=0,toolbar=yes,menubar=yes,scrollbars=yes,resizable=yes,location=no,status=yes');
		   }
	    </script>
	</head>
<body class="main">
	 <div class="location"><span>当前位置：品牌管理</span></div>
	 <div class="main">
	    <div style="margin:5px;">
	        <div id="tree" style="float:left;width:25%;height:400px;background-color:#e3eef9;border:1px solid #c1dbe7;margin-top:15px;"></div>
	        <div style="width:73%;float:right;">
		        <div style="width:100%;text-align:center;">
		             <div style="width:100%;text-align:center;margin:0 auto;">
		                  <table cellspacing="0" cellpadding="0" class="table1" style="width:100%;">
				             <tbody>
						         	 <tr>
						               <td width="20%" style="text-align:right;">名称：</td>
						               <td width="80%" style="text-align:left;">
						                   <input type="text" id="name" name="name" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						          <tr>
						               <td style="text-align:right;">分类：</td>
						               <td style="text-align:left;">
						                  <span id="cateName" style="width:100px;float:left;height:23px;line-height:23px;"> </span>
						                  <p class="upload" id="cate_select" style="margin-left:100px;">分类选择</p> 
						                  <input type="hidden" id="id_cate"  name="id_cate"/>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">拼音：</td>
						               <td style="text-align:left;">
						                  <input type="text" id="rome" name="rome" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">简拼：</td>
						               <td style="text-align:left;">
						                   <input type="text" id="jianPin" name="jianPin" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">英文名称：</td>
						               <td style="text-align:left;">
						                   <input type="text" id="enName" name="enName" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">关键字：</td>
						               <td style="text-align:left;">
						                   <input type="text" id="keyWords" name="keyWords" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">品牌LOGO：</td>
						               <td style="text-align:left;position:relative;">
						                   <input class="file" type="file" id="upload_path_logoimg_fir" name="upload_path_logoimg_fir"/> 
						                   <input type="hidden"  id="path_logoimg_fir" name="path_logoimg_fir" />
						                   <p class="upload">选择文件</p>
						                   <p class="up_button" style="	margin-left:20px;">点击上传</p>
                             			   <p class="viewview" style="	margin-left:20px;">预览</p>
                             			   <img class="cue" src="<%=path %>/images/question.png"
                             title="证件必须是彩色原件电子版，可以是扫描件或者数码照。仅支持.jpg .jpeg .bmp的图片格式，图片大小不超过2M。"/>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">品牌LOGO：</td>
						               <td style="text-align:left;position:relative;">
						                   <input class="file" type="file" id="upload_path_logoimg_sec" name="upload_path_logoimg_sec"/> 
						                   <input type="hidden"  id="path_logoimg_sec" name="path_logoimg_sec" />
						                   <p class="upload">选择文件</p>
						                   <p class="up_button" style="	margin-left:20px;">点击上传</p>
                             			   <p class="viewview" style="	margin-left:20px;">预览</p>
                             			   <img class="cue" src="<%=path %>/images/question.png"
                             title="证件必须是彩色原件电子版，可以是扫描件或者数码照。仅支持.jpg .jpeg .bmp的图片格式，图片大小不超过2M。"/>
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">网址：</td>
						               <td style="text-align:left;">
						                   <input type="text" id="url" name="url" style="width:400px;height:28px;" maxlength="50" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">排序：</td>
						               <td style="text-align:left;">
						                   <input type="text" id="sort" name="sort" style="width:250px;height:28px;" maxlength="4" /> 
						               </td>
						           </tr>
						           <tr>
						               <td style="text-align:right;">描述：</td>
						               <td style="text-align:left;">
						                   <textarea id="descr" name="descr" style="width:400px;height:80px;"></textarea>
						               </td>
						           </tr>
					           </tbody>
					       </table>    
		             </div>
		             <div id="content" style="width:100%;text-align:center;border:0px;" class="table1">
		                            <power:permission bh="add_brand">
					                	<input type="button" value="增加" class="query"  style="opacity: 1;" onclick="javascript:addNote();"/>
					                </power:permission>
					                <power:permission bh="update_brand">
							       		<input type="button" value="修改" class="query" onclick="javascript:updateNote();"/>
							        </power:permission>
							        <power:permission bh="del_brand">
							        	<input type="button" value="删除" class="query" onclick="javascript:deleteNote();"/>
							        </power:permission>
							        <power:permission bh="detail_product">
							        	<input type="button" value="详情" class="query" onclick="javascript:brandInfo();"/>
							        </power:permission>
							        <input type="button" value="清空" class="query" onclick="javascript:clearNote();"/>
				    </div>
		        </div>
	        </div>
        </div>
     </div>
     <!--预览按钮的弹出层-->
    <div id="review">
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
