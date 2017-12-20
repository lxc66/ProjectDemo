    require.config({
        baseUrl: '/js',
        map: {
            '*': {
                'css': 'require/css.min'
            }
        },
        paths: {
            jquery: 'lib/jquery/jquery',
            jquery_ext: 'lib/jquery/jquery-ext',
            validate: 'lib/validate/jquery.validate.extend',
            validate_messages: 'lib/validate/jquery.validate.messages_zh',
            validate_core: 'lib/validate/jquery.validate',
            layer: 'lib/layer/layer',
            bootstrap: 'lib/bootstrap/bootstrap.min',
            bootstrap_table: 'lib/bootstrap-table/locale/bootstrap-table-zh-CN.min',
            commonUtil: 'common/common.util'
        },
        shim: {
            bootstrap: {
                deps: ['jquery']
            },
            bootstrap_table:{
            	deps: [
            		'bootstrap',
            		'/js/lib/bootstrap-table/bootstrap-table.min.js',
            		'css!/js/lib/bootstrap-table/css/bootstrap-table.min.css'
            		
            	]
            },
            layer: {
                deps: [
                    'jquery',
                    'css!/js/lib/layer/skin/default/layer.css'
                ]
            },
            jquery_ext: {
            	deps: ['layer']
            },
            commonUtil: {
            	deps: ['jquery_ext']
            },
            validate: {
            	deps: [
            		'validate_messages',
            		'css!/js/lib/validate/css/validate.css'
            		]
            },
            validate_messages: {
            	deps: ['validate_core']
            }
            
        }
    });
    
require(["jquery","app"],function($,app){
	//test.alert();
	//test.msg('哈哈');
	app.init();
});