package cn.cgszl.common.dao.dto;

/**
 * 评论、留言状态枚举类
 *
 * @author cguisheng 2018/5/12 15:04
 */
public enum States {

    // 评论相关状态
    COMMENT_STATE_APPROVED("approved"),
    COMMENT_STATE_NOT_AUDIT("not_audit"),
    COMMENT_STATE_UN_PASS("unpass"),

    ARTICLE_STATE_PUBLISH("publish"),
    ARTICLE_STATE_UN_PUBLISH("unpublish");


    public String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    States(String state) {
        this.state = state;
    }
}
