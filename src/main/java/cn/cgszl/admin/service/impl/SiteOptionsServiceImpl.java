package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.SiteOptionsService;
import cn.cgszl.common.dao.mapper.SiteOptionsMapper;
import cn.cgszl.common.dao.pojo.SiteOptions;
import cn.cgszl.common.dao.pojo.SiteOptionsExample;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统设置业务接口实现
 *
 * @author cguisheng 2018/5/9 8:37
 */
@Service
@Transactional
public class SiteOptionsServiceImpl implements SiteOptionsService {

    @Resource
    private SiteOptionsMapper siteOptionsMapper;

    /**
     * 获取所有系统设置选项
     *
     * @throws CgszlException
     */
    @Override
    public List<SiteOptions> listSiteOptions() throws CgszlException {
        return siteOptionsMapper.selectByExample(new SiteOptionsExample());
    }

    /**
     * 保存系统是设置信息
     *
     * @param valueMap 设置信息集合
     * @throws CgszlException
     */
    @Override
    public void saveSiteOptions(Map<String, String> valueMap) throws CgszlException {
        for (String key : valueMap.keySet()) {
            this.insertSiteOptions(key, valueMap.get(key));
        }
    }

    /**
     * 保存系统设置信息
     *
     * @param key   主键
     * @param value 值
     * @throws CgszlException
     */
    @Override
    public void insertSiteOptions(String key, String value) throws CgszlException {
        SiteOptions siteOptions = siteOptionsMapper.selectByPrimaryKey(key);
        // 判断是否存在，不存在则新增，存在则更新
        if (null == siteOptionsMapper.selectByPrimaryKey(key)) {
            siteOptions = new SiteOptions();
            siteOptions.setName(key);
            siteOptions.setValue(value);
            siteOptionsMapper.insertSelective(siteOptions);
        } else {
            siteOptions.setValue(value);
            siteOptionsMapper.updateByPrimaryKeySelective(siteOptions);
        }
    }
}
