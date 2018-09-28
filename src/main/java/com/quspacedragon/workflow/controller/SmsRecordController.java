package com.quspacedragon.workflow.controller;


import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.common.SegmentLock;
import com.quspacedragon.workflow.constant.Constant;
import com.quspacedragon.workflow.entity.SmsRecord;
import com.quspacedragon.workflow.enums.SmsTypeEnum;
import com.quspacedragon.workflow.exception.ExceptionEnum;
import com.quspacedragon.workflow.service.ISmsRecordService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.RandomUtil;
import com.quspacedragon.workflow.util.ValidateUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
@Controller
@RequestMapping("/common/sms")
@Log4j
public class SmsRecordController {
    SegmentLock lock = new SegmentLock<String>();

    @Resource
    ISmsRecordService smsRecordService;

    @ApiOperation("获取短信验证码")
    @RequestMapping("/getAuthcode")
    @ResponseBody
    public Result getAuthcode(
            @ApiParam(value = "手机号码", required = true)
            @RequestAttribute String phone,
            @ApiParam(value = "使用途径", required = true)
            @RequestAttribute Integer type
    ) {
        if (StringUtils.isEmpty(phone)) {
            return ApiResultUtils.failResult(ExceptionEnum.PHONE_NOT_NULL);
        }
        if (!ValidateUtil.isMobile(phone)) {
            return ApiResultUtils.failResult("手机号格式错误");
        }
        try {
            SmsTypeEnum smsTypeEnum = SmsTypeEnum.findByType(type);
            lock.lock(phone + "_" + type);
            Integer num = smsRecordService.countTodayByPhoneAndType(phone, type);
            // 判断短信是否超标
            if (num >= Constant.Sms.SMS_SEND_MAX_COUNT) {
                return ApiResultUtils.failResult("短信发送超过上限");
            }
            SmsRecord lastSmsRecord = smsRecordService.findLastSmsRecord(phone, type);
            if (lastSmsRecord != null) {
                Long createTime = lastSmsRecord.getCreateTime();
                if ((System.currentTimeMillis() - createTime) < 60 * 1000L) {
                    return ApiResultUtils.failResult("请在一分钟后发送短信");
                }
            }

            String code = RandomUtil.generateInt(6);
            //发送短信
//            HttpSender.send(phone, code);
            SmsRecord smsRecordDO = new SmsRecord();
            smsRecordDO.setCode(code);
            smsRecordDO.setPhone(phone);
            smsRecordDO.setType(type);
            smsRecordService.insert(smsRecordDO);
            return ApiResultUtils.successResult("");
        } catch (Exception e) {
            log.error("", e);
            return ApiResultUtils.failResult(e.getMessage());
        } finally {
            lock.unlock(phone);
        }
    }
}
