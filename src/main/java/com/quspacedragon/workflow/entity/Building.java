package com.quspacedragon.workflow.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.Version;
import com.quspacedragon.workflow.entity.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author quspacedragon
 * @since 2018-07-24
 */
public class Building extends BaseEntity<Building> {

    private static final long serialVersionUID = 1L;

	private String name;
	private String code;
    /**
     * 面积 单位平方厘米
     */
	private Integer area;
    /**
     * 地下层数
     */
	@TableField("underground_num")
	private Integer undergroundNum;
    /**
     * 地上层数
     */
	@TableField("floor_num")
	private Integer floorNum;
    /**
     * 有无单元
     */
	@TableField("has_unit")
	private Integer hasUnit;
	private Long createTime;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getUndergroundNum() {
		return undergroundNum;
	}

	public void setUndergroundNum(Integer undergroundNum) {
		this.undergroundNum = undergroundNum;
	}

	public Integer getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(Integer floorNum) {
		this.floorNum = floorNum;
	}

	public Integer getHasUnit() {
		return hasUnit;
	}

	public void setHasUnit(Integer hasUnit) {
		this.hasUnit = hasUnit;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public static final String NAME = "name";

	public static final String CODE = "code";

	public static final String AREA = "area";

	public static final String UNDERGROUND_NUM = "underground_num";

	public static final String FLOOR_NUM = "floor_num";

	public static final String HAS_UNIT = "has_unit";

	public static final String CREATETIME = "createTime";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Building{" +
			"name=" + name +
			", code=" + code +
			", area=" + area +
			", undergroundNum=" + undergroundNum +
			", floorNum=" + floorNum +
			", hasUnit=" + hasUnit +
			", createTime=" + createTime +
			"}";
	}
}
