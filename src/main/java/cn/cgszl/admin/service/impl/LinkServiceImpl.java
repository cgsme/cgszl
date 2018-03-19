package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.LinkService;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.dao.mapper.MetasMapper;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.dao.pojo.MetasExample;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情链接业务接口实现类
 *
 * @author cguisheng 2018/3/19 11:22
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Resource
    private MetasMapper metasMapper;

    /**
     * 获取所有友情链接
     *
     * @return
     */
    @Override
    public List<Metas> getAllLinkList() throws CgszlException {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andTypeEqualTo(Types.LINK.getType());
        metasExample.setOrderByClause("sort asc");
        return metasMapper.selectByExample(metasExample);
    }

    /**
     * 根据标签名获取标签
     *
     * @param name 标签名
     * @return
     */
    @Override
    public List<Metas> getLinkByName(String name) {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andNameEqualTo(name);
        return metasMapper.selectByExample(metasExample);
    }

    /**
     * 保存链接
     *
     * @param metas 链接对象
     * @return
     */
    @Override
    public boolean SaveLink(Metas metas) throws CgszlException {
        // 默认排序为0
        if (metas.getSort() == null) {
            metas.setSort(0);
        }
        metas.setType(Types.LINK.getType());
        // 不存在Mid则为新增，否则为更新
        if (metas.getMid() != null) {
            return metasMapper.updateByPrimaryKeySelective(metas) > 0;
        } else {
            return metasMapper.insert(metas) > 0;
        }
    }

    /**
     * 根据链接标识删除链接
     *
     * @param mid 链接标识
     * @return
     */
    @Override
    public boolean deleteByMid(int mid) {
        return metasMapper.deleteByPrimaryKey(mid) > 0;
    }

    /**
     * 根据链接标识获取连接
     *
     * @param mid 链接标识
     * @param name
     * @return
     */
    @Override
    public List<Metas> getLinkByMid(Integer mid, String name) {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andNameEqualTo(name).andMidNotEqualTo(mid);
        return metasMapper.selectByExample(metasExample);
    }
}
