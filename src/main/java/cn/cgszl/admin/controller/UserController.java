package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.UserInfoService;
import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.pojo.User;
import cn.cgszl.common.dao.pojo.UserInfo;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.service.UserService;
import cn.cgszl.common.utils.CgszlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户控制器
 *
 * @author cguisheng 2018/3/24 17:05
 */
@Controller
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserService userService;

    /**
     * 显示个人资料页
     *
     * @return
     */
    @RequestMapping("/admin/editProfile")
    public String profile(HttpServletRequest request) {
        try {
            User user = CgszlUtils.getLoginUser(request);
            if (user != null) {
                List<UserInfo> userInfoList = userInfoService.getUserInfoById(user.getUid());
                if (!CollectionUtils.isEmpty(userInfoList)) {
                    request.setAttribute("userInfo", userInfoList.get(0));
                }
            }
        } catch (CgszlException e) {
            e.printStackTrace();
        }
        return "admin/profile";
    }

    /**
     * 保存个人资料
     *
     * @param user     用户对象
     * @param userinfo 用户信息对象
     */
    @RequestMapping(value = "/admin/user/saveUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult saveUserInfo(User user, UserInfo userinfo) {
        try {
            userService.updateUser(user);
            userInfoService.updateUserInfo(userinfo);
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, e.getMessage());
        }
    }
}
