/**
 * 定义cgszl工具类库
 * @author cguisheng
 * @since 2018-02-02
 */

// 定义工具类对象
var cgszlUtils = {

    /**
     * 序列化表单字段
     * @param form
     * @returns {{}}
     */
    serializeObject: function (form) {
        var formEL = $(form);
        var o = {};
        var a = formEL.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    },

    /**
     * 填充表单字段
     *
     * @param form
     * @param obj
     * @param isStatus
     */
    fillFormData: function (form, obj, isStatus) {
        var formEL = $(form);
        $.each(obj, function (index, item) {
            formEL.find("[name=" + index + "]").val(item);
        });
    },

    /**
     * 清空表单
     *
     * @param data
     * @returns {boolean}
     */
    emptyForm: function (data) {
        if (null == data || "" == data) return true;
        else return false;
    },

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
     * @param loadObj 获取数据的交互链接或者数据的JSON对象
     * @param loadSuccessFn 数据加载完后的回调
     * @param selectorStr 以jQuery方式命名的选择器字符串
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
    },

    /**
     * 判断是否是对象
     * @param object
     */
    isObj: function (object) {
        return object && typeof (object) == 'object'
            && Object.prototype.toString.call(object).toLowerCase() == "[object object]";
    },

    /**
     * 判断是否为数组
     * @param object
     */
    isArray: function (object) {
        return object && typeof (object) == 'object' && object.constructor == Array;
    },

    /**
     * 获取对象长度
     *
     * @param object
     */
    getLengthL: function (object) {
        var count = 0;
        for (var i in object) {
            count++;
        }
        return count;
    },

    /**
     * 比较json是否相等
     * @param currObj
     * @param targetObj
     * @returns {boolean}
     */
    compare: function (currObj, targetObj) {
        // 判断类型是否正确
        if (!cgszlUtils.isObj(currObj) || !cgszlUtils.isObj(targetObj)) {
            return false;
        }
        // 判断长度是否一致
        if (cgszlUtils.getLengthL(currObj) != cgszlUtils.getLengthL(targetObj)) {
            return false;
        }
        return cgszlUtils.compareObj(currObj, targetObj, true);  // 默认为true
    },

    /**
     * 对比对象是否相等
     *
     * @param currObj
     * @param targetObj
     * @param flag
     */
    compareObj: function (currObj, targetObj, flag) {
        for (var key in currObj) {
            if (!flag) //跳出整个循环
                break;
            if (!targetObj.hasOwnProperty(key)) {
                flag = false;
                break;
            }
            if (!cgszlUtils.isArray(currObj[key])) { //子级不是数组时,比较属性值
                if (targetObj[key] != currObj[key]) {
                    flag = false;
                    break;
                }
            } else {
                if (!cgszlUtils.isArray(targetObj[key])) {
                    flag = false;
                    break;
                }
                var oA = currObj[key], oB = targetObj[key];
                if (oA.length != oB.length) {
                    flag = false;
                    break;
                }
                for (var k in oA) {
                    if (!flag) //这里跳出循环是为了不让递归继续
                        break;
                    flag = cgszlUtils.compareObj(oA[k], oB[k], flag);
                }
            }
        }
        return flag;
    }


}