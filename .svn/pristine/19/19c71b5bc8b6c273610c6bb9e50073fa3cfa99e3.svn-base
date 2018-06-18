(function($, win) {
    var $el = $('<div class="back-home">\n' +
        '\t\t<img src="/static/app/img/home.svg">\n' +
        '\t</div>'),

        $style = $('<style>\n' +
            '\t\t.back-home {\n' +
            '\t\t\tdisplay: block;\n' +
            '\t\t\tposition: fixed;\n' +
            '\t\t\twidth: 30px;\n' +
            '\t\t\theight: 30px;\n' +
            '\t\t\tpadding: 5px;\n' +
            '\t\t\tright: 1%;\n' +
            '\t\t\tbottom: 20%;\n' +
            '\t\t\tbackground-color: #fff;\n' +
            '\t\t\tbox-shadow: 0 2px 2px #d7d7d7;\n' +
            '\t\t\tborder-radius: 50%;\n' +
            '\t\t\topacity: .9;\n' +
            '\t\t}\n' +
            '\n' +
            '\t\t.back-home img {\n' +
            '\t\t\twidth: 100%;\n' +
            '\t\t\theight: 100%;\n' +
            '\t\t}\n' +
            '\t</style>');

    $("head").append($style);

    $el.click(function() {
        win.location.href = "/App/index";
    });

    $el.appendTo("body");
})(jQuery, window);