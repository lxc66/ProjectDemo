<script type="text/javascript">
	$(function(){
	var  id="${parameters.id}";
	var  name="${parameters.name}";
	var  minDate="${parameters.minDate}";
	var  maxDate="${parameters.maxDate}";
	var disabledDays="${parameters.disabledDays}";
	var specialDays="${parameters.specialDays}";
	var styleClass="${parameters.styleClass}";
	var dateFmt="${parameters.dateFmt}";
	var sid="${parameters.sid}";
	var eid="${parameters.eid}";
	var doubleCalendar="${parameters.doubleCalendar}";
	var dateValue="${parameters.dateValue}";
	var other="${parameters.other}";
	var required="${parameters.required}";
    var placeholder="";
    
		var page_content = [];
		page_content.push("<input ");
		if( required=="true"){
		    styleClass+=" validate[required]";
		}
		if (styleClass!='') {
			page_content.push("class='" + styleClass + "'");
		}
		if (placeholder!='') {
			page_content.push("placeholder='" + placeholder + "'");
		}
		page_content.push("type='text'");
		page_content.push("value='" + dateValue + "'");
		page_content.push("id='" + id + "'");
		page_content.push("name='" + name + "'");
		page_content.push("onfocus=\"WdatePicker({ Hchanging:function(){if('${parameters.dateFmt}'=='HH:mm'|| '${parameters.dateFmt}'=='HH:mm:ss'){$('#" + id + "').prev('label').html('');}},mchanging:function(){if('${parameters.dateFmt}'=='HH:mm' || '${parameters.dateFmt}'=='HH:mm:ss'){$('#" + id + "').prev('label').html('');}},schanging:function(){if('${parameters.dateFmt}'=='HH:mm' || '${parameters.dateFmt}'=='HH:mm:ss'){$('#" + id + "').prev('label').html('');}},");
		page_content.push("oncleared:function(){$('#" + id + "').blur();},");
		
		if(doubleCalendar!=''){
			page_content.push("doubleCalendar:"+doubleCalendar+",");
		}
		page_content.push("readOnly:true,");
		page_content.push("isShowWeek:true,");
		if (dateFmt!='') {
			page_content.push("dateFmt:'" + dateFmt + "',");
		}
		if (specialDays!='') {
			page_content.push("specialDays:" + specialDays + ",");
		}
		if (disabledDays!='') {
			page_content.push("disabledDays:" + disabledDays + ",");
		}
		
		if (sid!='') {
			page_content.push("minDate:'#F{$dp.$D(\\'" + sid + "\\')}',");
		} else {
			if (minDate!='') {
				page_content.push("minDate:'" + minDate + "',");
			}
		}
		if (eid!='') {
			page_content.push("maxDate:'#F{$dp.$D(\\'" + eid + "\\')}',");
			page_content.push("onpicked:function(){$('#" + id + "').blur();$dp.$('" + eid + "').focus();},");
		} else {
			if (maxDate!='') {
				page_content.push("maxDate:'" + maxDate + "',");
			}
			//page_content.push("onpicked:function(){$('#" + id + "').blur();$('#" + id + "').validateTheField();},");
			page_content.push("onpicked:function(){$('#" + id + "').blur();},");
		}

		page_content.push("firstDayOfWeek:1,");
		page_content.push("qsEnabled:false");

		page_content.push("})\"");
		page_content.push(" />");
		

		$("#"+id+'div').append(page_content.join(""));
		
		//添加提示文字效果
		//if (placeholder!='') {
			//com.ue.util.placeholder("#"+id,{labelMode:true});
			//$("#"+id).removeClass("fl");
		//}
		
		//添加浮动防止外层宽度超出
		//$("#${parameters.id}div").wrap("<div class='fl'></div>");
		
		//判断去除前期加上的外层重复样式
		if($("#${parameters.id}div").parents("div.calendar_wrap").size()>0){
			$("#${parameters.id}div").parents("div.calendar_wrap").removeClass();
		}
		
	});
	
	
	
</script>
<div id="${parameters.id}div" class="calendar_wrap prompt-mark">
</div>