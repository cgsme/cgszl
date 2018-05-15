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
    List<Comment> listComments(Integer page, Integer limit, String type) throws CgszlException;

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

    /**
     * 保存评论
     *
     * @param comment 评论对象
     * @throws CgszlException
     */
    boolean saveComment(Comment comment) throws CgszlException;

    /**
     * 获取所有评论
     *
     * @param aid 文章标识
     * @return
     * @throws CgszlException
     */
    List<Comment> selectByArticleKey(Integer aid) throws CgszlException;

    /**
     * 根据文章标识获取文章列表（无回复的评论）
     *
     * @param aid 文章标识
     * @return
     * @throws CgszlException
     */
    List<Comment> listCommentsByAid(Integer aid) throws CgszlException;

    /**
     * 留言 批量标记为已读
     *
     * @param checkIds 选中的留言标识
     * @return
     */
    boolean batchRead(List<Comment> checkIds) throws CgszlException;

    /**
     * 批量删除
     *
     * @param checkIds 选中的留言标识
     * @return
     * @throws CgszlException
     */
    boolean batchDelete(Integer[] checkIds) throws CgszlException;

    /**
     * 留言已读
     *
     * @param comment 留言
     * @throws CgszlException
     */
    void alreadyRead(Comment comment) throws CgszlException;

    /**
     * 根据类型统计数量
     *
     * @param type 类型：评论、留言
     * @return
     * @throws CgszlException
     */
    Long countByType(String type) throws CgszlException;
}
