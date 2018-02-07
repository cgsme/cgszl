package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Metas;

import java.util.List;

/**
 * 元数据业务接口
 * @author cguisheng 2018/2/3 11:34
 */
public interface MetasService {

    /**
     * 获取所有分类
     * @return
     */
    List<Metas> getAllCatList();
}
