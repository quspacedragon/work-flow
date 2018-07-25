package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    private String phone;
    @TableField("pass_word")
    private String passWord;
    private String name;
    /**
     * 1个人2企业3个体户4政府机构5其他
     */
    private Integer property;
    @TableField("room_id")
    private Integer roomId;
    @TableField("move_in_time")
    private Date moveInTime;
    /**
     * 是否收费
     */
    @TableField("can_free")
    private Integer canFree;
    /**
     * 1身份证2军官证3港澳台4护照5其他   1社会信用代码2营业执照3税务登记号4组织结构代码5其他
     */
    @TableField("card_type")
    private Integer cardType;
    /**
     * 证件号码
     */
    @TableField("card_no")
    private String cardNo;
    /**
     * 1男2女
     */
    private Integer sex;
    private String address;
    /**
     * 国家
     */
    private String country;
    /**
     * 民族
     */
    private String ethnic;
    /**
     * 户籍
     */
    private String census;
    /**
     * 生日
     */
    private Date birthday;
    @TableField("create_admin_id")
    private Integer createAdminId;
    @TableField("create_memo")
    private Integer createMemo;
    /**
     * 中介公司
     */
    @TableField("intermediary_company")
    private String intermediaryCompany;
    /**
     * 租期开始时间
     */
    @TableField("lease_start_time")
    private Date leaseStartTime;
    /**
     * 租期结束时间
     */
    @TableField("lease_end_time")
    private Date leaseEndTime;
    /**
     * 计划迁出时间
     */
    @TableField("plan_out_time")
    private Date planOutTime;
    /**
     * 1夫妻2子女3父母
     */
    @TableField("user_type")
    private Integer userType;
    /**
     * 关联父id
     */
    @TableField("parent_id")
    private Integer parentId;
    private Long createTime;

    private String token;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Date getMoveInTime() {
        return moveInTime;
    }

    public void setMoveInTime(Date moveInTime) {
        this.moveInTime = moveInTime;
    }

    public Integer getCanFree() {
        return canFree;
    }

    public void setCanFree(Integer canFree) {
        this.canFree = canFree;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getCreateAdminId() {
        return createAdminId;
    }

    public void setCreateAdminId(Integer createAdminId) {
        this.createAdminId = createAdminId;
    }

    public Integer getCreateMemo() {
        return createMemo;
    }

    public void setCreateMemo(Integer createMemo) {
        this.createMemo = createMemo;
    }

    public String getIntermediaryCompany() {
        return intermediaryCompany;
    }

    public void setIntermediaryCompany(String intermediaryCompany) {
        this.intermediaryCompany = intermediaryCompany;
    }

    public Date getLeaseStartTime() {
        return leaseStartTime;
    }

    public void setLeaseStartTime(Date leaseStartTime) {
        this.leaseStartTime = leaseStartTime;
    }

    public Date getLeaseEndTime() {
        return leaseEndTime;
    }

    public void setLeaseEndTime(Date leaseEndTime) {
        this.leaseEndTime = leaseEndTime;
    }

    public Date getPlanOutTime() {
        return planOutTime;
    }

    public void setPlanOutTime(Date planOutTime) {
        this.planOutTime = planOutTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public static final String PHONE = "phone";

    public static final String PASS_WORD = "pass_word";

    public static final String NAME = "name";

    public static final String PROPERTY = "property";

    public static final String ROOM_ID = "room_id";

    public static final String MOVE_IN_TIME = "move_in_time";

    public static final String CAN_FREE = "can_free";

    public static final String CARD_TYPE = "card_type";

    public static final String CARD_NO = "card_no";

    public static final String SEX = "sex";

    public static final String ADDRESS = "address";

    public static final String COUNTRY = "country";

    public static final String ETHNIC = "ethnic";

    public static final String CENSUS = "census";

    public static final String BIRTHDAY = "birthday";

    public static final String CREATE_ADMIN_ID = "create_admin_id";

    public static final String CREATE_MEMO = "create_memo";

    public static final String INTERMEDIARY_COMPANY = "intermediary_company";

    public static final String LEASE_START_TIME = "lease_start_time";

    public static final String LEASE_END_TIME = "lease_end_time";

    public static final String PLAN_OUT_TIME = "plan_out_time";

    public static final String USER_TYPE = "user_type";

    public static final String PARENT_ID = "parent_id";

    public static final String CREATETIME = "createTime";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone=" + phone +
                ", passWord=" + passWord +
                ", name=" + name +
                ", property=" + property +
                ", roomId=" + roomId +
                ", moveInTime=" + moveInTime +
                ", canFree=" + canFree +
                ", cardType=" + cardType +
                ", cardNo=" + cardNo +
                ", sex=" + sex +
                ", address=" + address +
                ", country=" + country +
                ", ethnic=" + ethnic +
                ", census=" + census +
                ", birthday=" + birthday +
                ", createAdminId=" + createAdminId +
                ", createMemo=" + createMemo +
                ", intermediaryCompany=" + intermediaryCompany +
                ", leaseStartTime=" + leaseStartTime +
                ", leaseEndTime=" + leaseEndTime +
                ", planOutTime=" + planOutTime +
                ", userType=" + userType +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                "}";
    }
}
