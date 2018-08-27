package com.quspacedragon.workflow.controller.back;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.CheckProject;
import com.quspacedragon.workflow.entity.ProductType;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.enums.RoomStatusEnum;
import com.quspacedragon.workflow.request.RoomMergeRequest;
import com.quspacedragon.workflow.request.RoomSplitRequest;
import com.quspacedragon.workflow.service.IRoomService;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Controller
@Api("房间管理")
@RequestMapping("/room")
public class RoomController {
    @Resource
    IRoomService roomService;

    @ApiOperation(value = "房间保存", response = Room.class)
    @RequestMapping(value = "/v1/save", method = {RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result save(@ApiParam(value = "房间", required = true) @RequestBody(required = true) Room room,
                       HttpServletRequest httpServletRequest) {
        Room queryRoom = roomService.findByCode(room.getCode());
        if (queryRoom != null) {
            return ApiResultUtils.failResult("重复编码");
        }
        boolean insert = roomService.insert(room);
        if (!insert) {
            return ApiResultUtils.failResult("保存失败");
        }
        return ApiResultUtils.successResult(roomService.selectById(room.getId()));
    }

    @ApiOperation(value = "房间删除", response = Result.class)
    @RequestMapping(value = "/v1/delete", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@ApiParam(value = "id数组", required = true) @RequestParam(value = "ids") List<Integer> ids,
                         HttpServletRequest httpServletRequest) {
        return ApiResultUtils.successResult(roomService.deleteBatchIds(ids));
    }


    @ApiOperation(value = "房间合并", response = Result.class)
    @RequestMapping(value = "/v1/merge", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result merge(@ApiParam(value = "房间列表", required = true) @RequestBody(required = true) RoomMergeRequest roomMergeRequest,
                        HttpServletRequest httpServletRequest) {
        List<Room> rooms = roomService.selectBatchIds(roomMergeRequest.getIdList());
        //必须两个空置状态的房间
        if (checkStatus(rooms)) {
            return ApiResultUtils.failResult("请选择状态为空置的房间进行合并");
        }
        //业务类型是否相等
        if (!checkStatus(rooms)) {
            return ApiResultUtils.failResult("业务类型不一致的房间不允许合并");
        }
        return ApiResultUtils.successResult(roomService.mergeRoom(roomMergeRequest));
    }


    @ApiOperation(value = "房间拆分", response = Result.class)
    @RequestMapping(value = "/v1/split", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result merge(@ApiParam(value = "房间列表", required = true) @RequestBody(required = true) RoomSplitRequest roomSplitRequest,
                        HttpServletRequest httpServletRequest) {

        return ApiResultUtils.successResult(roomService.splitRoom(roomSplitRequest));
    }


    @ApiOperation(value = "房间作废", response = Result.class)
    @RequestMapping(value = "/v1/invalid", method = RequestMethod.GET)
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result invalid(@ApiParam(value = "id列表", required = true) @RequestParam(required = true, value = "idList") List<Integer> idList,
                          HttpServletRequest httpServletRequest) {

        List<Room> collect = idList.stream().map(r -> {
            Room room = new Room();
            room.setId(r);
            room.setStatus(RoomStatusEnum.invalid.getStatus());
            return room;
        }).collect(Collectors.toList());
        boolean flag = roomService.updateBatchById(collect);
        return ApiResultUtils.successResult(flag);
    }


    @ApiOperation(value = "房间恢复", response = Result.class)
    @RequestMapping(value = "/v1/recover", method = RequestMethod.GET)
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result recover(@ApiParam(value = "id列表", required = true) @RequestParam(required = true, value = "idList") List<Integer> idList,
                          HttpServletRequest httpServletRequest) {

        List<Room> collect = idList.stream().map(r -> {
            Room room = new Room();
            room.setId(r);
            room.setStatus(RoomStatusEnum.normal.getStatus());
            return room;
        }).collect(Collectors.toList());
        boolean flag = roomService.updateBatchById(collect);
        return ApiResultUtils.successResult(flag);
    }


    @ApiOperation(value = "房间列表", response = Page.class)
    @RequestMapping(value = "/v1/list", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result list(
            @ApiParam(value = "名称或者编码", required = false) @RequestParam(value = "code", required = false) String code,
            @ApiParam(value = "页码", required = false) @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            HttpServletRequest httpServletRequest) {
        Page<Room> buildingPage = new Page<>();
        EntityWrapper<Room> buildingEntityWrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(code)) {
            buildingEntityWrapper.like(true, Room.ROOM_NO, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.like(true, Room.CODE, code);
            buildingEntityWrapper.or();
            buildingEntityWrapper.andNew();
        }
        buildingPage.setCurrent(pageNo);
        Page<Room> page = roomService.selectPage(buildingPage, buildingEntityWrapper);
        int num = roomService.selectCount(buildingEntityWrapper);
        page.setTotal(num);
        return ApiResultUtils.successResult(page);
    }


    /**
     * 判断两个房间是否空置
     *
     * @param rooms
     * @return true非空置
     */
    private boolean checkStatus(List<Room> rooms) {
        Optional<Room> first = rooms.stream().filter(r -> !RoomStatusEnum.empty.getStatus().equals(r.getStatus())).findFirst();
        return first.isPresent();
    }

    /**
     * 判断两个房间是否空置
     *
     * @param rooms
     * @return true非空置
     */
    private boolean checkBusinessType(List<Room> rooms) {
        Integer type = null;
        for (Room room : rooms) {
            if (type == null) {
                type = room.getBusinessType();
            } else {
                if (!type.equals(room.getBusinessType())) {
                    return false;
                }
            }

        }
        return true;
    }
}
