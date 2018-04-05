package cn.cgszl.common.dao.pojo;

public class Log {
    private Integer id;

    private String action;

    private String data;

    private Integer operUserId;

    private String ip;

    private String created;

    private Boolean result;

    private Integer processTime;

    private String resultCn;

    private String operUserName;

    private String module;

    public Log(Integer id, String action, String data, Integer operUserId, String ip, String created, Boolean result, Integer processTime, String resultCn, String operUserName, String module) {
        this.id = id;
        this.action = action;
        this.data = data;
        this.operUserId = operUserId;
        this.ip = ip;
        this.created = created;
        this.result = result;
        this.processTime = processTime;
        this.resultCn = resultCn;
        this.operUserName = operUserName;
        this.module = module;
    }

    public Log() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created == null ? null : created.trim();
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Integer processTime) {
        this.processTime = processTime;
    }

    public String getResultCn() {
        return resultCn;
    }

    public void setResultCn(String resultCn) {
        this.resultCn = resultCn == null ? null : resultCn.trim();
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName == null ? null : operUserName.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }
}