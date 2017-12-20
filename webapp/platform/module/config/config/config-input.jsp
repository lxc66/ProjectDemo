<!DOCTYPE html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<script>
	var row_count = 0;
	Namespace.register('com.ue.platform.module.config.input');
	com.ue.platform.module.config.input = {
		pageId : "${pageId}",
		doSave : function() {
			if(!$("#${pageId}theForm").valid()){
				return;
			}
			var formData = $("#${pageId}theForm").serialize();
			$.ajax({
				type : "POST",
				url : "${path}/sys/config/save",
				processData : true,
				data : formData,
				success : function(id) {
					com.ue.platform.module.config.list.refresh();
					art.dialog.list[com.ue.platform.module.config.list.pageId].close();
				}
			});
		},
		showOrHideFormDiv:function() {
			var selectedValue = $("input[type='radio'][name='model.kind']:checked",$("#${pageId}theForm")).val();
			if (selectedValue == '1') {
				$("#${pageId}modulelistDiv").hide();
				$("#${pageId}userOptionDiv").show();
			}else if (selectedValue == '2') {
				$("#${pageId}modulelistDiv").show();
				$("#${pageId}userOptionDiv").show();
			}else if (selectedValue == '3') {
				$("#${pageId}modulelistDiv").hide();
				$("#${pageId}userOptionDiv").hide();
			}
		},
		checkConfigItem : function(obj) {
			var defalultCheckbox = $(obj).closest("tr").find("td:eq(5) input:checked");
			if(defalultCheckbox.length==1){
				$(obj).attr("checked", false);
			}
		},
		checkConfigItemDefault : function(obj) {
			$(obj).closest("tr").find("#CK").attr("checked", false);
			$(obj).closest("tr").find("td:eq(6) input").attr("checked", false);
			$("#${pageId}configItemTable tr").each(function() {
				var defaultCheckbox = $(this).find("td:eq(5) input");
				if(defaultCheckbox.attr("name")!=$(obj).attr("name")){
					defaultCheckbox.attr("checked", false);
				}
			});
			$(obj).attr("checked", true);
		},
		checkConfigItemDisable:function(obj){
			var defalultCheckbox = $(obj).closest("tr").find("td:eq(5) input:checked");
			if(defalultCheckbox.length==1){
				$(obj).attr("checked", false);
			}
			
			if($(obj).attr("checked")){
				if ($(obj).parent().prev().children().get(0).checked) {
					$(obj).attr("checked", false);
					$(obj).attr("value", "0");
				} else{
					$(obj).attr("checked", true);
					$(obj).attr("value", "1");
				}
			}
		},
		init:function(){
			var validateOptions = {
				rules: {
					"model.code" : {
						required:true,
						maxlength:25,
						remote:{
							type:"POST",
							url:"${path}/sys/config/ajaxCheckCode",
							data:{
								code:function(){return $("#${pageId}code").val();},
								excludeCode:"${model.code}"
			                } 
						}
					}
				},
				messages : {
					"model.code" : {
						remote : "配置编码已存在，请重新输入！"
					}
				}
			};
			$.hz.validate.init("${pageId}theForm",validateOptions);
			this.showOrHideFormDiv();
		}
	};
	$(document).ready(function() {
			com.ue.platform.module.config.input.init();
			$('#${pageId}theForm').closest('.ui-layout-pane').addClass('inLayout-center');
			//隐藏模板tr
			//$("#${pageId}configItemTable tr").eq(2).hide();
			$("#BtAdd").click(function() {
				var i = $("#${pageId}configItemTable tr").length;
				//复制一行
				var tr = $("tr#${pageId }cloneTr").clone();
				tr.removeAttr("id")
				//序号
				tr.find("td").get(0).innerHTML = (i+1);
				var hiddenId = tr.find("td:eq(1) input[type='hidden']");
				var inputName = tr.find("td:eq(2) input");
				var inputValue = tr.find("td:eq(3) input");
				var checkDefault = tr.find("td:eq(5) input");
				var checkDisable = tr.find("td:eq(6) input");
				
				
				//checkbox
// 				hiddenId.attr("name", "model.configItemList[" + i + "].id");
				//名称
				inputName.attr("id", "${pageId}Name_" + i);
				inputName.attr("name", "model.configItemList[" + i + "].name");
				//值
				inputValue.attr("id", "${pageId}Value_" + i);
				inputValue.attr("name", "model.configItemList[" + i + "].value");
				/// 附件
// 				var tdAtt = $(tr.find("td")[4]);
// 				var divId = "att" + $.uuid();
				//origAlert(divId); 
// 				tdAtt.html(com.ue.upload.getUploadUIStr(divId));
				
				//默认值
				checkDefault.attr("id", "${pageId}enableFlagRadio_" + i);
				checkDefault.attr("name", "model.configItemList[" + i + "].defaultFlag");
				checkDefault.attr("checked", false);
				//是否禁用
				checkDisable.attr("id", "${pageId}enableFlagCheckbox_" + i);
				checkDisable.attr("name", "model.configItemList[" + i + "].enableFlag");
				checkDisable.attr("checked", false);
				tr.show();
				tr.appendTo("#${pageId}configItemTable");
				
				//更新附件index
				//origAlert(divId);
// 				com.ue.upload.showUploadFlash(divId, '${attachmentCode}');
			});
			$("#BtDel").click(function() {
				if ($(":checked[name='CK']").length < 1) {
					alert("请选择要删除的项！");
					return;
				}
				confirm("确定要删除吗？", function() {
					$("#${pageId}configItemTable input[id='CK']:checked").each(function() {
						$(this).closest("tr").remove();
					});
					$("#${pageId}configItemTable tr").each(function(i) {
						var tr = $(this);
						//序号
						tr.find("td").get(0).innerHTML = (i+1);
						var hiddenId = tr.find("td:eq(1) input[type='hidden']");
						var inputName = tr.find("td:eq(2) input");
						var inputValue = tr.find("td:eq(3) input");
						var checkDefault = tr.find("td:eq(5) input");
						var checkDisable = tr.find("td:eq(6) input");
						//checkbox
						hiddenId.attr("name", "model.configItemList[" + i + "].id");
						//名称
						inputName.attr("id", "${pageId}Name_" + i);
						inputName.attr("name", "model.configItemList[" + i + "].name");
						//值
						inputValue.attr("id", "${pageId}Value_" + i);
						inputValue.attr("name", "model.configItemList[" + i + "].value");
						//默认值
						checkDefault.attr("name", "model.configItemList[" + i + "].defaultFlag");
						//是否禁用
						checkDisable.attr("id","${pageId}enableFlagCheckbox_" + i);
						checkDisable.attr("name", "model.configItemList[" + i + "].enableFlag");
					});
				});
		});
	});
