package com.quspacedragon.workflow.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.common.DictTypeEnum;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Dict;
import com.quspacedragon.workflow.service.IDictService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.ConverUtils;
import com.quspacedragon.workflow.vo.DictVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
        if (!DictTypeEnum.contains(type)) {
            return ApiResultUtils.failResult(HttpStatus.BAD_REQUEST.ordinal(), "不合法的type");
        }

        Dict dict = new Dict();
        dict.setDictType(type);
        dict.setDictName(name);
        dict.setCode(code);
        dictService.insert(dict);
        dict = dictService.selectById(dict.getId());
        return ApiResultUtils.successResult(ConverUtils.conver(dict, DictVo.class));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "字典删除", httpMethod = "DELETE", response = Boolean.class, notes = "删除")
    public Result<Boolean> delete(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        dictService.deleteById(id);
        return ApiResultUtils.successResult(true);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "字典修改", httpMethod = "PUT", response = DictVo.class, notes = "修改")
    public Result<DictVo> update(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id,
                                 @ApiParam(name = "name", value = "名称") String name) {
        Dict dict = dictService.selectById(id);
        dict.setDictName(name);
        dictService.updateById(dict);
        dict = dictService.selectById(dict.getId());
        return ApiResultUtils.successResult(ConverUtils.conver(dict, DictVo.class));
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "字典查询", httpMethod = "GET", response = DictVo.class, notes = "查询")
    public Result<DictVo> get(@ApiParam(name = "id", value = "主键id") @PathVariable("id") Integer id) {
        Dict dict = dictService.selectById(id);
        return ApiResultUtils.successResult(ConverUtils.conver(dict, DictVo.class));
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
        List<DictVo> collect = dicts.stream().map(r -> (DictVo) ConverUtils.conver(r, DictVo.class)).collect(Collectors.toList());
        return ApiResultUtils.successResult(collect);
    }


}
