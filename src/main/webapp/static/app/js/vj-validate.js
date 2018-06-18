(function(win, $) {

    var rules = {
        "required": function(val) {
            return (val.replace(/^\s+|\s+$/g, "")).length !== 0;
        },
        "phone": function(val) {
            return /^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/.test(val);
        },
        "email": function(val) {
            return /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(val);
        },
        "idcard": function(val) {
            return /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/.test(val);
        },
        "url": function(val) {
            return /^https?:\/\/(([a-zA-Z0-9_-])+(\.)?)*(:\d+)?(\/((\.)?(\?)?=?&?[a-zA-Z0-9_-](\?)?)*)*$/i.test(val);
        },
        "postcode": function(val) {
            return /^[1-9]\d{5}(?!\d)$/.test(val);
        },
        "date": function(val) {
            return /^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$/.test(val);
        },
        "digital": function(val) {
            return /^\d+$/.test(val);
        },
        "chinese": function(val) {
            return /^[\u4e00-\u9fa5]$/gm.test(val);
        }
    };

    var extend = function(dest, src) {
        for (var attr in src) {
            if (src.hasOwnProperty(attr)) {
                if (!(attr in dest)) {
                    dest[attr] = src[attr];
                }
            }
        }
        return dest;
    };

    var VjValidate = function(rs) {
        this.rules = extend(rules, rs);

        this.init = function() {
            var cache = {};

            $.ajaxSetup('error', function() {
                $.toast('网络错误');
            });

            $.ajaxSetup('dataType', "json");

            $("form[data-vj-validate]").each(function(index, val) {
                $(val).submit(function(e) {
                    e.preventDefault();
                    if (cache[index]) {
                        return false;
                    } else {
                        cache[index] = $(val);
                    }

                    var valid = true;

                    $(this).find("input[data-vj-rule]").each(function(i, v) {
                        var data = $(v).data();
                        var value = $(v).val();
                        if (data.vjRule) {
                            if (rules[data.vjRule]) {
                                if (!rules[data.vjRule](value)) {
                                    if (data.vjMsg) {
                                        $.toast(data.vjMsg);
                                    } else {
                                        $.toast('表单中有字段检验不合法');
                                    }
                                    valid = false;
                                    cache[index] = null;
                                    return false;
                                }
                            }
                        }
                    });

                    if (!valid) {
                        return false;
                    } else {
                        $.post($(val).attr("action"), $(val).serialize(), function(res) {
                            res = JSON.parse(res);
                            if (res.statusCode == 1) {
                                $.toast(res.msg);

                                if (res.url) {
                                    setTimeout(function() {
                                        window.location.href = res.url;
                                    }, 1000);
                                }
                            } else {
                                $.toast(res.msg);
                            }
                            setTimeout(function() {
                                cache[index] = null;
                            }, 2000);
                        });
                    }
                });
            });

            $("*[data-vj-ajax]").each(function(index, val) {
                $(val).click(function() {
                    var data = $(this).data();
                    var send = function(data) {
                        if (data.vjUrl) {
                            $.get(data.vjUrl, function(data) {
                                data = JSON.parse(data);
                                if (data.statusCode == 1) {
                                    $.toast(data.msg);
                                    if (data.url) {
                                        setTimeout(function() {
                                            window.location.href = data.url;
                                        }, 1000);
                                    }
                                } else {
                                    $.toast(data.msg);
                                }
                            });
                        }
                    };
                    if (data.vjConfirm) {
                        $.vjConfirm(data.vjConfirm, function(status) {
                            if (status === 1) {
                                send(data);
                            }
                        });
                    } else {
                        send(data);
                    }
                });
            });

            $("*[data-vj-href]").each(function(index, val) {
                $(val).click(function(e) {
                    e.preventDefault();
                    window.location.href = $(this).data("vj-href");
                });
            });
        };
    };

    win.VjValidate = VjValidate;

})(window, jQuery);