package cn.cgszl.common.service;

import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
    Attach sava(String fileName, String ftype, String fkey, Integer userId) throws CgszlException;

    /**
     * 获取附件信息列表
     *
     * @param page  页码
     * @param limit 页面记录数
     * @return 分页信息对象
     * @throws CgszlException 系统异常
     */
    PageInfo<Attach> listAttach(Integer page, Integer limit) throws CgszlException;

    /**
     * 根据附件标识删除附件
     *
     * @param id 附件标识
     * @return 删除结果
     * @throws CgszlException 系统异常
     */
    boolean deleteById(Integer id) throws CgszlException;

    /**
     * 根据附件标识获取附件信息
     *
     * @param id 附件标识
     * @return 附件对象
     * @throws CgszlException 系统异常
     */
    Attach getAttachById(Integer id) throws CgszlException;

    /**
     * 根据文件名获取文件
     *
     * @param fileName 文件名称
     * @return
     */
    List<Attach> getAttachByName(String fileName) throws CgszlException;
}
