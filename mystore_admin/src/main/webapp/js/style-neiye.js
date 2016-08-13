$(document).ready(function(){
	/*左侧菜单栏*/
	$(".left ul").eq(0).css("display","block").siblings("ul").css("display","none");
	
	$(".left h3").click(function(){
		$(this).find("img").attr("src",$("#path").val()+"/images/arrow-down.jpg").parent("h3").siblings("h3").find("img").attr("src",$("#path").val()+"/images/arrow-right.jpg");
	});

	$(".left h3").click(function(){	
		$(this).next("ul").show().end().siblings("h3").next("ul").hide();
	});
	$(".left li").click(function(){
		$(this).css({"color":"#2168c1"}).siblings().css({"color":"#333"});
	});
		
    /*鼠标经过table的行背景色变化*/
	$(".table_list").find("td").mouseover(function(){
		$(this).parent("tr").find("td").css("background-color","#f2f0f1");
		$(this).parent("tr").find("input").css("background-color","#f2f0f1");
	})
	$(".table_list").find("td").mouseleave(function(){
		$(this).parent("tr").find("td").css("background-color","#ffffff");
		$(this).parent("tr").find("input").css("background-color","#ffffff");
	})
	
	/*鼠标经过table行字颜色变蓝*/
	$(".table3").find("td").mouseover(function(){
		$(this).parent("tr").find("td").css("color","#0563dd");
		$(this).parent("tr").find("input").css("color","#0563dd");
	})
	$(".table3").find("td").mouseleave(function(){
		$(this).parent("tr").find("td").css("color","#000");
		$(this).parent("tr").find("input").css("color","#000");
	})
	
	/*鼠标经过按钮的样式变化*/
	$(".query,.reset").mouseover(function(){
		$(this).fadeTo("fast",0.6);
	});
	$(".query,.reset").mouseover(function(){
		$(this).fadeTo("fast",1);
	});
});



