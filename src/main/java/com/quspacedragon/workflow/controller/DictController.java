package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Dict;
import com.quspacedragon.workflow.service.IDictService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.DictVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2017-09-24
 */
@Controller
@RequestMapping("/dict")
public class DictController {
    @Resource
    private IDictService dictService;


    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "字典添加", httpMethod = "POST", response = DictVo.class, notes = "添加字典")
    public Result<DictVo> insert(@ApiParam(name = "type", value = "保存字典类型") Integer type,
                                 @ApiParam(name = "name", value = "名称") String name,
                                 @ApiParam(name = "code", value = "拼音简码") String code) {
        DictVo dict = new DictVo();

        return ApiResultUtils.successResult(dict);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "字典删除", httpMethod = "DELETE", response = Boolean.class, notes = "删除")
    public Result<Boolean> delete(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        Dict dict = new Dict();

        return ApiResultUtils.successResult(true);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "字典修改", httpMethod = "PUT", response = DictVo.class, notes = "修改")
    public Result<DictVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id,
                               @ApiParam(name = "name", value = "名称") String name) {
        Dict dict = new Dict();

        return ApiResultUtils.successResult(dict);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "字典查询", httpMethod = "GET", response = DictVo.class, notes = "查询")
    public Result<DictVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        Dict dict = new Dict();

        return ApiResultUtils.successResult(dict);
    }

    @GetMapping("/")
    @ResponseBody
    @ApiOperation(value = "字典列表查询", httpMethod = "GET", response = DictVo.class, notes = "查询")
    public Result<List<DictVo>> list(@ApiParam(name = "type", value = "字典类型 1品种 2颜色", required = true) Integer type) {
        Dict dict = new Dict();
        dict.setDictType(type);
        EntityWrapper<Dict> dictEntityWrapper = new EntityWrapper<>();
        dictEntityWrapper.setEntity(dict);
        List<Dict> dicts = dictService.selectList(dictEntityWrapper);
        return ApiResultUtils.successResult(dicts);
    }


}
