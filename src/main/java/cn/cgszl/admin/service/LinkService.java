package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 友情链接业务接口
 *
 * @author cguisheng 2018/3/19 11:16
 */
public interface LinkService {

    /**
     * 获取所有友情链接
     *
     * @return
     */
    List<Metas> getAllLinkList() throws CgszlException;

    /**
     * 根据链接标题查询链接
     *
     * @param name
     * @return
     */
    List<Metas> getLinkByName(String name) throws CgszlException;

    /**
     * 保存链接
     *
     * @param metas
     * @return
     */
    boolean SaveLink(Metas metas) throws CgszlException;

    /**
     * 根据链接标识删除链接
     *
     * @param mid
     * @return
     */
    boolean deleteByMid(int mid) throws CgszlException;
}
