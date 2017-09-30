package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.UserHelper;
import com.quspacedragon.workflow.entity.Goods;
import com.quspacedragon.workflow.service.IGoodsService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.EnterpriseVo;
import com.quspacedragon.workflow.vo.GoodsVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-27
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;


    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "货物添加", httpMethod = "POST", response = GoodsVo.class, notes = "保存货物")
    public Result<GoodsVo> insert() {
        EnterpriseVo user = UserHelper.getUser();

        GoodsVo dict = new GoodsVo();

        return ApiResultUtils.successResult(dict);
    }


    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "货物查询", httpMethod = "GET", response = GoodsVo.class, notes = "查询")
    public Result<List<GoodsVo>> list() {
        Goods dict = new Goods();
        EntityWrapper<Goods> dictEntityWrapper = new EntityWrapper<>();
        dictEntityWrapper.setEntity(dict);
        List<Goods> dicts = goodsService.selectList(dictEntityWrapper);
        return ApiResultUtils.successResult(dicts);
    }
}
