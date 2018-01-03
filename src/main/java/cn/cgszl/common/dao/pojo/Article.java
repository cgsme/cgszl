package cn.cgszl.common.dao.pojo;

public class Article {
    private Integer aid;

    private String title;

    private String slug;

    private Integer created;

    private Integer modified;

    private Integer authorId;

    private String type;

    private String status;

    private String tags;

    private String categories;

    private Integer hits;

    private Integer commentsNum;

    private Boolean allowComment;

    private Boolean allowPing;

    private Boolean allowFeed;

    private Integer likeNum;

    private Integer unlikeNum;

    private Boolean draft;

    private Boolean deleteFlag;

    private String content;

    public Article(Integer aid, String title, String slug, Integer created, Integer modified, Integer authorId, String type, String status, String tags, String categories, Integer hits, Integer commentsNum, Boolean allowComment, Boolean allowPing, Boolean allowFeed, Integer likeNum, Integer unlikeNum, Boolean draft, Boolean deleteFlag) {
        this.aid = aid;
        this.title = title;
        this.slug = slug;
        this.created = created;
        this.modified = modified;
        this.authorId = authorId;
        this.type = type;
        this.status = status;
        this.tags = tags;
        this.categories = categories;
        this.hits = hits;
        this.commentsNum = commentsNum;
        this.allowComment = allowComment;
        this.allowPing = allowPing;
        this.allowFeed = allowFeed;
        this.likeNum = likeNum;
        this.unlikeNum = unlikeNum;
        this.draft = draft;
        this.deleteFlag = deleteFlag;
    }

    public Article(Integer aid, String title, String slug, Integer created, Integer modified, Integer authorId, String type, String status, String tags, String categories, Integer hits, Integer commentsNum, Boolean allowComment, Boolean allowPing, Boolean allowFeed, Integer likeNum, Integer unlikeNum, Boolean draft, Boolean deleteFlag, String content) {
        this.aid = aid;
        this.title = title;
        this.slug = slug;
        this.created = created;
        this.modified = modified;
        this.authorId = authorId;
        this.type = type;
        this.status = status;
        this.tags = tags;
        this.categories = categories;
        this.hits = hits;
        this.commentsNum = commentsNum;
        this.allowComment = allowComment;
        this.allowPing = allowPing;
        this.allowFeed = allowFeed;
        this.likeNum = likeNum;
        this.unlikeNum = unlikeNum;
        this.draft = draft;
        this.deleteFlag = deleteFlag;
        this.content = content;
    }

    public Article() {
        super();
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories == null ? null : categories.trim();
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    public Boolean getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Boolean allowPing) {
        this.allowPing = allowPing;
    }

    public Boolean getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Boolean allowFeed) {
        this.allowFeed = allowFeed;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getUnlikeNum() {
        return unlikeNum;
    }

    public void setUnlikeNum(Integer unlikeNum) {
        this.unlikeNum = unlikeNum;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}