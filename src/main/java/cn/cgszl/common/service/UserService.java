package cn.cgszl.common.service;

import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.exception.CgszlException;

/**
 * 用户业务接口
 *
 * @author cguisheng 2017/12/27 17:37
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 验证登录
     *
     * @param username
     * @return
     */
    User validateLogin(String username, String password);

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     */
    void updateUser(User user) throws CgszlException;

    /**
     * 修改密码
     *
     *
     * @param user
     * @param oldPassword     新密码
     * @param newPassword     旧密码
     * @param confirmPassword 确认密码
     * @throws CgszlException
     */
    void changePassword(User user, String oldPassword, String newPassword, String confirmPassword) throws CgszlException;
}
