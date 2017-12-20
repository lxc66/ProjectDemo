<!DOCTYPE html>
<%@page language="java" pageEncoding="utf-8"%>
<%@ include file="/platform/common/jsp/taglibs.jsp" %>
<div class="container-fluid layer-content-custom">
   <form class="form-horizontal m-t" role="form" id="theForm">
       <div class="form-group">
       	   <input type="hidden" name="userId" value="${model.id}" id="userId"/>
           <label class="col-sm-3 control-label">原始密码：</label>
           <div class="col-sm-7">
               <input id="original_password" name="original_password" class="form-control" type="password">
               <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>请输入原始密码</span>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label">密码：</label>
           <div class="col-sm-7">
               <input id="password" name="password" class="form-control" type="password">
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label">确认密码：</label>
           <div class="col-sm-7">
               <input id="confirm_password" name="confirm_password" class="form-control" type="password">
               <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 请再次输入您的密码</span>
           </div>
       </div>
	</form>
</div>
<div class="layer-footer-custom">
	<div class="col-sm-12">
        <button class="btn btn-primary m-r-xs" id="submitBtn">提交</button>
        <button class="btn btn-default m-l-xs" id="cancelBtn">取消</button>
	</div>
</div>
<script type="text/javascript">
require(["jquery","/platform/module/user/user-changePassword.js"],function($,page){
	page.init();
});

</script>
