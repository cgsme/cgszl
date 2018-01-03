package cn.cgszl.common.dao.pojo;

public class RelationshipKey {
    private Integer aid;

    private Integer mid;

    public RelationshipKey(Integer aid, Integer mid) {
        this.aid = aid;
        this.mid = mid;
    }

    public RelationshipKey() {
        super();
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}