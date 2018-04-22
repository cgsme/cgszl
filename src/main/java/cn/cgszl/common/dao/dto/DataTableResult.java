package cn.cgszl.common.dao.dto;

import cn.cgszl.common.dao.pojo.Log;

import java.util.List;

/**
 * 数据表格结果对象
 *
 * @author cguisheng 2018/4/20 16:26
 */
public class DataTableResult {

    // 计数器
    private Integer draw;
    // 没有过滤的记录数（数据库里总共记录数）
    private Long recordsTotal;
    // 过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
    private Long recordsFiltered;
    // 数据
    private Object data;
    // 错误信息
    private String error;

    private DataTableResult(Integer sEcho, Long recordsTotal, Long recordsFiltered, Object data) {
        this.draw = sEcho;
        this.recordsFiltered = recordsFiltered;
        this.recordsTotal = recordsTotal;
        this.data = data;
    }

    private DataTableResult(Integer draw, Long total, Object data) {
        this.recordsFiltered = total;
        this.recordsTotal = total;
        this.draw = draw;
        this.data = data;
    }

    private DataTableResult(List<Log> logList) {
        this.data = logList;
    }

    /**
     * 成功响应
     *
     * @param sEcho                操作次数
     * @param iTotalRecords        总记录数
     * @param iTotalDisplayRecords 每页记录数
     * @param data                 数据
     * @return 结果对象
     */
    public static DataTableResult ok(Integer sEcho, Long iTotalRecords, Long iTotalDisplayRecords, Object data) {
        return new DataTableResult(sEcho, iTotalRecords, iTotalDisplayRecords, data);
    }

    /**
     * 响应成功，总记录数与筛选记录数一致
     *
     * @param sEcho 操作次数
     * @param total 总记录数
     * @param data  数据
     * @return
     */
    public static DataTableResult ok(Integer sEcho, Long total, Object data) {
        return new DataTableResult(sEcho, total, data);
    }

    private DataTableResult() {
    }

    private DataTableResult(Integer draw, Long iTotalRecords, Long iTotalDisplayRecords, Object data, String error) {
        this.draw = draw;
        this.recordsTotal = iTotalRecords;
        this.recordsFiltered = iTotalDisplayRecords;
        this.data = data;
        this.error = error;
    }

    @Override
    public String toString() {
        return "DataTableResult{" +
                "sEcho=" + draw +
                ", iTotalRecords=" + recordsTotal +
                ", iTotalDisplayRecords=" + recordsFiltered +
                ", aaData=" + data +
                ", error='" + error + '\'' +
                '}';
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * 响应
     *
     * @param logList 日志列表数据
     * @return
     */
    public static DataTableResult ok(List<Log> logList) {
        return new DataTableResult(logList);
    }
}
