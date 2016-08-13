var crntPicSelectCommand;
(function(){
    //Section 1 : 按下自定义按钮时执行的代码
    var a= {
        exec:function(editor){
            crntPicSelectCommand = editor.name;
            art.dialog({
            	title:"请选择图片",
            	width:1000,
            	height:600,
            	id:"picSelectorDialog",
                content: "<iframe src='http://www.baidu.com' style='width:920px;height:600px' />"
            });
        }
    },
    //Section 2 : 创建自定义按钮、绑定方法
    b='selectpic';
    CKEDITOR.plugins.add(b,{
        init:function(editor){
            editor.addCommand(b,a);
            editor.ui.addButton('selectpic',{
                label:'选择图片',
                icon: this.path + 'pic.png',
                command:b
            });
        }
    });
})();