package cn.cgszl.common.dao.dto;

/**
 * metas类别
 *
 * @author cguisheng 2018/2/23 21:49
 */
public enum Types {
    TAG("tag"),
    CATEGORY("category"),
    ARTICLE("post"),
    PUBLISH("publish"),
    PAGE("page"),
    DRAFT("draft"),
    LINK("link"),
    // 留言
    MESSAGE("message"),
    // 评论
    COMMENT("comment"),
    IMAGE("image"),
    FILE("file"),
    PHOTOFILE("photoFile"),
    ATTACHFILE("attachFile"),
    SQLFILE("sqlFile"),
    CSRF_TOKEN("csrf_token"),
    COMMENTS_FREQUENCY("comments:frequency"),

    /**
     * 附件存放的URL，默认为网站地址，如集成第三方则为第三方CDN域名
     */
    ATTACH_URL("attach_url"),

    /**
     * 网站要过滤，禁止访问的ip列表
     */
    BLOCK_IPS("site_block_ips");


    private String type;

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    Types(java.lang.String type) {
        this.type = type;
    }
}
