package cn.cgszl.portal.controller;

import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.utils.CgszlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 评论控制器
 *
 * @author cguisheng 2018/4/6 10:42
 */
@Controller("portalCommentController")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 获取所有评论
     *
     * @param aid 评论标识
     * @return
     */
    @RequestMapping("/portal/article/listArticleComment")
    @ResponseBody
    public CommonResult listArticleComment(@RequestParam(name = "aid") Integer aid) {
        try {
            // 获取有回复的评论
            List<Comment> commentListWithReply = commentService.selectByArticleKey(aid);
            // 获取无回复的评论
            List<Comment> commentListWithOutReplay = commentService.listCommentsByAid(aid);
            // 合并集合
            commentListWithOutReplay.addAll(commentListWithReply);
            // 按评论时间排序
            Collections.sort(commentListWithOutReplay, new Comparator<Comment>() {
                // 升序 --> comment1 compareTo comment2
                // 降序 --> comment2 compareTo comment1
                @Override
                public int compare(Comment comment1, Comment comment2) {
                    return comment1.getCreated().compareTo(comment2.getCreated());
                }
            });
            return CommonResult.ok(commentListWithOutReplay);
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/portal/article/saveComment")
    @ResponseBody
    public CommonResult saveComment(HttpServletRequest request, Comment comment) {
        try {
            if (StringUtils.isBlank(comment.getContent())) {
                return CommonResult.fail(false, "留言不能为空");
            }
            // 设置ip
            comment.setIp(CgszlUtils.getClientIPAddress(request));
            boolean result = commentService.saveComment(comment);
            if (result) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "评论失败");
            }
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }

    }
}
