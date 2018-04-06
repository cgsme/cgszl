/**
 * Dependencies
 * cgszl.js
 */

// 定义cgszl的工具命名空间
cz.extendNs("cgszl.utils");

/**
 * 定义cgszl工具类库
 * @author xhuatang@cgszl.com
 * @since 2011-07-28
 */
;(function ($, cz, undefined) {

    /**
     * 字段的选择器
     */
    var FIELDS_SELECTOR_TYPE = [
        "input:not(:file)",
        "select",
        "textarea"].join(",");

    /**
     * 加载的DIV的ID
     */
    var LOADING_DIV_ID = "_cz_page_loading_div";

    /**
     * 遮罩DIV的ID
     */
    var MODAL_DIV_ID = "_cz_page_modal_div";

    // 用户访问的URL
    var USER_VISITOR_URL = "_ccip_visitor_url";


    (function (global) {
        'use strict';
        // existing version for noConflict()
        var _Base64 = global.Base64;
        var version = "2.1.5";
        // if node.js, we use Buffer
        var buffer;
        if (typeof module !== 'undefined' && module.exports) {
            buffer = require('buffer').Buffer;
        }
        // constants
        var b64chars
            = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';
        var b64tab = function (bin) {
            var t = {};
            for (var i = 0, l = bin.length; i < l; i++) t[bin.charAt(i)] = i;
            return t;
        }(b64chars);
        var fromCharCode = String.fromCharCode;
        // encoder stuff
        var cb_utob = function (c) {
            if (c.length < 2) {
                var cc = c.charCodeAt(0);
                return cc < 0x80 ? c
                    : cc < 0x800 ? (fromCharCode(0xc0 | (cc >>> 6))
                        + fromCharCode(0x80 | (cc & 0x3f)))
                        : (fromCharCode(0xe0 | ((cc >>> 12) & 0x0f))
                            + fromCharCode(0x80 | ((cc >>> 6) & 0x3f))
                            + fromCharCode(0x80 | ( cc & 0x3f)));
            } else {
                var cc = 0x10000
                    + (c.charCodeAt(0) - 0xD800) * 0x400
                    + (c.charCodeAt(1) - 0xDC00);
                return (fromCharCode(0xf0 | ((cc >>> 18) & 0x07))
                    + fromCharCode(0x80 | ((cc >>> 12) & 0x3f))
                    + fromCharCode(0x80 | ((cc >>> 6) & 0x3f))
                    + fromCharCode(0x80 | ( cc & 0x3f)));
            }
        };
        var re_utob = /[\uD800-\uDBFF][\uDC00-\uDFFFF]|[^\x00-\x7F]/g;
        var utob = function (u) {
            return u.replace(re_utob, cb_utob);
        };
        var cb_encode = function (ccc) {
            var padlen = [0, 2, 1][ccc.length % 3],
                ord = ccc.charCodeAt(0) << 16
                    | ((ccc.length > 1 ? ccc.charCodeAt(1) : 0) << 8)
                    | ((ccc.length > 2 ? ccc.charCodeAt(2) : 0)),
                chars = [
                    b64chars.charAt(ord >>> 18),
                    b64chars.charAt((ord >>> 12) & 63),
                    padlen >= 2 ? '=' : b64chars.charAt((ord >>> 6) & 63),
                    padlen >= 1 ? '=' : b64chars.charAt(ord & 63)
                ];
            return chars.join('');
        };
        var btoa = global.btoa ? function (b) {
            return global.btoa(b);
        } : function (b) {
            return b.replace(/[\s\S]{1,3}/g, cb_encode);
        };
        var _encode = buffer
            ? function (u) {
                return (new buffer(u)).toString('base64')
            }
            : function (u) {
                return btoa(utob(u))
            }
        ;
        var encode = function (u, urisafe) {
            return !urisafe
                ? _encode(u)
                : _encode(u).replace(/[+\/]/g, function (m0) {
                    return m0 == '+' ? '-' : '_';
                }).replace(/=/g, '');
        };
        var encodeURI = function (u) {
            return encode(u, true)
        };
        // decoder stuff
        var re_btou = new RegExp([
            '[\xC0-\xDF][\x80-\xBF]',
            '[\xE0-\xEF][\x80-\xBF]{2}',
            '[\xF0-\xF7][\x80-\xBF]{3}'
        ].join('|'), 'g');
        var cb_btou = function (cccc) {
            switch (cccc.length) {
                case 4:
                    var cp = ((0x07 & cccc.charCodeAt(0)) << 18)
                        | ((0x3f & cccc.charCodeAt(1)) << 12)
                        | ((0x3f & cccc.charCodeAt(2)) << 6)
                        | (0x3f & cccc.charCodeAt(3)),
                        offset = cp - 0x10000;
                    return (fromCharCode((offset >>> 10) + 0xD800)
                        + fromCharCode((offset & 0x3FF) + 0xDC00));
                case 3:
                    return fromCharCode(
                        ((0x0f & cccc.charCodeAt(0)) << 12)
                        | ((0x3f & cccc.charCodeAt(1)) << 6)
                        | (0x3f & cccc.charCodeAt(2))
                    );
                default:
                    return fromCharCode(
                        ((0x1f & cccc.charCodeAt(0)) << 6)
                        | (0x3f & cccc.charCodeAt(1))
                    );
            }
        };
        var btou = function (b) {
            return b.replace(re_btou, cb_btou);
        };
        var cb_decode = function (cccc) {
            var len = cccc.length,
                padlen = len % 4,
                n = (len > 0 ? b64tab[cccc.charAt(0)] << 18 : 0)
                    | (len > 1 ? b64tab[cccc.charAt(1)] << 12 : 0)
                    | (len > 2 ? b64tab[cccc.charAt(2)] << 6 : 0)
                    | (len > 3 ? b64tab[cccc.charAt(3)] : 0),
                chars = [
                    fromCharCode(n >>> 16),
                    fromCharCode((n >>> 8) & 0xff),
                    fromCharCode(n & 0xff)
                ];
            chars.length -= [0, 0, 2, 1][padlen];
            return chars.join('');
        };
        var atob = global.atob ? function (a) {
            return global.atob(a);
        } : function (a) {
            return a.replace(/[\s\S]{1,4}/g, cb_decode);
        };
        var _decode = buffer
            ? function (a) {
                return (new buffer(a, 'base64')).toString()
            }
            : function (a) {
                return btou(atob(a))
            };
        var decode = function (a) {
            return _decode(
                a.replace(/[-_]/g, function (m0) {
                    return m0 == '-' ? '+' : '/'
                })
                    .replace(/[^A-Za-z0-9\+\/]/g, '')
            );
        };
        var noConflict = function () {
            var Base64 = global.Base64;
            global.Base64 = _Base64;
            return Base64;
        };
        // export Base64
        global.Base64 = {
            VERSION: version,
            atob: atob,
            btoa: btoa,
            fromBase64: decode,
            toBase64: encode,
            utob: utob,
            encode: encode,
            encodeURI: encodeURI,
            btou: btou,
            decode: decode,
            noConflict: noConflict
        };
        // if ES5 is available, make Base64.extendString() available
        if (typeof Object.defineProperty === 'function') {
            var noEnum = function (v) {
                return {value: v, enumerable: false, writable: true, configurable: true};
            };
            global.Base64.extendString = function () {
                Object.defineProperty(
                    String.prototype, 'fromBase64', noEnum(function () {
                        return decode(this)
                    }));
                Object.defineProperty(
                    String.prototype, 'toBase64', noEnum(function (urisafe) {
                        return encode(this, urisafe)
                    }));
                Object.defineProperty(
                    String.prototype, 'toBase64URI', noEnum(function () {
                        return encode(this, true)
                    }));
            };
        }
        // that's it!
    })(window);


// 私有的方法
    var privateMethods = {

        /**
         * 使用JSON对字段进行复制
         * @param json 要赋值的JSON
         */
        setFieldsByJson: function (json, selectorStr) {
            // 遍历表单中的每个字段
            for (var key in json) {
                var fieldSelector = "[name='" + key + "']";
                if (selectorStr) {
                    fieldSelector = selectorStr + " " + fieldSelector;
                }

                var $field = $(fieldSelector);
                if ($field.length === 0 ||
                    typeof(json[key]) === "object") {
                    continue;
                }

                // 如果是单选或者复选框
                if ($field.is(":checkbox,:radio")) {
                    this.setMultipleFields($field, json[key]);
                } else if ($field.is("input:text,input:password,input:hidden,select,textarea")) {// 对下拉框、文本、多文本进行赋值
                    $field.val(json[key] + "");
                }
            }//end for
        },

        /**
         * 设置单选复选
         * @param $field 单选、复选对象
         * @param val    设置的值对象
         */
        setMultipleFields: function ($field, val) {
            var valArry = [];
            if (typeof(val) === "array") {
                valArry = val;
            } else {
                valArry = (val + "").split(",");
            }

            // 循环获取多个相同名的对象
            $field.each(function () {
                var $this = $(this);
                for (var i = 0, len = valArry.length; i < len; i++) {
                    if ($this.val() === valArry[i]) {
                        if ($field.is(":checkbox,:radio")) {
                            $this.attr("checked", true);
                        } else {
                            $this.attr("selected", true);
                        }
                        break;
                    }// end if
                }//end for
            });
        },

        /**
         * 添加加载的DIV对象
         */
        showLoading: function () {
            if ($(".mini-mask-background").length > 0) {
                return;
            }

            // 请求遮罩
            var $modalDiv = $("#" + MODAL_DIV_ID);
            if ($modalDiv.length === 0) {
                $modalDiv = $('<div id="' + MODAL_DIV_ID + '"\
					style="width:100%;height:100%;\
					position:absolute;opacity:0.0;-moz-opacity:.0;filter(alpha(opacity=0))\
					z-index:9998;display:none;top:0;right:0;"></div>');
                $modalDiv.appendTo($("body"));
            }
            $modalDiv.show();

            // 显示加载提示
            var $loadingDiv = $("#" + LOADING_DIV_ID);
            if ($loadingDiv.length === 0) {
                $loadingDiv = $('<div id="' + LOADING_DIV_ID + '"\
					style="background:#999;color:#FFF;width:160px;\
					padding-left:8px;position:absolute;line-height:26px;\
					z-index:9999;display:none;top:0;right:0;font-size:12px;"></div>');

                $loadingDiv.appendTo($("body"));
            }
            $loadingDiv.text("正在与后台交互，请稍候……");
            $loadingDiv.show();
        },

        /**
         * 移除加载的DIV
         */
        hideLoading: function (msg) {
            var $loadingDiv = $("#" + LOADING_DIV_ID);
            var $modalDiv = $("#" + MODAL_DIV_ID);
            $modalDiv.fadeOut();
            if (!msg) {
                $loadingDiv.fadeOut();
            } else {
                $loadingDiv.css("background", "#ff8989");
                $loadingDiv.text(msg);
                setTimeout(function () {
                    $loadingDiv.fadeOut();
                }, 3000);
            }
        },

        /**
         * 获取页面中最大的z-index
         * @returns
         */
        getMaxZIndex: function () {
            var maxZIndex = Math.max.apply(
                null,
                $.map($('*'),
                    function (e, n) {
                        if ($(e).css('position') === 'absolute') {
                            var zIndex = parseInt($(e).css('z-index')) || 1;
                            return zIndex;
                        }
                    }));
            return maxZIndex;
        }
    };

// 定义工具类对象
    cz.utils = {

        /**
         * 获取GUID
         */
        getGuid: function () {
            var s4 = function () {
                return (((1 + Math.random()) * 0x10000) | 0).toString(16)
                    .substring(1);
            };
            return (s4() + s4() + "-" + s4() + "-" + s4() + "-" + s4()
                + "-" + s4() + s4() + s4());
        },// end getGuid function

        /**
         * 获取32位的UNID
         */
        getUnid: function () {
            return this.getGuid().toUpperCase().replace(new RegExp("-", "g"), "");
        },

        /**
         * 获取指定区域的JSON值
         * @param selectorStr 以jQuery方式命名的选择器字符串
         */
        getFieldsJson: function (selectorStr) {
            var json = {};

            // 循环获取所有被选择的对象
            $(selectorStr).each(function () {

                // 循环获取选择对象内的字段
                $(this).find(FIELDS_SELECTOR_TYPE).each(function () {
                    var $field = $(this);

                    // 获取字段输入表单的名称
                    var key = $field.attr("name");

                    // 需要有字段名称，名称为空，则不添加内容
                    if (!key) {
                        return;
                    }

                    // 如果是文本框、密码框
                    if ($field.is("input:text,input:password,input:hidden:not(:checkbox):not(:radio),select,textarea")) {
                        json[key] = $field.val();
                    } else if ($field.is("input:checkbox,input:radio")) {// 如果是单选框、复选框

                        // 如果被选中
                        if ($field.is(":checked")) {
                            if (json[key]) {
                                json[key] += "," + $field.val();
                            } else {
                                json[key] = $field.val();
                            }
                        }//end if
                    }// end if

                });//end each

            });//end each

            return json;
        },

        /**
         * 获取指定区域的JSON值 单个的话返回js对象 多个的话返回数组
         * @param selectorStr 以jQuery方式命名的选择器字符串
         */
        getFieldsJsons: function (selectorStr) {
            var resultJson = new Array();
            var singleJson;
            var length = $(selectorStr).length;
            // 循环获取所有被选择的对象
            $(selectorStr).each(function () {
                var json = {};

                // 循环获取选择对象内的字段
                $(this).find(FIELDS_SELECTOR_TYPE).each(function () {
                    var $field = $(this);

                    // 获取字段输入表单的名称
                    var key = $field.attr("name");

                    // 需要有字段名称，名称为空，则不添加内容
                    if (!key) {
                        return;
                    }

                    // 如果是文本框、密码框
                    if ($field.is("input:text,input:password,input:hidden:not(:checkbox):not(:radio),select,textarea")) {
                        json[key] = $field.val();
                    } else if ($field.is("input:checkbox,input:radio")) {// 如果是单选框、复选框

                        // 如果被选中
                        if ($field.is(":checked")) {
                            if (json[key]) {
                                json[key] += "," + $field.val();
                            } else {
                                json[key] = $field.val();
                            }
                        }//end if
                    }// end if

                });//end each
                if (length > 1) {
                    resultJson.push(json);
                } else {
                    singleJson = json;
                    return singleJson;
                }
            });//end each
            if (length > 1) {
                return resultJson;
            } else {
                return singleJson;
            }
        },

        /**
         * 渲染字段值
         * @param loadObj
         *            获取数据的交互链接或者数据的JSON对象
         * @param loadSuccessFn
         *            数据加载完后的回调
         * @param selectorStr
         *              以jQuery方式命名的选择器字符串
         */
        renderFields: function (loadObj, loadSuccessFn, selectorStr) {

            // 判断是否从后台获取数据
            if (typeof(loadObj) === "string") {
                var actionUrl = loadObj;
                actionUrl += (actionUrl.indexOf("?") >= 0) ? "&" : "?";
                actionUrl += "r=" + Math.random();
                this.loadJson(actionUrl, function (formItems) {

                    privateMethods.setFieldsByJson(formItems, selectorStr);

                    // 如果执行完需要再加载相应的事件，则执行
                    if (loadSuccessFn
                        && "function" === typeof (loadSuccessFn)) {
                        loadSuccessFn.call(null, formItems);
                    }
                });
            } else if (typeof(loadObj) === "object") {//直接JSON赋值
                var formItems = loadObj;
                if (loadObj && loadObj.formItems) {
                    formItems = loadObj.formItems;
                }
                privateMethods.setFieldsByJson(formItems, selectorStr);

                // 如果执行完需要再加载相应的事件，则执行
                if (loadSuccessFn
                    && "function" === typeof (loadSuccessFn)) {
                    loadSuccessFn.call(null, formItems);
                }
            }
        },

        /**
         * 从后台获取JSON数据
         * @param actionUrl     交互的URL
         * @param loadSuccessFn 加载成功后的回调方法
         * @param isAsync       是否为异步 true是， false否
         */
        loadJson: function (actionUrl, loadSuccessFn, isAsync) {

            if (!isAsync && false !== isAsync) {
                isAsync = true;
            }

            // 添加url传递到action
            actionUrl = this.addLocalUrlToUrl(actionUrl);

            var resultJson = null;

            privateMethods.showLoading();

            // 提交到后台
            $.ajax({
                type: "post",
                url: actionUrl,
                dataType: "json",
                //	timeout : 30000,
                async: isAsync,
                success: function (data, textStatus) {
                    privateMethods.hideLoading();
                    if (!data.success) {
                        alert(data.message);
                        return;
                    }
                    var loadObj = null;
                    if (!data.content) {
                        loadObj = null;
                    } else if (typeof(data.content) === "string") {
                        loadObj = eval("(" + data.content + ")");
                    } else {
                        loadObj = data.content;
                    }

//				var debugStr = "<div><b>交互的地址：</b></div><div>" + actionUrl + "</div>";
//				debugStr += "<div><b>返回的JSON：</b></div><div>" + $.toJSON(loadObj) + "</div>";
//				
//				cz.utils.debug(debugStr);

                    resultJson = loadObj;

                    // 如果执行完需要再加载相应的事件，则执行
                    if (loadSuccessFn
                        && "function" === typeof (loadSuccessFn)) {
                        loadSuccessFn.call(null, loadObj);
                    }
                },//end success
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    switch (textStatus) {
                        case "timeout":
                            privateMethods.hideLoading("请求超时，请检查网络配置或联系管理员！");
                            break;
                        case "error":
                            if (0 === XMLHttpRequest.status) {// ajax请求时，会话过期或者未登录
                                //add by jly 2017-02-27 隐藏加载的DIV
                                privateMethods.hideLoading();
                                location.href = location.href;
                            } else {
                                privateMethods.hideLoading("与后台的交互错误！");
                            }
                            break;
                        case "parsererror" :
                            if (200 === XMLHttpRequest.status) {// ajax请求时，会话过期或者未登录
                                //add by jly 2017-02-27 隐藏加载的DIV
                                privateMethods.hideLoading();
                                location.href = location.href;
                            } else {
                                privateMethods.hideLoading("与后台的交互错误！");
                            }
                            break;
                        default:
                            break;
                    }
                },//end error
                statusCode: {// 处理错误状态
                    404: function () {
                        privateMethods.hideLoading("交互的页面不存在！");
                    }, 12029: function () {
                        privateMethods.hideLoading("与服务器通讯失败，请检查网络配置或联系管理员！");
                    }
                }//end statusCode
            });// end ajax
            // 如果是同步获取，则返回结果
            if (!isAsync) {
                return resultJson;
            }
        },

        /**
         * 从后台获取JSON数据，不需要登录使用（后台不需要判断用户是否登录）
         * @param actionUrl     交互的URL
         * @param loadSuccessFn 加载成功后的回调方法
         * @param isAsync       是否为异步 true是， false否
         */
        loadJsonWithoutLogin: function (actionUrl, loadSuccessFn, isAsync) {

            if (!isAsync && false !== isAsync) {
                isAsync = true;
            }

            // 添加url传递到action
            actionUrl = this.addLocalUrlToUrl(actionUrl);

            var resultJson = null;

            privateMethods.showLoading();

            // 提交到后台
            $.ajax({
                type: "post",
                url: actionUrl,
                dataType: "json",
                async: isAsync,
                success: function (data, textStatus) {
                    if (!data.success) {
                        alert(data.message);
                        return;
                    }
                    var loadObj = null;
                    if (typeof(data.content) === "string") {
                        loadObj = eval("(" + data.content + ")");
                    } else {
                        loadObj = data.content;
                    }

                    var debugStr = "<div><b>交互的地址：</b></div><div>" + actionUrl + "</div>";
                    debugStr += "<div><b>返回的JSON：</b></div><div>" + $.toJSON(loadObj) + "</div>";

                    cz.utils.debug(debugStr);

                    resultJson = loadObj;

                    // 如果执行完需要再加载相应的事件，则执行
                    if (loadSuccessFn
                        && "function" === typeof (loadSuccessFn)) {
                        loadSuccessFn.call(null, loadObj);
                    }

                    privateMethods.hideLoading();

                },//end success
                error: function (e) {
                    privateMethods.hideLoading("与后台的交互错误！");
                },//end error
                statusCode: {// 处理错误状态
                    404: function () {
                        privateMethods.hideLoading("交互的页面不存在！");
                    }, 12029: function () {
                        privateMethods.hideLoading("与服务器通讯失败，请检查网络配置！");
                    }
                }//end statusCode
            });// end ajax
            // 如果是同步获取，则返回结果
            if (!isAsync) {
                return resultJson;
            }
        },

        /**
         * 提交JSON数据
         * @param actionUrl     交互的URL
         * @param json          JSON数据
         * @param postSuccessFn 提交成功的回调函数
         * @param otherParams   其他参数
         * @param isAsync       是否异步提交，true：是
         */
        postJson: function (actionUrl, json, postSuccessFn, otherParams, isAsync) {

            if (!isAsync && false !== isAsync) {
                isAsync = true;
            }

            // 提交的数据
            var postData = json;

            // 如果是JSON的字符串
            if (typeof(json) !== "string" && json) {
                postData = $.toJSON(json);
            }

            // 添加url
            actionUrl = this.addLocalUrlToUrl(actionUrl);

            privateMethods.showLoading();

            // 提交到后台
            $.ajax({
                type: "post",
                url: actionUrl,
                data: postData,
                dataType: "json",
                //timeout : 30000,
                contentType: "application/json; charset=utf-8",
                async: isAsync,
                success: function (data, textStatus) {
                    privateMethods.hideLoading();
                    if (postSuccessFn) {
                        postSuccessFn.call($(this), data, textStatus, otherParams);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    switch (textStatus) {
                        case "timeout":
                            privateMethods.hideLoading("请求超时，请检查网络配置或联系管理员！");
                            break;
                        case "error":
                            if (0 === XMLHttpRequest.status) {// ajax请求时，会话过期或者未登录
                                //add by jly 2017-02-27 隐藏加载的DIV
                                privateMethods.hideLoading();
                                location.href = location.href;
                                /*try{
                                    top.location.href = top.location.href;
                                }catch(e){
                                    location.href = location.href;
                                }*/
                                //var data = $.evalJSON(XMLHttpRequest.responseText);
                            } else {
                                privateMethods.hideLoading("与后台的交互错误！");
                            }
                            break;
                        case "parsererror" :
                            if (200 === XMLHttpRequest.status) {// ajax请求时，会话过期或者未登录
                                //add by jly 2017-02-27 隐藏加载的DIV
                                privateMethods.hideLoading();
                                location.href = location.href;
                            } else {
                                privateMethods.hideLoading("与后台的交互错误！");
                            }
                            break;
                        default:
                            //add by jly 2017-02-27 隐藏加载的DIV
                            privateMethods.hideLoading();
                            break;
                    }
                },
                statusCode: {// 处理错误状态
                    404: function () {
                        privateMethods.hideLoading("交互的页面不存在！");
                    }, 12029: function () {
                        privateMethods.hideLoading("与服务器通讯失败，请检查网络配置或联系管理员！");
                    }
                }
            });// end ajax
        },

        /**
         * 提交带文件表单 cshiyong
         * @param actionUrl     交互的URL
         * @param formId          JSON数据
         * @param postSuccessFn 提交成功的回调函数
         * @param otherParams   其他参数
         * @param isAsync       是否异步提交，true：是
         */
        postFile: function (actionUrl, formId, postSuccessFn, otherParams, isAsync) {

            if (!isAsync && false !== isAsync) {
                isAsync = true;
            }

            // 添加url传递到action
            actionUrl = this.addLocalUrlToUrl(actionUrl);

            privateMethods.showLoading();

            // 表单设置
            var options = {
                url: actionUrl, // action路径
                async: isAsync,
                dataType: "json",
                success: function (data) {
                    privateMethods.hideLoading();
                    postSuccessFn(data);
                },
                error: function () {
                    privateMethods.hideLoading("与后台的交互错误！");
                },
                statusCode: {// 处理错误状态
                    404: function () {
                        privateMethods.hideLoading("交互的页面不存在！");
                    }
                }
            };

            // 加载表单设置
            $('#' + formId).ajaxForm(options);

            // 提交表单
            $('#' + formId).submit();
        },

        /**
         * 获取url参数如index.htm?id=1 返回1
         * url将URL中的字符串时进行分解获取参数name的实际值
         * modify by jc 20100420
         */
        getUrlParameter: function (name, url) {
            var params = null;
            if (url) {
                params = url.replace(/[^\?\&]*(\?|&)/, "").split('&');
            } else {
                params = window.location.search.slice(1).split('&');
            }
            for (var i = 0; i < params.length; i++) {
                var temp = params[i].split("=");
                if (temp[0] == name) {
                    //支持值里面有=，如&purl=unid=123&，取出的值为unid=123 mdf by jc 20110311
                    return params[i].replace(/^[\w]*=/, "");
                }
            }
            return "";
        },

        /**
         * 获取url参数如index.htm?id=1 返回1
         * @param name 参数名
         * @param url  链接，如果为空，则直接使用当前页面的URL
         * @returns    参数值
         */
        getUrlParam: function (name, url) {
            return this.getUrlParameter(name, url);
        },

        /**
         * 添加当前访问的URL到action地址中传递到后台
         * @param url action地址
         */
        addLocalUrlToUrl: function (url) {
            var _ccip_visitor_url = location.href;
            return this.addUrlParam(USER_VISITOR_URL, cz.utils.base64Encode(_ccip_visitor_url), url);
        },

        /**
         * 添加当前访问的URL
         * @param postData json 数据
         */
        addLocalUrlToPostData: function (postData) {
            if (!postData) {
                postData = {};
            }
            var _ccip_visitor_url = location.href;
            postData["_ccip_visitor_url"] = cz.utils.base64Encode(_ccip_visitor_url);
            return postData;
        },

        /**
         * 添加URL的参数
         * @param name   名称
         * @param value  值
         * @param url    链接地址
         */
        addUrlParam: function (name, value, url) {
            if (!value) {
                return url;
            }
            var tmpUrl = url;

            // 参数如果存在，则直接替换参数
            if (this.getUrlParameter(name, tmpUrl)) {

            } else {

                // 判断是否已经有其他参数
                if (tmpUrl.indexOf("?") >= 0) {
                    tmpUrl += "&";
                } else {
                    tmpUrl += "?";
                }
                tmpUrl += name + "=" + value;
            }//end if

            return tmpUrl;
        },

        /**
         * 删除URL连接的参数
         * @param url       URL地址
         * @param paramName 参数名称
         * @returns         删除后的链接名称
         */
        removeParam: function (url, paramName) {

            var tmpUrl = url;
            var index = url.indexOf("?");
            var params = {};
            if (index > 0) {
                tmpUrl = url.substring(0, index);
                paramsStr = url.substring(index + 1);
                var paramParts = paramsStr.split("&");
                for (var i = 0, len = paramParts.length; i < len; i++) {
                    var kv = paramParts[i].split("=");
                    var key = kv[0];
                    if (key === paramName) {
                        continue;
                    }
                    var value = kv[1];
                    params[key] = value;
                }// end for
            }// end if

            var paramsStr = "";

            for (var key in params) {
                if (!paramsStr) {
                    paramsStr += "?";
                } else {
                    paramsStr += "&";
                }
                paramsStr += key + "=" + params[key];
            }
            tmpUrl += paramsStr;

            return tmpUrl;
        },

        /**
         * 获取锚点标记
         */
        getAnchor: function () {
            var pageUrl = location.href;
            var index = pageUrl.indexOf("#");
            if (index > 0) {
                return pageUrl.substring(index + 1);
            }
            return null;
        },

        /**
         * 判断兩個對象是否相等
         * @param obj1        對象1
         * @param obj2        對象2
         * @returns {Boolean} 是否相等，true:相等
         */
        objIsEquals: function (obj1, obj2) {
            var p;

            // 判断obj1的所有对象是否存在
            for (p in obj1) {
                if (typeof(obj2[p]) == 'undefined') {
                    return false;
                }
            }

            for (p in obj1) {
                if (obj1[p]) {
                    switch (typeof(obj1[p])) {
                        case 'object':
                            if (!objIsEquals(obj1[p], obj2[p])) {
                                return false;
                            }
                            break;
                        case 'function':
                            if (typeof(obj2[p]) === 'undefined' ||
                                (obj1[p].toString() != obj2[p].toString()))
                                return false;
                            break;
                        default:
                            if (obj1[p] !== obj2[p]) {
                                return false;
                            }
                    }//end switch
                } else {

                    // 如果在obj1不存在，而obj2存在
                    if (obj2[p]) {
                        return false;
                    }
                }// end if
            }//end for


            for (p in obj2) {
                if (typeof(obj1[p]) == 'undefined') {
                    return false;
                }
            }

            return true;
        },

        /**
         * 判断key值在数组中是否存在
         * @param key   key值
         * @param array 数组
         * @returns {Boolean} 是否存在 true：是
         */
        isInArry: function (key, array) {
            for (var i = 0, len = array.length; i < len; i++) {
                if (array[i] === key) {
                    return true;
                }
            }
            return false;
        },

        /**
         * 加载JS文件
         * @param jsFilePath js文件的完整路径
         */
        loadJs: function (jsFilePath) {
            if (typeof(jsFilePath) === "string") {
                jsFilePath = jsFilePath + "?r=" + Math.random();
                document.write('<script src="' + jsFilePath + '" type="text/javascript" ></sc' + 'ript>');
            } else {
                for (var i = 0, len = jsFilePath.length; i < len; i++) {
                    var path = jsFilePath[i] + "?r=" + Math.random();
                    document.write('<script src="' + path + '" type="text/javascript" ></sc' + 'ript>');
                }
            }
        },

        /**
         * 获取合法的URL，比如http://a.com//d,返回http://a.com/d
         * @param url URL对象
         */
        getRawUrl: function (url) {
            if (!url) {
                return "";
            }
            var rawUrl;
            var urlProtocolReg = /^(http|https):\/\//;
            var urlProtocol = "";
            if (urlProtocolReg.test(url)) {
                urlProtocol = url.match(urlProtocolReg)[0];
            }
            var tmpUrlSuffix = url.substring(urlProtocol.length);
            var rawReg = /(\/)\1+/g;
            rawUrl = urlProtocol + tmpUrlSuffix.replace(rawReg, "$1");
            return rawUrl;
        },

        /**
         * Base64编码
         * @param encodeStr 编码字符串
         */
        base64Encode: function (encodeStr) {
            var encodeStr = encodeURI(encodeStr);
            return Base64.encode(encodeStr);
        },

        /**
         * Base64解码
         * @param encodeStr 解码字符串
         */
        base64Decode: function (encodeStr) {
            var encodeStr = Base64.decode(encodeStr);
            return decodeURI(encodeStr);
        },

        /**
         * 调试输出
         *
         * @param str
         *            输出内容
         */
        debug: function (str) {
            // 判断是否JS_DEBUG有定义为true，true时则在页面中输出调试的结果
            if (typeof (JS_DEBUG) !== "undefined" && JS_DEBUG) {

                var $debugDiv = $("#debugDiv");

                var $debugHideDiv = $("#debugHideDiv");

                // 判断页面中是否有存在id为debugDiv的调试div
                if ($debugDiv.size() === 0) {

                    // 定义div对象
                    $debugDiv = $("<div/>")
                        .attr("id", "debugDiv")
                        .css({
                            "border": "1px solid #ccc",
                            "margin": "5px",
                            "padding": "10px",
                            "word-break": "break-all",
                            "font-size": "12px",
                            "background-color": "#FFFFFF",
                            "z-index": privateMethods.getMaxZIndex() + 1,
                            "position": "absolute",
                            "height": "200px",
                            "line-height": "120%",
                            "overflow": "scroll",
                            "dispay": "none",
                            "top": 0,
                            "left": 0,
                            "right": 0
                        })
                        .scroll(function () {
                            $(this).find("#_closeDebugDiv").css({
                                "top": $(this).scrollTop() + "px"
                            });
                        });

                    $("<div />")
                        .css({
                            "position": "absolute",
                            "right": 0,
                            "top": 0,
                            "z-index": privateMethods.getMaxZIndex() + 1,
                            "cursor": "pointer",
                            "color": "#FF0000"
                        })
                        .attr({
                            "id": "_closeDebugDiv"
                        })
                        .click(function () {
                            $(this).parent().slideUp();
                            $debugHideDiv.show();
                        })
                        .text("Ｘ")
                        .appendTo($debugDiv);

                    // 在body的最前面添加调试的div
                    $debugDiv.prependTo($("body"));

                    $debugHideDiv = $("<div/>")
                        .attr({
                            id: "debugHideDiv"
                        })
                        .css({
                            "position": "absolute",
                            "top": 0,
                            "right": 0,
                            "width": 20,
                            "height": 20,
                            "z-index": privateMethods.getMaxZIndex() + 1,
                            "cursor": "pointer",
                            "color": "#FF0000",
                            "text-align": "center",
                            "background-color": "#ccc"
                        })
                        .text("√")
                        .hide()
                        .click(function () {
                            $(this).hide();
                            $debugDiv.slideDown();
                        });
                    $debugHideDiv.prependTo($("body"));
                }

                $debugDiv.find("#_closeDebugDiv").css({
                    "top": $debugDiv.scrollTop() + "px"
                });


                // 按钮事件
                var bodyEvent = function (e) {
                    if (!$(e.target).is("#debugDiv,#debugDiv div,button,input[type='button'],input[type='submit']")) {
                        $debugDiv.slideUp();
                        $("body").unbind("click", bodyEvent);
                        $debugHideDiv.show();
                    }
                };

                $("body").click(bodyEvent);

                if (typeof(str) === "object") {
                    str = $.toJSON(str);
                }

                $("<div></div>")
                    .css({
                        "border-bottom": "1px solid #000000",
                        "margin-bottom": "5px"
                    })
                    .html(str)
                    .prependTo($debugDiv);

                $debugHideDiv.hide();
                $debugDiv.slideDown();
            }//end if
        },// end debug function

        /**
         * 获取Iframe对象的window对象
         *
         * @param iframeObj Iframe对象
         * @returns window对象
         * @author lliangjian
         * @since 2013-12-11
         */
        getIframeWindow: function (iframeObj) {
            try {
                if (iframeObj) {
                    if (iframeObj.contentWindow) {
                        // 谷歌、火狐支持
                        return iframeObj.contentWindow;
                    } else {
                        // IE支持
                        return iframeObj.window;
                    }
                }
            } catch (e) {
            }
            return null;
        },

        /**
         * 转换时间戳
         * @param time   时间戳
         * @param format 格式化类型
         * @returns      格式化的日期时间格式
         */
        translateTimstampTo: function (time, format) {
            var testDate = new Date(time);
            var o = {
                "M+": testDate.getMonth() + 1,
                "d+": testDate.getDate(),
                "h+": testDate.getHours(),
                "m+": testDate.getMinutes(),
                "s+": testDate.getSeconds(),
                "q+": Math.floor((testDate.getMonth() + 3) / 3),
                "S": testDate.getMilliseconds()
            };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (testDate.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        },

        /**
         * 日期时间转换为时间戳格式
         * @param timeStr 日期时间字符串
         * @param format  格式化的内容
         */
        dateToTimstamp: function (timeStr, format) {
            var re = /(\d{4})(?:-(\d{1,2})(?:-(\d{1,2}))?)?(?:\s+(\d{1,2}):(\d{1,2}):(\d{1,2}))?/.exec(timeStr);
            return new Date(re[1], (re[2] || 1) - 1, re[3] || 1, re[4] || 0, re[5] || 0, re[6] || 0).getTime() / 1000;
        },

        /**
         * 日期时间格式重新格式化
         * @param timeStr   日期时间字符串
         * @param newFormat 格式化
         */
        dateReformat: function (timeStr, newFormat) {
            var longTimestamp = this.dateToTimstamp(timeStr);
            return this.translateTimstampTo(longTimestamp * 1000, newFormat);
        },
        /**
         * 获取cookie
         * @param name
         * @returns {null}
         */
        getCookie: function (name) {
            var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
            if (arr = document.cookie.match(reg))
                return unescape(arr[2]);
            else
                return null;
        }
    };//end cz.utils

})(jQuery, cgszl);