package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.ConsoleService;
import cn.cgszl.common.dao.dto.States;
import cn.cgszl.common.dao.dto.Statistics;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.service.AttachService;
import cn.cgszl.common.service.BlogService;
import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.service.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制台业务接口实现类
 *
 * @author cguisheng 2018/5/15 1:07
 */
@Service
@Transactional
public class ConsoleServiceImpl implements ConsoleService {

    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    @Resource
    private LinkService linkService;

    @Resource
    private AttachService attachService;

    /**
     * 获取最新文章
     *
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Article> getRecentArticle() throws CgszlException {
        return blogService.getBlogList(1, 5).getList();
    }

    /**
     * 获取最新评论、留言
     *
     * @param type 类型：评论、留言
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Comment> getrecentComment(String type) throws CgszlException {
        return commentService.listComments(1, 5, type);
    }

    /**
     * 获取统计数据
     *
     * @return
     * @throws CgszlException
     */
    @Override
    public Statistics getStatistics() throws CgszlException {
        Statistics statistics = new Statistics();
        Long articleNum = blogService.countArticle(States.ARTICLE_STATE_PUBLISH.getState());
        Long commentNum = commentService.countByType(Types.COMMENT.getType());
        Long messageNum = commentService.countByType(Types.MESSAGE.getType());
        Long linkNum = linkService.countLink();
        Long pictureNum = attachService.countPicture();
        statistics.setArticleNum(articleNum);
        statistics.setCommentNum(commentNum);
        statistics.setMessageNum(messageNum);
        statistics.setLinkNum(linkNum);
        statistics.setPictureNum(pictureNum);
        return statistics;
    }
}
