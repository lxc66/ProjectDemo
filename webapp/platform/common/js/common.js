
//common.js比较特殊，有对jquery的扩展也有对原生的修改

//ajax全局设置
$.ajaxSetup({
	cache: false,
	error: function(XmlHttpRequest, textStatus, errorThrown){
		var errorMsg;
		if(XmlHttpRequest.readyState==4){
			errorMsg = $(XmlHttpRequest.responseText).find("#error_msg").html();
		}else{
			errorMsg = "很抱歉，页面加载过程中出现错误，这可能是因为系统出现了异常或数据错误，请您稍后重试或联系系统管理员！";
		}
		if(errorMsg){
			$.dialog({
				id:"alertAjaxErrorMsg",
				content:"" + errorMsg,
				fixed: true,
				padding:'10px',
				lock: true,
				width: 248,
				height: 100,
				drag: false,
				resize: false,
				icon: 'warning',
				ok:function(){}
			}).show().focus();
		}else{

		}
	}
});

//禁用后退键
document.onkeypress = document.onkeydown = function(e){
	  var ev = e || window.event;//获取event对象     
	  var obj = ev.target || ev.srcElement;//获取事件源     
	  var t = obj.type || obj.getAttribute('type');//获取事件源类型    
	  //获取作为判断条件的事件类型
	  var vReadOnly = obj.getAttribute('readonly');
	  var vEnabled = obj.getAttribute('enabled');
	  //处理null值情况
	  vReadOnly = (vReadOnly == null) ? false : vReadOnly;
	  vEnabled = (vEnabled == null) ? true : vEnabled;
	    
	  //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
	  //并且readonly属性为true或enabled属性为false的，则退格键失效  
	  var flag1 = (ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") && (vReadOnly==true || vEnabled!=true))?true:false;  
	   
	  //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
	  var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")?true:false;          

	  if(ev.keyCode == 13 && t == "text"){//禁止回车提交表单，一个表单中只有一个type="text"
	  	return false;
	  }
	  
	  //判断
	  if(flag2){
	      return false;
	  }  
	  if(flag1){
	      return false;
	  }
};
