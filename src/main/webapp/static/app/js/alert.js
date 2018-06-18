(function($) {
    $.toast = (function() {
        var $el = null;
        return function(text) {
            if ($el) {
                return;
            } else {
                $el = $("<div>" + text + "</div>");
                $el.css({
                    "display": "block",
                    "position": "fixed",
                    "bottom": "20%",
                    "left": "50%",
                    "transform": "translateX(-50%)",
                    "text-align": "center",
                    "padding": "3% 8%",
                    "border-radius": "10px",
                    "font-size": "1.2em",
                    "background-color": "rgba(0, 0, 0, .7)",
                    "color": "#fff",
                    "opacity": 0
                });
                $("body").append($el);
                $el.animate({
                    "opacity": 1
                }, 300, 'linear', function() {
                    setTimeout(function() {
                        $el.animate({
                            "opacity": 0
                        }, 300, 'linear', function() {
                            $el.remove();
                            $el = null;
                        });
                    }, 2000);
                });
            }
        };
    })();

    $.vjConfirm = (function() {
        var $el = null;
        return function(title, func) {
            if ($el) {
                return;
            }
            $("<style id='vj-dialog-style'>\n" +
                "        .vj-mask {\n" +
                "            position: fixed;\n" +
                "            width: 100%;\n" +
                "            height: 100%;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            background-color: rgba(0, 0, 0, .6);\n" +
                "            opacity: 0;\n" +
                "        }\n" +
                "        .vj-mask .vj-dialog {\n" +
                "            display: block;\n" +
                "            position: fixed;\n" +
                "            top: 0;\n" +
                "            left: 0;\n" +
                "            right: 0;\n" +
                "            bottom: 0;\n" +
                "            margin: auto;\n" +
                "            width: 60%;\n" +
                "            height: 20%;\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .vj-mask .vj-dialog .vj-dialog-body {\n" +
                "            display: block;\n" +
                "            height: 70%;\n" +
                "            width: 100%;\n" +
                "            position: relative;\n" +
                "            border-radius: 10px 10px 0 0;\n" +
                "        }\n" +
                "\n" +
                "        .vj-mask .vj-dialog .vj-dialog-body p {\n" +
                "            display: block;\n" +
                "            position: absolute;\n" +
                "            left: 50%;\n" +
                "            top: 50%;\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "            transform: translate(-50%, -50%);\n" +
                "            font-size: 1.4em;\n" +
                "        }\n" +
                "\n" +
                "        .vj-mask .vj-dialog .vj-dialog-footer {\n" +
                "            display: flex;\n" +
                "            height: 30%;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "\n" +
                "        .vj-mask .vj-dialog .vj-dialog-footer p {\n" +
                "            display: block;\n" +
                "            position: absolute;\n" +
                "            left: 50%;\n" +
                "            top: 50%;\n" +
                "            padding: 0;\n" +
                "            margin: 0;\n" +
                "            transform: translate(-50%, -50%);\n" +
                "            font-size: 1.2em;\n" +
                "        }\n" +
                "\n" +
                "        .vj-mask .vj-dialog .vj-dialog-footer .left {\n" +
                "            flex: 1;\n" +
                "            height: 100%;\n" +
                "            border-top: solid #e3e3e3 1px;\n" +
                "            border-radius: 0 0 0 10px;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .vj-mask .vj-dialog .vj-dialog-footer .right {\n" +
                "            flex: 1;\n" +
                "            height: 100%;\n" +
                "            border-top: solid #FC605A 1px;\n" +
                "            background-color: #FC605A;\n" +
                "            border-radius: 0 0 10px 0;\n" +
                "            color: #fff;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .vj-mask .vj-dialog .vj-dialog-footer .right p {\n" +
                "            color: #fff !important;\n" +
                "        }\n" +
                "    </style>").appendTo("head");
            var start = function(obj) {
                $("body").append(obj);
                obj.animate({
                    opacity: 1
                }, 100, 'swing');
                obj.find('.vj-dialog').animate({
                    height: "28%",
                    width: "90%"
                }, 200, 'swing', function() {
                    obj.find('.vj-dialog').animate({
                        height: "25%",
                        width: "80%"
                    }, 50, 'swing');
                });
            };

            var end = function(obj) {
                obj.animate({
                    opacity: 0
                }, 200, 'swing', function() {
                    obj.remove();
                    $("#vj-dialog-style").remove();
                    $el = null;
                });
            };

            $el = $('<div class="vj-mask">\n' +
                '        <div class="vj-dialog">\n' +
                '            <div class="vj-dialog-body">\n' +
                '                <p>' + title + '</p>\n' +
                '            </div>\n' +
                '            <div class="vj-dialog-footer">\n' +
                '                <div class="left">\n' +
                '                    <p>取消</p>\n' +
                '                </div>\n' +
                '                <div class="right">\n' +
                '                    <p>确定</p>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '    </div>');
            $el.find(".vj-dialog-footer").click(function(e) {
                var t = e.target;
                console.log(t);
                if ($(t).hasClass("left") || $(t).parent().hasClass("left")) {
                    end($el);
                    func(0);
                } else {
                    end($el);
                    func(1);
                }
            });
            start($el);
        }
    })();
}(jQuery));