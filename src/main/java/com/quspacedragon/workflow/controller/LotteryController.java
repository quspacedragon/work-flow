package com.quspacedragon.workflow.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.quspacedragon.workflow.common.Result;
import com.quspacedragon.workflow.controller.wechat.WxErrorController;
import com.quspacedragon.workflow.entity.Lottery;
import com.quspacedragon.workflow.entity.LotteryPrice;
import com.quspacedragon.workflow.entity.LotteryRecord;
import com.quspacedragon.workflow.entity.WinRecord;
import com.quspacedragon.workflow.service.ILotteryPriceService;
import com.quspacedragon.workflow.service.ILotteryRecordService;
import com.quspacedragon.workflow.service.ILotteryService;
import com.quspacedragon.workflow.service.IWinRecordService;
import com.quspacedragon.workflow.util.ApiResultUtils;
import com.quspacedragon.workflow.util.UUIDUtil;
import com.quspacedragon.workflow.vo.LotteryPriceVo;
import com.quspacedragon.workflow.vo.UserInfoVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-15
 */
@Controller
@RequestMapping("/lottery")
public class LotteryController {
    private static final Logger logger = LoggerFactory.getLogger(WxErrorController.class);
    private static final Lock lock = new ReentrantLock();

    @Autowired
    private WxMaService wxService;
    @Autowired
    private ILotteryPriceService lotteryPriceService;
    @Autowired
    private ILotteryService lotteryService;
    @Autowired
    private ILotteryRecordService lotteryRecordService;
    @Autowired
    private IWinRecordService winRecordService;


    @RequestMapping(value = "/lottery", method = RequestMethod.GET)
    @ApiOperation(value = "抽奖")
    public Result lottery(
            @ApiParam(name = "小游戏code", required = true)
            @RequestParam(name = "code", required = true) String code,
            @ApiParam(name = "抽奖活动id", required = true)
            @RequestParam(name = "lotteryActivityId", required = true) String lotteryActivityId,
            @ApiParam(name = "手机号", required = true)
            @RequestParam(name = "phone", required = false) String phone) {
        WxMaJscode2SessionResult wxMaJscode2SessionResult = null;
        try {
            wxMaJscode2SessionResult = wxService.jsCode2SessionInfo(code);
        } catch (WxErrorException e) {
            logger.error("获取微信信息错误", e);
            return ApiResultUtils.failResult(e.getMessage());
        }
        String openid = wxMaJscode2SessionResult.getOpenid();
        String unionid = wxMaJscode2SessionResult.getUnionid();
        Lottery lottery = lotteryService.selectById(lotteryActivityId);
        if (lottery == null) {
            return ApiResultUtils.failResult("活动id错误");
        }
        Integer perMaxNum = lottery.getPerMaxNum();
        long timeMillis = System.currentTimeMillis();

        Long startTime = lottery.getStartTime();
        if (startTime > timeMillis) {
            return ApiResultUtils.failResult("活动还未开始");
        }
        if (startTime > timeMillis) {
            return ApiResultUtils.failResult("活动已经结束");
        }
        LotteryRecord lotteryRecord = new LotteryRecord();
        lotteryRecord.setUnionid(unionid);

        LotteryPriceVo lotteryPriceVo = new LotteryPriceVo();

        try {
            lock.lock();
            List<LotteryRecord> lotteryRecords = lotteryRecordService.selectList(new EntityWrapper(lotteryRecord));
            if (lotteryRecords.size() >= perMaxNum) {
                return ApiResultUtils.failResult("当前抽奖活动已达上限");
            }
            LotteryPrice lotteryPrice = new LotteryPrice();
            lotteryPrice.setLotteryId(lotteryActivityId);
            List<LotteryPrice> lotteryPrices = lotteryPriceService.selectList(new EntityWrapper<>(lotteryPrice));
            LotteryPrice winLotteryprice = lotteryPrice(lotteryPrices);
            lotteryRecord.setId(UUIDUtil.generateUUID());
            lotteryRecord.setOpenid(openid);
            lotteryRecord.setPhone(phone);
            lotteryRecordService.insert(lotteryRecord);
            if (winLotteryprice == null) {
                ApiResultUtils.successResult(lotteryPriceVo);
            }
            WinRecord winRecord = new WinRecord();
            winRecord.setPhone(phone);
            winRecord.setId(UUIDUtil.generateUUID());
            winRecord.setUnionid(unionid);
            winRecord.setOpenid(openid);
            winRecord.setLotteryPriceId(winLotteryprice.getId());
            winRecord.setLotteryPriceName(winLotteryprice.getName());
            winRecord.setLotteryPriceNum(1);
            winRecordService.insert(winRecord);
            lotteryPriceVo.setLottery(true);
            lotteryPriceVo.setLotteryPrice(winLotteryprice);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("抽奖异常", e);
        } finally {
            lock.unlock();
        }
        return ApiResultUtils.successResult(lotteryPriceVo);
    }

    @ApiOperation(value = "基础信息")
    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public Result winList(@RequestParam(name = "code", required = true) String code,
                          @RequestParam(name = "lotteryActivityId", required = true) String lotteryActivityId,
                          @RequestParam(name = "phone", required = false) String phone) {
        WxMaJscode2SessionResult wxMaJscode2SessionResult = null;
        try {
            wxMaJscode2SessionResult = wxService.jsCode2SessionInfo(code);
        } catch (WxErrorException e) {
            logger.error("获取微信信息错误", e);
            return ApiResultUtils.failResult(e.getMessage());
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserInfo(wxMaJscode2SessionResult);

        String openid = wxMaJscode2SessionResult.getOpenid();
        String unionid = wxMaJscode2SessionResult.getUnionid();
        WinRecord winRecord = new WinRecord();
        winRecord.setOpenid(openid);
        winRecord.setUnionid(unionid);
        List<WinRecord> winRecords = winRecordService.selectList(new EntityWrapper<>(winRecord));
        userInfoVo.setWinRecords(winRecords);
        Lottery lottery = lotteryService.selectById(lotteryActivityId);
        LotteryRecord lotteryRecord = new LotteryRecord();
        lotteryRecord.setUnionid(unionid);

        LotteryPriceVo lotteryPriceVo = new LotteryPriceVo();
        int recordNum = lotteryRecordService.selectCount(new EntityWrapper(lotteryRecord));
        Integer ramaiNum = lottery.getPerMaxNum() - recordNum;

        userInfoVo.setRemainNum(ramaiNum > 0 ? ramaiNum : 0);
        return ApiResultUtils.successResult(userInfoVo);
    }


    /**
     * 抽奖
     *
     * @param openid
     * @param unionid
     * @param lotteryPrices
     * @return
     */
    private LotteryPrice lotteryPrice(List<LotteryPrice> lotteryPrices) {
        int winProbability = RandomUtils.nextInt(100);
        int probability = 0;
        for (LotteryPrice lotteryPrice : lotteryPrices) {
            if (winProbability < lotteryPrice.getProbability()) {
                if (lotteryPrice.getRemainNum() > 0) {
                    return lotteryPrice;
                }
                return null;
            }
            probability += lotteryPrice.getProbability();
        }
        return null;
    }

}
