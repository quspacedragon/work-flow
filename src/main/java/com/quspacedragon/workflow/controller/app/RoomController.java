package com.quspacedragon.workflow.controller.app;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Building;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.entity.WorkOrder;
import com.quspacedragon.workflow.service.IUnitService;
import com.quspacedragon.workflow.service.impl.BuildingService;
import com.quspacedragon.workflow.service.impl.RoomService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Title: RoomController
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/9/9
 */
@Api("app-房间相关")
@Controller("appRoomController")
@RequestMapping("/app/room")
@LoginIntercept
public class RoomController {
    @Resource
    private BuildingService buildingService;
    @Resource
    private RoomService roomService;
    @Resource
    IUnitService unitService;


    @ApiOperation(value = "楼栋列表", response = Building.class)
    @RequestMapping(value = "/building/v1/list", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @ApiParam(value = "每页条数", required = false) @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            HttpServletRequest httpServletRequest) {
        Page<Building> buildingPage = new Page<Building>(pageNo,pageSize);
        EntityWrapper<Building> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, ProductType.NAME, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, ProductType.CODE, code);
        }
        Page<Building> page = buildingService.selectPage(buildingPage, buildingEntityWrapper);
        int num = buildingService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }

    @ApiOperation(value = "房间列表", response = Room.class)
    @RequestMapping(value = "/room/v1/list",method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result roomlist(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true) String token,
            @ApiParam(value = "楼栋id", required = true) @RequestParam(value = "buildingId", required = false) Integer buildingId,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @ApiParam(value = "每页条数", required = false) @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            HttpServletRequest httpServletRequest) {
        Page<Room> buildingPage = new Page<>(pageNo,pageSize);
        EntityWrapper<Room> buildingEntityWrapper = new EntityWrapper<>();
        buildingEntityWrapper.eq(Room.BUILDING_ID, buildingId);
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, Room.ROOM_NO, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, Room.CODE, code);
        }

        buildingPage.setCurrent(pageNo);
        Page<Room> page = roomService.selectPage(buildingPage, buildingEntityWrapper);
        List<Room> records = page.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            records.forEach(r -> r.setUnit(unitService.selectById(r.getUnitId())));
        }
        int num = roomService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }


}
