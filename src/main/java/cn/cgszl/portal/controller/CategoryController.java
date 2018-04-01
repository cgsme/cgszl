package cn.cgszl.portal.controller;

import cn.cgszl.admin.service.CategoryService;
import cn.cgszl.common.CommonResult;
import cn.cgszl.common.dao.pojo.Metas;
import cn.cgszl.common.exception.CgszlException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类控制器
 *
 * @author cguisheng 2018/4/1 15:21
 */
@Controller(value = "portalCategoryController")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/portal/listHotCategories")
    @ResponseBody
    public CommonResult listHotCategories(Integer page, Integer limit) {
        List<Metas> categoriesList = null;
        try {
            categoriesList = categoryService.listHotCategories(page, limit);
        } catch (CgszlException e) {
            e.printStackTrace();
            return CommonResult.fail(false, "系统异常");
        }
        return CommonResult.ok(true, categoriesList);
    }

}
