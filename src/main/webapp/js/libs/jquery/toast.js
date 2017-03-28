/**
 * 信息提示
 * author 张龙 2016-09-02
 */
(function($) {
    $.extend({
        toast: function(message, delay) {
            message = message ? message : "";
            delay = delay ? delay : 1500;
            var plugin = this;
            plugin.prototype.toastTimer = null;
            if (plugin.toastTimer != null) {
                clearTimeout(plugin.toastTimer)
            }
            var dialog = document.getElementById("toast_box");
            if (dialog) {
                document.body.removeChild(dialog)
            }
            dialog = document.createElement("div");
            dialog.setAttribute("id", "toast_box");
            dialog.style.cssText = "display:inline-block;position:fixed;max-width:80%;margin:0 auto;left:-9999px;bottom:50px;text-align:center;z-index:9999";
            dialog.innerHTML = "<span style='display: inline-block;padding: 0.4em 0.4em 0.4em 0.4em;color: #FFF;text-align: center;font-size: 22px;font-weight: 600;word-wrap: break-word;border: 1px solid #888;-webkit-border-radius: 3px;-moz-border-radius: 3px;border-radius: 3px;-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, .4);-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, .4),box-shadow: 0 1px 4px rgba(0, 0, 0, .4);background: rgba(0, 0, 0, .7);-webkit-user-select:none;-ms-user-select:none;-moz-user-select:none;'>" + message + "</span>";
            document.body.appendChild(dialog);
            var zoom = $("body").css("zoom");
            if (!zoom) {
                zoom = 1
            }
            dialog.style.left = (document.body.clientWidth - dialog.offsetWidth * zoom) / (2 * zoom) + "px";
            dialog.style.display = "inline-block";
            plugin.toastTimer = setTimeout(function() {
                document.body.removeChild(dialog);
            }, delay);
            return this
        }
    })
})(jQuery);
