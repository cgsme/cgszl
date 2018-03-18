package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.CategoryService;
import cn.cgszl.common.constant.WebConst;
import cn.cgszl.common.dao.dto.MetasDto;
import cn.cgszl.common.dao.mapper.MetasMapper;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.dao.pojo.MetasExample;
import cn.cgszl.common.exception.CgszlException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分类管理业务接口实现类
 *
 * @author cguisheng 2018/2/26 15:50
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {


    @Resource
    private MetasMapper metasMapper;

    /**
     * 获取所有分类
     *
     * @return 分类集合
     */
    @Override
    public List<MetasDto> getAllCategoryList(String type, String orderBy) throws CgszlException {
//        MetasExample metasExample = new MetasExample();
//        metasExample.createCriteria().andTypeEqualTo("category");
        if (StringUtils.isNotBlank(type)) {
            if (StringUtils.isBlank(orderBy)) {
                orderBy = "count desc, a.mid desc";
            }
            Map<String, Object> paramType = new HashMap<String, Object>();
            paramType.put("type", type);
            paramType.put("order", orderBy);
            return metasMapper.selectFormSql(paramType);
        }
        return null;
    }

    /**
     * 获取所有标签
     *
     * @return 标签集合
     * @throws CgszlException 自定义系统异常
     */
    @Override
    public List<Metas> getAllTagList() throws CgszlException {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andTypeEqualTo("tag");
        return metasMapper.selectByExample(metasExample);
    }

    /**
     * 验证分类名称唯一性
     *
     * @param name 分类名称
     * @return 是否存在同名分类
     * @throws CgszlException 自定义系统异常
     */
    @Override
    public boolean checkCatName(String name) throws CgszlException {
        MetasExample metasExample = new MetasExample();
        metasExample.createCriteria().andNameEqualTo(name);
        return metasMapper.selectByExample(metasExample).size() > 0;
    }

    /**
     * 新增分类
     *
     * @param metas 分类对象
     * @return
     * @throws CgszlException
     */
    @Override
    public boolean addCategory(Metas metas) throws CgszlException {
        metas.setType("category");
        metas.setSort(0);
        metas.setParent(0);
        return metasMapper.insertSelective(metas) > 0;
    }

    /**
     * 根据分类标识删除分类
     *
     * @param mid 分类标识
     * @return 是否删除成功
     * @throws CgszlException 自定义系统异常
     */
    @Override
    public boolean deleteByMid(String mid) throws CgszlException {
        // TODO 重置对应分类的分类类型
        return metasMapper.deleteByPrimaryKey(Integer.parseInt(mid)) > 0;
    }

    /**
     * 更新分类
     *
     * @param metas 分类对象
     * @return 更新结果
     * @throws CgszlException 自定义系统异常
     */
    @Override
    public boolean updataCategory(Metas metas) throws CgszlException {
        return metasMapper.updateByPrimaryKeySelective(metas) > 0;
    }
}
