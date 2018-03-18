package cn.cgszl.common.dao.dto;

import cn.cgszl.common.dao.pojo.Metas;

/**
 * metas数据传输对象
 *
 * @author cguisheng 2018/3/18 12:20
 */
public class MetasDto extends Metas {

    // 记录分类下所有文章数量
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MetasDto(Integer mid, String name, String slug, String type, String description, Integer sort, Integer parent) {
        super(mid, name, slug, type, description, sort, parent);
    }
}
