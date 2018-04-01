package cn.cgszl.portal.controller;

import cn.cgszl.admin.service.CategoryService;
import cn.cgszl.common.GridData;
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
    @RequestMapping(value = "/portal/listHotTags")
    @ResponseBody
    public List<MetasDto> listHotTags() {
        try {
            List<MetasDto> tagList = categoryService.getAllCategoryList(Types.TAG.getType(), "mid desc");
            return tagList;
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }
}
