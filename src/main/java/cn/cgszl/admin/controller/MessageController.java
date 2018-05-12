package cn.cgszl.admin.controller;

import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.dto.GridData;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.log.SystemLog;
import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.utils.GsonUtil;
import com.github.pagehelper.PageInfo;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 留言管理控制器
 *
 * @author cguisheng 2018/5/12 1:47
 */
@Controller
public class MessageController {

    @Resource
    private CommentService commentService;

    /**
     * 留言管理
     *
     * @return
     */
    @RequestMapping("/admin/messagemanager")
    public String messageManager() {
        return "admin/messagemanager";
    }

    /**
     * 获取留言列表
     *
     * @return
     */
    @RequestMapping("/admin/message/listMessages")
    @ResponseBody
    public GridData listMessages(Integer page, Integer limit) {
        try {
            List<Comment> messageList = commentService.listComments(page, limit, Types.MESSAGE.getType());
            PageInfo<Comment> commentPageInfo = new PageInfo<Comment>(messageList);
            return GridData.build(messageList, commentPageInfo.getTotal());
        } catch (CgszlException e) {
            e.printStackTrace();
        }
        return GridData.build(null, 0);
    }

    /**
     * 批量标记已读
     *
     * @param messageList 选中的记录
     * @return
     */
    @RequestMapping("/admin/message/batchRead")
    @ResponseBody
    @SystemLog(module = "留言管理模块", methods = "批量标记已读")
    public CommonResult batchRead(@RequestBody List<Comment> messageList) {
        try {
            if (StringUtils.isNotBlank("12")) {
                boolean result = commentService.batchRead(messageList);
                if (result) {
                    return CommonResult.ok();
                } else {
                    return CommonResult.fail(false, "系统异常");
                }
            }
        } catch (CgszlException e) {
            e.printStackTrace();
        }
        return CommonResult.fail(false, "系统异常");
    }

    /**
     * 批量删除
     *
     * @param checkIdsStr 选中的记录标识
     * @return
     */
    @RequestMapping("/admin/message/batchDelete")
    @ResponseBody
    @SystemLog(module = "留言管理模块", methods = "批量删除留言")
    public CommonResult batchDelete(@RequestParam("checkIds[]") Integer[] checkIdsStr) {
        try {
            boolean result = commentService.batchDelete(checkIdsStr);
            if (result) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "系统异常");
            }
        } catch (CgszlException e) {
            e.printStackTrace();
        }
        return CommonResult.fail(false, "系统异常");
    }

    /**
     * 已读留言
     *
     * @param comment 留言
     * @return
     */
    @RequestMapping(value = "/admin/message/alreadyRead")
    @ResponseBody
    @SystemLog(module = "留言管理模块", methods = "留言已读")
    public CommonResult alreadyReadById(@RequestBody Comment comment) {
        try {
            commentService.alreadyRead(comment);
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 根据留言标识删除留言
     *
     * @param coid 留言标识
     * @return
     */
    @RequestMapping(value = "/admin/message/deleteById")
    @ResponseBody
    @SystemLog(module = "留言管理模块", methods = "删除留言")
    public CommonResult deleteById(Integer coid) {
        try {
            commentService.deleteCommenntByid(coid);
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }
}
