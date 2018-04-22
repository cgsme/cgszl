package cn.cgszl.admin.service;

import cn.cgszl.common.dao.pojo.Log;
import cn.cgszl.common.exception.CgszlException;

import java.util.List;

/**
 * 日志业务接口
 *
 * @author cguisheng 2018/4/5 16:16
 */
public interface LogService {

    /**
     * 保存日志
     *
     * @param log 日志对象
     * @throws CgszlException
     */
    void saveLog(Log log) throws CgszlException;

    /**
     * 获取日志列表
     *
     * @return
     */
    List<Log> listLog();
}
