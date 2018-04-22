package cn.cgszl.admin.controller;

import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.log.SystemLog;
import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.dto.GridData;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论管理控制器
 *
 * @author cguisheng 2018/2/23 21:17
 */
@Controller
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 评论管理页
     *
     * @return
     */
    @RequestMapping(value = "/admin/commentmanager")
    public String commentManager() {

        return "admin/commentmanager";
    }

    /**
     * 评论列表
     *
     * @param page  页码
     * @param limit 每页记录数
     * @return
     */
    @RequestMapping(value = "/admin/comment/listComments")
    @ResponseBody
    public GridData listComments(Integer page, Integer limit) {
        try {
            List<Comment> commentList = commentService.listComments(page, limit, Types.COMMENT.getType());
            PageInfo<Comment> commentPageInfo = new PageInfo<Comment>(commentList);
            return GridData.build(commentList, commentPageInfo.getTotal());
        } catch (CgszlException e) {
            e.printStackTrace();
            return GridData.build(null, 0);
        }
    }

    /**
     * 根据评论标识删除评论
     *
     * @param coid 评论标识
     * @return
     */
    @RequestMapping(value = "/admin/comment/deleteById")
    @ResponseBody
    @SystemLog(module = "评论管理模块", methods = "删除评论")
    public CommonResult deleteById(Integer coid) {
        try {
            commentService.deleteCommenntByid(coid);
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 更新评论状态
     *
     * @param coid   评论标识
     * @param status 状态
     * @return 通用结果对象
     */
    @RequestMapping(value = "/admin/comment/updateCommentStatus")
    @ResponseBody
    @SystemLog(module = "评论管理模块", methods = "更新评论状态")
    public CommonResult updateCommentStatus(Integer coid, String status) {
        try {
            commentService.updateCommentStatus(coid, status);
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }
}
