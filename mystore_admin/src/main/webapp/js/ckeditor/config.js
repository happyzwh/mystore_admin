/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	 config.language = 'zh-cn';
	
	//注册自定义按钮插件
     config.extraPlugins="selectpic";
     
//	 config.skin = 'default';
	//设置字体
	 config.font_names = '宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;' + config.font_names;
	 //设置编辑器整体颜色
	// config.uiColor = '#4965D6';

	 //设置编辑器高度
	 config.height = 200;
	 //设置默认字体
	 config.font_defaultLabel = 'Arial'; //默认字体
	 //工具栏配置
//    config.toolbarGroups = [
//                            //复制粘贴等
//                            { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
//                            { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
//                            //插入图片等
//                            { name: 'insert',   groups: [ 'linkbutton' ]  },
//                            //字体样式加粗倾斜等
//                            { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
//                            //对齐排版
//                            { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align' ] },
//                            //样式
//                            { name: 'styles' },
//                            { name: 'colors'},
//                       
//                            
//                    ];
//    
//   
	 
	 config.toolbar = 'Full';
	 
	 config.toolbar_Full =
	 [
	         { name: 'document', items : [ 'Source','-','DocProps','Preview','Print','-','Templates' ] },
	         { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
	         { name: 'forms', items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 
	         'HiddenField' ] },
	         { name: 'insert', items : [ 'selectpic','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ] },
	         { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
	         '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
	         { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
	         { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
	        
	         { name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
	         { name: 'colors', items : [ 'TextColor','BGColor' ] },
	         { name: 'tools', items : [ 'Maximize', 'ShowBlocks','-','About' ] }
	 ];
    
//    config.removeButtons = 'Image,Flash,Iframe,Underline,Subscript,Superscript';
      config.removeButtons = 'Flash,Iframe,Underline,Subscript,Superscript';
    
 // 图片上传配置  
      config.filebrowserUploadUrl = $("#basePath").val()+'/upload/uploadAction!upload.dhtml?id_pro='+$("#proId").val();  
//    config.filebrowserImageUploadUrl = 'upload.do?type=Image';  
//    config.filebrowserFlashUploadUrl = 'upload.do?type=Flash'; 
 	
      
      
    // 图片浏览配置  
//    config.filebrowserImageBrowseUrl = '/pic/typelist/list.htm';
      config.enterMode = CKEDITOR.ENTER_BR;
      config.shiftEnterMode = CKEDITOR.ENTER_P;
      config.autoUpdateElement = false;
      config.FullPage = true
      config.EnterMode = '' ;    // p | div | br
      config.ShiftEnterMode = 'br' ; // p | div | br
      
      config.allowedContent=true;

};
