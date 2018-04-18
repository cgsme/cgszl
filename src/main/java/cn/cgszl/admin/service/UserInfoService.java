package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.UserInfo;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 用户详细信息业务接口
 *
 * @author cguisheng 2018/4/18 15:45
 */
public interface UserInfoService {

    /**
     * 根据用户标识查询用户详细信息
     *
     * @param uid 用户标识
     * @return
     */
    List<UserInfo> getUserInfoById(Integer uid) throws CgszlException;

    /**
     * 更新用户细腻些
     *
     * @param userinfo 用户信息对象
     */
    void updateUserInfo(UserInfo userinfo) throws CgszlException;
}