</script>
	<uc:form id="${pageId}theForm" method="post">
		<c:if test="${not empty model.id}">
			<uc:hidden name="model.id" value="${model.id}" />
		</c:if>
		<div class="formLayout03 m5">
			<fieldset>
				<legend> 配置信息 </legend>
				<div class="control control_down"></div>
				<div class="form_content">
					<div class="formItem">
						<div class="tit w2">
							<em>*</em>配置类型
						</div>
						<div class="bdmain w7">
							<div class="selStyle fl w">
								<uc:radiolist list="${kindMap}" name="model.kind" value="${model.kind}" onclick="com.ue.platform.module.config.input.showOrHideFormDiv();"></uc:radiolist>
							</div>
						</div>
					</div>
					<div class="formItem">
						<div class="tit w2">
							<em>*</em>配置模式
						</div>
						<div class="bdmain w7">
							<div class="selStyle fl w">
								<uc:radiolist list="${modeMap}" name="model.mode" value="${model.mode}"></uc:radiolist>
							</div>
						</div>
					</div>
					<div class="formItem">
						<label class="tit w2"> <em>*</em>名称
						</label>
						<div class="bdmain w7">
							<uc:textfield name="model.name" value="${model.name}" maxlength="25" required="true" title="请输入名称" cssClass="inputStyle w" />
						</div>
					</div>
					<div class="formItem">
						<label class="tit w2"> <em>*</em>编码
						</label>
						<div class="bdmain w7">
							<uc:textfield id="${pageId}code" name="model.code" value="${model.code}" maxlength="25" required="true" title="请输入编码" cssClass="inputStyle w" />
						</div>
					</div>
					<div class="formItem">
						<div id="${pageId}modulelistDiv" style="display: none;">
							<label class="tit w2"> <em>*</em>模块
							</label>
							<div class="bdmain w7">
								<uc:select list="${modulelist}" cssClass="selectStyle w250" listKey="id" listValue="name" name="model.moduleId" value="${model.moduleId}"></uc:select>
							</div>
						</div>
					</div>
					
					<div class="formItem">
						<div id="${pageId}userOptionDiv" style="display: none;">
							<label class="tit w2"> <em>*</em>用户是否可见
							</label>
							<div class="bdmain w7">
								<div class="selStyle fl w">
									<uc:radiolist list="${visibleFlagMap}" name="model.visibleFlag" value="${model.visibleFlag}"></uc:radiolist>
								</div>
							</div>
						</div>
					</div>

					<div class="formItem">
						<label class="tit w2"> 描述 </label>
						<div class="bdmain w7">
							<uc:textarea name="model.description" id="${pageId}description"
								cssClass="textareaStyle w h100" maxlength="100"
								value="${model.description}" cols="30"></uc:textarea>
						</div>
					</div>
				</div>
			</fieldset>

			<fieldset>
				<legend> 配置项信息 </legend>
				<div class="control control_down"></div>
				<div class="form_content">
					<div class="clearfix mt10 ml10">
						<a href="javascript:void(0);" id="BtAdd" class="button">添加配置项</a>
						<a href="javascript:void(0);" id="BtDel" class="button">删除配置项</a>
					</div>
					<table class="cssTableBody w9 m10">
						<thead>
						<tr>
							<th class="tc w30">序号</th>
							<th class="tc w30"><input id="CKA" name="CKA"
								type="checkbox" /></th>
							<th>名称</th>
							<th>值</th>
							<th>附件</th>
							<th>作为默认值</th>
							<th>是否禁用</th>
						</tr>
						</thead>
						<tbody id="${pageId}configItemTable">
							<c:if test="${not empty configItems}">
								<c:forEach items="${configItems}" var="item" varStatus="status">
									<tr>
										<td class="tc">
											${status.count}
										</td>
										<td class="tc">
											<input id="CK" type="checkbox" name="CK" onclick="com.ue.platform.module.config.input.checkConfigItem(this)" />
											<input type="hidden" name="model.configItemList[${status.index}].id" value="${item.id}"/>
										</td>
										<td>
											<uc:textfield name="model.configItemList[${status.index}].name" value="${item.name}" maxlength="25" required="true" 
												title="请输入名称" cssClass="inputStyle w p0" id="${pageId}Name_${status.index}" />
										</td>
										<td>
											<uc:textfield name="model.configItemList[${status.index}].value" value="${item.value}" maxlength="25" required="true" 
												title="请输入值" cssClass="inputStyle w p0" id="${pageId}Value_${status.index}" />
										</td>
										<td>
										</td>
										<td>
											<input type="radio" id="${pageId}defaultValueFlagRadio_${status.index}" name="model.configItemList[${status.index}].defaultFlag"
												value="1" onclick="com.ue.platform.module.config.input.checkConfigItemDefault(this)" 
												<c:if test="${item.defaultFlag=='1'}"> checked="checked"</c:if>
											/>
										</td>
										<td>
											<input type="checkbox" id="${pageId}enableFlagCheckbox_${status.index}" name="model.configItemList[${status.index}].enableFlag"
												value="1" onclick="com.ue.platform.module.config.input.checkConfigItemDisable(this)" 
												<c:if test="${item.enableFlag=='1'}"> checked="checked"</c:if>
											/>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>
					</table>
				</div>
			</fieldset>
		</div>
	</uc:form>
<div style="display:none">
	<table>
		<tr id="${pageId }cloneTr">
			<td class="tc"></td>
			<td class="tc">
				<input id="CK" type="checkbox" name="CK" onclick="com.ue.platform.module.config.input.checkConfigItem(this)" />
				<input type="hidden" name="" value="" id="" />
			</td>
			<td>
				<uc:textfield name="" value="" maxlength="25" required="true" title="请输入名称" cssClass="inputStyle w p0" id="" />
			</td>
			<td>
				<uc:textfield name="" value="" maxlength="25" required="true" title="请输入值" cssClass="inputStyle w p0" id="" />
			</td>
			<td>{0}</td>
			<td>
				<input type="radio" id="" name="" value="1" onclick="com.ue.platform.module.config.input.checkConfigItemDefault(this)" />
			</td>
			<td>
				<input type="checkbox" id="" name="" value="1" onclick="com.ue.platform.module.config.input.checkConfigItemDisable(this)" />
			</td>
		</tr>
	</table>
</div>
	
