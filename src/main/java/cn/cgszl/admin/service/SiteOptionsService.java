package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.SiteOptions;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;
import java.util.Map;

/**
 * 系统设置业务接口
 *
 * @author cguisheng 2018/5/9 8:37
 */
public interface SiteOptionsService {

    /**
     * 获取所有系统设置选项
     */
    List<SiteOptions> listSiteOptions() throws CgszlException;

    /**
     * 保存系统设置信息
     *
     * @param valueMap 设置信息集合
     * @throws CgszlException
     */
    void saveSiteOptions(Map<String, String> valueMap) throws CgszlException;

    /**
     * 保存站点信息
     *
     * @param key   主键
     * @param value 值
     */
    void insertSiteOptions(String key, String value) throws CgszlException;
}
