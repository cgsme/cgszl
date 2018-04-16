package cn.cgszl.common.service.impl;

import cn.cgszl.common.dao.pojo.Article;
import cn.cgszl.common.service.BlogService;
import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.dao.mapper.CommentMapper;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.dao.pojo.CommentExample;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.utils.CgszlUtils;
import cn.cgszl.common.utils.DateKit;
import com.github.pagehelper.PageHelper;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论业务接口实现类
 *
 * @author cguisheng 2018/4/2 11:29
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private BlogService blogService;

    /**
     * 评论列表
     *
     * @param page
     * @param limit
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Comment> listComments(Integer page, Integer limit) throws CgszlException {
        PageHelper.startPage(page, limit);
        return commentMapper.selectByExampleWithBLOBs(new CommentExample());
    }

    /**
     * 根据评论标识删除评论
     *
     * @param coid 评论标识
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean deleteCommenntByid(Integer coid) throws CgszlException {
        return commentMapper.deleteByPrimaryKey(coid) > 0;
    }

    /**
     * 更新评论状态
     *
     * @param coid   评论标识
     * @param status 状态
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean updateCommentStatus(Integer coid, String status) throws CgszlException {
        Comment comment = commentMapper.selectByPrimaryKey(coid);
        comment.setStatus(status);
        return commentMapper.updateByPrimaryKeySelective(comment) > 0;
    }

    /**
     * 保存评论
     *
     * @param comment 评论对象
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean saveComment(Comment comment) throws CgszlException {

        if (null != comment) {
            // 防止xss注入
            comment.setContent(CgszlUtils.cleanXSS(comment.getContent()));
            comment.setAuthor(CgszlUtils.cleanXSS(comment.getAuthor()));
            // 解析emoji表情
            comment.setContent(EmojiParser.parseToAliases(comment.getContent()));
            comment.setAuthor(EmojiParser.parseToAliases(comment.getAuthor()));

            // 评论时间
            comment.setCreated(DateKit.getCurrentUnixTime());

            // 保存评论
            boolean result = commentMapper.insertSelective(comment) > 0;
            // 获取被评论的文章
            Article article = blogService.getArticleById(comment.getAid());
            // 文章评论数加 1
            article.setCommentsNum(article.getCommentsNum() + 1);
            // 更新文章评论数
            blogService.savePost(article);
            return result;
        }
        return false;
    }

    /**
     * 获取所有评论
     *
     * @param aid 文章标识
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Comment> selectByArticleKey(Integer aid) throws CgszlException {
        return commentMapper.selectByArticleKey(aid);
    }

    /**
     * 获取评论列表（无回复的评论）
     *
     * @param aid 文章标识
     * @return
     * @throws CgszlException
     */
    @Override
    public List<Comment> listCommentsByAid(Integer aid) throws CgszlException {
        return commentMapper.listCommentsByAid(aid);
    }
}
