package cn.cgszl.portal.controller;

import cn.cgszl.common.service.CategoryService;
import cn.cgszl.common.dao.dto.MetasDto;
import cn.cgszl.common.dao.dto.Types;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页控制器
 *
 * @author cguisheng 2017/10/30 21:35
 */
@Controller
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/index")
    public String index() {
        return "portal/index";
    }

    /**
     * 获取热门标签
     *
     * @return
     */
    @RequestMapping(value = "/portal/index/listHotTags")
    @ResponseBody
    public List<MetasDto> listHotTags(Integer page, Integer limit) {
        try {
            List<MetasDto> tagList = categoryService.listHotTags(page, limit);
            return tagList;
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }
}
