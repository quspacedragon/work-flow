package com.quspacedragon.workflow.controller.app;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.LoginHelper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.BaseEntity;
import com.quspacedragon.workflow.entity.MeterRecord;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.entity.WorkOrder;
import com.quspacedragon.workflow.service.IMeterRecordService;
import com.quspacedragon.workflow.service.impl.RoomService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.vo.MeterDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-09-09
 */
@Api("app-水电表抄送")
@Controller("appMeterRecordController")
@RequestMapping("/app/meterRecord")
@LoginIntercept
public class MeterRecordController {

    @Resource
    IMeterRecordService meterRecordService;
    @Resource
    LoginHelper loginHelper;
    @Resource
    RoomService roomService;


    @ApiOperation(value = "水电抄表 type =1为水表 type=2为电表", response = MeterRecord.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @ApiImplicitParam(name = "meterRecord", value = "抄表", required = true, dataType = "MeterRecord")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@RequestBody(required = true) MeterRecord meterRecord,
                       @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token,
                       HttpServletRequest httpServletRequest) {
        Integer loginUserId = loginHelper.getLoginUserId(httpServletRequest);
        meterRecord.setWriteUserId(loginUserId);

        boolean insert = meterRecordService.insert(meterRecord);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(meterRecordService.selectById(meterRecord.getId()));
    }


    @ApiOperation(value = "水电详情", response = MeterDetail.class)
    @RequestMapping(value = "/v1/detail", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "房间id", required = true) @RequestParam(value = "roomId", required = false) Integer roomId,
            @ApiParam(value = "类型 1电表 2水表", required = true) @RequestParam(value = "type", required = false) Integer type,
            @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token,
            HttpServletRequest httpServletRequest) {

        Room room = roomService.selectById(roomId);


        EntityWrapper<MeterRecord> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq(MeterRecord.ROOM_ID, roomId);
        entityWrapper.eq(BaseEntity.TYPE, type);
        Page<MeterRecord> page = new Page<>();
        page.setOrderByField(BaseEntity.CREATE_TIME);
        page.setAsc(false);
        page.setSize(2);
        page = meterRecordService.selectPage(page, entityWrapper);
        List<MeterRecord> records = page.getRecords();
        MeterDetail meterDetail = new MeterDetail();
        meterDetail.setRoomName(room.getRoomNo());
        if (CollectionUtils.isEmpty(records)) {
            meterDetail.setLastNum(0);
            meterDetail.setNowNum(0);
        }
        int size = records.size();
        if (size == 1) {
            MeterRecord meterRecord = records.get(0);
            meterDetail.setNowNum(meterRecord.getNum());
            meterDetail.setLastNum(0);
        }
        if (size >= 2) {
            MeterRecord meterRecord = records.get(0);
            MeterRecord meterRecord1 = records.get(1);
            meterDetail.setLastNum(meterRecord1.getNum());
            meterDetail.setNowNum(meterRecord.getNum());
        }
        return ApiResultUtils.successResult(meterDetail);
    }


}
