package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageInfo;

/**
 * 附件管理业务接口
 *
 * @author cguisheng 2018/3/24 10:16
 */
public interface AttachService {

    /**
     * 保存附件信息
     *
     * @param fileName 文件名
     * @param ftype    文件类型
     * @param fkey     文件相对路径
     * @param userId   用户标识
     * @return
     */
    boolean sava(String fileName, String ftype, String fkey, Integer userId) throws CgszlException;

    /**
     * 获取附件信息列表
     * @param page 页码
     * @param limit 页面记录数
     * @return
     * @throws CgszlException
     */
    PageInfo<Attach> listAttach(Integer page, Integer limit) throws CgszlException;

    /**
     * 根据附件标识删除附件
     * @param id 附件标识
     * @return
     * @throws CgszlException
     */
    boolean deleteById(Integer id) throws CgszlException;
}
