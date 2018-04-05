package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.LogService;
import cn.cgszl.common.dao.mapper.LogMapper;
import cn.cgszl.common.dao.pojo.Log;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日志业务接口实现类
 *
 * @author cguisheng 2018/4/5 16:41
 */
@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    /**
     * 保存日志
     * @param log 日志对象
     * @throws CgszlException
     */
    @Override
    public void saveLog(Log log) throws CgszlException {
        logMapper.insert(log);
    }
}
