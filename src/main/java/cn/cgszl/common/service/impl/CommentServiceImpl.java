package cn.cgszl.common.service.impl;

import cn.cgszl.common.service.CommentService;
import cn.cgszl.common.dao.mapper.CommentMapper;
import cn.cgszl.common.dao.pojo.Comment;
import cn.cgszl.common.dao.pojo.CommentExample;
import cn.cgszl.common.exception.CgszlException;
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
}
