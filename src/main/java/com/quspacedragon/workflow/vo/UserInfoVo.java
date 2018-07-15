package com.quspacedragon.workflow.vo;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.quspacedragon.workflow.entity.WinRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Title: UserInfoVo
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2018/7/15
 */
@ApiModel
public class UserInfoVo {
    @ApiModelProperty(name = "中奖列表")
    List<WinRecord> winRecords;
    @ApiModelProperty(name = "微信用户信息")
    WxMaJscode2SessionResult userInfo;
    @ApiModelProperty(name = "剩余抽奖次数")
    Integer remainNum;

    public List<WinRecord> getWinRecords() {
        return winRecords;
    }

    public void setWinRecords(List<WinRecord> winRecords) {
        this.winRecords = winRecords;
    }

    public WxMaJscode2SessionResult getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxMaJscode2SessionResult userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }
}
