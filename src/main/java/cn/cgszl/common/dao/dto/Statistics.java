package cn.cgszl.common.dao.dto;

/**
 * 统计数据实体类
 *
 * @author cguisheng 2018/5/15 1:22
 */
public class Statistics {

    // 文章数
    private Long articleNum;
    // 评论数
    private Long commentNum;
    // 留言数
    private Long messageNum;
    //友链数
    private Long linkNum;
    // 附件数
    private Long attachNum;
    // 照片数
    private Long pictureNum;

    public Long getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Long articleNum) {
        this.articleNum = articleNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Long getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Long messageNum) {
        this.messageNum = messageNum;
    }

    public Long getLinkNum() {
        return linkNum;
    }

    public void setLinkNum(Long linkNum) {
        this.linkNum = linkNum;
    }

    public Long getAttachNum() {
        return attachNum;
    }

    public void setAttachNum(Long attachNum) {
        this.attachNum = attachNum;
    }

    public Long getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(Long pictureNum) {
        this.pictureNum = pictureNum;
    }
}
