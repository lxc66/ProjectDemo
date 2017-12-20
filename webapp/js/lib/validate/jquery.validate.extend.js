/**
 * jquery.validate 验证框架封装
 */
(function($) {
//	$.validator.setDefaults({
//		onkeyup : false,
//		errorClass: "validator_error",
//		validClass: "validator_valid",
//		errorElement: "label"
//	});

    $.validator.setDefaults({
    	onkeyup : false,
    	highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        success: function (element) {
            element.closest('.form-group').removeClass('has-error').addClass('has-success');
        },
        errorElement: "span",
        errorPlacement: function (error, element) {
            if (element.is(":radio") || element.is(":checkbox")) {
                error.appendTo(element.parent().parent());
            } else {
                error.appendTo(element.parent());
            }
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    });
	
	// 手机号验证   
    $.validator.addMethod("mobile", function(value, element) {   
        var regular = /^1(3|4|5|7|8)\d{9}$/;
        return this.optional(element) || (regular.test(value));
    }, "请正确填写手机号码");
    
    // 身份证验证   
    $.validator.addMethod("identityCard", function(value, element) {   
    	var regular = /\d{18}|\d{15}/;
    	return this.optional(element) || (regular.test(value));
    }, "请正确填写身份证号码");
    
})(jQuery);