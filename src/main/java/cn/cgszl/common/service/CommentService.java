package cn.cgszl.common.service;

import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 评论业务接口
 *
 * @author cguisheng 2018/4/2 11:28
 */
public interface CommentService {

    /**
     * 获取所有评论
     *
     * @param page  页码
     * @param limit 每页记录数
     * @return
     */
    List<Comment> listComments(Integer page, Integer limit) throws CgszlException;

    /**
     * 根据评论标识删除评论
     *
     * @param coid 评论标识
     * @return
     * @throws CgszlException
     */
    boolean deleteCommenntByid(Integer coid) throws CgszlException;

    /**
     * 更新评论的状态
     *
     * @param coid   评论标识
     * @param status 状态
     * @return
     * @throws CgszlException
     */
    boolean updateCommentStatus(Integer coid, String status) throws CgszlException;
}
