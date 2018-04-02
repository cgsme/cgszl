package cn.cgszl.common.service.impl;

import cn.cgszl.common.service.MetasService;
import cn.cgszl.common.dao.mapper.MetasMapper;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.dao.pojo.MetasExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 元数据业务接口实现类
 * @author cguisheng 2018/2/3 11:35
 */
@Service
@Transactional
public class MetasServiceImpl implements MetasService {

    // 注入mapper
    @Resource
    private MetasMapper metasMapper;

    @Override
    public List<Metas> getAllCatList() {
        MetasExample metasExample = new MetasExample();
        // 只查询类别为category的分类
        metasExample.createCriteria().andTypeEqualTo("category");
        List<Metas> metasList = metasMapper.selectByExample(metasExample);
        return metasList;
    }
}
