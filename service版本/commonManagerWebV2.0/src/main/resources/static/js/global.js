$(document).ready(function() {
	"use strict";
	
	
//	//默认active
//	$(".tab_content").hide(); //隐藏全部的tab菜单内容
//	$("ul.tabs li:first").addClass("active").show(); //对第一个li标签添加class="active属性"
//	$(".tab_content:first").show(); //显示第一个tab内容
//
//	//点击事件
//	$("ul.tabs li").click(function () {
//		$("ul.tabs li").removeClass("active"); //移除class="active"属性
//		$(this).addClass("active"); //添加class="active"到选择标签中
//		$(".tab_content").hide(); //隐藏全部标签内容
//		var activeTab = $(this).find("a").attr("href"); //找到所属属性值来识别活跃选项卡和内容
//		$(activeTab).fadeIn(); //使内容消失
//		return false;
//	});
	
	var unSelected = "#999";
    var selected = "#333";
    $(function () {
        $("select").css("color", unSelected);
        $("option").css("color", selected);
        $("select").change(function () {
            var selItem = $(this).val();
            if (selItem === $(this).find('option:first').val()) {
                $(this).css("color", unSelected);
            } else {
                $(this).css("color", selected);
            }
        });
    });
	
	$(".tab_content").height($("body").height() - $(".tabs-bar").height());
	
//	$(".credentials .list").height($(".credentials .list").width());
//	console.log($(".credentials .list").width());
	
	$(".filter > a.button").click(function() {
		$(this).toggleClass("active");
		$(this).next("form").slideToggle(200);
		$(this).nextAll(".filterbg").fadeToggle();
		$(".main").toggleClass("blur");
	});
	
	
	 $.fn.serializeJson = function () {
         var serializeObj = {};
         $(this.serializeArray()).each(function () {
             serializeObj[this.name] = this.value;
         });
         return serializeObj;
     };
     
     $.fn.getUrlParam = function (name) {
	        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) return unescape(r[2]); return null;
	    }
     
    
});
/**
 * tel键盘下，允许输入两位小数
 * @param obj
 * @returns
 */
function onlyNumber(obj){ 
    //先把非数字的都替换掉，除了数字和. 
    obj.value = obj.value.replace(/[^\d\.]/g,''); 
    //必须保证第一个为数字而不是. 
    obj.value = obj.value.replace(/^\./g,'0.'); 
    //保证只有出现一个.而没有多个. 
    obj.value = obj.value.replace(/\.{2,}/g,'.'); 
    //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.');
     //只能输入两个小数
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); 
} 