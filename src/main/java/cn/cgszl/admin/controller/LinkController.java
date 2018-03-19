package cn.cgszl.admin.controller;

import cn.cgszl.admin.service.LinkService;
import cn.cgszl.common.CommonResult;
import cn.cgszl.common.GridData;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.exception.CgszlException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友情链接管理控制器
 *
 * @author cguisheng 2018/3/18 23:47
 */
@Controller
public class LinkController {

    @Resource
    private LinkService linkService;

    /**
     * 显示友情链接列表
     *
     * @return
     */
    @RequestMapping("/admin/linkmanager")
    public String linkManager() {
        return "admin/linkmanager";
    }

    /**
     * 获取所有友情链接
     *
     * @return
     */
    @RequestMapping("/admin/getAllLinkList")
    @ResponseBody
    public GridData getAllLinkList(int page, int limit) {
        try {
            PageHelper pageHelper = new PageHelper();
            pageHelper.startPage(page, limit);
            List<Metas> linkList = linkService.getAllLinkList();
            PageInfo<Metas> metasPageInfo = new PageInfo<Metas>(linkList);
            return GridData.build(linkList, metasPageInfo.getTotal());
        } catch (CgszlException e) {
            // TODO 异常处理
            return null;
        }
    }

    /**
     * 根据链接名称获取链接信息
     *
     * @param name 链接名称
     * @return 通用对象
     */
    @RequestMapping(value = "/admin/checkLinkName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult checkLinkName(String name) {
        List<Metas> metasList = null;
        try {
            metasList = linkService.getLinkByName(name);
            if (null != metasList && !metasList.isEmpty()) {
                return CommonResult.fail(false, "链接标题已存在！");
            }
            return CommonResult.ok();
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 保存链接
     *
     * @param metas 链接对象
     * @return
     */
    @RequestMapping(value = "/admin/saveLink", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult saveLink(Metas metas) {
        try {
            boolean result = linkService.SaveLink(metas);
            if (result) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "保存失败");
            }
        } catch (CgszlException e) {
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 根据链接标识删除链接
     *
     * @param mid 链接标识
     * @return
     */
    @RequestMapping(value = "/admin/deleteByMid")
    @ResponseBody
    public CommonResult deleteLinkById(int mid) {
        boolean result = false;
        try {
            result = linkService.deleteByMid(mid);
        } catch (CgszlException e) {
            e.printStackTrace();
            result = false;
        }
        if (result) {
            return CommonResult.ok();
        } else {
            return CommonResult.fail(false, "删除失败");
        }
    }
}
