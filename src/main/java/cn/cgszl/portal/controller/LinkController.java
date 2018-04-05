package cn.cgszl.portal.controller;

import cn.cgszl.common.dao.dto.CommonResult;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.exception.CgszlException;
import cn.cgszl.common.service.LinkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台友情链接控制器
 *
 * @author cguisheng 2018/4/5 20:20
 */
@Controller("portalLinkController")
public class LinkController {

    @Resource
    private LinkService linkService;

    /**
     * 获取友情链接列表
     *
     * @return
     */
    @RequestMapping(value = "/portal/link/listLinksList")
    @ResponseBody
    public CommonResult listLinksList() {
        try {
            List<Metas> linkList = linkService.getAllLinkList();
            return CommonResult.ok(linkList);
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
    }
}
