package cn.cgszl.common.dao.pojo;

public class UserInfo {
    private Integer id;

    private Integer uid;

    private String tecHobby;

    private String qq;

    private String phone;

    private String sex;

    private String post;

    private String address;

    private String merry;

    private String github;

    private String maxim;

    private String introduction;

    public UserInfo(Integer id, Integer uid, String tecHobby, String qq, String phone, String sex, String post, String address, String merry, String github, String maxim, String introduction) {
        this.id = id;
        this.uid = uid;
        this.tecHobby = tecHobby;
        this.qq = qq;
        this.phone = phone;
        this.sex = sex;
        this.post = post;
        this.address = address;
        this.merry = merry;
        this.github = github;
        this.maxim = maxim;
        this.introduction = introduction;
    }

    public UserInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTecHobby() {
        return tecHobby;
    }

    public void setTecHobby(String tecHobby) {
        this.tecHobby = tecHobby == null ? null : tecHobby.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMerry() {
        return merry;
    }

    public void setMerry(String merry) {
        this.merry = merry == null ? null : merry.trim();
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github == null ? null : github.trim();
    }

    public String getMaxim() {
        return maxim;
    }

    public void setMaxim(String maxim) {
        this.maxim = maxim == null ? null : maxim.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}