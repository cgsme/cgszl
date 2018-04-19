package cn.cgszl.common.service.impl;

import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.service.UserService;
import cn.cgszl.common.dao.mapper.UserMapper;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.utils.CgszlUtils;
import cn.cgszl.common.utils.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务接口实现类
 *
 * @author cguisheng 2017/12/27 17:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 验证用户登录
     *
     * @param username
     * @return
     */
    @Override
    public User validateLogin(String username, String password) {
        User user = this.getUserByUsername(username);
        if (null != user && user.getPassword().equals(MD5.getMD5Code(password))) {
            return user;
        }
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     */
    @Override
    public void updateUser(User user) throws CgszlException {
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 修改密码
     *
     * @param user
     * @param oldPassword     新密码
     * @param newPassword     旧密码
     * @param confirmPassword 确认密码
     * @throws CgszlException
     */
    @Override
    public void changePassword(User user, String oldPassword, String newPassword, String confirmPassword) throws CgszlException {
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)
                || StringUtils.isBlank(confirmPassword)) {
            throw new CgszlException("请输入完整信息");
        }
        if (!confirmPassword.equals(newPassword)) {
            throw new CgszlException("两次密码输入不一致");
        }
        String oldPasswordCode = MD5.getMD5Code(oldPassword);
        String newPasswordCode = MD5.getMD5Code(newPassword);
        if ((null == user) || !user.getPassword().equals(oldPasswordCode)) {
            throw new CgszlException("旧密码验证失败，请重新输入……");
        }
        user.setPassword(newPasswordCode);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
