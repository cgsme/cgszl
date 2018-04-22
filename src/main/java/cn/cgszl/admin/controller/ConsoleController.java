package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.LogService;
import cn.cgszl.common.dao.dto.DataTableResult;
import cn.cgszl.common.dao.pojo.Log;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制台控制器
 *
 * @author cguisheng 2018/3/21 9:57
 */
@Controller
public class ConsoleController {

    @Resource
    private LogService logService;

    /**
     * 获取操作日志列表
     *
     * @param sEcho          操作次数
     * @param iDisplayStart  数据起始位子
     * @param iDisplayLength 每页大小
     * @return
     */
    /*@RequestMapping("/admin/log/listLog")
    @ResponseBody
    public DataTableResult listLog(Integer sEcho, Integer iDisplayStart, Integer iDisplayLength,
                                   String sSortDir_0, String sSearch) {
//        sEcho = sEcho + 1;
        // 计算页码
        iDisplayStart = (iDisplayStart / iDisplayLength) + 1;
        PageInfo<Log> logPageInfo = logService.listLog(iDisplayStart, iDisplayLength);
        return DataTableResult.ok(sEcho, logPageInfo.getTotal(), logPageInfo.getList());
    }*/

    /**
     * 获取操作日志列表
     *
     * @return
     */
    @RequestMapping("/admin/log/listLog")
    @ResponseBody
    public DataTableResult listLogClient() {
        List<Log> logPageInfo = logService.listLog();
        return DataTableResult.ok(logPageInfo);
    }

}
