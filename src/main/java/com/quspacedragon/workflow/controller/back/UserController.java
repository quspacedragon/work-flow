package com.quspacedragon.workflow.controller.back;


import com.quspacedragon.workflow.annoation.LoginIntercept;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.entity.Room;
import com.quspacedragon.workflow.entity.SmsRecord;
import com.quspacedragon.workflow.entity.User;
import com.quspacedragon.workflow.enums.SmsTypeEnum;
import com.quspacedragon.workflow.request.RoomMergeRequest;
import com.quspacedragon.workflow.request.UserMoveinRequest;
import com.quspacedragon.workflow.service.ISmsRecordService;
import com.quspacedragon.workflow.service.IUserService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    ISmsRecordService smsRecordService;


    @ApiOperation(value = "修改号码", response = Result.class)
    @RequestMapping(value = "/v1/updatePhone", method = {RequestMethod.GET, RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result updatePhone(
            @ApiParam(value = "主键id", required = true) @RequestParam(required = true, value = "id") Integer id,
            @ApiParam(value = "手机号", required = true) @RequestParam(required = true, value = "phone") String phone,
            HttpServletRequest httpServletRequest) {
        User user = userService.selectById(id);
        if (user == null) {
            return ApiResultUtils.failResult("用户不存在");

        }
        user.setContactNumber(phone);
        boolean b = userService.insertOrUpdate(user);
        return ApiResultUtils.successResult(b);
    }

    @ApiOperation(value = "迁入", response = Result.class)
    @RequestMapping(value = "/v1/movein", method = {RequestMethod.POST})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result movein(
            @ApiParam(value = "请求对象", required = true) @RequestBody(required = true) UserMoveinRequest userMoveinRequest,
            HttpServletRequest httpServletRequest) {
        Boolean aBoolean = userService.moveIn(userMoveinRequest);
        return ApiResultUtils.successResult(aBoolean);
    }

    @ApiOperation(value = "迁出", response = Result.class)
    @RequestMapping(value = "/v1/moveout", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result moveout(
            @ApiParam(value = "id", required = true) @RequestParam(required = true, value = "id") Integer id,
            @ApiParam(value = "迁出日期 时间戳", required = true) @RequestParam(required = true, value = "moveOutTime") Long moveOutTime,
            @ApiParam(value = "备注", required = false) @RequestParam(required = false, value = "memo") String memo,
            HttpServletRequest httpServletRequest) {
        User user = userService.selectById(id);
        user.setMoveOutTime(moveOutTime);
        user.setMemo(memo);
        boolean insert = userService.insert(user);
        return ApiResultUtils.successResult(insert);
    }


    @ApiOperation(value = "修改登录密码", response = Result.class)
    @RequestMapping(value = "/v1/updateLoginAccount", method = {RequestMethod.GET})
    @LoginIntercept
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Result updateLoginAccount(
            @ApiParam(value = "id", required = true) @RequestParam(required = true, value = "id") Integer id,
            @ApiParam(value = "手机号码", required = true) @RequestParam(required = true, value = "phone") String phone,
            @ApiParam(value = "验证码", required = false) @RequestParam(required = true, value = "code") String code,
            HttpServletRequest httpServletRequest) {
        User user = userService.selectById(id);
        Boolean valiade = smsRecordService.valiade(phone, SmsTypeEnum.update_login_phone.getType(), code);
        if (valiade) {
            user.setPhone(phone);
            boolean b = userService.updateById(user);
            return ApiResultUtils.successResult(b);
        }
        return ApiResultUtils.failResult("验证码错误");
    }

}
