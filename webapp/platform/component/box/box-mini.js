///platform/component/box/jquery.box.js
!function(t){var s={init:function(){t(this).each(function(){if(t(this).find(".box1-header").size()<=0){var s=[];return s.push('<div class="box1-header">'),s.push("	<h2></h2>"),s.push('	<div class="box1-header-ctrls">'),s.push('		<span class="spin" alt=""></span>'),s.push('		<a class="close" title="" href="javascript:void(null);"></a>'),s.push("	</div>"),s.push("</div>"),t(this).wrapInner('<div class="box1-content"></div>'),null!=t(this).attr("angle")&&"corner"==t(this).attr("angle")&&t(this).addClass("corners shadow"),t(this).prepend(t(s.join(""))),null!=t(this).attr("boxTitle")&&t(this).find(".box1-header h2").html(t(this).attr("boxTitle")),null!=t(this).attr("boxHeight")&&t(this).find(".box1-content").height(t(this).attr("boxHeight")),null!=t(this).attr("boxState")&&"close"==t(this).attr("boxState")&&(t(this).find(".box1-content").slideToggle(200),t(this).find(".box1-header-ctrls a").removeClass("close").addClass("open")),null!=t(this).attr("boxState")&&"false"==t(this).attr("boxState")&&t(this).find(".box1-header-ctrls").addClass("none"),t(this).box1("control"),t(this).box1("resetWidth"),t(this)}})},resetWidth:function(){return t(this).each(function(){if(null!=t(this).attr("boxWidth")){var s=t(this).attr("boxWidth"),i=s.substr(s.length-1,1);if("%"==i)t(this).width(s);else{var e=Number(t(this).attr("boxWidth"));t(this).width(e)}}else{t(this).width("100%");var h=Number(t(this).width()-(t(this).outerWidth()-t(this).width()));t(this).width(h)}}),t(this)},control:function(){return t(this).each(function(){t(this).find(".box1-header-ctrls a").unbind("click").bind("click",function(){t(this).parents(".box1-header").next(".box1-content").slideToggle(200);var s=t(this).attr("class");"open"==s?t(this).removeClass("open").addClass("close"):t(this).removeClass("close").addClass("open"),t(this).parent(".box1-header-ctrls").find("span.spin").fadeIn(400).show(500,function(){t(this).fadeOut(350)})})}),t(this)}};t.fn.extend({box1:function(i){return i?i&&"string"==typeof i&&s[i]?s[i].apply(this,Array.prototype.slice.call(arguments,1)):void 0:t(this).box1("init")}})}(jQuery);
;