package cn.cgszl.common.dao.pojo;

public class Comment {
    private Integer coid;

    private Integer aid;

    private Integer created;

    private String author;

    private Integer authorId;

    private Integer ownerId;

    private String mail;

    private String url;

    private String ip;

    private String agent;

    private String type;

    private String status;

    private Integer parent;

    private String content;

    public Comment(Integer coid, Integer aid, Integer created, String author, Integer authorId, Integer ownerId, String mail, String url, String ip, String agent, String type, String status, Integer parent) {
        this.coid = coid;
        this.aid = aid;
        this.created = created;
        this.author = author;
        this.authorId = authorId;
        this.ownerId = ownerId;
        this.mail = mail;
        this.url = url;
        this.ip = ip;
        this.agent = agent;
        this.type = type;
        this.status = status;
        this.parent = parent;
    }

    public Comment(Integer coid, Integer aid, Integer created, String author, Integer authorId, Integer ownerId, String mail, String url, String ip, String agent, String type, String status, Integer parent, String content) {
        this.coid = coid;
        this.aid = aid;
        this.created = created;
        this.author = author;
        this.authorId = authorId;
        this.ownerId = ownerId;
        this.mail = mail;
        this.url = url;
        this.ip = ip;
        this.agent = agent;
        this.type = type;
        this.status = status;
        this.parent = parent;
        this.content = content;
    }

    public Comment() {
        super();
    }

    public Integer getCoid() {
        return coid;
    }

    public void setCoid(Integer coid) {
        this.coid = coid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
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

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}