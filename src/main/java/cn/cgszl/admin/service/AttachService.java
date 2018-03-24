package cn.cgszl.admin.service;

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
    boolean sava(String fileName, String ftype, String fkey, Integer userId);
}
