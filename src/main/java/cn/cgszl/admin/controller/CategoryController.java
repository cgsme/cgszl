package cn.cgszl.admin.controller;

import cn.cgszl.common.service.CategoryService;
import cn.cgszl.common.dto.CommonResult;
import cn.cgszl.common.dto.GridData;
import cn.cgszl.common.dao.dto.MetasDto;
import cn.cgszl.common.dao.dto.Types;
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
 * 分类/标签管理控制器
 *
 * @author cguisheng 2018/2/23 21:49
 */
@Controller
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/admin/categorymanager")
    public String catgoryManager() {
        return "admin/categorymanager";
    }

    /**
     * 获取所有分类
     *
     * @param page  当前页
     * @param limit 每页显示记录数
     * @return layui数据表格通用结果对象
     */
    @RequestMapping(value = "/admin/getAllCategoryList")
    @ResponseBody
    public GridData getAllCategoryList(int page, int limit) {
        try {
            PageHelper.startPage(page, limit);
            List<MetasDto> categoryList = categoryList = categoryService.getAllCategoryList(Types.CATEGORY.getType(),
                    null);
            PageInfo<MetasDto> metasPageInfo = metasPageInfo = new PageInfo<MetasDto>(categoryList);
            return GridData.build(categoryList, metasPageInfo.getTotal());
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所有标签
     *
     * @param page  当前页
     * @param limit 每页记录数
     * @return layui通用结果对象
     */
    @RequestMapping(value = "/admin/getAllTagsList")
    @ResponseBody
    public GridData getAllTagsList(int page, int limit) {
        try {
            PageHelper.startPage(page, limit);
//        List<Metas> tagsList = categoryService.getAllTagList();
            List<MetasDto> tagsList = categoryService.getAllCategoryList(Types.TAG.getType(),null);
            PageInfo<MetasDto> tagsPageInfo = new PageInfo<MetasDto>(tagsList);
            return GridData.build(tagsList, tagsPageInfo.getTotal());
        } catch (CgszlException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新增/修改分类
     *
     * @return
     */
    @RequestMapping(value = "/admin/addCategory")
    @ResponseBody
    public CommonResult addCategory(Metas metas) {
        try {
            boolean result = false;
            if (metas.getMid() != null) {
                //TODO 更新分类信息，同时更新该分类下所有文章的分类类型
                result = categoryService.updataCategory(metas);
            } else {
                result = categoryService.addCategory(metas);
            }
            if (result) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "系统异常");
            }
        } catch (CgszlException e) {
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 验证分类名称唯一性
     *
     * @param name 分类名称
     * @return
     */
    @RequestMapping(value = "/admin/checkCatName", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult checkCatName(String name) {
        boolean result = false;
        try {
            result = categoryService.checkCatName(name);
        } catch (CgszlException e) {
            e.printStackTrace();
            result = false;
        }
        if (result) {
            return CommonResult.fail(false, "已存在同名的分类...");
        } else {
            return CommonResult.ok();
        }
    }

    /**
     * 根据分类标识删除分类
     *
     * @param mid 分类标识
     * @return 通用结果对象
     */
    @RequestMapping(value = "/admin/category/deleteByMid")
    @ResponseBody
    public CommonResult deleteByMid(String mid) {
        try {
            boolean result = categoryService.deleteByMid(mid);
            if (result) {
                return CommonResult.ok();
            } else {
                return CommonResult.fail(false, "系统异常");
            }
        } catch (CgszlException e) {
            return CommonResult.fail(false, "系统异常");
        }
    }

    /**
     * 修改分类页
     *
     * @return 页面地址
     */
    @RequestMapping(value = "/admin/editCategory")
    public String editCategory() {
        return "admin/addcategory";
    }
}
