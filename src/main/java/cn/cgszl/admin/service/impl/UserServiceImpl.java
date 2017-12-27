package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.UserService;
import cn.cgszl.common.dao.mapper.UserMapper;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.dao.pojo.UserExample;
import cn.cgszl.common.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务接口实现类
 * @author cguisheng 2017/12/27 17:38
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 验证用户登录
     * @param username
     * @return
     */
    @Override
    public boolean validateLogin(String username, String password) {
        User user = this.getUserByUsername(username);
        if (null != user) {
            // 返回密码是否匹配
            return user.getPassword().equals(MD5.getMD5Code(password));
        }
        return false;
    }
}
