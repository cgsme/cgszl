package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.UserInfoService;
import cn.cgszl.common.dao.mapper.UserInfoMapper;
import cn.cgszl.common.dao.pojo.UserInfo;
import cn.cgszl.common.dao.pojo.UserInfoExample;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息业务接口实现
 *
 * @author cguisheng 2018/4/18 15:46
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 根据用户标识查询用户详细信息
     *
     * @param uid 用户标识
     * @return
     * @throws CgszlException
     */
    @Override
    public List<UserInfo> getUserInfoById(Integer uid) throws CgszlException {
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andUidEqualTo(uid);
        return userInfoMapper.selectByExample(userInfoExample);
    }

    /**
     * 更新用户信息
     *
     * @param userinfo 用户信息对象
     */
    @Override
    public void updateUserInfo(UserInfo userinfo) throws CgszlException {
        userInfoMapper.updateByPrimaryKeySelective(userinfo);
    }
}
