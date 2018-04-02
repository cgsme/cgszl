package cn.cgszl.common.service.impl;

import cn.cgszl.common.service.AttachService;
import cn.cgszl.common.dao.mapper.AttachMapper;
import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.dao.pojo.AttachExample;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.utils.DateKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 附件管理业务接口
 *
 * @author cguisheng 2018/3/24 10:16
 */
@Service
@Transactional
public class AttachServiceImpl implements AttachService {

    @Resource
    private AttachMapper attachMapper;

    /***
     * 保存附件信息
     * @param fileName 文件名
     * @param ftype 文件类型
     * @param fkey  文件相对路径
     * @param userId    用户标识
     * @return
     */
    @Override
    public boolean sava(String fileName, String ftype, String fkey, Integer userId) {
        Attach attach = new Attach();
        attach.setFname(fileName);
        attach.setFtype(ftype);
        attach.setFkey(fkey);
        attach.setAuthorId(userId);
        // 设置当前时间
        attach.setCreated(DateKit.getCurrentUnixTime());
        attachMapper.insertSelective(attach);
        return false;
    }

    /**
     * 获取附件信息
     *
     * @param page  页码
     * @param limit 页面记录数
     * @return
     * @throws CgszlException
     */
    @Override
    public PageInfo<Attach> listAttach(Integer page, Integer limit) throws CgszlException {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 500;
        }
        // 开启分页
        PageHelper.startPage(page, limit);
        AttachExample attachExample = new AttachExample();
        attachExample.setOrderByClause("id desc");
        List<Attach> attaches = attachMapper.selectByExample(attachExample);
        return new PageInfo<Attach>(attaches);
    }

    /**
     * 根据附件标识删除附件
     *
     * @param id 附件标识
     * @return 删除结果
     * @throws CgszlException 系统异常
     */
    @Override
    public boolean deleteById(Integer id) throws CgszlException {
        return attachMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 根据附件标识获取附件信息
     *
     * @param id 附件标识
     * @return
     * @throws CgszlException
     */
    @Override
    public Attach getAttachById(Integer id) throws CgszlException {
        return attachMapper.selectByPrimaryKey(id);
    }
}
