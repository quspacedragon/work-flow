package com.quspacedragon.workflow.vo;

import com.quspacedragon.workflow.entity.LotteryPrice;

/**
 * Title: LotteryPriceVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/15
 */
public class LotteryPriceVo {
    private Boolean lottery = false;
    private LotteryPrice lotteryPrice;

    public Boolean getLottery() {
        return lottery;
    }

    public void setLottery(Boolean lottery) {
        this.lottery = lottery;
    }

    public LotteryPrice getLotteryPrice() {
        return lotteryPrice;
    }

    public void setLotteryPrice(LotteryPrice lotteryPrice) {
        this.lotteryPrice = lotteryPrice;
    }
}
