/**
 * Created by admin on 2017/2/27.
 */
(function(doc, win) {
    var docEl = doc.documentElement,
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function() {
            var clientWidth = docEl.clientWidth > 768 ? 768 : docEl.clientWidth;
            //console.log(docEl.clientWidth);
            //console.log(docEl.clientWidth >= 768);
            if (!clientWidth) return;
            docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
        };

    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);

var footerMsgCon=document.getElementById("footerMsgCon");
var msg=document.getElementById("msg");
var sub=document.getElementById("sub");

msg.onclick=function(){
  footerMsgCon.style.display="block";
  sub.style.display="block";
  msg.style.display="none";
}
sub.onclick=function(){
    footerMsgCon.style.display="none";
    sub.style.display="none";
    msg.style.display="block";
}