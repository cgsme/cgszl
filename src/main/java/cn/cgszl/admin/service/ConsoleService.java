package cn.cgszl.admin.service;

import cn.cgszl.common.dao.dto.Statistics;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 控制台业务接口
 *
 * @author cguisheng 2018/5/15 1:03
 */
public interface ConsoleService {

    /**
     * 获取最新文章
     *
     * @return
     * @throws CgszlException
     */
    List<Article> getRecentArticle() throws CgszlException;

    /**
     * 获取最新评论、留言
     *
     * @param type 类型：评论、留言
     * @return
     * @throws CgszlException
     */
    List<Comment> getrecentComment(String type) throws CgszlException;

    /**
     * 获取统计数据
     *
     * @return
     * @throws CgszlException
     */
    Statistics getStatistics() throws CgszlException;
}
