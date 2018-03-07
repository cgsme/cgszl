package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 分类管理业务接口
 *
 * @author cguisheng 2018/2/26 15:49
 */
public interface CategoryService {

    /**
     * 获取所有分类
     *
     * @return 分类集合
     */
    List<Metas> getAllCategoryList() throws CgszlException;

    /**
     * 获取所有标签
     *
     * @return 标签集合
     * @throws CgszlException 自定义系统异常
     */
    List<Metas> getAllTagList() throws CgszlException;

    /**
     * 验证分了名称唯一性
     *
     * @param name 分类名称
     * @return 是否存在
     */
    boolean checkCatName(String name) throws CgszlException;

    /**
     * 新增分类
     *
     * @param name 分类名称
     * @return 是否新增成功
     * @throws CgszlException 自定义系统异常
     */
    boolean addCategory(Metas name) throws CgszlException;

    /**
     * 根据分类标识删除分类
     *
     * @param mid 分类标识
     * @return 是否删除成功
     * @throws CgszlException 自定义系统异常
     */
    boolean deleteByMid(String mid) throws CgszlException;

    /**
     * 更新分类
     *
     * @param metas 分类对象
     * @return
     * @throws CgszlException
     */
    boolean updataCategory(Metas metas) throws CgszlException;
}
