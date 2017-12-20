
window.confirm = function(title,func){
	$.confirm(title,func)
}

window.alert = function(title,func){
	$.alert(title,func)
}

//string类的扩展
/**
 * 判断字符串是否以某个字符结尾
 * @param {Object} s1
 * @param {Object} s2
 * @return {TypeName} 
 */
String.prototype.endwith = function endWith(str) {
	if (this.length < str.length)
		return false;
	if (this == str)
		return true;
	if (this.substring(this.length - str.length) == str)
		return true;
	return false;
};

/** 
 * 用正则表达式将前后空格用空字符串替代
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * replaceAll
 */
String.prototype.replaceAll = function(s1, s2) {
	var r = new RegExp(s1.replace(/([\(\)\[\]\{\}\^\$\+\-\*\?\.\"\'\|\/\\])/g,"\\$1"), "ig");
	return this.replace(r, s2);
};