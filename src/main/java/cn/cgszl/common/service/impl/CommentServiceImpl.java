package cn.cgszl.common.service.impl;

import cn.cgszl.common.dao.dto.States;
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
import org.apache.commons.lang3.StringUtils;
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
    public List<Comment> listComments(Integer page, Integer limit, String type) throws CgszlException {
        PageHelper.startPage(page, limit);
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTypeEqualTo(type);
        return commentMapper.selectByExampleWithBLOBs(commentExample);
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
            // 非空校验
            if (StringUtils.isNotBlank(comment.getContent())) {
                // 防止xss注入
                comment.setContent(CgszlUtils.cleanXSS(comment.getContent()));
                // 解析emoji表情
                comment.setContent(EmojiParser.parseToAliases(comment.getContent()));
            }
            if (StringUtils.isNotBlank(comment.getAuthor())) {
                comment.setAuthor(EmojiParser.parseToAliases(comment.getAuthor()));
                comment.setAuthor(CgszlUtils.cleanXSS(comment.getAuthor()));
            }
            if (StringUtils.isBlank(comment.getAuthor())) {
                comment.setAuthor("热心网友" + DateKit.getCurrentUnixTime());
            }
            // 评论时间
            comment.setCreated(DateKit.getCurrentUnixTime());
            // 保存评论
            boolean result = commentMapper.insertSelective(comment) > 0;
            if (null != comment.getAid()) {
                // 获取被评论的文章
                Article article = blogService.getArticleById(comment.getAid());
                // 文章评论数加 1
                article.setCommentsNum(article.getCommentsNum() + 1);
                // 更新文章评论数
                blogService.savePost(article);
            }
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

    /**
     * 留言批量标记为已读
     *
     * @param checkIds 选中的留言标识
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean batchRead(List<Comment> checkIds) throws CgszlException {
        boolean result = false;
        for (Comment comment : checkIds) {
            comment.setStatus(States.COMMENT_STATE_APPROVED.getState());
            result = commentMapper.updateByPrimaryKeySelective(comment) > 0;
            if (!result) {
                return false;
            }
        }
        return result;
    }

    /**
     * 批量删除
     *
     * @param checkIds 选中的留言标识
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean batchDelete(Integer[] checkIds) throws CgszlException {
        boolean result = false;
        for (Integer id : checkIds) {
            result = commentMapper.deleteByPrimaryKey(id) > 0;
            if (!result) {
                return false;
            }
        }
        return result;
    }

    /**
     * 留言已读
     *
     * @param comment 留言
     * @throws CgszlException
     */
    @Override
    public void alreadyRead(Comment comment) throws CgszlException {
        if (org.springframework.util.StringUtils.isEmpty(comment)) {
            throw new CgszlException("操作失败");
        }
        comment.setStatus(States.COMMENT_STATE_APPROVED.getState());
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    /**
     * 根据类型统计数量
     *
     * @param type 类型：评论、留言
     * @return
     * @throws CgszlException
     */
    @Override
    public Long countByType(String type) throws CgszlException {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTypeEqualTo(type);
        return commentMapper.countByExample(commentExample);
    }
}
