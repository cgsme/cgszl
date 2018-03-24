package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.AttachService;
import cn.cgszl.common.dao.mapper.AttachMapper;
import cn.cgszl.common.dao.pojo.Attach;
import cn.cgszl.common.utils.DateKit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
