package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.service.IProductTypeService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/productType")
public class ProductTypeController {


    @Resource
    IProductTypeService productTypeService;

    @ApiOperation(value = "产品类型保存", response = ProductType.class)
    @RequestMapping(value = "/v1/save", method = RequestMethod.POST)
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@ApiParam(value = "产品类型", required = true) @RequestBody(required = true) ProductType productType,
                       HttpServletRequest httpServletRequest) {
        Integer id = productType.getId();
        ProductType byCode = productTypeService.findByCode(productType.getCode());
        if (byCode != null) {
            return ApiResultUtils.failResult("重复编码");
        }
        boolean insert = productTypeService.insert(productType);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(productTypeService.selectById(productType.getId()));
    }

    @ApiOperation(value = "产品类型删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = RequestMethod.GET)
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id", required = true) @RequestParam(value = "id") Integer id,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(productTypeService.deleteById(id));
    }

    @ApiOperation(value = "产品类型列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "业态类型", required = false) @RequestParam(value = "businessType", required = false) Integer businessType,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<ProductType> productTypePage = new Page<>();
        EntityWrapper<ProductType> productTypeEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            productTypeEntityWrapper.like(true, ProductType.NAME, code);
            productTypeEntityWrapper.or();
            productTypeEntityWrapper.like(true, ProductType.CODE, code);
            productTypeEntityWrapper.andNew();
        }
        if (businessType != null) {
            productTypeEntityWrapper.eq(ProductType.BUSINESS_TYPE, businessType);
        }
        productTypePage.setCurrent(pageNo);
        Page<ProductType> page = productTypeService.selectPage(productTypePage, productTypeEntityWrapper);
        int num = productTypeService.selectCount(productTypeEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }


}
