package cn.cgszl.portal.controller;

import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.utils.CgszlUtils;
import cn.cgszl.common.utils.DateKit;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 联系控制器
 *
 * @author cguisheng 2018/4/22 17:48
 */
@Controller
public class ContactController {

    @Resource
    private CommentService commentService;

    /**
     * 留言
     *
     * @param comment 留言对象
     * @return
     */
    @RequestMapping("/portal/comment/leaveMessage")
    @ResponseBody
    public CommonResult leaveMessage(HttpServletRequest request, Comment comment) {
        try {
            if (null != comment) {
                if (StringUtils.isBlank(comment.getContent())) {
                    return CommonResult.fail(false, "留言不能为空");
                }
                // 设置ip
                comment.setIp(CgszlUtils.getClientIPAddress(request));
                comment.setType(Types.MESSAGE.getType());
                comment.setAgent(CgszlUtils.getOsAndBrowserInfo(request));
                boolean result = commentService.saveComment(comment);
                if (result) {
                    return CommonResult.ok();
                }
            }
            return CommonResult.fail(false, "留言失败");
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, e.getMessage());
        }
    }
}
