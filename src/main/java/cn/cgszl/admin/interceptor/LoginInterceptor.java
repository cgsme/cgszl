package cn.cgszl.admin.interceptor;

import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.utils.CgszlUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author cguisheng 2018/3/24 11:41
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 拦截前
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURL().toString();

        // 公开的url
        if (url.contains("index.html") || url.contains("login.html")) {
            return true;
        }
        // session中获取用户信息
        User user = CgszlUtils.getLoginUser(httpServletRequest);
        if (null == user) {
            // 跳转到登录页
            httpServletResponse.sendRedirect("/admin/index.html");
            return false;
        }
        return true;
    }

    /**
     * handler拦截执行后，modelAndView返回前执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * Handler执行完成之后调用这个方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
