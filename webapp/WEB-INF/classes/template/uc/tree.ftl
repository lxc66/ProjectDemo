<ul id='${parameters.id?html}'></ul>
<script>
	$(document).ready(function(){
		var settings = {
			nodes:${parameters.nodes},
			isDropdown:${parameters.dropdown?html}, //下拉树
			showParentName:${parameters.showParentName?html}, //下拉树显示名称是否包含父级
			checkValue:"${parameters.checkValue?html}", //回显值
			expandLevel:parseInt("${parameters.expandLevel?html}"), //回显值
			name:"${parameters.name?html}",
			width:"${parameters.width?html}",
			required:${parameters.required?html},
			check: {
				enable: ${parameters.checkEnable?html},
				chkStyle: "${parameters.checkStyle?html}",
				radioType: "all"
			}
		}
		$.hz.ztree.init("${parameters.id?html}",settings);
	});
		if(""!='${parameters.cascade?html}'){
			//com.ue.cmpt.tree.setCascade('${parameters.id?html}');
		}
		if(""!="${parameters.placeholder?html}"){
			//com.ue.cmpt.tree.setPlaceholder('${parameters.id?html}','${parameters.placeholder?html}');
		}
</script>
