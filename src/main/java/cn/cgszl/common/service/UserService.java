package cn.cgszl.common.service;

import cn.cgszl.common.dao.pojo.User;

/**
 * 用户业务接口
 * @author cguisheng 2017/12/27 17:37
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 验证登录
     * @param username
     * @return
     */
    User validateLogin(String username, String password);
}
