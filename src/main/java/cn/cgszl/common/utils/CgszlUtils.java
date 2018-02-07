package cn.cgszl.common.utils;

import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 系统工具类
 * @author cguisheng 2018/2/2 14:23
 */
public class CgszlUtils {

    /**
     * 获取当前登录用户
     * @param request 请求对象
     * @return 当前用户信息
     */
    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return null;
        }
        return (User) session.getAttribute(WebConst.LOGIN_SESSION_KEY);

    }
}
