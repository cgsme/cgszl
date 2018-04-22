package cn.cgszl.admin.service.impl;

import cn.cgszl.admin.service.LogService;
import cn.cgszl.common.dao.mapper.LogMapper;
import cn.cgszl.common.dao.pojo.Log;
import cn.cgszl.common.dao.pojo.LogExample;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     *
     * @param log 日志对象
     * @throws CgszlException
     */
    @Override
    public void saveLog(Log log) throws CgszlException {
        logMapper.insert(log);
    }

    /**
     * 获取日志列表
     *
     * @return 日志列表
     */
    @Override
    public List<Log> listLog() {
        LogExample logExample = new LogExample();
        logExample.setOrderByClause("created desc");
        return logMapper.selectByExample(logExample);
    }
}
