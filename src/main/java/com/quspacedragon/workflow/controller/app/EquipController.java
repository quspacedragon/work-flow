package com.quspacedragon.workflow.controller.app;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.CheckRecord;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.service.ICheckRecordService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Title: LoginController
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/25
 */
@Api("app-设备")
@Controller("appEquipController")
@RequestMapping("/user")
public class EquipController {

    @Resource
    ICheckRecordService checkRecordService;
    @Resource
    LoginHelper loginHelper;


    @ApiOperation(value = "设备巡查列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest,
            @RequestParam(value = "token", required = true) String token) {
        Integer loginUserId = loginHelper.getLoginUserId(token, httpServletRequest);

        Page<CheckRecord> buildingPage = new Page<>();
        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setUserId(loginUserId);

        EntityWrapper<CheckRecord> buildingEntityWrapper = new EntityWrapper<>();
        buildingEntityWrapper.setEntity(checkRecord);
        long now = System.currentTimeMillis();
        buildingEntityWrapper.ge(CheckRecord.VALID_START_TIME, now);
        buildingEntityWrapper.le(CheckRecord.VALID_END_TIME, now);


        int num = checkRecordService.selectCount(buildingEntityWrapper);


        HashMap<String, Object> result = Maps.newHashMap();
//        result.put("")
        return ApiResultUtils.successResult(result);
    }


}
