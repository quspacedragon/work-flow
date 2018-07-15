package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-15
 */
@TableName("lottery_price")
public class LotteryPrice extends BaseEntity<LotteryPrice> {

    private static final long serialVersionUID = 1L;

    private String name;
    /**
     * 总数量
     */
    @TableField("total_num")
    private Integer totalNum;
    /**
     * 剩余数量
     */
    @TableField("remain_num")
    private Integer remainNum;
    private Integer probability;
    private Long createTime;

    private String lotteryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(String lotteryId) {
        this.lotteryId = lotteryId;
    }

    public static final String NAME = "name";

    public static final String TOTAL_NUM = "total_num";

    public static final String REMAIN_NUM = "remain_num";

    public static final String PROBABILITY = "probability";

    public static final String CREATETIME = "createTime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LotteryPrice{" +
                "name=" + name +
                ", totalNum=" + totalNum +
                ", remainNum=" + remainNum +
                ", probability=" + probability +
                ", createTime=" + createTime +
                "}";
    }
}
